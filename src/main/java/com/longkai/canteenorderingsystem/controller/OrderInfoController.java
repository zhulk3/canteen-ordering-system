package com.longkai.canteenorderingsystem.controller;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.*;
import com.longkai.canteenorderingsystem.pojo.OrderDetail;
import com.longkai.canteenorderingsystem.pojo.OrderInfo;
import com.longkai.canteenorderingsystem.pojo.Pager;
import com.longkai.canteenorderingsystem.service.OrderInfoService;
import com.longkai.canteenorderingsystem.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orderinfo")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private UserInfoService userInfoService;
    public static final ObjectMapper mapper = new ObjectMapper();

    @ResponseBody
    @RequestMapping(value = "/commitOrder")
    public String commitOrder(String inserted, String orderinfo)
            throws JsonParseException, JsonMappingException, IOException {
        try {
            // 创建ObjectMapper对象,实现JavaBean和JSON的转换
            ObjectMapper mapper = new ObjectMapper();
            // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            // 将json字符串orderinfo转换成JavaBean对象（订单信息）
            OrderInfo oi = mapper.readValue(orderinfo, OrderInfo[].class)[0];
            // 保存订单信息
            orderInfoService.addOrderInfo(oi);
            // 将json字符串转换成List<OrderDetail>集合（订单明细信息）
            JavaType javaType = getCollectionType(ArrayList.class, OrderDetail.class);
            List<OrderDetail> odList = (List<OrderDetail>) mapper.readValue(inserted, javaType);
            // 给订单明细对象的其他属性赋值
            for (OrderDetail od : odList) {
                od.setOid(oi.getId());
                // 保存订单明细
                orderInfoService.addOrderDetail(od);
            }
            return "success";
        } catch (Exception e) {

            return "failure";
        }
    }

    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> list(Integer page, Integer rows, OrderInfo orderInfo) {
        // 初始化一个分页类对象pager
        Pager pager = new Pager();
        pager.setCurPage(page);
        pager.setPerPageRows(rows);
        // 创建对象params，用于封装查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderInfo", orderInfo);
        // 获取满足条件的订单总数
        int totalCount = orderInfoService.count(params);
        // 获取满足条件的订单列表
        List<OrderInfo> orderinfos = orderInfoService.findOrderInfo(orderInfo, pager);
        // 创建result对象，保存查询结果数据
        Map<String, Object> result = new HashMap<String, Object>(2);
        result.put("total", totalCount);
        result.put("rows", orderinfos);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteOrder", produces = "text/html;charset=UTF-8")
    public String deleteOrder(String oids) {
        String str = "";
        try {
            oids = oids.substring(0, oids.length() - 1);
            String[] ids = oids.split(",");
            for (String id : ids) {
                orderInfoService.deleteOrder(Integer.parseInt(id));
            }
            str = "{\"success\":\"true\",\"message\":\"删除成功！\"}";
        } catch (Exception e) {
            str = "{\"success\":\"false\",\"message\":\"删除失败！\"}";
        }
        return str;
    }

    @RequestMapping("/getOrderInfo")
    public String getOrderInfo(@RequestParam("oid") String oid, Model model) throws Exception {
        try {
            OrderInfo oi = orderInfoService.getOrderInfoById(Integer.parseInt(oid));
            model.addAttribute("oi", oi);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/orderdetail.jsp";
    }

    @RequestMapping("/getOrderDetails")
    @ResponseBody
    public List<OrderDetail> getOrderDetails(String oid) {
        List<OrderDetail> ods = orderInfoService.getOrderDetailByOid(Integer.parseInt(oid));
        for (OrderDetail od : ods) {
            // od.setPid(od.getPi().getId());
            od.setPrice(od.getPi().getPrice());
            od.setTotalprice(od.getPi().getPrice() * od.getNum());
        }
        return ods;
    }
}

