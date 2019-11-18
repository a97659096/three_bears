package com.quotorcloud.quotor.academy.api.dto;

import com.quotorcloud.quotor.academy.api.entity.ConditionCard;
import com.quotorcloud.quotor.academy.api.entity.ConditionCardDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ConditionCardDTO extends ConditionCard {

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
    private List<ConditionCardDetail> topUpGive;

    private List<String> categoryIds;

    /**
     * 时长(当结束类型为固定时长时，需要用到)
     */
    private Integer duration;

    /**
     * 单位(当结束类型为固定时长时，需要用到)
     */
    private Integer unit;

    private Integer pageNo;

    private Integer pageSize;

}
