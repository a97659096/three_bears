package com.quotorcloud.quotor.academy.service;

import com.quotorcloud.quotor.academy.api.entity.MessageCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 短信模板类别 服务类
 * </p>
 *
 * @author tianshihao
 * @since 2019-12-02
 */
public interface MessageCategoryService extends IService<MessageCategory> {

    List<MessageCategory> listBoxMessageCategory(String shopId);
}
