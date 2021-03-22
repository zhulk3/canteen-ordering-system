package com.longkai.canteenorderingsystem.dao;


import com.longkai.canteenorderingsystem.pojo.AdminInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface AdminInfoDao {

    @Select("select * from admin_info where name = #{name} and pwd = #{pwd}")
    AdminInfo selectByNameAndPwd(AdminInfo adminInfo);

    @Select("select * from user_info where id = #{id}")
    @Results({@Result(id = true, column = "id", property = "id"),
            @Result(column = "pwd", property = "pwd"),
            @Result(column = "name", property = "name"),
            @Result(column = "office",property = "office"),
            @Result(column = "beginTime",property = "beginTime"),
            @Result(column = "endTime",property = "endTime"),
            @Result(column = "telephone",property = "telephone"),
            @Result(column = "email",property = "email"),
            @Result(column = "id", property = "fs", many = @Many(select = "com.longkai.canteenorderingsystem.dao.FunctionsDao.selectByAdminId", fetchType = FetchType.EAGER))
    })
    AdminInfo selectById(int id);

    @Update("update admin_info set pwd = #{pwd} where id = #{id}")
    int modifyPwd(AdminInfo adminInfo);

    @Select("select * from admin_info")
    List<AdminInfo> selectAll();

    @Delete("delete from admin_info where id = #{id}")
    int deleteAdminInfo(int id);

    @Update("update admin_info set name=#{name},pwd=#{pwd},office=#{office},beginTime=#{beginTime},endTime=#{endTime},telephone=#{telephone},email=#{email} where id = #{id}")
    int updateAdminInfo(AdminInfo adminInfo);

    @Insert("insert into admin_info (name,pwd,office,beginTime,endTime,telephone,email) values(#{name},#{pwd},#{office},#{beginTime},#{endTime},#{telephone},#{email})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int addAdmin(AdminInfo ai);
}

