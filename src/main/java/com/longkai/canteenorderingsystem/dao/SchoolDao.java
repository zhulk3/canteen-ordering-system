package com.longkai.canteenorderingsystem.dao;

import com.longkai.canteenorderingsystem.pojo.School;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository(value = "schoolDao")
public interface SchoolDao {
    @Select("select * from school where id = #{id}")
    School selectById(int id);
}
