package com.quotorcloud.quotor.academy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quotorcloud.quotor.academy.api.dto.ConditionCardDTO;
import com.quotorcloud.quotor.academy.api.dto.ConditionCardDetailDTO;
import com.quotorcloud.quotor.academy.api.entity.ConditionCard;
import com.quotorcloud.quotor.academy.api.entity.ConditionCardDetail;
import com.quotorcloud.quotor.academy.api.vo.ConditionCardVO;
import com.quotorcloud.quotor.academy.mapper.ConditionCardMapper;
import com.quotorcloud.quotor.academy.service.ConditionCardDetailService;
import com.quotorcloud.quotor.academy.service.ConditionCardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quotorcloud.quotor.academy.service.ConditionCategoryService;
import com.quotorcloud.quotor.common.core.constant.CommonConstants;
import com.quotorcloud.quotor.common.core.exception.MyException;
import com.quotorcloud.quotor.common.core.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 卡片信息 服务实现类
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-11
 */
@Service
@Slf4j
public class ConditionCardServiceImpl extends ServiceImpl<ConditionCardMapper, ConditionCard> implements ConditionCardService {

    @Autowired
    private ConditionCardMapper conditionCardMapper;

    @Autowired
    private ConditionCardDetailService conditionCardDetailService;

    @Autowired
    private ConditionCategoryService conditionCategoryService;

    /**
     * 新增
     * @param conditionCardDTO
     * @return
     */
    @Override
    @Transactional
    public Boolean saveConditionCard(ConditionCardDTO conditionCardDTO) {
        ConditionCard conditionCard = getConditionCard(conditionCardDTO);
        conditionCardMapper.insert(conditionCard);
        List<ConditionCardDetail> cardContent = getConditionCardDetails(conditionCardDTO, conditionCard);
        //插入操作
        conditionCardDetailService.saveBatch(cardContent);
        return Boolean.TRUE;
    }

    @Override
    public JSONObject selectConditionCard(ConditionCardDTO conditionCardDTO) {
        Page<ConditionCardDTO> page = PageUtil.getPage(conditionCardDTO.getPageNo(), conditionCardDTO.getPageSize());
        //得到id集合，方便查询子级下的内容
        if(!ComUtil.isEmpty(conditionCardDTO.getCCategoryId())) {
            conditionCardDTO.setCategoryIds(conditionCategoryService.findCategoryIds(conditionCardDTO.getCCategoryId()));
        }
        //分页查询
        List<ConditionCardVO> conditionCardVOS = conditionCardMapper.selectCardPage(page, conditionCardDTO);
        for (ConditionCardVO conditionCard : conditionCardVOS){
            mapCardVO(conditionCard);
        }
        return PageUtil.getPagePackage("cards", conditionCardVOS, page);
    }

    @Override
    public Boolean updateConditionCard(ConditionCardDTO conditionCardDTO) {
        //把有变化的类型放到集合里批量删除
        List<Integer> types = new ArrayList<>();
        if(!ComUtil.isEmpty(conditionCardDTO.getCardContent())){
            types.add(CommonConstants.CONDITION_CARD_CONTENT);
        }
        if(!ComUtil.isEmpty(conditionCardDTO.getBuyCardGive())){
            types.add(CommonConstants.CONDITION_BUY_CARD_GIVE);
        }
        if(!ComUtil.isEmpty(conditionCardDTO.getTopUpGive())){
            types.add(CommonConstants.CONDITION_TOPUP_GIVE);
        }
        conditionCardDetailService.remove(new QueryWrapper<ConditionCardDetail>()
                .eq("cd_card_id", conditionCardDTO.getId())
                .in("cd_detail_type", types));
        //修改卡片信息
        ConditionCard conditionCard = getConditionCard(conditionCardDTO);
        conditionCardMapper.updateById(conditionCard);
        //修改卡片详情
        List<ConditionCardDetail> conditionCardDetails = getConditionCardDetails(conditionCardDTO, conditionCard);
        conditionCardDetailService.saveBatch(conditionCardDetails);
        return Boolean.TRUE;
    }

    @Override
    public Boolean removeConditionCard(String id) {
        ConditionCard conditionCard = new ConditionCard();
        conditionCard.setId(id);
        conditionCard.setCDelState(CommonConstants.STATUS_DEL);
        conditionCardMapper.updateById(conditionCard);
        return Boolean.TRUE;
    }

