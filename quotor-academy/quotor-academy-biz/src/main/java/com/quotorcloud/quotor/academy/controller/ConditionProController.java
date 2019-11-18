package com.quotorcloud.quotor.academy.controller;


import com.alibaba.fastjson.JSON;
import com.quotorcloud.quotor.academy.api.dto.ConditionProDTO;
import com.quotorcloud.quotor.academy.api.entity.ConditionCategory;
import com.quotorcloud.quotor.academy.service.ConditionProService;
import com.quotorcloud.quotor.common.core.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 项目信息表 前端控制器
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-08
 */
@RestController
@RequestMapping("/pro")
public class ConditionProController {

    @Autowired
    private ConditionProService conditionProService;

    @PostMapping("save")
    public R saveConditionPro(ConditionProDTO conditionProDTO){
        return R.ok(conditionProService.saveConditionPro(conditionProDTO));
    }

    @GetMapping("list")
    public R listConditionPro(ConditionProDTO conditionProDTO){
        return R.ok(conditionProService.listConditionPro(conditionProDTO));
    }

    @PutMapping("update")
    public R updateConditionPro(ConditionProDTO conditionProDTO){
        return R.ok(conditionProService.updateConditionPro(conditionProDTO));
    }

    @DeleteMapping("/{id}")
    public R removeConditionProById(@PathVariable String id){
        return R.ok(conditionProService.removeConditionPro(id));
    }

    @GetMapping("list/{id}")
    public R selectConditionProById(@PathVariable String id){
        return R.ok(conditionProService.selectProById(id));
    }

    @GetMapping("list/category/tree")
    public R treeConditionPro(ConditionCategory conditionCategory){
        return R.ok(JSON.parse(conditionProService.treeProByCategory(conditionCategory)));
    }
}
