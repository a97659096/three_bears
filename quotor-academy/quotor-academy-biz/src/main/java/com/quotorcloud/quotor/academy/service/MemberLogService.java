package com.quotorcloud.quotor.academy.service;

import com.quotorcloud.quotor.academy.api.dto.MemberLogDTO;
import com.quotorcloud.quotor.academy.api.entity.MemberLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.quotorcloud.quotor.academy.api.vo.MemberLogVO;

import java.util.List;

/**
 * <p>
 * 客户护理日志 服务类
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-28
 */
public interface MemberLogService extends IService<MemberLog> {

    Boolean saveMemberLog(MemberLogDTO memberLog);

    Boolean updateMemberLog(MemberLogDTO memberLogDTO);

    Boolean removeMemberLog(String id);

    List<MemberLogVO> selectMemberLogVO(MemberLogDTO memberLogDTO);
}
