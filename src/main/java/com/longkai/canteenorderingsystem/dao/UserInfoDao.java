package com.longkai.canteenorderingsystem.dao;



import com.longkai.canteenorderingsystem.dao.provider.UserInfoDynaSqlProvider;
import com.longkai.canteenorderingsystem.pojo.AdminInfo;
import com.longkai.canteenorderingsystem.pojo.UserInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository(value = "userInfoDao")
public interface UserInfoDao {
    @Select("select * from user_info where status = 1")
    List<UserInfo> getValidUsers();

    @Select("select * from user_info where id = #{id}")
    UserInfo getUserInfoById(int id);

    @Results({@Result(id = true,column = "id",property = "id"),
            @Result(column = "password",property = "password"),
            @Result(column = "sex",property = "sex"),
            @Result(column = "realName",property = "realName"),
            @Result(column = "address",property = "address"),
            @Result(column = "regDate",property = "regDate"),
            @Result(column = "status",property = "status"),
            @Result(column = "sid",property = "school",one = @One(select = "com.longkai.canteenorderingsystem.dao.SchoolDao.selectById",fetchType = FetchType.EAGER))})
    @SelectProvider(type = UserInfoDynaSqlProvider.class, method = "selectWithParam")
    List<UserInfo> selectByPage(Map<String, Object> params);


    @SelectProvider(type = UserInfoDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    @Update("update user_info set status = #{flag} where id in (${ids})")
    void updateStatus(@Param("ids") String ids, @Param("flag") int flag);

    @Select("select * from user_info where userName = #{userName} and password = #{password}")
    UserInfo selectByNameAndPassword(UserInfo userInfo);

}

