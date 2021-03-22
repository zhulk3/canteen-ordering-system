package com.longkai.canteenorderingsystem.dao;

import com.longkai.canteenorderingsystem.pojo.Canteen;
import com.longkai.canteenorderingsystem.pojo.Type;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("canteenDao")
public interface CanteenDao {

    @Select("select * from canteens where id = #{id}")
    Canteen selectCanteenById(int id);

    @Insert("insert into canteens (name,address,isServe)values(#{name},#{address},#{isServe})")
    @Options(useGeneratedKeys = true,keyColumn = "id")
    int addCanteen(Canteen canteen);

    @Update("update canteens set name=#{name},address=#{address},isServe=#{isServe} where id=#{id}")
    int update(Canteen canteen);

    @Select("select * from canteens")
    List<Canteen> selectAll();

    @Delete("delete from canteens where id = #{id}")
    int deleteCanteen(int id);

}
