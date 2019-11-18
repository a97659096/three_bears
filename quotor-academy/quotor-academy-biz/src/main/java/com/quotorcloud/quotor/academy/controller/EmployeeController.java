package com.quotorcloud.quotor.academy.controller;


import com.quotorcloud.quotor.academy.api.dto.EmployeeDTO;
import com.quotorcloud.quotor.academy.service.EmployeeService;
import com.quotorcloud.quotor.common.core.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 员工信息表 前端控制器
 * </p>
 *
 * @author tianshihao
 * @since 2019-10-29
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 查询职位和头衔下拉列表
     * @return
     */
    @GetMapping("po_head")
    public R selectPositionAndHeadTitle(){
        return R.ok(employeeService.selectEmployeePositionAndHeadTitle());
    }

    /**
     * 按id查询员工信息
     * @param id
     * @return
     */
    @GetMapping("list/{id}")
    public R selectEmployeeById(@PathVariable String id){
        return R.ok(employeeService.selectEmployeeById(id));
    }

    /**
     * 新增员工
     * @param employeeDTO
     * @return
     */
    @PostMapping("save")
    public R saveEmployee(EmployeeDTO employeeDTO){
        return R.ok(employeeService.saveEmployee(employeeDTO));
    }

    /**
     * 查询员工列表
     * @param employeeDTO
     * @return
     */
    @GetMapping("list")
    public R listEmployee(EmployeeDTO employeeDTO){
        return R.ok(employeeService.listEmployee(employeeDTO));
    }

    /**
     * 删除员工信息
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R deleteEmployee(@PathVariable String id){
        return R.ok(employeeService.removeEmployee(id));
    }

    /**
     * 修改员工信息
     * @param employeeDTO
     * @return
     */
    @PutMapping("/update")
    public R updateEmployee(EmployeeDTO employeeDTO){
        return R.ok(employeeService.updateEmployee(employeeDTO));
    }

    @GetMapping("list/box")
    public R listBoxEmployee(String shopId){

        return R.ok(employeeService.selectEmployeeListBox(shopId));
    }
}
