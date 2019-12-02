package com.quotorcloud.quotor.academy.api.dto;

import lombok.Data;


@Data
public class InviteJobDTO {

    private String id;

    /**
     * 岗位名称
     */
    private String positionName;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 福利
     */
    private String welfare;

    /**
     * 工资（日新）
     */
    private String salary;

    /**
     * 地址
     */
    private String address;

    /**
     * 所属店铺标识
     */
    private String shopId;

    /**
     * 所属店铺名称
     */
    private String shopName;

    /**
     * 招聘状态，1发布，2下架，3待发布
     */
    private Integer status;

    /**
     * 岗位详情
     */
    private String positionDetail;

    /**
     * 个人详情
     */
    private String ownerDetail;

    /**
     * 开始日期
     */
    private String dateRange;

    /**
     * 发布时间
     */
    private String issueTime;

    /**
     * 类型，1招聘，2下店
     */
    private Integer type;

    /**
     * 下店人员标识
     */
    private String xdrId;

    /**
     * 下店人员名称
     */
    private String xdrName;

}
