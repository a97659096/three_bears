package com.quotorcloud.quotor.academy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.quotorcloud.quotor.academy.api.dto.MemberDTO;
import com.quotorcloud.quotor.academy.api.entity.Employee;
import com.quotorcloud.quotor.academy.api.entity.Member;
import com.quotorcloud.quotor.academy.api.vo.MemberVO;
import com.quotorcloud.quotor.academy.mapper.MemberMapper;
import com.quotorcloud.quotor.academy.service.EmployeeService;
import com.quotorcloud.quotor.academy.service.MemberService;
import com.quotorcloud.quotor.academy.util.ShopSetterUtil;
import com.quotorcloud.quotor.common.core.constant.CommonConstants;
import com.quotorcloud.quotor.common.core.constant.FileConstants;
import com.quotorcloud.quotor.common.core.util.ComUtil;
import com.quotorcloud.quotor.common.core.util.FileUtil;
import com.quotorcloud.quotor.common.security.service.QuotorUser;
import com.quotorcloud.quotor.common.security.util.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 客户信息表 服务实现类
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-19
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ShopSetterUtil shopSetterUtil;

    @Override
    public Boolean saveMember(MemberDTO memberDTO) {
        Member member = new Member();
        //映射赋值
        mapMemberDODTO(memberDTO, member);
        member.setDelState(CommonConstants.STATUS_NORMAL);
        member.setMember(CommonConstants.NOT_MEMBER);
        //存入图片
        FileUtil.saveFileAndField(member, memberDTO, "headImg",
                FileConstants.FileType.FILE_MEMBER_IMG_DIR, null);
        memberMapper.insert(member);
        return Boolean.TRUE;
    }


    @Override
    public Page<MemberVO> listMember(Page page, MemberDTO memberDTO) {
        shopSetterUtil.shopSetter(memberDTO, memberDTO.getShopId());
        //分页查询
        Page<MemberVO> memberVOPage = memberMapper.selectMemberPage(page, memberDTO);
        for (MemberVO memberVO : memberVOPage.getRecords()) {
            mapMemberDOVO(memberVO);
        }
        return memberVOPage;
    }

    @Override
    public Boolean updateMember(MemberDTO memberDTO) {
        Member member = this.getById(memberDTO.getId());
        //刪除圖片
        FileUtil.removeFileAndField(member, memberDTO, "headImg", FileConstants.FileType.FILE_MEMBER_IMG_DIR);
        //保存圖片
        FileUtil.saveFileAndField(member, memberDTO, "headImg", FileConstants.FileType.FILE_MEMBER_IMG_DIR, null);

        BeanUtils.copyProperties(memberDTO, member, "headImg", "traceEmployee");
        mapMemberDODTO(memberDTO, member);

        memberMapper.updateById(member);

        return Boolean.TRUE;
    }

    @Override
    public Boolean removeMember(String id) {
        memberMapper.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public MemberVO getMemberById(String id) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(id);
        MemberVO memberVO = memberMapper.selectMemberById(id);
        if(!ComUtil.isEmpty(memberVO)){
            mapMemberDOVO(memberVO);
        }
        return memberVO;
    }

    @Override
    public List<JSONObject> selectMemberListBox(String shopId) {
        //获取店铺信息
        QuotorUser user = SecurityUtils.getUser();
        if(ComUtil.isEmpty(user)){
            return null;
        }
        if(ComUtil.isEmpty(user.getDeptId())){
            return null;
        }
        List<Member> members = memberMapper.selectList(new QueryWrapper<Member>().eq("del_state", CommonConstants.STATUS_NORMAL)
                .eq("shop_id", ComUtil.isEmpty(shopId) ? user.getDeptId() : shopId));
        return members.stream().map(member -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", member.getId());
            jsonObject.put("name", member.getName());
            return jsonObject;
        }).collect(Collectors.toList());
    }


    private void mapMemberDOVO(MemberVO memberVO) {
        //遍历结果集
            //关联员工获取出来
            String traceEmployeeDataBase = memberVO.getTraceEmployeeDataBase();
            if(!ComUtil.isEmpty(traceEmployeeDataBase)){
                //用，拆分开
                List<String> traceList = Splitter.on(CommonConstants.SEPARATOR)
                        .splitToList(traceEmployeeDataBase);
                // id:name  把name取出来
                List<String> traceEmployeeList = traceList.stream().map(trace ->
                        trace.substring(trace.indexOf(":") + 1))
                        .collect(Collectors.toList());
                //再把name用逗号隔开拼成字符串
//                String employeeName = Joiner.on(CommonConstants.SEPARATOR).join(traceEmployeeList);
                //set进去
                memberVO.setTraceEmployee(traceEmployeeList);
            }
    }


    private void mapMemberDODTO(MemberDTO memberDTO, Member member) {
        BeanUtils.copyProperties(memberDTO, member, "headImg", "traceEmployee");

        List<String> traceEmployee = memberDTO.getTraceEmployee();

        //存入跟踪员工
        if(!ComUtil.isEmpty(traceEmployee)){
            //查询员工信息
            List<Employee> employees = (List<Employee>) employeeService.listByIds(traceEmployee);
            //将员工信息中的id和name用:分开add进一个数组  id:name
            List<String> idNameMap = employees.stream().map(employee -> employee.getId() + ":" + employee.getName())
                    .collect(Collectors.toList());
            //然后再用，把集合合并， id:name,id:name
            String traceEmp = Joiner.on(CommonConstants.SEPARATOR).join(idNameMap);
            member.setTraceEmployee(traceEmp);
        }

        //获取用户信息并赋值
        shopSetterUtil.shopSetter(member, memberDTO.getShopId());

        //推荐人
        if(!ComUtil.isEmpty(memberDTO.getReferrerId())){
            Member byId = this.getById(memberDTO.getReferrerId());
            member.setReferrerId(byId.getId());
            member.setReferreName(byId.getName());
        }
    }
}
