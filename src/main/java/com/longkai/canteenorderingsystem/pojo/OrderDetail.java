package com.longkai.canteenorderingsystem.pojo;


public class OrderDetail {
    private int id;
    private int oid;
    private OrderInfo oi;
    private int pid;
    private ProductInfo pi;
    private int num;
    private double price;
    private double totalprice;


    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public void setOi(OrderInfo oi) {
        this.oi = oi;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setPi(ProductInfo pi) {
        this.pi = pi;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTotalprice(double totalPrice) {
        this.totalprice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public int getOid() {
        return oid;
    }

    public OrderInfo getOi() {
        return oi;
    }

    public int getPid() {
        return pid;
    }

    public ProductInfo getPi() {
        return pi;
    }

    public int getNum() {
        return num;
    }

    public double getTotalprice() {
        return totalprice;
    }
}

