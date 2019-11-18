package com.quotorcloud.quotor.academy.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quotorcloud.quotor.academy.api.dto.CourseOrderDTO;
import com.quotorcloud.quotor.academy.api.entity.CourseOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quotorcloud.quotor.academy.api.vo.CourseAppointmentVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 课程订单表（课程预约） Mapper 接口
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-05
 */
public interface CourseOrderMapper extends BaseMapper<CourseOrder> {

    IPage<CourseAppointmentVO> selectCourseOrderPage(Page<CourseOrder> page, @Param("co")CourseOrderDTO courseOrderDTO);

}
