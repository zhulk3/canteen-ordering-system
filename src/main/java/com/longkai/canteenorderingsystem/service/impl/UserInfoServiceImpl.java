package com.longkai.canteenorderingsystem.service.impl;


import com.longkai.canteenorderingsystem.dao.UserInfoDao;
import com.longkai.canteenorderingsystem.pojo.Pager;
import com.longkai.canteenorderingsystem.pojo.UserInfo;
import com.longkai.canteenorderingsystem.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userInfoService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoDao userInfoDao;

    @Override
    public List<UserInfo> getValidUser() {
        return userInfoDao.getValidUsers();
    }

    @Override
    public UserInfo getUserInfoById(int id) {
        return userInfoDao.getUserInfoById(id);
    }

    @Override
    public List<UserInfo> findUserInfo(UserInfo userInfo, Pager pager) {
        Map<String, Object> map = new HashMap<>();
        map.put("userInfo", userInfo);
        int recordCount = userInfoDao.count(map);
        pager.setRowCount(recordCount);
        if (recordCount > 0) {
            map.put("pager", pager);
        }
        return userInfoDao.selectByPage(map);
    }

    @Override
    public Integer count(Map<String, Object> params) {
        return userInfoDao.count(params);
    }

    @Override
    public void modifyStatus(String ids, int flag) {
        userInfoDao.updateStatus(ids, flag);
    }
}

