package com.quotorcloud.quotor.academy.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quotorcloud.quotor.academy.api.entity.EmployeeAttendance;
import com.quotorcloud.quotor.academy.service.EmployeeAttendanceService;
import com.quotorcloud.quotor.common.core.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 员工考勤信息表 前端控制器
 * </p>
 *
 * @author tianshihao
 * @since 2019-12-03
 */
@RestController
@RequestMapping("/employee/attendance")
public class EmployeeAttendanceController {

    @Autowired
    private EmployeeAttendanceService employeeAttendanceService;

    /**
     * 打卡
     * @param userId 用户标识
     * @return
     */
    @PostMapping
    public R checkWorkAttendance(@RequestBody EmployeeAttendance employeeAttendance){
        return R.ok(employeeAttendanceService.checkWorkAttendance(employeeAttendance));
    }

    /**
     * 将考勤状态改为正常
     * @param id
     * @return
     */
    @PutMapping("{id}")
    public R updateWorkAttendanceStateNormal(@PathVariable String id){
        return R.ok(employeeAttendanceService.updateWorkAttendanceStateNormal(id));
    }

    /**
     * 将当天的考勤状况查询出来
     * @param page
     * @param attendanceState 考勤状态
     * @param 店铺标识
     * @param date 日期
     * @return
     */
    @GetMapping("list")
    public R listCheckWorkAttendance(Page page, EmployeeAttendance employeeAttendance){
        return R.ok(employeeAttendanceService.listCheckWorkAttendance(page, employeeAttendance));
    }

    /**
     * 查询指定月份的考勤状态
     * @param dateMonth  2019-11 指定月份
     * @return
     */
    @GetMapping("list/month")
    public R listCheckWorkAttendanceByMonth(EmployeeAttendance employeeAttendance){
        return R.ok(employeeAttendanceService.listCheckWorkAttendanceByMonth(employeeAttendance));
    }

    /**
     * 汇总查询
     * @param page
     * @param dateRange 1111,2222 开始-结束时间
     * @param shopId  店铺标识
     * @return
     */
    @GetMapping("list/statistic")
    public R statisticsAttendance(Page page, EmployeeAttendance employeeAttendance){
        return R.ok(employeeAttendanceService.statisticsAttendance(page, employeeAttendance));
    }


}
