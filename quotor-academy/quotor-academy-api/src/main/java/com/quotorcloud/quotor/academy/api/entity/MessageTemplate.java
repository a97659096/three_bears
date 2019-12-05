package com.quotorcloud.quotor.academy.api.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * 短信模板内容
 * </p>
 *
 * @author tianshihao
 * @since 2019-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bear_message_template")
public class MessageTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标识
     */
    @TableId(value = "template_id", type = IdType.UUID)
    private String templateId;

    /**
     * 模板名称
     */
    private String templateCategoryId;

    /**
     * 模板内容
     */
    private String templateContent;

    /**
     * 模板类型，1通用模板，2自定义模板
     */
    private Integer templateType;

    /**
     * 所属店铺
     */
    private String templateShopId;

    @TableLogic
    private Integer delState;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;


}
