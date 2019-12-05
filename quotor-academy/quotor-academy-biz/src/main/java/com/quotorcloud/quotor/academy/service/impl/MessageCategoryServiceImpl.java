package com.quotorcloud.quotor.academy.service.impl;

import com.quotorcloud.quotor.academy.api.entity.MessageCategory;
import com.quotorcloud.quotor.academy.mapper.MessageCategoryMapper;
import com.quotorcloud.quotor.academy.service.MessageCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 短信模板类别 服务实现类
 * </p>
 *
 * @author tianshihao
 * @since 2019-12-02
 */
@Service
public class MessageCategoryServiceImpl extends ServiceImpl<MessageCategoryMapper, MessageCategory> implements MessageCategoryService {

    @Autowired
    private MessageCategoryMapper messageCategoryMapper;

    @Override
    public List<MessageCategory> listBoxMessageCategory(String shopId) {
        return messageCategoryMapper.listBoxByShopId(shopId);
    }
}
