package com.longkai.canteenorderingsystem.service.impl;

import com.longkai.canteenorderingsystem.dao.TypeDao;
import com.longkai.canteenorderingsystem.pojo.Type;
import com.longkai.canteenorderingsystem.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("typeService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Override
    public List<Type> getAll() {
        return typeDao.selectAll();
    }

    @Override
    public int addType(Type type) {
        return typeDao.add(type);
    }

    @Override
    public void updateType(Type type) {
        typeDao.update(type);
    }
}

