package com.quotorcloud.quotor.academy.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quotorcloud.quotor.academy.api.entity.EmployeeScheduClass;
import com.baomidou.mybatisplus.extension.service.IService;
import com.quotorcloud.quotor.academy.api.vo.EmployeeScheduClassVO;

/**
 * <p>
 * 员工排班班次信息表 服务类
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-19
 */
public interface EmployeeScheduClassService extends IService<EmployeeScheduClass> {

    Boolean saveEmployeeScheduClass(EmployeeScheduClass employeeScheduClass);

    Boolean removeEmployeeScheduClass(String id);

    JSONObject listClass(EmployeeScheduClass employeeScheduClass);

    Boolean updateEmployeeScheduClass(EmployeeScheduClass employeeScheduClass);

    EmployeeScheduClassVO getClassById(String id);
}
