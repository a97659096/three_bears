package com.quotorcloud.quotor.academy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quotorcloud.quotor.academy.api.entity.MessageCategory;
import com.quotorcloud.quotor.academy.api.entity.MessageTemplate;
import com.quotorcloud.quotor.academy.service.MessageCategoryService;
import com.quotorcloud.quotor.academy.service.MessageTemplateService;
import com.quotorcloud.quotor.common.core.exception.MyException;
import com.quotorcloud.quotor.common.core.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 短信模板类别 前端控制器
 * </p>
 *
 * @author tianshihao
 * @since 2019-12-02
 */
@RestController
@RequestMapping("/message/category")
public class MessageCategoryController {

    @Autowired
    private MessageCategoryService messageCategoryService;

    @Autowired
    private MessageTemplateService messageTemplateService;

    /**
     * 新增短信模板类别
     * @param messageCategory
     * @return
     */
    @PostMapping
    public R saveMessageCategory(@RequestBody MessageCategory messageCategory){
        return R.ok(messageCategoryService.save(messageCategory));
    }

    /**
     * 删除短信模板类别
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public R updateMessageCategory(@PathVariable String id){
        int count = messageTemplateService.count(new QueryWrapper<MessageTemplate>().eq("template_category_id", id));
        if(count > 0){
            throw new MyException("此模板分类下还存在模板内容，删除失败");
        }
        return R.ok(messageCategoryService.removeById(id));
    }

    /**
     * 修改短信模板类别
     * @param messageCategory
     * @return
     */
    @PutMapping
    public R updateMessageCategory(@RequestBody MessageCategory messageCategory){
        return R.ok(messageCategoryService.updateById(messageCategory));
    }

    /**
     * 下拉框查询模板类别
     * @param shopId
     * @return
     */
    @GetMapping("listbox/{shopId}")
    public R listBoxMessageCategory(@PathVariable String shopId){
        return R.ok(messageCategoryService.listBoxMessageCategory(shopId));
    }

}
