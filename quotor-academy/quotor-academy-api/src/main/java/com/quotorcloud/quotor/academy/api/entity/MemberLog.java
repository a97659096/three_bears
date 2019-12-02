package com.quotorcloud.quotor.academy.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 客户护理日志
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bear_member_log")
public class MemberLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标识
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 员工标识
     */
    private String employeeId;

    /**
     * 会员标识
     */
    private String memberId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 相关图片
     */
    private String img;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;


}
