package com.quotorcloud.quotor.academy.controller;


import com.quotorcloud.quotor.academy.api.entity.EmployeeSchedu;
import com.quotorcloud.quotor.academy.service.EmployeeScheduService;
import com.quotorcloud.quotor.common.core.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 员工排班信息表 前端控制器
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-19
 */
@RestController
@RequestMapping("/employee/schedu")
public class EmployeeScheduController {

    @Autowired
    private EmployeeScheduService employeeScheduService;

    @PostMapping
    public R saveEmployeeSchedu(@RequestBody EmployeeSchedu employeeSchedu){
        return R.ok(employeeScheduService.saveEmployeeSchedu(employeeSchedu));
    }

    @DeleteMapping("{id}")
    public R removeEmployeeSchedu(@PathVariable String id){
        return R.ok(employeeScheduService.removeEmployeeSchedu(id));
    }

    @PutMapping
    public R updateEmployeeSchedu(@RequestBody EmployeeSchedu employeeSchedu){
        return R.ok(employeeScheduService.updateEmployeeSchedu(employeeSchedu));
    }
}
