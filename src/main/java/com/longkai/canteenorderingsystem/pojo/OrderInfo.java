package com.longkai.canteenorderingsystem.pojo;

public class OrderInfo {
    private Integer id;
    private int uid;
    private UserInfo ui;
    private String status;
    private String ordertime;
    private double orderprice;
    private String orderTimeFrom;
    private String orderTimeTo;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setUi(UserInfo ui) {
        this.ui = ui;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrdertime(String orderTime) {
        this.ordertime = orderTime;
    }

    public void setOrderprice(double orderPrice) {
        this.orderprice = orderPrice;
    }

    public void setOrderTimeFrom(String orderTimeFrom) {
        this.orderTimeFrom = orderTimeFrom;
    }

    public void setOrderTimeTo(String orderTimeTo) {
        this.orderTimeTo = orderTimeTo;
    }

    public Integer getId() {
        return id;
    }

    public int getUid() {
        return uid;
    }

    public UserInfo getUi() {
        return ui;
    }

    public String getStatus() {
        return status;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public double getOrderprice() {
        return orderprice;
    }

    public String getOrderTimeFrom() {
        return orderTimeFrom;
    }

    public String getOrderTimeTo() {
        return orderTimeTo;
    }
}

