package com.quotorcloud.quotor.academy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.quotorcloud.quotor.academy.api.dto.ExpendDTO;
import com.quotorcloud.quotor.academy.api.entity.Expend;
import com.quotorcloud.quotor.academy.api.entity.ExpendType;
import com.quotorcloud.quotor.academy.api.vo.ExpendVO;
import com.quotorcloud.quotor.academy.mapper.ExpendMapper;
import com.quotorcloud.quotor.academy.service.ExpendService;
import com.quotorcloud.quotor.academy.service.ExpendTypeService;
import com.quotorcloud.quotor.common.core.constant.CommonConstants;
import com.quotorcloud.quotor.common.core.constant.FileConstants;
import com.quotorcloud.quotor.common.core.constant.enums.ExceptionEnum;
import com.quotorcloud.quotor.common.core.exception.MyException;
import com.quotorcloud.quotor.common.core.util.ComUtil;
import com.quotorcloud.quotor.common.core.util.DateTimeUtil;
import com.quotorcloud.quotor.common.core.util.FileUtil;
import com.quotorcloud.quotor.common.core.util.PageUtil;
import com.quotorcloud.quotor.common.security.service.QuotorUser;
import com.quotorcloud.quotor.common.security.util.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 支出信息表 服务实现类
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-01
 */
@Service
public class ExpendServiceImpl extends ServiceImpl<ExpendMapper, Expend> implements ExpendService {

    @Autowired
    private ExpendMapper expendMapper;

    @Autowired
    private ExpendTypeService expendTypeService;

    private static final String AFFIX = "affix/";

    /**
     * 新增支出
     * @param expendDTO
     * @return
     */
    @Override
    public Boolean saveExpend(ExpendDTO expendDTO) {
        Expend expend = new Expend();

        BeanUtils.copyProperties(expendDTO, expend, "eImg", "eEmployeeNameList");

        expend.setEDelState(CommonConstants.STATUS_NORMAL);
        if(!ComUtil.isEmpty(expendDTO.getEImg())){
            String affixName = FileUtil.saveFile(expendDTO.getEImg(),
                    FileConstants.FileType.FILE_EXPEND_IMG_DIR, AFFIX);
            expend.setEImg(affixName);
        }

        if(!ComUtil.isEmpty(expendDTO.getEEmployeeNameList())){
            String empNameList = Joiner.on(CommonConstants.SEPARATOR).join(expendDTO.getEEmployeeNameList());
            expend.setEEmployeeNameList(empNameList);
        }

        //设置店铺信息
        setShopInfo(expend);

        //TODO 后期有时间进行优化 类型不会太多，考虑放入redis中
        //支出类型赋值
        if(!ComUtil.isEmpty(expendDTO.getEExpendTypeId())){
            ExpendType expendType = expendTypeService.getById(expendDTO.getEExpendTypeId());
            expend.setEExpendTypeName(expendType.getName());
        }
        expendMapper.insert(expend);
        return Boolean.TRUE;
    }

    /**
     * 查询支出
     * @param expendDTO
     * @return
     */
    @Override
    public JSONObject listExpend(ExpendDTO expendDTO) {
        //设置日期
        if(!ComUtil.isEmpty(expendDTO.getDateRange())){
            List<String> stringDate = DateTimeUtil.getStringDate(expendDTO.getDateRange());
            if(!ComUtil.isEmpty(stringDate)){
                expendDTO.setStart(stringDate.get(0));
                expendDTO.setEnd(stringDate.get(1));
            }
        }
        //设置店铺信息
        if(ComUtil.isEmpty(expendDTO.getEShopId())){
            QuotorUser user = SecurityUtils.getUser();
            if(ComUtil.isEmpty(user)){
                return null;
            }
            expendDTO.setEShopId(String.valueOf(user.getDeptId()));
        }

        Page<Expend> page = PageUtil.getPage(expendDTO.getPageNo(), expendDTO.getPageSize());
        IPage<Expend> expendIPage = expendMapper.selectExpendPage(page, expendDTO);
        List<Expend> records = expendIPage.getRecords();
        List<ExpendVO> expendVOS = Lists.newArrayList();
        for(Expend expend : records){
            ExpendVO expendVO = new ExpendVO();
            BeanUtils.copyProperties(expend, expendVO, "eImg", "eEmployeeNameList");
            if(!ComUtil.isEmpty(expend.getEEmployeeNameList())){
                expendVO.setEEmployeeNameList(Splitter.on(CommonConstants.SEPARATOR).splitToList(expend.getEEmployeeNameList()));
            }
            FileUtil.addPrefix(expend, ExpendVO.class, expendVO, "eImg");
            expendVOS.add(expendVO);
        }
        return PageUtil.getPagePackage("expends", expendVOS, page);
    }

    /**
     * 删除支出
     * @param id
     * @return
     */
    @Override
    public Boolean removeExpend(String id) {
        Expend expend = new Expend();
        expend.setId(id);
        expend.setEDelState(1);
        expendMapper.updateById(expend);
        return Boolean.TRUE;
    }

    /**
     * 修改支出
     * @param expendDTO
     * @return
     */
    @Override
    public Boolean updateExpend(ExpendDTO expendDTO) {
        if(ComUtil.isEmpty(expendDTO.getId())){
            throw new MyException(ExceptionEnum.NOT_FIND_ID);
        }
        Expend expend = expendMapper.selectById(expendDTO.getId());

        BeanUtils.copyProperties(expendDTO, expend, "eImgString","eImg");
        //设置店铺信息
        setShopInfo(expend);

        FileUtil.removeFileAndField(expend, expendDTO, "eImg", FileConstants.FileType.FILE_EXPEND_IMG_DIR);

        FileUtil.saveFileAndField(expend, expendDTO, "eImg", FileConstants.FileType.FILE_EXPEND_IMG_DIR,null);
        expendMapper.updateById(expend);
        return Boolean.TRUE;
    }

    //设置店铺信息
    private void setShopInfo(Expend expend){
        QuotorUser user = SecurityUtils.getUser();
        if(ComUtil.isEmpty(user)){
            return;
        }
        expend.setEShopId(String.valueOf(user.getDeptId()));
        expend.setEShopName(String.valueOf(user.getDeptName()));

    }

    @Override
    public ExpendVO selectExpendById(String id) {
        Expend expend = expendMapper.selectById(id);
        if(ComUtil.isEmpty(expend)){
            return null;
        }
        ExpendVO expendVO = new ExpendVO();
        BeanUtils.copyProperties(expend, expendVO, "eImg");
        FileUtil.addPrefix(expend, ExpendVO.class, expendVO, "eImg");
        return expendVO;
    }
}
