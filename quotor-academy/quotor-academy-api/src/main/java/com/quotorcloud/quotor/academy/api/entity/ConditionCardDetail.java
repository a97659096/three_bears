package com.quotorcloud.quotor.academy.api.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 卡片详情
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bear_condition_card_detail")
public class ConditionCardDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId(value = "cd_id", type = IdType.UUID)
    private String id;

    /**
     * 卡片标识
     */
    private String cdCardId;

    /**
     * 详情类型：1卡内容，2购卡赠送，3充值赠送
     */
    private Integer cdDetailType;

    /**
     * 内容标识
     */
    private String cdContentId;

    /**
     * 内容名称
     */
    private String cdName;

    /**
     * 内容价格
     */
    private BigDecimal cdPrice;

    /**
     * 内容次数
     */
    private Integer cdCount;

    /**
     * 内容折扣
     */
    private Integer cdDiscount;

    /**
     * 内容金额
     */
    private BigDecimal cdGiveMoney;

    /**
     * 充值满赠金额
     */
    private BigDecimal cdReachMoney;

    /**
     * 创建时间
     */
    @TableField(value = "cd_gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    @TableField(value = "cd_gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;


}
