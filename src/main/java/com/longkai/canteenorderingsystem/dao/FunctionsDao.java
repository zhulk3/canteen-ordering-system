package com.longkai.canteenorderingsystem.dao;


import com.longkai.canteenorderingsystem.pojo.Functions;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "functionsDao")
public interface FunctionsDao {

    @Select("select * from functions where id in(select fid from powers where aid = #{aid})")
    List<Functions> selectByAdminId(Integer aid);
}

