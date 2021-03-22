package com.longkai.canteenorderingsystem.service.impl;


import com.longkai.canteenorderingsystem.dao.AdminInfoDao;
import com.longkai.canteenorderingsystem.pojo.AdminInfo;
import com.longkai.canteenorderingsystem.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("adminInfoService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class AdminInfoServiceImpl implements AdminInfoService {

    @Autowired
    private AdminInfoDao adminInfoDao;

    @Override
    public AdminInfo login(AdminInfo adminInfo) {
        return adminInfoDao.selectByNameAndPwd(adminInfo);
    }

    @Override
    public AdminInfo getAdminInfoAndFunctions(Integer id) {
        return adminInfoDao.selectById(id);
    }

    @Override
    public int modifyPwd(AdminInfo adminInfo) {
        return  adminInfoDao.modifyPwd(adminInfo);
    }

    @Override
    public List<AdminInfo> selectAll() {
        return adminInfoDao.selectAll();
    }

    @Override
    public int deleteAdminInfo(int id) {
        return adminInfoDao.deleteAdminInfo(id);
    }

    @Override
    public int updateAdminInfo(AdminInfo adminInfo) {
        return adminInfoDao.updateAdminInfo(adminInfo);
    }

    @Override
    public int addAdmin(AdminInfo adminInfo) {
        return adminInfoDao.addAdmin(adminInfo);
    }
}

