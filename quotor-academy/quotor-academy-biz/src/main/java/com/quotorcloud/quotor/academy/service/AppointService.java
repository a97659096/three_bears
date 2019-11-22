package com.quotorcloud.quotor.academy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quotorcloud.quotor.academy.api.dto.AppointDTO;
import com.quotorcloud.quotor.academy.api.dto.AppointEmployeeDTO;
import com.quotorcloud.quotor.academy.api.entity.Appoint;
import com.baomidou.mybatisplus.extension.service.IService;
import com.quotorcloud.quotor.academy.api.vo.AppointVO;

import java.util.List;

/**
 * <p>
 * 预约信息表 服务类
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-21
 */
public interface AppointService extends IService<Appoint> {

    Boolean saveAppoint(AppointDTO appointDTO);

    Boolean updateAppoint(AppointDTO appointDTO);

    Boolean cancelAppoint(String id);

    List<AppointEmployeeDTO> formAppoint(AppointVO appointVO);

    IPage<AppointVO> listAppoint(Page<AppointVO> page, AppointVO appointVO);

    AppointDTO listAppointById(String id, String appointId);

}
