package com.quotorcloud.quotor.academy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quotorcloud.quotor.academy.api.entity.InviteJobPosition;
import com.quotorcloud.quotor.academy.service.InviteJobPositionService;
import com.quotorcloud.quotor.common.core.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 招聘岗位信息表 前端控制器
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-28
 */
@RestController
@RequestMapping("/invite/job/position")
public class InviteJobPositionController {

    @Autowired
    private InviteJobPositionService inviteJobPositionService;

    @PostMapping
    public R savePosition(@RequestBody InviteJobPosition inviteJobPosition){
        return R.ok(inviteJobPositionService.save(inviteJobPosition));
    }

    @GetMapping("list")
    public R listPosition(Page<InviteJobPosition> page, InviteJobPosition inviteJobPosition){
        return R.ok(inviteJobPositionService.page(page, new QueryWrapper<>()));
    }

    @GetMapping("list/box")
    public R listBoxPosition(){
        return R.ok(inviteJobPositionService.list(new QueryWrapper<>()));
    }

    @DeleteMapping("{id}")
    public R removePosition(@PathVariable String id){
        return R.ok(inviteJobPositionService.removeById(id));
    }

    @PutMapping
    public R updatePosition(@RequestBody InviteJobPosition inviteJobPosition){
        return R.ok(inviteJobPositionService.updateById(inviteJobPosition));
    }
}
