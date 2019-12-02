package com.quotorcloud.quotor.academy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 微信配置类
 */
@Configuration
//@PropertySource(value="classpath:application.properties")
public class WeChatConfig {


    /**
     * 公众号appid
     */
//    @Value("${wxpay.appid}")
    private String appId;

    /**
     * 公众号秘钥
     */
//    @Value("${wxpay.appsecret}")
    private String appsecret;

    /**
     * 开放平台appid
     */
//    @Value("${wxopen.appid}")
    private String openAppid;

    /**
     * 开放平台appsecret
     */
//    @Value("${wxopen.appsecret}")
    private String openAppsecret;

    /**
     * 开放平台回调url
     */
//    @Value("${wxopen.redirect_url}")
    private String openRedirectUrl;

    /**
     * 微信开放平台二维码连接
     */
    private final static String OPEN_QRCODE_URL= "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_login&state=%s#wechat_redirect";


    /**
     * 开放平台获取access_token地址
     */
    private final static String OPEN_ACCESS_TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";


    /**
     * 获取用户信息
     */
    private final static String OPEN_USER_INFO_URL ="https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";


    /**
     * 获取OpenId
     */
    private final static String OPEN_ID_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    public static String getOpenIdUrl() {
        return OPEN_ID_URL;
    }

    /**
     * 商户号id
     */
//    @Value("${wxpay.mer_id}")
    private String mchId;

    /**
     * 支付key
     */
//    @Value("${wxpay.key}")
    private String key;

    /**
     * 预约课程微信支付回调url
     */
//    @Value("${wxpay.callback}")
    private String appointCoursePayCallbackUrl;

    /**
     * 预约讲师微信支付回调url
     */
//    @Value("${wxpay.callback}")
    private String appointTeacherPayCallbackUrl;


    /**
     * 统一下单url
     */
    private static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";


    public static String getUnifiedOrderUrl() {
        return UNIFIED_ORDER_URL;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAppointCoursePayCallbackUrl() {
        return appointCoursePayCallbackUrl;
    }

    public void setAppointCoursePayCallbackUrl(String appointCoursePayCallbackUrl) {
        this.appointCoursePayCallbackUrl = appointCoursePayCallbackUrl;
    }

    public String getAppointTeacherPayCallbackUrl() {
        return appointTeacherPayCallbackUrl;
    }

    public void setAppointTeacherPayCallbackUrl(String appointTeacherPayCallbackUrl) {
        this.appointTeacherPayCallbackUrl = appointTeacherPayCallbackUrl;
    }

    public static String getOpenUserInfoUrl() {
        return OPEN_USER_INFO_URL;
    }

    public static String getOpenAccessTokenUrl() {
        return OPEN_ACCESS_TOKEN_URL;
    }

    public static String getOpenQrcodeUrl() {
        return OPEN_QRCODE_URL;
    }

    public String getOpenAppid() {
        return openAppid;
    }

    public void setOpenAppid(String openAppid) {
        this.openAppid = openAppid;
    }

    public String getOpenAppsecret() {
        return openAppsecret;
    }

    public void setOpenAppsecret(String openAppsecret) {
        this.openAppsecret = openAppsecret;
    }

    public String getOpenRedirectUrl() {
        return openRedirectUrl;
    }

    public void setOpenRedirectUrl(String openRedirectUrl) {
        this.openRedirectUrl = openRedirectUrl;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }
}
