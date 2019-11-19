package com.quotorcloud.quotor.academy.api.vo;

import com.alibaba.fastjson.JSONObject;
import com.quotorcloud.quotor.academy.api.entity.ConditionSetMealDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ConditionProVO {

    private String pId;

    /**
     * 项目名称
     */
    private String pName;

    /**
     * 适用门店标识
     */
    private String pShopId;

    /**
     * 适用门店名称
     */
    private String pShopName;

    /**
     * 统一售价
     */
    private BigDecimal pUnifiedPrice;

    /**
     * 项目时长
     */
    private Integer pTimeLength;

    /**
     * 项目类别标识
     */
    private String pCategoryId;

    /**
     * 项目类别名称
     */
    private String pCategoryName;

    /**
     * 手机端是否可预约，1:可预约，0:不可预约
     */
    private Integer cSubscribe;

    /**
     * 项目编号
     */
    private String pNumber;

    /**
     * 项目体验价
     */
    private BigDecimal pExperiencePrice;

    /**
     * 项目描述
     */
    private String pDescribe;

    /**
     * 项目状态，1可用，0不可用
     */
    private Integer pState;



    /**
     * 项目配图
     */
    private String img;


    private List<JSONObject> pImg;

    /**
     * 删除状态，0正常，1删除
     */
    private Integer pDelState;

    private List<ConditionSetMealDetail> mealDetails;

}