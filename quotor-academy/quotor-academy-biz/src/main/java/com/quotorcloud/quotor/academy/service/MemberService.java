package com.quotorcloud.quotor.academy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quotorcloud.quotor.academy.api.dto.MemberDTO;
import com.quotorcloud.quotor.academy.api.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.quotorcloud.quotor.academy.api.vo.MemberVO;

/**
 * <p>
 * 客户信息表 服务类
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-19
 */
public interface MemberService extends IService<Member> {

    Boolean saveMember(MemberDTO memberDTO);

    Page<MemberVO> listMember(Page page, MemberDTO memberDTO);

    Boolean updateMember(MemberDTO memberDTO);

    Boolean removeMember(String id);

    MemberVO getMemberById(String id);
}
