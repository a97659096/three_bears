package com.quotorcloud.quotor.academy.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quotorcloud.quotor.academy.api.dto.ExpendDTO;
import com.quotorcloud.quotor.academy.api.entity.Expend;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 支出信息表 Mapper 接口
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-01
 */
public interface ExpendMapper extends BaseMapper<Expend> {

    IPage<Expend> selectExpendPage(Page page, @Param("exp") ExpendDTO expendDTO);
}
