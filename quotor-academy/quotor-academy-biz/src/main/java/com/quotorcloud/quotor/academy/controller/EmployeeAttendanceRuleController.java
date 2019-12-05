package com.quotorcloud.quotor.academy.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quotorcloud.quotor.academy.api.entity.EmployeeAttendanceRule;
import com.quotorcloud.quotor.academy.service.EmployeeAttendanceRuleService;
import com.quotorcloud.quotor.common.core.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 员工考勤规则信息表 前端控制器
 * </p>
 *
 * @author tianshihao
 * @since 2019-12-03
 */
@RestController
@RequestMapping("/employee/attendance/rule")
public class EmployeeAttendanceRuleController {

    @Autowired
    private EmployeeAttendanceRuleService employeeAttendanceRuleService;

    @PostMapping
    public R saveEmployeeAttendanceRule(@RequestBody EmployeeAttendanceRule employeeAttendanceRule){
        return R.ok(employeeAttendanceRuleService.saveEmployeeAttendance(employeeAttendanceRule));
    }

    @PutMapping
    public R updateEmployeeAttendanceRule(@RequestBody EmployeeAttendanceRule employeeAttendanceRule){
        return R.ok(employeeAttendanceRuleService.updateEmployeeAttendance(employeeAttendanceRule));
    }

    @DeleteMapping("{id}")
    public R removeEmployeeAttendanceRule(@PathVariable String id){
        return R.ok(employeeAttendanceRuleService.removeById(id));
    }

    @GetMapping("list")
    public R listEmployeeAttendanceRule(Page page, EmployeeAttendanceRule attendanceRule){
        return R.ok(employeeAttendanceRuleService.listEmployeeAttendanceRule(page, attendanceRule));
    }




}
