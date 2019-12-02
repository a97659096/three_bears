package com.quotorcloud.quotor.academy.api.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class InnTeacherAppointVO {

    private String id;

    private String openId;

    private String shopId;

    private String shopName;

    private String shopHeadImg;

    private LocalDate startDate;

    private LocalDate endDate;

    private String remark;

    private String outTradeNo;

    private BigDecimal totalFee;

    private String innTeacherId;

    private String innTeacherName;



    private String ip;

    private LocalDateTime payTime;
}
