package com.quotorcloud.quotor.academy.controller;


import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.quotorcloud.quotor.academy.api.dto.CourseOrderDTO;
import com.quotorcloud.quotor.academy.api.entity.CourseOrder;
import com.quotorcloud.quotor.academy.service.CourseOrderService;
import com.quotorcloud.quotor.academy.service.CourseService;
import com.quotorcloud.quotor.academy.service.TeacherService;
import com.quotorcloud.quotor.common.core.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程订单表（课程预约） 前端控制器
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-05
 */
@RestController
@RequestMapping("/course/order")
public class CourseOrderController {

    @Autowired
    private CourseOrderService courseOrderService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    //保存订单生成二维码返回，native扫码支付
    @GetMapping("/native/save")
    public void saveCourseOrder(CourseOrderDTO courseOrderDTO,
                                HttpServletRequest request, HttpServletResponse response){

        String codeUrl = courseOrderService.saveCourseOrder(courseOrderDTO, request);

        if(codeUrl == null) {
            throw new  NullPointerException();
        }

        //将codeurl生成二维码
        try{
            //生成二维码配置
            Map<EncodeHintType,Object> hints =  new HashMap<>();
            //设置纠错等级
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            //编码类型
            hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");

            BitMatrix bitMatrix = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE,400,400,hints);
            OutputStream out =  response.getOutputStream();

            MatrixToImageWriter.writeToStream(bitMatrix,"png",out);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping("/jsapi/save")
    public R saveJSAPICourseOrder(String courseId, Integer userId, String code,
                                  HttpServletRequest request, HttpServletResponse response){
        return null;
    }

    @GetMapping("/list/appointment")
    public R listAppointment(CourseOrderDTO courseOrderDTO){
        return R.ok(courseOrderService.listAppointment(courseOrderDTO));
    }

    @GetMapping("/listbox")
    public R listBox(Integer status){
        List<JSONObject> courseListbox = courseService.listCourseListBox(status);
        List<JSONObject> teacherListbox = teacherService.listBoxTeacher();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseListbox", courseListbox);
        jsonObject.put("teacherListbox", teacherListbox);
        return R.ok(jsonObject);
    }

    @PostMapping
    public R saveCourseOrder(@RequestBody CourseOrder courseOrder){
        return R.ok(courseOrderService.save(courseOrder));
    }

    /**
     * 批量修改学习状态
     * @param courseOrders
     * @return
     */
    @PutMapping("study-state")
    public R updateBatchStudyState(@RequestBody List<CourseOrder> courseOrders){
        return R.ok(courseOrderService.updateBatchById(courseOrders));
    }
}
