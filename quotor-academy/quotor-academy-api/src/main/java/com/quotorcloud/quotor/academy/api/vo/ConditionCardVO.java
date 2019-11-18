package com.quotorcloud.quotor.academy.api.vo;

import com.quotorcloud.quotor.academy.api.dto.ConditionCardDetailDTO;
import com.quotorcloud.quotor.academy.api.entity.ConditionCard;
import com.quotorcloud.quotor.academy.api.entity.ConditionCardDetail;
import lombok.Data;

import java.util.List;

@Data
public class ConditionCardVO extends ConditionCard {

    /**
     * 卡包含内容
     */
    private List<ConditionCardDetail> cardContent;

    /**
     * 购卡赠送
     */
    private List<ConditionCardDetail> buyCardGive;

    /**
     * 充值赠送
     */
    private List<ConditionCardDetailDTO> topUpGive;

    private List<ConditionCardDetail> conditionCardDetails;
    
}
