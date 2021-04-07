package com.longkai.canteenorderingsystem.dao;

import com.longkai.canteenorderingsystem.dao.provider.OrderInfoDynaSqlProvider;
import com.longkai.canteenorderingsystem.pojo.OrderDetail;
import com.longkai.canteenorderingsystem.pojo.OrderInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository(value = "orderInfoDao")
public interface OrderInfoDao {
    @Results({@Result(column = "uid", property = "ui", one = @One(select = "com.longkai.canteenorderingsystem.dao.UserInfoDao.getUserInfoById", fetchType = FetchType.EAGER))})
    @SelectProvider(type = OrderInfoDynaSqlProvider.class, method = "selectWithParam")
    List<OrderInfo> selectByPage(Map<String, Object> params);

    @SelectProvider(value = OrderInfoDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    @Insert("insert into order_info (uid,status,ordertime,orderprice)values(#{uid},#{status},#{ordertime},#{orderprice})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int saveOrderInfo(OrderInfo orderInfo);

    @Insert("insert into order_detail (oid,pid,num)values(#{oid},#{pid},#{num})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int saveOrderDetail(OrderDetail orderDetail);

    @Results({@Result(column = "uid", property = "ui", one = @One(select = "com.longkai.canteenorderingsystem.dao.UserInfoDao.getUserInfoById", fetchType = FetchType.EAGER))})
    @Select("select * from order_info where id = #{id}")
    OrderInfo getOrderInfoById(int id);

    @Results({@Result(column = "pid", property = "pi", one = @One(select = "com.longkai.canteenorderingsystem.dao.ProductInfoDao.getProductInfoById", fetchType = FetchType.EAGER))})
    @Select("select * from order_detail where oid = #{oid}")
    List<OrderDetail> getOrderDetailByOid(int oid);

    @Delete("delete from order_info where id = #{id}")
    int deleteOrderInfo(int id);

    @Delete("delete from order_detail where oid = #{id}")
    int deleteOrderDetail(int id);

    @Select("select * from order_info where uid = #{uid}")
    List<OrderInfo> selectOrderInfoByUid(int uid);

}

