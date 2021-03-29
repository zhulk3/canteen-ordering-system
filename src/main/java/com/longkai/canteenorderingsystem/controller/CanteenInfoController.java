package com.longkai.canteenorderingsystem.controller;

import com.longkai.canteenorderingsystem.pojo.Canteen;
import com.longkai.canteenorderingsystem.pojo.Type;
import com.longkai.canteenorderingsystem.service.CanteenInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/canteen")
public class CanteenInfoController {

    @Autowired
    private CanteenInfoService canteenInfoService;

    @RequestMapping(value = "/getCanteen")
    @ResponseBody
    public List<Canteen> getCanteen() {
        List<Canteen> canteensList = canteenInfoService.getAll();
        return canteensList;
    }

    @RequestMapping(value = "/getSelectCanteenList/{flag}")
    @ResponseBody
    public List<Canteen> getSelectCanteenList(@PathVariable("flag") Integer flag) {
        List<Canteen> canteenList = canteenInfoService.getAll();
        if (flag == 1) {
            Canteen t = new Canteen();
            t.setId(0);
            t.setName("请选择");
            canteenList.add(t);
        }
        return canteenList;
    }

    @RequestMapping(value = "/addCanteen", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addCanteen(Canteen canteen) {
        try {
            canteenInfoService.addCanteen(canteen);
            return "{\"success\":\"true\",\"message\":\"添加成功\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"success\":\"false\",\"message\":\"添加失败\"}";
        }
    }

    @RequestMapping(value = "/updateCanteen", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateCanteen(Canteen canteen) {
        try {
            canteenInfoService.updateCanteen(canteen);
            return "{\"success\":\"true\",\"message\":\"修改成功\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"success\":\"false\",\"message\":\"修改失败\"}";
        }
    }

    @RequestMapping(value = "/deleteCanteen",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteCanteen(String cids){
        String str = "";
        try {
            cids = cids.substring(0, cids.length() - 1);
            String[] ids = cids.split(",");
            for (String id : ids) {
                canteenInfoService.deleteCanteen(Integer.parseInt(id));
            }
            str = "{\"success\":\"true\",\"message\":\"删除成功！\"}";
        } catch (Exception e) {
            e.printStackTrace();
            str = "{\"success\":\"false\",\"message\":\"删除失败！\"}";
        }
        return str;
    }

}
