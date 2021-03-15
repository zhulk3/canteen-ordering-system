package com.longkai.canteenorderingsystem.pojo;

import java.util.List;

public class AdminInfo {
    private int id;
    private String name;
    private String pwd;
    private List<Functions> fs;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }

    public List<Functions> getFs() {
        return fs;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setFs(List<Functions> fs) {
        this.fs = fs;
    }
}

