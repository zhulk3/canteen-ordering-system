package com.longkai.canteenorderingsystem.pojo;


public class Powers {
    private AdminInfo adminInfo;
    private Functions functions;

    public AdminInfo getAdminInfo() {
        return adminInfo;
    }

    public Functions getFunctions() {
        return functions;
    }

    @Override
    public String toString() {
        return "Powers{" +
                "adminInfo=" + adminInfo +
                ", functions=" + functions +
                '}';
    }

    public void setAdminInfo(AdminInfo adminInfo) {
        this.adminInfo = adminInfo;
    }

    public void setFunctions(Functions functions) {
        this.functions = functions;
    }
}

