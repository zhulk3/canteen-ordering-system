package com.longkai.canteenorderingsystem.dao;


import com.longkai.canteenorderingsystem.pojo.Type;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository(value = "typeDao")
public interface TypeDao {

    @Select("select * from type")
    List<Type> selectAll();

    @Select("select * from type where id = #{id}")
    Type selectById(int id);

    @Insert("insert into type (name)values(#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int add(Type type);

    @Update("update type set name=#{name} where id=#{id}")
    int update(Type type);

    @Delete("delete from type where id = #{id}")
    int deleteType(int id);
}

