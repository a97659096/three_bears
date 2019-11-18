package com.quotorcloud.quotor.academy.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.quotorcloud.quotor.academy.api.dto.CourseDTO;
import com.quotorcloud.quotor.academy.api.entity.Course;
import com.quotorcloud.quotor.academy.api.vo.CourseVO;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

/**
 * <p>
 * 课程信息表 服务类
 * </p>
 *
 * @author tianshihao
 * @since 2019-10-28
 */
public interface CourseService extends IService<Course> {

    Boolean saveCourse(CourseDTO courseDTO);

    String saveCourseImg(MultipartFile multipartFile);

    JSONObject listCourse(CourseDTO courseDTO) throws ParseException;

    Boolean removeCourse(String id);

    Boolean updateCourse(CourseDTO courseDTO);

    CourseVO selectCourseById(String id) throws ParseException;

    //查询课程列表
    List<JSONObject> listCourseListBox(Integer type);

}
