package com.quotorcloud.quotor.academy.service;

import com.quotorcloud.quotor.academy.api.entity.EmployeeSchedu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.quotorcloud.quotor.academy.api.vo.EmployeeScheduVO;

import java.util.List;

/**
 * <p>
 * 员工排班信息表 服务类
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-19
 */
public interface EmployeeScheduService extends IService<EmployeeSchedu> {

    Boolean saveEmployeeSchedu(EmployeeSchedu employeeSchedu);

    Boolean removeEmployeeSchedu(String id);

    Boolean updateEmployeeSchedu(EmployeeSchedu employeeSchedu);

    List<EmployeeScheduVO> selectEmployeeSchedu(String shopId);
}
