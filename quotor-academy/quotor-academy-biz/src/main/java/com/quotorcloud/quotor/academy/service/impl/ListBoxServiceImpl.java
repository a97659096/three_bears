package com.quotorcloud.quotor.academy.service.impl;

import com.quotorcloud.quotor.academy.api.entity.ListBox;
import com.quotorcloud.quotor.academy.mapper.ListBoxMapper;
import com.quotorcloud.quotor.academy.service.ListBoxService;
import com.quotorcloud.quotor.common.core.util.ComUtil;
import com.quotorcloud.quotor.common.security.service.QuotorUser;
import com.quotorcloud.quotor.common.security.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListBoxServiceImpl implements ListBoxService {

    @Autowired
    private ListBoxMapper listBoxMapper;

    /**
     * 检查 如果没有进行新增
     * @param name
     * @param module
     * @param tag
     */
    @Override
    public void checkListBox(String name, String module, String tag) {
        if(ComUtil.isEmpty(name)){
            return;
        }
        //设置店铺信息
        QuotorUser user = SecurityUtils.getUser();
        if(ComUtil.isEmpty(user)){
            return;
        }

        List<String> boxNameList = listBox(module, tag);
        if(!boxNameList.contains(name)){
            listBoxMapper.insert(ListBox.builder().module(module)
                    .tag(tag).name(name).shopId(String.valueOf(user.getDeptId()))
                    .build());
        }
    }

    /**
     * 获取列表
     * @param module
     * @param tag
     * @return
     */
    @Override
    public List<String> listBox(String module, String tag) {
        //设置店铺信息
        QuotorUser user = SecurityUtils.getUser();
        if(ComUtil.isEmpty(user)){
            return null;
        }

        List<ListBox> listBoxes = listBoxMapper.selectListBox(module, tag, String.valueOf(user.getDeptId()));
        return listBoxes.stream().map(ListBox::getName).collect(Collectors.toList());
    }
}
