package com.longkai.canteenorderingsystem.service;


import com.longkai.canteenorderingsystem.pojo.AdminInfo;

public interface AdminInfoService {
    AdminInfo login(AdminInfo adminInfo);

    AdminInfo getAdminInfoAndFunctions(Integer id);
}

