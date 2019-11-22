package com.quotorcloud.quotor.academy.util;

import com.quotorcloud.quotor.common.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

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
}
