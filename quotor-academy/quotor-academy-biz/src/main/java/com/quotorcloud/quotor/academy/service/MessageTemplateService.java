package com.quotorcloud.quotor.academy.service;

import com.quotorcloud.quotor.academy.api.entity.MessageTemplate;
import com.baomidou.mybatisplus.extension.service.IService;
import com.quotorcloud.quotor.academy.api.vo.MessageCategoryTemplateVO;

import java.util.List;

/**
 * <p>
 * 短信模板内容 服务类
 * </p>
 *
 * @author tianshihao
 * @since 2019-12-02
 */
public interface MessageTemplateService extends IService<MessageTemplate> {

    List<MessageCategoryTemplateVO> listMessageCategoryTemplate(String shopId);

    Boolean updateMessageTemplate(MessageTemplate messageTemplate);

    Boolean removeMessageTemplate(String id);

    Boolean saveMessageTemplate(MessageTemplate messageTemplate);
}
