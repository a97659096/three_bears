package com.quotorcloud.quotor.academy.service.impl;

import com.quotorcloud.quotor.academy.api.entity.EmployeeSchedu;
import com.quotorcloud.quotor.academy.mapper.EmployeeScheduMapper;
import com.quotorcloud.quotor.academy.service.EmployeeScheduService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工排班信息表 服务实现类
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-19
 */
@Service
public class EmployeeScheduServiceImpl extends ServiceImpl<EmployeeScheduMapper, EmployeeSchedu> implements EmployeeScheduService {

    @Autowired
    private EmployeeScheduMapper employeeScheduMapper;

    @Override
    public Boolean saveEmployeeSchedu(EmployeeSchedu employeeSchedu) {
        employeeScheduMapper.insert(employeeSchedu);
        return Boolean.TRUE;
    }

    @Override
    public Boolean removeEmployeeSchedu(String id) {
        employeeScheduMapper.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Boolean updateEmployeeSchedu(EmployeeSchedu employeeSchedu) {
        employeeScheduMapper.updateById(employeeSchedu);
        return Boolean.TRUE;
    }
}
