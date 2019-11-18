package com.quotorcloud.quotor.academy.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quotorcloud.quotor.academy.api.dto.ConditionCardDTO;
import com.quotorcloud.quotor.academy.api.entity.ConditionCard;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quotorcloud.quotor.academy.api.vo.ConditionCardVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 卡片信息 Mapper 接口
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-11
 */
public interface ConditionCardMapper extends BaseMapper<ConditionCard> {

    List<ConditionCardVO> selectCardPage(Page<ConditionCardDTO> page,
                                         @Param("card") ConditionCardDTO card);

}
