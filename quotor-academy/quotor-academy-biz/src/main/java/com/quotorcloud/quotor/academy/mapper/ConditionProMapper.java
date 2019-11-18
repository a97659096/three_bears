package com.quotorcloud.quotor.academy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quotorcloud.quotor.academy.api.dto.ConditionProDTO;
import com.quotorcloud.quotor.academy.api.entity.ConditionPro;
import com.quotorcloud.quotor.academy.api.vo.ConditionProVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 项目信息表 Mapper 接口
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-08
 */
public interface ConditionProMapper extends BaseMapper<ConditionPro> {

    IPage<ConditionProVO> selectProPage(Page<ConditionPro> page, @Param("pro") ConditionProDTO conditionProDTO);
}
