package com.quotorcloud.quotor.academy.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quotorcloud.quotor.academy.api.dto.MemberDTO;
import com.quotorcloud.quotor.academy.service.MemberService;
import com.quotorcloud.quotor.common.core.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 客户信息表 前端控制器
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-19
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public R saveMember(MemberDTO memberDTO){
        return R.ok(memberService.saveMember(memberDTO));
    }

    @PutMapping
    public R updateMember(MemberDTO memberDTO){
        return R.ok(memberService.updateMember(memberDTO));
    }

    @DeleteMapping("{id}")
    public R deleteMember(@PathVariable String id){
        return R.ok(memberService.removeMember(id));
    }

    @GetMapping("list")
    public R listMember(Page page, MemberDTO memberDTO){
        return R.ok(memberService.listMember(page, memberDTO));
    }

    @GetMapping("list/{id}")
    public R selectMemberById(@PathVariable String id){
        return R.ok(memberService.getMemberById(id));
    }

}
