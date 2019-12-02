package com.quotorcloud.quotor.academy.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.quotorcloud.quotor.common.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class OrderUtil {
    @Autowired
    private RedisUtil redisUtil;

    @Value("${spring.redis.key.prefix.orderId}")
    private String REDIS_KEY_PREFIX_ORDER_ID;

    /**
     * 生成18位订单编号:8位日期+2位平台号码+2位支付方式+6位以上自增id
     */
    public String generateOrderSn(Integer shopId) {
        StringBuilder sb = new StringBuilder();
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        //按加盟商id 和 月做key值
        String key = REDIS_KEY_PREFIX_ORDER_ID + shopId + date.substring(0, 6);
        Long increment = redisUtil.incr(key, 1);
        sb.append(date);
        sb.append(String.format("%04d", shopId));
        String incrementStr = increment.toString();
        if (incrementStr.length() <= 6) {
            sb.append(String.format("%06d", increment));
        } else {
            sb.append(incrementStr);
        }
        return sb.toString();
    }

    public void genertorQRCode(String codeUrl, HttpServletResponse response){
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
}
