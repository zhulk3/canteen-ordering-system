package com.longkai.canteenorderingsystem.dao;


import com.longkai.canteenorderingsystem.dao.provider.ProductInfoDynaSqlProvider;
import com.longkai.canteenorderingsystem.pojo.ProductInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository(value = "productInfoDao")
public interface ProductInfoDao {
    // 分页获取商品
    @Results({@Result(id = true, column = "id", property = "id"), @Result(column = "code", property = "code"),
            @Result(column = "name", property = "name"),
            @Result(column = "pic", property = "pic"), @Result(column = "num", property = "num"),
            @Result(column = "price", property = "price"), @Result(column = "intro", property = "intro"),
            @Result(column = "status", property = "status"),
            @Result(column = "tid", property = "type", one = @One(select = "com.longkai.canteenorderingsystem.dao.TypeDao.selectById", fetchType = FetchType.EAGER))})
    @SelectProvider(type = ProductInfoDynaSqlProvider.class, method = "selectWithParam")
    List<ProductInfo> selectByPager(Map<String, Object> params);

    // 根据条件查询商品总数
    @SelectProvider(type = ProductInfoDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    // 添加商品
    @Insert("insert into product_info(code,name,tid,pic,num,price,intro,status) "
            + "values(#{code},#{name},#{type.id},#{pic},#{num},#{price},#{intro},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(ProductInfo pi);

    // 修改商品
    @Update("update product_info set code=#{code},name=#{name},tid=#{type.id},"
            + "pic=#{pic},num=#{num},price=#{price},intro=#{intro}," + "status=#{status} where id=#{id}")
    void edit(ProductInfo pi);

    // 更新商品状态
    @Update("update product_info set status=#{flag} where id in (${ids})")
    void updateState(@Param("ids") String ids, @Param("flag") int flag);

    // 获取在售商品列表
    @Select("select * from product_info where status=1")
    List<ProductInfo> getOnSaleProduct();

    // 根据产品id获取商品对象
    @Select("select * from product_info where id=#{id}")
    ProductInfo getProductInfoById(int id);

}

