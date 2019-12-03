package com.quotorcloud.quotor.academy.api.vo;

import com.quotorcloud.quotor.academy.api.entity.MessageTemplate;
import lombok.Data;

import java.util.List;

@Data
public class MessageCategoryTemplateVO {

    private String categoryId;

    /**
     * 类别名称
     */
    private String categoryName;

    /**
     * 所属店铺
     */
    private String categoryShopId;

    /**
     * 类型：1通用，2自定义
     */
    private Integer categoryType;

    private List<MessageTemplate> messageTemplates;

}
