package com.quotorcloud.quotor.academy.mapper;

import com.quotorcloud.quotor.academy.api.entity.EmployeeSchedu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quotorcloud.quotor.academy.api.vo.EmployeeScheduVO;

import java.util.List;

/**
 * <p>
 * 员工排班信息表 Mapper 接口
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-19
 */
public interface EmployeeScheduMapper extends BaseMapper<EmployeeSchedu> {

    List<EmployeeScheduVO> selectEmployeeScheduVO(String shopId);

}
