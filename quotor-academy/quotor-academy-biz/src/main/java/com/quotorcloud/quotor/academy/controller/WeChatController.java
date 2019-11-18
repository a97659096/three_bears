package com.quotorcloud.quotor.academy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quotorcloud.quotor.academy.api.entity.CourseOrder;
import com.quotorcloud.quotor.academy.config.WeChatConfig;
import com.quotorcloud.quotor.academy.service.CourseOrderService;
import com.quotorcloud.quotor.common.core.constant.CommonConstants;
import com.quotorcloud.quotor.common.core.util.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.SortedMap;

@RestController
@RequestMapping("wechat")
public class WeChatController {

    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private CourseOrderService courseOrderService;


    /**
     * 微信支付回调
     */
    @RequestMapping("/course/order/callback")
    public void orderCallback(HttpServletRequest request, HttpServletResponse response) throws Exception {

        InputStream inputStream =  request.getInputStream();

        //BufferedReader是包装设计模式，性能更搞
        BufferedReader in =  new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
        StringBuffer sb = new StringBuffer();
        String line ;
        while ((line = in.readLine()) != null){
            sb.append(line);
        }
        in.close();
        inputStream.close();
        Map<String,String> callbackMap = WXPayUtil.xmlToMap(sb.toString());
        System.out.println(callbackMap.toString());

        SortedMap<String,String> sortedMap = WXPayUtil.getSortedMap(callbackMap);

        //判断签名是否正确
        if(WXPayUtil.isCorrectSign(sortedMap,weChatConfig.getKey())){

            if("SUCCESS".equals(sortedMap.get("result_code"))){

                String outTradeNo = sortedMap.get("out_trade_no");
                CourseOrder dbCourseOrder = courseOrderService.getOne(new QueryWrapper<CourseOrder>().eq("out_trade_no",
                        outTradeNo));

                if(dbCourseOrder != null && dbCourseOrder.getPayState()==2){  //判断逻辑看业务场景
                    CourseOrder courseOrder = new CourseOrder();
                    courseOrder.setOpenId(sortedMap.get("openid"));
                    courseOrder.setOutTradeNo(outTradeNo);
                    courseOrder.setNotifyTime(LocalDateTime.now());
                    courseOrder.setPayState(CommonConstants.PAY);
                    boolean rows = courseOrderService.update(courseOrder, new QueryWrapper<CourseOrder>()
                            .eq("out_trade_no", courseOrder.getOutTradeNo()));
                    if(rows){ //通知微信订单处理成功
                        response.setContentType("text/xml");
                        response.getWriter().println("success");
                        return;
                    }
                }
            }
        }
        //都处理失败
        response.setContentType("text/xml");
        response.getWriter().println("fail");
    }
}
