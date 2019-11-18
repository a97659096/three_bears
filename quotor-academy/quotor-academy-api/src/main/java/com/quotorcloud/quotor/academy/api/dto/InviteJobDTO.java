package com.quotorcloud.quotor.academy.api.dto;

import lombok.Data;


@Data
public class InviteJobDTO {

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
    private String dateRange;

    /**
     * 发布时间
     */
    private String ijIssueTime;

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

    private Integer pageNo;

    private Integer pageSize;


}
