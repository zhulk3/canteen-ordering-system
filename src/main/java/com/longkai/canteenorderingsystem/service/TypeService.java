package com.longkai.canteenorderingsystem.service;

import com.longkai.canteenorderingsystem.pojo.Type;

import java.util.List;

public interface TypeService {

    List<Type> getAll();

    int addType(Type type);

    void updateType(Type type);

    int deleteType(int id);
}

