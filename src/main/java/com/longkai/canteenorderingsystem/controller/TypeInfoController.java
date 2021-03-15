package com.longkai.canteenorderingsystem.controller;

import com.longkai.canteenorderingsystem.pojo.Type;
import com.longkai.canteenorderingsystem.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/type")
public class TypeInfoController {

    @Autowired
    private TypeService typeService;

    @RequestMapping(value = "/getType/{flag}")
    @ResponseBody
    public List<Type> getType(@PathVariable("flag") Integer flag) {
        List<Type> typeList = typeService.getAll();
        if (flag == 1) {
            Type t = new Type();
            t.setId(0);
            t.setName("请选择");
            typeList.add(t);
        }
        return typeList;
    }

    @RequestMapping(value = "/addType", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addType(Type type) {
        try {
            typeService.addType(type);
            return "{\"success\":\"true\",\"message\":\"添加成功\"}";
        } catch (Exception e) {
            return "{\"success\":\"false\",\"message\":\"添加失败\"}";
        }
    }

    @RequestMapping(value = "/updateType", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateType(Type type) {
        try {
            typeService.updateType(type);
            return "{\"success\":\"true\",\"message\":\"修改成功\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"success\":\"false\",\"message\":\"修改失败\"}";
        }
    }
}

