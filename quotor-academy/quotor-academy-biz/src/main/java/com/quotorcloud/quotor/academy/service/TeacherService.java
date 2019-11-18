package com.quotorcloud.quotor.academy.service;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.quotorcloud.quotor.academy.api.entity.Teacher;
import com.quotorcloud.quotor.academy.api.dto.TeacherDTO;
import com.quotorcloud.quotor.academy.api.vo.TeacherVO;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 讲师信息表 服务类
 * </p>
 *
 * @author tianshihao
 * @since 2019-10-24
 */
public interface TeacherService extends IService<Teacher> {

    Boolean saveTeacher(TeacherDTO teacherDTO);

    JSONObject listTeacher(TeacherDTO teacherDTO);

    Boolean updateTeacher(TeacherDTO teacherDTO);

    Boolean deleteTeacher(String id);

    List<JSONObject> listBoxTeacher();

    TeacherVO selectTeacherById(String id);

    Set<String> selectTeacherNation();

}
