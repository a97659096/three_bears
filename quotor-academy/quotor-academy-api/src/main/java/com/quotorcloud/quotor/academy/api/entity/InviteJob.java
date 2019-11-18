package com.quotorcloud.quotor.academy.api.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 招聘下店信息表
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bear_invite_job")
public class InviteJob implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId(value = "ij_id", type = IdType.UUID)
    private String id;

    /**
     * 岗位名称
     */
    private String ijPositionName;

    /**
     * 联系电话
     */
    private String ijPhone;

    /**
     * 福利
     */
    private String ijWelfare;

    /**
     * 工资（日新）
     */
    private String ijSalary;

    /**
     * 地址
     */
    private String ijAddress;

    /**
     * 所属店铺标识
     */
    private String ijShopId;

    /**
     * 所属店铺名称
     */
    private String ijShopName;

    /**
     * 招聘状态，1发布，2下架，3待发布
     */
    private Integer ijStatus;

    /**
     * 岗位详情
     */
    private String ijPositionDetail;

    /**
     * 个人详情
     */
    private String ijOwnerDetail;

    /**
     * 开始日期
     */
    private LocalDate ijStartDate;

    /**
     * 结束日期
     */
    private LocalDate ijEndDate;

    /**
     * 发布时间
     */
    private LocalDateTime ijIssueTime;

    /**
     * 类型，1招聘，2下店
     */
    private Integer ijType;

    /**
     * 下店人员标识
     */
    private String ijXdrId;

    /**
     * 下店人员名称
     */
    private String ijXdrName;

    private Integer ijDelState;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime ijGmtCreate;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime ijGmtModified;


}
