package com.longkai.canteenorderingsystem.service;


import com.longkai.canteenorderingsystem.pojo.AdminInfo;

import java.util.List;

public interface AdminInfoService {
    AdminInfo login(AdminInfo adminInfo);

    AdminInfo getAdminInfoAndFunctions(Integer id);

    int modifyPwd(AdminInfo adminInfo);

    List<AdminInfo> selectAll();

    int deleteAdminInfo(int id);

    int updateAdminInfo(AdminInfo adminInfo);

    int addAdmin(AdminInfo adminInfo);


}

