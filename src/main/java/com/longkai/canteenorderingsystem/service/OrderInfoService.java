package com.longkai.canteenorderingsystem.service;

import com.longkai.canteenorderingsystem.pojo.OrderDetail;
import com.longkai.canteenorderingsystem.pojo.OrderInfo;
import com.longkai.canteenorderingsystem.pojo.Pager;
import java.util.List;
import java.util.Map;

public interface OrderInfoService {

    List<OrderInfo> findOrderInfo(OrderInfo orderInfo, Pager pager);

    Integer count(Map<String,Object>params);

    int addOrderInfo(OrderInfo orderInfo);

    int addOrderDetail(OrderDetail orderDetail);

    OrderInfo getOrderInfoById(int id);

    List<OrderDetail> getOrderDetailByOid(int oid);

    int deleteOrder(int id);
}

