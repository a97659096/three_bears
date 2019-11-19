package com.quotorcloud.quotor.academy.api.vo;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MemberVO {

    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别，男1，女2
     */
    private Integer sex;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 渠道来源：1微博，2微信，3团购，4上门，5客户介绍，6员工推荐，7其他
     */
    private Integer channel;

    /**
     * 所属门店标识
     */
    private String shopId;

    /**
     * 所属门店名称
     */
    private String shopName;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 会员编号
     */
    private String number;

    /**
     * 年龄段：1：20岁以下，2：20~30岁，3：30~40岁，4：40~50岁，5：50~60岁，6:60岁以上
     */
    private Integer ageRange;

    /**
     * 跟踪员工
     */
    private String traceEmployeeDataBase;

    private String traceEmployee;

    /**
     * 入会日期
     */
    private LocalDate joinDate;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 本店序号
     */
    private String shopSerialNumber;

    /**
     * 推荐人标识
     */
    private String referrerId;

    /**
     * 推荐人姓名
     */
    private String referreName;

    /**
     * 身份证号
     */
    private String identityCard;

    /**
     * 客户邮箱
     */
    private String email;

    /**
     * 客户QQ
     */
    private String qq;

    /**
     * 客户微信号
     */
    private String wechat;

    /**
     * 客户职业
     */
    private String job;

    /**
     * 客户地址
     */
    private String address;

    /**
     * 客户备注
     */
    private String remark;

    /**
     * 身高
     */
    private String height;

    /**
     * 体重
     */
    private String weight;

    /**
     * 血型：1:A型，2:B型，3:AB型，4:O型，5:其它
     */
    private Integer bloodType;

    /**
     * 过敏禁忌
     */
    private String taboo;

    /**
     * 是否是会员，1会员，2散客
     */
    private Integer member;

}
