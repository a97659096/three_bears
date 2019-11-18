package com.quotorcloud.quotor.academy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.quotorcloud.quotor.academy.api.dto.InviteJobDTO;
import com.quotorcloud.quotor.academy.api.entity.InviteJob;
import com.quotorcloud.quotor.academy.api.vo.InviteJobVO;
import com.quotorcloud.quotor.academy.mapper.InviteJobMapper;
import com.quotorcloud.quotor.academy.service.InviteJobService;
import com.quotorcloud.quotor.common.core.constant.CommonConstants;
import com.quotorcloud.quotor.common.core.util.ComUtil;
import com.quotorcloud.quotor.common.core.util.DateTimeUtil;
import com.quotorcloud.quotor.common.core.util.PageUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 招聘下店信息表 服务实现类
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-04
 */
@Service
public class InviteJobServiceImpl extends ServiceImpl<InviteJobMapper, InviteJob> implements InviteJobService {

    @Autowired
    private InviteJobMapper inviteJobMapper;

    @Override
    public Boolean saveInviteJob(InviteJobDTO inviteJobDTO) {
        InviteJob inviteJob = new InviteJob();
        BeanUtils.copyProperties(inviteJobDTO, inviteJob, "rangeDate", "ijIssueTime");
        //处理特殊字段
        manageSpecialField(inviteJobDTO, inviteJob);
        inviteJobMapper.insert(inviteJob);
        return Boolean.TRUE;
    }

    @Override
    public Boolean updateInviteJob(InviteJobDTO inviteJobDTO) {
        InviteJob inviteJob = new InviteJob();
        BeanUtils.copyProperties(inviteJobDTO,inviteJob, "rangeDate", "ijIssueTime");
        //处理特殊字段
        manageSpecialField(inviteJobDTO, inviteJob);
        inviteJobMapper.updateById(inviteJob);
        return Boolean.TRUE;
    }

    //TODO 店铺和下店人
    private void manageSpecialField(InviteJobDTO inviteJobDTO, InviteJob inviteJob) {
        List<String> stringDate = DateTimeUtil.getStringDate(inviteJobDTO.getDateRange());
        if(!ComUtil.isEmpty(stringDate)){
            inviteJob.setIjStartDate(DateTimeUtil.stringToLocalDate(stringDate.get(0)));
            inviteJob.setIjEndDate(DateTimeUtil.stringToLocalDate(stringDate.get(1)));
        }
        if(inviteJobDTO.getIjStatus().equals(CommonConstants.ISSUE)){
            inviteJob.setIjIssueTime(LocalDateTime.now());
        }
        //招聘，对店铺处理
        if(!ComUtil.isEmpty(inviteJob.getIjType())) {
            if (inviteJob.getIjType().equals(CommonConstants.INVITE)) {

            } else if (inviteJob.getIjType().equals(CommonConstants.INN)) {

            }
        }

    }


    @Override
    public Boolean removeInviteJob(String id) {
        InviteJob inviteJob = new InviteJob();
        inviteJob.setId(id);
        inviteJob.setIjDelState(CommonConstants.STATUS_DEL);
        inviteJobMapper.updateById(inviteJob);
        return Boolean.TRUE;
    }

    @Override
    public JSONObject listInviteJob(InviteJobDTO inviteJobDTO) throws ParseException {
        //封装page
        Page<InviteJob> page = PageUtil.getPage(inviteJobDTO.getPageNo(), inviteJobDTO.getPageSize());
        //查询数据库
        IPage<InviteJob> inviteJobIPage = inviteJobMapper.selectPage(page, new QueryWrapper<InviteJob>()
                .eq("ijDelState", CommonConstants.STATUS_NORMAL)
                .like(!ComUtil.isEmpty(inviteJobDTO.getIjPositionName()), "ijPositionName", inviteJobDTO.getIjPositionName())
                .eq(!ComUtil.isEmpty(inviteJobDTO.getIjStatus()), "ijStatus", inviteJobDTO.getIjStatus())
                .orderByDesc("ij_gmt_create"));
        List<InviteJob> records = inviteJobIPage.getRecords();
        List<InviteJobVO> inviteJobVOS = Lists.newArrayList();
        //转换成VO
        for (InviteJob record : records) {
            InviteJobVO inviteJobVO = new InviteJobVO();
            BeanUtils.copyProperties(record, inviteJobVO, "ijStartDate", "ijEndDate");
            //对时间进行特殊处理转换成时间戳
            String start = DateTimeUtil.dateToStamp(DateTimeUtil.localDateToString(record.getIjStartDate()));
            String end = DateTimeUtil.dateToStamp(DateTimeUtil.localDateToString(record.getIjEndDate()));
            //set进去
            List<Long> dateRange = Lists.newArrayList(Long.valueOf(start), Long.valueOf(end));
            inviteJobVO.setRangeDate(dateRange);
            inviteJobVOS.add(inviteJobVO);
        }
        return PageUtil.getPagePackage("inviteJobs", inviteJobVOS, page);
    }
}
