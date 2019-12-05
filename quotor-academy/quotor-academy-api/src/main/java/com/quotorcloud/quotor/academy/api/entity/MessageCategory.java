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
 * 短信模板类别
 * </p>
 *
 * @author tianshihao
 * @since 2019-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bear_message_category")
public class MessageCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId(value = "id", type = IdType.UUID)
    private String categoryId;

    /**
     * 类别名称
     */
    private String categoryName;

    /**
     * 所属店铺
     */
    private String categoryShopId;

    /**
     * 类型：1通用，2自定义
     */
    private Integer categoryType;

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
