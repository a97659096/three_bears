package com.quotorcloud.quotor.academy.mapper;

import com.quotorcloud.quotor.academy.api.entity.MessageTemplate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quotorcloud.quotor.academy.api.vo.MessageCategoryTemplateVO;

import java.util.List;

/**
 * <p>
 * 短信模板内容 Mapper 接口
 * </p>
 *
 * @author tianshihao
 * @since 2019-12-02
 */
public interface MessageTemplateMapper extends BaseMapper<MessageTemplate> {

    List<MessageCategoryTemplateVO> listMessageCategoryTemplateByShopId(String shopId);

}
