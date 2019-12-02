package com.quotorcloud.quotor.academy.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quotorcloud.quotor.academy.api.entity.Appoint;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quotorcloud.quotor.academy.api.vo.AppointVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 预约信息表 Mapper 接口
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-21
 */
public interface AppointMapper extends BaseMapper<Appoint> {

    IPage<AppointVO> selectAppointPage(Page<AppointVO> page,
                                       @Param("appoint") AppointVO appointVO);

    List<AppointVO> selectAppointForm(@Param("appoint") AppointVO appointVO);
}
