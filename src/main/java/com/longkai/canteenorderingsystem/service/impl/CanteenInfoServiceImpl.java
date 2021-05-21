package com.longkai.canteenorderingsystem.service.impl;

import com.longkai.canteenorderingsystem.dao.CanteenDao;
import com.longkai.canteenorderingsystem.pojo.Canteen;
import com.longkai.canteenorderingsystem.service.CanteenInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("canteenInfoService")
public class CanteenInfoServiceImpl implements CanteenInfoService {
    @Autowired
    private CanteenDao canteenDao;
    @Override
    public List<Canteen> getAll() {
        return canteenDao.selectAll();
    }

    @Override
    public int addCanteen(Canteen canteen) {
        return canteenDao.addCanteen(canteen);
    }

    @Override
    public void updateCanteen(Canteen canteen) {
        canteenDao.update(canteen);
    }

    @Override
    public int deleteCanteen(int id) {
        return canteenDao.deleteCanteen(id);
    }
}
