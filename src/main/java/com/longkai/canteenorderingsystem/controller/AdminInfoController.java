package com.longkai.canteenorderingsystem.controller;

import com.longkai.canteenorderingsystem.pojo.AdminInfo;
import com.longkai.canteenorderingsystem.pojo.Functions;
import com.longkai.canteenorderingsystem.pojo.TreeNode;
import com.longkai.canteenorderingsystem.service.AdminInfoService;
import com.longkai.canteenorderingsystem.util.JsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SessionAttributes(value = {"admin"})
@Controller
@RequestMapping("/admininfo")
public class AdminInfoController {

    @Autowired
    private AdminInfoService adminInfoService;

    @RequestMapping(value = "/login", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String login(AdminInfo adminInfo, ModelMap modelMap) {
        AdminInfo ai = adminInfoService.login(adminInfo);
        if (ai != null && ai.getName() != null) {
            if (adminInfoService.getAdminInfoAndFunctions(ai.getId()).getFs().size() > 0) {
                modelMap.put("admin", ai);
                return "{\"success\":\"true\",\"message\":\"登录成功\"}";
            } else {
                return "{\"success\":\"false\",\"message\":\"您没有权限，请联系超级管理员设置权限\"}";
            }
        } else {
            return "{\"success\":\"false\",\"message\":\"登录失败\"}";
        }
    }

    @RequestMapping("/getTree")
    @ResponseBody
    public List<TreeNode> getTree(@RequestParam(value = "adminid") String adminid) {
        AdminInfo adminInfo = adminInfoService.getAdminInfoAndFunctions(Integer.parseInt(adminid));
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        List<Functions> functionsList = adminInfo.getFs();
        Collections.sort(functionsList);
        for (Functions functions : functionsList) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(functions.getId());
            treeNode.setFid(functions.getParentId());
            treeNode.setText(functions.getName());
            if (treeNode.getId() != 11) {
                nodes.add(treeNode);
            }


        }
        List<TreeNode> treeNodes = JsonFactory.buildTree(nodes, 0);
        return treeNodes;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public String loginOut(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "{\"success\":\"true\",\"message\":\"注销成功\"}";
    }

    @RequestMapping(value = "/modifyPassword", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String modifyPassword(AdminInfo adminInfo, String newpwd) {
        AdminInfo ai = adminInfoService.login(adminInfo);
        ai.setPwd(newpwd);
        if (ai != null) {
            adminInfoService.modifyPwd(ai);
            return "{\"success\":\"true\",\"message\":\"修改成功\"}";
        } else {
            return "{\"success\":\"false\",\"message\":\"修改失败\"}";
        }
    }

    @RequestMapping("/admininfoList")
    @ResponseBody
    public List<AdminInfo> getAdminList() {
        List<AdminInfo> adminInfoList = adminInfoService.selectAll();
        return adminInfoList;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAdminInfo", produces = "text/html;charset=UTF-8")
    public String deleteAdminInfo(@RequestParam(value = "aids")String aids){
        String str = "";
        try {
            aids = aids.substring(0, aids.length() - 1);
            String[] ids = aids.split(",");
            for (String id : ids) {
                adminInfoService.deleteAdminInfo(Integer.parseInt(id));
            }
            str = "{\"success\":\"true\",\"message\":\"删除成功！\"}";
        } catch (Exception e) {
            e.printStackTrace();
            str = "{\"success\":\"false\",\"message\":\"删除失败！\"}";
        }
        return str;
    }

    @RequestMapping(value = "/addAdmin")
    @ResponseBody
    public String addAdmin(AdminInfo adminInfo){
        try {
            adminInfoService.addAdmin(adminInfo);
            return "{\"success\":\"true\",\"message\":\"添加成功\"}";
        } catch (Exception e) {
            return "{\"success\":\"false\",\"message\":\"添加失败\"}";
        }
    }

    @RequestMapping(value = "/updateAdminInfo")
    @ResponseBody
    public String  updateAdminInfo(AdminInfo adminInfo){
        try {
            adminInfoService.updateAdminInfo(adminInfo);
            return "{\"success\":\"true\",\"message\":\"修改成功\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"success\":\"false\",\"message\":\"修改失败\"}";
        }
    }


}

