package com.quotorcloud.quotor.academy.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quotorcloud.quotor.academy.api.dto.AppointDTO;
import com.quotorcloud.quotor.academy.api.vo.AppointVO;
import com.quotorcloud.quotor.academy.service.AppointService;
import com.quotorcloud.quotor.common.core.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 预约信息表 前端控制器
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-21
 */
@RestController
@RequestMapping("/appoint")
public class AppointController {

    @Autowired
    private AppointService appointService;

    @PostMapping
    public R saveAppoint(@RequestBody AppointDTO appointDTO){
        return R.ok(appointService.saveAppoint(appointDTO));
    }

    @PutMapping
    public R updateAppoint(@RequestBody AppointDTO appointDTO){
        return R.ok(appointService.updateAppoint(appointDTO));
    }

    @DeleteMapping("{id}")
    public R cancelAppoint(@PathVariable String id){
        return R.ok(appointService.cancelAppoint(id));
    }

    @GetMapping("form")
    public R formAppoint(AppointVO appointVO){
        return R.ok(appointService.formAppoint(appointVO));
    }

    @GetMapping("list")
    public R listAppoint(Page<AppointVO> page, AppointVO appointVO){
        return R.ok(appointService.listAppoint(page, appointVO));
    }

    @GetMapping("getone")
    public R getOneAppoint(String id, String appointId){
        return R.ok(appointService.listAppointById(id, appointId));
    }

}
