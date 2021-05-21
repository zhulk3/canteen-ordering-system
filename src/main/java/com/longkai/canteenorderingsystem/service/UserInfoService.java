package com.longkai.canteenorderingsystem.service;


import com.longkai.canteenorderingsystem.pojo.Pager;
import com.longkai.canteenorderingsystem.pojo.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserInfoService {
    List<UserInfo> getValidUser();

    UserInfo getUserInfoById(int id);

    List<UserInfo> findUserInfo(UserInfo userInfo, Pager pager);

    Integer count(Map<String,Object>params);

    void modifyStatus(String ids,int flag);

    UserInfo login(UserInfo userInfo);

}


