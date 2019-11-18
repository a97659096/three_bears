package com.quotorcloud.quotor.academy.service;

import com.alibaba.fastjson.JSONObject;
import com.quotorcloud.quotor.academy.api.dto.BannerDTO;
import com.quotorcloud.quotor.academy.api.entity.Banner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * banner管理 服务类
 * </p>
 *
 * @author tianshihao
 * @since 2019-11-02
 */
public interface BannerService extends IService<Banner> {

    Boolean saveBanner(BannerDTO bannerDTO);

    JSONObject listBanner(BannerDTO bannerDTO);

    Boolean updateBanner(BannerDTO bannerDTO);

    Boolean removeBanner(String id);

    List<JSONObject> selectBannerByShopId(String shopId);

    Banner selectBannerById(String id);
}
