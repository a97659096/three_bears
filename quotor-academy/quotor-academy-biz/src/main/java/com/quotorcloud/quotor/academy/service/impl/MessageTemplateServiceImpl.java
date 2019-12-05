package com.quotorcloud.quotor.academy.service.impl;

import com.quotorcloud.quotor.academy.api.entity.MessageTemplate;
import com.quotorcloud.quotor.academy.api.vo.MessageCategoryTemplateVO;
import com.quotorcloud.quotor.academy.mapper.MessageTemplateMapper;
import com.quotorcloud.quotor.academy.service.MessageTemplateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 短信模板内容 服务实现类
 * </p>
 *
 * @author tianshihao
 * @since 2019-12-02
 */
@Service
public class MessageTemplateServiceImpl extends ServiceImpl<MessageTemplateMapper, MessageTemplate> implements MessageTemplateService {

    @Autowired
    private MessageTemplateMapper messageTemplateMapper;

    @Override
    public List<MessageCategoryTemplateVO> listMessageCategoryTemplate(String shopId) {
        return messageTemplateMapper.listMessageCategoryTemplateByShopId(shopId);
    }

    @Override
    public Boolean updateMessageTemplate(MessageTemplate messageTemplate) {
        messageTemplateMapper.updateById(messageTemplate);
        return Boolean.TRUE;
    }

    @Override
    public Boolean removeMessageTemplate(String id) {
        messageTemplateMapper.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Boolean saveMessageTemplate(MessageTemplate messageTemplate) {
        messageTemplateMapper.insert(messageTemplate);
        return Boolean.TRUE;
    }


}
