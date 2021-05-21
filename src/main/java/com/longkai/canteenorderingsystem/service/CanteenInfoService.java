package com.longkai.canteenorderingsystem.service;

import com.longkai.canteenorderingsystem.pojo.Canteen;

import java.util.List;

public interface CanteenInfoService {
    List<Canteen> getAll();

    int addCanteen(Canteen canteen);

    void updateCanteen(Canteen canteen);

    int deleteCanteen(int id);
}
