package com.quotorcloud.quotor.academy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.quotorcloud.quotor.academy.api.dto.EmployeeDTO;
import com.quotorcloud.quotor.academy.api.entity.Employee;
import com.quotorcloud.quotor.academy.api.vo.EmployeeVO;
import com.quotorcloud.quotor.academy.mapper.EmployeeMapper;
import com.quotorcloud.quotor.academy.service.EmployeeService;
import com.quotorcloud.quotor.academy.service.ListBoxService;
import com.quotorcloud.quotor.common.core.constant.CommonConstants;
import com.quotorcloud.quotor.common.core.constant.FileConstants;
import com.quotorcloud.quotor.common.core.constant.ListBoxConstants;
import com.quotorcloud.quotor.common.core.constant.enums.ExceptionEnum;
import com.quotorcloud.quotor.common.core.exception.MyException;
import com.quotorcloud.quotor.common.core.util.*;
import com.quotorcloud.quotor.common.security.service.QuotorUser;
import com.quotorcloud.quotor.common.security.util.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 员工信息表 服务实现类
 * </p>
 * TODO 预约排序功能待补充
 * @author tianshihao
 * @since 2019-10-29
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private ListBoxService listBoxService;

    private static String HEAD_IMG = "headImg/";

    private static String WORK_LIST = "worksList/";

    /**
     * 查询职位和头衔
     * @return
     */
    @Override
    public JSONObject selectEmployeePositionAndHeadTitle() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("position", listBoxService.listBox(ListBoxConstants.EMPLOYEE_MODULE, ListBoxConstants.EMPLOYEE_MODULE_POSITION));
        jsonObject.put("headTitle", listBoxService.listBox(ListBoxConstants.EMPLOYEE_MODULE, ListBoxConstants.EMPLOYEE_MODULE_HEAD));
        return jsonObject;
    }

    /**
     * 查询员工列表
     * @param shopId
     * @return
     */
    @Override
    public List<JSONObject> selectEmployeeListBox(String shopId) {
        //获取店铺信息
        QuotorUser user = SecurityUtils.getUser();
        if(ComUtil.isEmpty(user)){
            return null;
        }
        if(ComUtil.isEmpty(user.getDeptId())){
            return null;
        }
        //查询店铺下的员工
        List<Employee> employees = employeeMapper.selectList(new QueryWrapper<Employee>()
                .eq("e_del_state", CommonConstants.STATUS_NORMAL)
                .eq("e_shop_id", ComUtil.isEmpty(shopId)?user.getDeptId():shopId));
        //转换格式进行拼接员工列表
        return employees.stream().map(employee -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", employee.getId());
            jsonObject.put("name", employee.getName());
            return jsonObject;
        }).collect(Collectors.toList());
    }

    /**
     * 按id查询员工信息
     * @param id
     * @return
     */
    @Override
    public EmployeeVO selectEmployeeById(String id) {
        Employee employee = employeeMapper.selectOne(new QueryWrapper<Employee>()
                .eq("e_del_state", 0).eq("e_id",id));
        return getEmployeeVO(employee);
    }

    /**
     * 修改员工信息
     * @param employeeDTO
     * @return
     */
    @Override
    public Boolean updateEmployee(EmployeeDTO employeeDTO) {
        String id = employeeDTO.getId();
        if(ComUtil.isEmpty(id)){
            throw new MyException(ExceptionEnum.NOT_FIND_ID);
        }
        Employee employee = employeeMapper.selectById(id);
        List<String> fields = Lists.newArrayList("headImg", "works");
        FileUtil.removeFileAndField(employee, Employee.class, employeeDTO, fields, FileConstants.FileType.FILE_EMPLOYEE_IMG_DIR_);
        employeeMapper.updateById(mapEmployeeDTOToDO(employeeDTO, employee));
        return Boolean.TRUE;
    }

    /**
     * 删除员工信息
     * @param id
     * @return
     */
    @Override
    public Boolean removeEmployee(String id) {
        if(ComUtil.isEmpty(id)){
            throw new MyException(ExceptionEnum.NOT_FIND_ID);
        }
        try {
            Employee employee = new Employee();
            employee.setId(id);
            employee.setDelState(CommonConstants.STATUS_DEL);
            employeeMapper.updateById(employee);
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException(ExceptionEnum.DELETE_EMPLOYEE_FAILED);
        }
        return Boolean.TRUE;
    }

    /**
     * 查询员工信息
     * @param employeeDTO
     * @return
     */
    @Override
    public JSONObject listEmployee(EmployeeDTO employeeDTO) {
        //设置店铺信息
        QuotorUser user = SecurityUtils.getUser();
        if(ComUtil.isEmpty(user)){
            return null;
        }
        employeeDTO.setShopId(String.valueOf(user.getDeptId()));
        //设置日期
        setEmployeeDTODate(employeeDTO);
        //封装Page
        Page<Employee> page = PageUtil.getPage(employeeDTO.getPageNo(), employeeDTO.getPageSize());
        //查询员工信息
        IPage<Employee> employeeIPage = employeeMapper.selectPage(page, new QueryWrapper<Employee>()
                .eq("e_del_state", 0)
                .like(!ComUtil.isEmpty(employeeDTO.getName()), "e_name", employeeDTO.getName())
                .eq(!ComUtil.isEmpty(employeeDTO.getPositionName()), "e_position_name", employeeDTO.getPositionName())
                .eq("e_shop_id", employeeDTO.getShopId())
                .ge(!ComUtil.isEmpty(employeeDTO.getStart()), "e_join_date", employeeDTO.getStart())
                .le(!ComUtil.isEmpty(employeeDTO.getEnd()), "e_join_date", employeeDTO.getEnd())
                .like(!ComUtil.isEmpty(employeeDTO.getJobNumber()), "e_job_number", employeeDTO.getJobNumber())
                .like(!ComUtil.isEmpty(employeeDTO.getWorkState()), "e_work_state", employeeDTO.getWorkState())
                .orderByDesc("e_gmt_create"));
        List<Employee> records = employeeIPage.getRecords();
        List<EmployeeVO> employeeVOS = Lists.newLinkedList();
        //对特殊字段进行转换重新赋值
        for (Employee employee : records){
            EmployeeVO employeeVO = getEmployeeVO(employee);
            employeeVOS.add(employeeVO);
        }
        return PageUtil.getPagePackage("employees", employeeVOS, page);
    }

    private void setEmployeeDTODate(EmployeeDTO employeeDTO){
        List<String> date = DateTimeUtil.getStringDate(employeeDTO.getDateRange());
        if(!ComUtil.isEmpty(date)){
            employeeDTO.setStart(date.get(0));
            employeeDTO.setEnd(date.get(1));
        }
    }

    /**
     * DOVO映射
     * @param employee
     * @return
     */
    private EmployeeVO getEmployeeVO(Employee employee) {
        EmployeeVO employeeVO = new EmployeeVO();
        BeanUtils.copyProperties(employee, employeeVO);
        if(!ComUtil.isEmpty(employee.getJoinDate())){
            employeeVO.setJoinDate(DateTimeUtil.localDateToString(employee.getJoinDate()));
        }
        //需要加前缀的字段
        FileUtil.addPrefix(employee, EmployeeVO.class, employeeVO, "works");
        return employeeVO;
    }

    /**
     * 新增员工信息
     * TODO 店铺信息未填充
     * @param employeeDTO
     * @return
     */
    @Override
    public Boolean saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        try {
            employeeMapper.insert(mapEmployeeDTOToDO(employeeDTO, employee));
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException(ExceptionEnum.SAVE_EMPLOYEE_FAILED);
        }
        return Boolean.TRUE;
    }

    private Employee mapEmployeeDTOToDO(EmployeeDTO employeeDTO, Employee employee){
        //映射
        BeanUtils.copyProperties(employeeDTO, employee, "eWorks", "eHeadImg", "joinDate");

        //设置店铺信息
        QuotorUser user = SecurityUtils.getUser();
        if(ComUtil.isEmpty(user)){
            return null;
        }
        employee.setShopId(String.valueOf(user.getDeptId()));
        employee.setShopName(user.getDeptName());

        //处理职位
        listBoxService.checkListBox(employee.getPositionName(), ListBoxConstants.EMPLOYEE_MODULE,
                ListBoxConstants.EMPLOYEE_MODULE_POSITION);
        //处理头衔
        listBoxService.checkListBox(employee.getHeadTitleName(), ListBoxConstants.EMPLOYEE_MODULE,
                ListBoxConstants.EMPLOYEE_MODULE_HEAD);

        //处理日期
        if(!ComUtil.isEmpty(employeeDTO.getJoinDate())){
            employee.setJoinDate(DateTimeUtil.stringToLocalDate(employeeDTO.getJoinDate()));
        }

        //处理头像和作品集（通过反射得到方法信息进行操作）
        FileUtil.saveFileAndField(employee, Employee.class, employeeDTO,
                Lists.newArrayList("headImg","works"),
                FileConstants.FileType.FILE_EMPLOYEE_IMG_DIR_, null);
        return employee;
    }
}
