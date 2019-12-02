package com.quotorcloud.quotor.academy.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quotorcloud.quotor.academy.api.dto.InviteJobDTO;
import com.quotorcloud.quotor.academy.api.entity.InviteJob;
import com.baomidou.mybatisplus.extension.service.IService;
import com.quotorcloud.quotor.academy.api.vo.InviteJobVO;

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

    IPage<InviteJobVO> listInviteJob(Page<InviteJobVO> page,
                        InviteJobDTO inviteJobDTO) throws ParseException;

    InviteJobVO selectInviteJobById(Integer type, String id) throws ParseException;

}
