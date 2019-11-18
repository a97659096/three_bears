package com.quotorcloud.quotor.academy.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.quotorcloud.quotor.academy.api.dto.EmployeeDTO;
import com.quotorcloud.quotor.academy.api.entity.Employee;
import com.quotorcloud.quotor.academy.api.vo.EmployeeVO;

import java.util.List;

/**
 * <p>
 * 员工信息表 服务类
 * </p>
 *
 * @author tianshihao
 * @since 2019-10-29
 */
public interface EmployeeService extends IService<Employee> {

    Boolean saveEmployee(EmployeeDTO employeeDTO);

    JSONObject listEmployee(EmployeeDTO employeeDTO);

    Boolean removeEmployee(String id);

    Boolean updateEmployee(EmployeeDTO employeeDTO);

    EmployeeVO selectEmployeeById(String id);

    JSONObject selectEmployeePositionAndHeadTitle();

    List<JSONObject> selectEmployeeListBox(String shopId);

}
