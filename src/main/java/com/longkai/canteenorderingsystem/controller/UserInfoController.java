package com.longkai.canteenorderingsystem.controller;

import com.longkai.canteenorderingsystem.pojo.AdminInfo;
import com.longkai.canteenorderingsystem.pojo.Pager;
import com.longkai.canteenorderingsystem.pojo.UserInfo;
import com.longkai.canteenorderingsystem.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/userinfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(@RequestParam("name") String name,@RequestParam("password") String password) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(name);
        userInfo.setPassword(password);
        UserInfo ui = userInfoService.login(userInfo);
        if (ui != null) {
            return "{\"success\":\"true\",\"message\":\"登录成功\"}";
        } else {
            return "{\"success\":\"false\",\"message\":\"登录失败\"}";
        }
    }

    @RequestMapping(value = "/getUserInfo")
    @ResponseBody
    public UserInfo getUserInfo(@RequestParam("name") String name,@RequestParam("password") String password){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(name);
        userInfo.setPassword(password);
        System.out.println(userInfo+"getUserInfo");
        UserInfo ui = userInfoService.login(userInfo);
        return ui;
    }

    @ResponseBody
    @RequestMapping(value = "/getValidUser")
    public List<UserInfo> getValidUser() {
        List<UserInfo> userInfoList = userInfoService.getValidUser();
        UserInfo userInfo = new UserInfo();
        userInfo.setId(0);
        userInfo.setUserName("请选择");
        userInfoList.add(userInfo);
        return userInfoList;
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> userInfoList(Integer page, Integer rows, UserInfo userInfo) {
        // 创建分页类对象
        Pager pager = new Pager();
        pager.setCurPage(page);
        pager.setPerPageRows(rows);
        // 创建对象params，封装查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userInfo", userInfo);
        // 根据查询条件，获取客户记录数
        int totalCount = userInfoService.count(params);
        // 根据查询条件，分页获取客户列表
        List<UserInfo> userinfos = userInfoService.findUserInfo(userInfo, pager);
        System.out.println(userinfos);
        // 创建对象result，保存查询结果数据
        Map<String, Object> result = new HashMap<String, Object>(2);
        result.put("total", totalCount);
        result.put("rows", userinfos);
        return result;
    }

    @RequestMapping(value = "/setIsEnableUser", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String setIsEnableUser(@RequestParam(value = "uids") String uids,
                                  @RequestParam(value = "flag") String flag) {
        try {
            userInfoService.modifyStatus(uids.substring(0, uids.length() - 1), Integer.parseInt(flag));
            return "{\"success\":\"true\",\"message\":\"更改成功\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"success\":\"false\",\"message\":\"更改失败\"}";
        }
    }
}

