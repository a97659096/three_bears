package com.quotorcloud.quotor.academy.controller;


import com.quotorcloud.quotor.academy.api.entity.InnTeacherOrder;
import com.quotorcloud.quotor.academy.service.InnTeacherOrderService;
import com.quotorcloud.quotor.academy.util.OrderUtil;
import com.quotorcloud.quotor.common.core.util.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 下店订单表 前端控制器
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-28
 */
@RestController
@RequestMapping("/inn/teacher/order")
public class InnTeacherOrderController {

    @Autowired
    private InnTeacherOrderService innTeacherOrderService;

    @Autowired
    private OrderUtil orderUtil;

    @PostMapping("native/save")
    public void nativeSaveOrder(InnTeacherOrder innTeacherOrder, HttpServletRequest request,
                                HttpServletResponse response){
        String codeUrl = innTeacherOrderService.saveInnTeacherOrderNATIVE(innTeacherOrder, request);
        if(ComUtil.isEmpty(codeUrl)){
            throw new NullPointerException();
        }
        orderUtil.genertorQRCode(codeUrl, response);
    }

}
