package com.quotorcloud.quotor.academy.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quotorcloud.quotor.academy.api.dto.InviteJobDTO;
import com.quotorcloud.quotor.academy.api.vo.InviteJobVO;
import com.quotorcloud.quotor.academy.service.InviteJobService;
import com.quotorcloud.quotor.common.core.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * <p>
 * 招聘求职信息表 前端控制器
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-04
 */
@RestController
@RequestMapping("/invite/job")
public class InviteJobController {

    @Autowired
    private InviteJobService inviteJobService;

    @PostMapping
    public R saveInviteJob(InviteJobDTO inviteJobDTO){
        return R.ok(inviteJobService.saveInviteJob(inviteJobDTO));
    }

    @PutMapping
    public R updateInviteJob(InviteJobDTO inviteJobDTO){
        return R.ok(inviteJobService.updateInviteJob(inviteJobDTO));
    }

    @GetMapping("list")
    public R listInviteJob(Page<InviteJobVO> page, InviteJobDTO inviteJobDTO) throws ParseException {
        return R.ok(inviteJobService.listInviteJob(page, inviteJobDTO));
    }

    @DeleteMapping("{id}")
    public R removeInviteJob(@PathVariable String id){
        return R.ok(inviteJobService.removeInviteJob(id));
    }

    @GetMapping("list/one")
    public R selectInviteJobById(Integer type, String id) throws ParseException {
        return R.ok(inviteJobService.selectInviteJobById(type, id));
    }

}