    private ConditionCard getConditionCard(ConditionCardDTO conditionCardDTO) {
        //首先保存除卡内容，购卡赠送，充值赠送外的内容
        ConditionCard conditionCard = new ConditionCard();
        //拷贝属性
        BeanUtils.copyProperties(conditionCardDTO, conditionCard);
        //判断结束日期类型，如果是固定时长需要特殊处理
        Integer unit = conditionCardDTO.getUnit();
        Integer duration = conditionCardDTO.getDuration();
        //根据长度和单位设置到期时间
        switch (unit){
            //1为年
            case 1:
                conditionCard.setCEndDate(LocalDate.now().plusYears(duration));
                break;
            //2为月
            case 2:
                conditionCard.setCEndDate(LocalDate.now().plusMonths(duration));
                break;
            //3为日
            case 3:
                conditionCard.setCEndDate(LocalDate.now().plusDays(duration));
                break;
        }
        return conditionCard;
    }

    private List<ConditionCardDetail> getConditionCardDetails(ConditionCardDTO conditionCardDTO, ConditionCard conditionCard) {
        //处理卡内容、购卡赠送
        List<ConditionCardDetail> cardContent = conditionCardDTO.getCardContent();
        //设置type为1,1为卡内容
        cardContent.forEach(content -> {
            content.setCdDetailType(CommonConstants.CONDITION_CARD_CONTENT);
            content.setCdCardId(conditionCard.getId());
        });
        //如果是总次卡，name所有的内容次数都应该是一样的，如果不一样进行回滚
        if(conditionCardDTO.getCTemplateType().equals(CommonConstants.TOTAL_DEGREE_CARD)){
            Set<Integer> set = cardContent.stream().map(ConditionCardDetail::getCdCount).collect(Collectors.toSet());
            if(set.size() > 1){
                log.error("总次卡，存在次数不一致的问题,进行事务回滚。。。");
                throw new RuntimeException();
            }
        }
        List<ConditionCardDetail> buyCardGive = conditionCardDTO.getBuyCardGive();
        //设置type为2，2为购卡赠送
        buyCardGive.forEach(cardgive -> {
            cardgive.setCdDetailType(CommonConstants.CONDITION_BUY_CARD_GIVE);
            cardgive.setCdCardId(conditionCard.getId());
        });
        //把两个集合add到一个集合里，通过type区分开来
        cardContent.addAll(buyCardGive);
        //最后处理充值赠送内容
        List<ConditionCardDetail> topUpGive = conditionCardDTO.getTopUpGive();
        topUpGive.forEach(give -> {
            give.setCdDetailType(CommonConstants.CONDITION_TOPUP_GIVE);
            give.setCdCardId(conditionCard.getId());
        });
        cardContent.addAll(topUpGive);
        return cardContent;
    }

    private void mapCardVO(ConditionCardVO conditionCard) {
        List<ConditionCardDetail> conditionCardDetails = conditionCard.getConditionCardDetails();
        //根据详情类型分组
        Map<Integer, List<ConditionCardDetail>> collect = conditionCardDetails.stream().collect(Collectors.groupingBy(ConditionCardDetail::getCdDetailType));
        //卡片内容
        List<ConditionCardDetail> cardContent = collect.get(CommonConstants.CONDITION_CARD_CONTENT);
        if(!ComUtil.isEmpty(cardContent)){
            conditionCard.setCardContent(cardContent);
        }
        //购卡赠送
        List<ConditionCardDetail> buyCardGive = collect.get(CommonConstants.CONDITION_BUY_CARD_GIVE);
        if(!ComUtil.isEmpty(buyCardGive)){
            conditionCard.setBuyCardGive(buyCardGive);
        }
        //充值赠送
        List<ConditionCardDetail> topUpGive = collect.get(CommonConstants.CONDITION_TOPUP_GIVE);
        if(!ComUtil.isEmpty(topUpGive)){
            Map<BigDecimal, List<ConditionCardDetail>> decimalListMap = topUpGive.stream().collect(Collectors.groupingBy(ConditionCardDetail::getCdReachMoney));
            List<ConditionCardDetailDTO> conditionCardDetailDTOS = new LinkedList<>();
            for (BigDecimal decimal : decimalListMap.keySet()){
                ConditionCardDetailDTO conditionCardDetailDTO = new ConditionCardDetailDTO();
                conditionCardDetailDTO.setKey(decimal);
                conditionCardDetailDTO.setValue(decimalListMap.get(decimal));
                conditionCardDetailDTOS.add(conditionCardDetailDTO);
            }
            //按充值金额进行排序
            Collections.sort(conditionCardDetailDTOS);
            conditionCard.setTopUpGive(conditionCardDetailDTOS);
        }
    }
}
