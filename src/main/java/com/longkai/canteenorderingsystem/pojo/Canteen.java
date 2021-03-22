package com.longkai.canteenorderingsystem.pojo;

public class Canteen {
    private int id;
    private String name;
    private String address;
    private int isServe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIsServe() {
        return isServe;
    }

    public void setIsServe(int isServe) {
        this.isServe = isServe;
    }

    @Override
    public String toString() {
        return "Canteen{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", isServe=" + isServe +
                '}';
    }
}
