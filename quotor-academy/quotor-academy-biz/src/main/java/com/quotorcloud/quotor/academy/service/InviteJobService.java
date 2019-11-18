package com.quotorcloud.quotor.academy.service;

import com.alibaba.fastjson.JSONObject;
import com.quotorcloud.quotor.academy.api.dto.InviteJobDTO;
import com.quotorcloud.quotor.academy.api.entity.InviteJob;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;

/**
 * <p>
 * 招聘下店信息表 服务类
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-04
 */
public interface InviteJobService extends IService<InviteJob> {

    Boolean saveInviteJob(InviteJobDTO inviteJobDTO);

    Boolean updateInviteJob(InviteJobDTO inviteJobDTO);

    Boolean removeInviteJob(String id);

    JSONObject listInviteJob(InviteJobDTO inviteJobDTO) throws ParseException;
}
