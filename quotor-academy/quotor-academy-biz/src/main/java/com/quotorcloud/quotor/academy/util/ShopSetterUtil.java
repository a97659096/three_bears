package com.quotorcloud.quotor.academy.util;

import com.quotorcloud.quotor.common.core.util.ComUtil;
import com.quotorcloud.quotor.common.core.util.MethodUtil;
import com.quotorcloud.quotor.common.security.service.QuotorUser;
import com.quotorcloud.quotor.common.security.util.SecurityUtils;
import org.springframework.stereotype.Component;

@Component
public class ShopSetterUtil {

    public void shopSetter(Object o, Class<?> clazz, String shopId){

        //获取用户信息并赋值
        if(!ComUtil.isEmpty(shopId)){

        }else {
            QuotorUser user = SecurityUtils.getUser();
            if(ComUtil.isEmpty(user) || ComUtil.isEmpty(user.getDeptId())){
                return;
            }
            MethodUtil.setValue(o, clazz, "shopId", String.class, user.getDeptId());
            MethodUtil.setValue(o, clazz, "shopName", String.class, user.getDeptName());
        }
    }
}
