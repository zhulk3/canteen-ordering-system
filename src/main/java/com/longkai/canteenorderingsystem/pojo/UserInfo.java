package com.longkai.canteenorderingsystem.pojo;

public class UserInfo {
    private int id;
    private String userName;
    private String password;
    private String sex;
    private String realName;
    private String address;
    private String email;
    private String regDate;
    private int status;
    private School school;

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getSex() {
        return sex;
    }

    public String getRealName() {
        return realName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getRegDate() {
        return regDate;
    }

    public int getStatus() {
        return status;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
