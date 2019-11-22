package com.quotorcloud.quotor.academy.mapper;

import com.quotorcloud.quotor.academy.api.dto.AppointEmployeeDTO;
import com.quotorcloud.quotor.academy.api.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 员工信息表 Mapper 接口
 * </p>
 *
 * @author tianshihao
 * @since 2019-10-29
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    List<Employee> selectEmployeePositionAndHeadTitle();

    List<AppointEmployeeDTO> selectAppointEmployee(@Param("shopId") String shopId);
}
