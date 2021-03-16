package com.longkai.canteenorderingsystem.service.impl;

import com.longkai.canteenorderingsystem.dao.OrderInfoDao;
import com.longkai.canteenorderingsystem.pojo.OrderDetail;
import com.longkai.canteenorderingsystem.pojo.OrderInfo;
import com.longkai.canteenorderingsystem.pojo.Pager;
import com.longkai.canteenorderingsystem.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("orderInfoService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderInfoDao orderInfoDao;

    @Override
    public List<OrderInfo> findOrderInfo(OrderInfo orderInfo, Pager pager) {
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("orderInfo", orderInfo);
        int recordCount = orderInfoDao.count(params);
        pager.setRowCount(recordCount);
        if (recordCount > 0) {
            params.put("pager", pager);
        }
        return orderInfoDao.selectByPage(params);
    }

    @Override
    public int addOrderDetail(OrderDetail orderDetail) {
        return orderInfoDao.saveOrderDetail(orderDetail);
    }

    @Override
    public Integer count(Map<String, Object> params) {
        return orderInfoDao.count(params);
    }

    @Override
    public int addOrderInfo(OrderInfo orderInfo) {
        return orderInfoDao.saveOrderInfo(orderInfo);
    }

    @Override
    public OrderInfo getOrderInfoById(int id) {
        return orderInfoDao.getOrderInfoById(id);
    }

    @Override
    public List<OrderDetail> getOrderDetailByOid(int oid) {
        return orderInfoDao.getOrderDetailByOid(oid);
    }

    @Override
    public int deleteOrder(int id) {
        int result = 1;
        try {
            orderInfoDao.deleteOrderDetail(id);
            orderInfoDao.deleteOrderInfo(id);
        } catch (Exception e) {
            result = 0;
        }
        return result;
    }
}

