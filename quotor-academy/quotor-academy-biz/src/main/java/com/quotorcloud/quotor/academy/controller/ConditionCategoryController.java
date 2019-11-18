package com.quotorcloud.quotor.academy.controller;


import com.quotorcloud.quotor.academy.api.entity.ConditionCategory;
import com.quotorcloud.quotor.academy.service.ConditionCategoryService;
import com.quotorcloud.quotor.common.core.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 品项类别信息维护 前端控制器
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-07
 */
@RestController
@RequestMapping("/condition/category")
public class ConditionCategoryController {

    @Autowired
    private ConditionCategoryService conditionCategoryService;

    @PostMapping
    public R saveConditionCategory(@RequestBody ConditionCategory conditionCategory){
        return R.ok(conditionCategoryService.saveConditionCategory(conditionCategory));
    }

    @GetMapping("tree")
    public R listTreeCategoryByShopId(ConditionCategory conditionCategory){
        return R.ok(conditionCategoryService.getCategoryTree(conditionCategory));
    }

    @PutMapping
    public R updateCategoryById(@RequestBody ConditionCategory conditionCategory){
        return R.ok(conditionCategoryService.updateCategory(conditionCategory));
    }

    @DeleteMapping("{id}")
    public R deleteCategoryById(@PathVariable String id){
        return R.ok(conditionCategoryService.removeCategory(id));
    }


    public static void main(String[] args) {
        String ipAddressByRegex = cutIp(
                "http://192.168.3.24:9001/employee/works/20191118/15740467885073061.jpg");
        System.out.println(ipAddressByRegex);
    }

    public static String cutIp(String ip){
        int i = ip.lastIndexOf("http://192.168.3.24:9001/");
        String substring = ip.substring(i);
        return substring;
    }

    public static List<String> getIps(String ipString){
        String regEx="((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
        List<String> ips = new ArrayList<String>();
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(ipString);
        while (m.find()) {
            String result = m.group();
            ips.add(result);
        }
        return ips;
    }

    public static List<String> getIPAddressByRegex(String str) {
        List<String> ips = new ArrayList<String>();
        String regex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
        if (str.matches(regex)) {
            String[] arr = str.split("\\.");
            for (int i = 0; i < 4; i++) {
                ips.add(arr[i]);
            }
        }
        return ips;
    }
}
