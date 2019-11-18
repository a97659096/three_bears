package com.quotorcloud.quotor.academy.service;

import com.alibaba.fastjson.JSONObject;
import com.quotorcloud.quotor.academy.api.dto.ConditionCardDTO;
import com.quotorcloud.quotor.academy.api.entity.ConditionCard;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 卡片信息 服务类
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-11
 */
public interface ConditionCardService extends IService<ConditionCard> {

    Boolean saveConditionCard(ConditionCardDTO conditionCardDTO);

    JSONObject selectConditionCard(ConditionCardDTO conditionCardDTO);

    Boolean updateConditionCard(ConditionCardDTO conditionCardDTO);

    Boolean removeConditionCard(String id);
}
