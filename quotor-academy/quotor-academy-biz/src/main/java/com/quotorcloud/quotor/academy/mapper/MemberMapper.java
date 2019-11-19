package com.quotorcloud.quotor.academy.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quotorcloud.quotor.academy.api.dto.MemberDTO;
import com.quotorcloud.quotor.academy.api.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quotorcloud.quotor.academy.api.vo.MemberVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 客户信息表 Mapper 接口
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-19
 */
public interface MemberMapper extends BaseMapper<Member> {

    Page<MemberVO> selectMemberPage(Page page,@Param("member") MemberDTO memberDTO);

    MemberVO selectMemberById(@Param("id") String id);
}
