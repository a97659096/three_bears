package com.quotorcloud.quotor.academy.mapper;

import com.quotorcloud.quotor.academy.api.entity.MessageCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 短信模板类别 Mapper 接口
 * </p>
 *
 * @author tianshihao
 * @since 2019-12-02
 */
public interface MessageCategoryMapper extends BaseMapper<MessageCategory> {

    List<MessageCategory> listBoxByShopId(String shopId);
}
