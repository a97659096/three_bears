package com.quotorcloud.quotor.academy.api.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 卡片信息
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bear_condition_card")
public class ConditionCard implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId(value = "c_id", type = IdType.UUID)
    private String id;

    /**
     * 卡模板类型：1储值卡，2折扣卡，3时段卡，4总次卡，5分次卡
     */
    private Integer cTemplateType;

    /**
     * 卡片名称
     */
    private String cName;

    /**
     * 适用门店标识
     */
    private String cShopId;

    /**
     * 适用门店名称
     */
    private String cShopName;

    /**
     * 卡售价
     */
    private BigDecimal cPrice;

    /**
     * 卡面额
     */
    private BigDecimal cDenomination;

    /**
     * 开始日期类型：1购卡时间，2首次使用时间，3指定日期
     */
    private Integer cStartDateType;

    /**
     * 开始日期
     */
    private LocalDate cStartDate;

    /**
     * 结束日期类型：1不限时长，2固定时长，3指定日期
     */
    private Integer cEndDateType;

    /**
     * 结束日期
     */
    private LocalDate cEndDate;

    /**
     * 卡小类标识
     */
    private String cCategoryId;

    /**
     * 卡小类名称
     */
    private String cCategoryName;

    /**
     * 售卡须知
     */
    private String cSellNotice;

    /**
     * 卡状态：1启用，2停用
     */
    private Integer cState;

    /**
     * 卡片的使用方式，1售卖，2赠送，3都可以
     */
    private Integer cUseWay;

    /**
     * 卡属性：1会员卡，2营销卡
     */
    private Integer cProperty;

    /**
     * 开单限购：1不限次数，2限购
     */
    private Integer cLimitBuy;

    /**
     * 开单限购次数
     */
    private Integer cLimitBuyCount;

    /**
     * 是否删除，0正常，1删除
     */
    private Integer cDelState;

    /**
     * 创建时间
     */
    @TableField(value = "c_gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    @TableField(value = "c_gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

}
