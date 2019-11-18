package com.quotorcloud.quotor.academy.service;

import com.alibaba.fastjson.JSONObject;
import com.quotorcloud.quotor.academy.api.dto.CourseOrderDTO;
import com.quotorcloud.quotor.academy.api.entity.CourseOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 课程订单表（课程预约） 服务类
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-05
 */
public interface CourseOrderService extends IService<CourseOrder> {

    String saveCourseOrder(CourseOrderDTO courseOrderDTO, HttpServletRequest request);

    Map<String, String> saveJSAPICourseOrder(CourseOrderDTO courseOrderDTO, HttpServletRequest request);

    JSONObject listAppointment(CourseOrderDTO courseOrderDTO);

    Boolean removeCourseOrder(String id);
}
