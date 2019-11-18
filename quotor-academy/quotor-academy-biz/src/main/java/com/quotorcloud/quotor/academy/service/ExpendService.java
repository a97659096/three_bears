package com.quotorcloud.quotor.academy.service;

import com.alibaba.fastjson.JSONObject;
import com.quotorcloud.quotor.academy.api.dto.ExpendDTO;
import com.quotorcloud.quotor.academy.api.entity.Expend;
import com.baomidou.mybatisplus.extension.service.IService;
import com.quotorcloud.quotor.academy.api.vo.ExpendVO;

/**
 * <p>
 * 支出信息表 服务类
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-01
 */
public interface ExpendService extends IService<Expend> {

    Boolean saveExpend(ExpendDTO expendDTO);

    JSONObject listExpend(ExpendDTO expendDTO);

    Boolean removeExpend(String id);

    Boolean updateExpend(ExpendDTO expendDTO);

    ExpendVO selectExpendById(String id);
}
