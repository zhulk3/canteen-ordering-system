package com.longkai.canteenorderingsystem.dao.provider;


import com.longkai.canteenorderingsystem.pojo.UserInfo;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;


public class UserInfoDynaSqlProvider {
    // 分页动态查询
    public String selectWithParam(final Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM("user_info");
                if (params.get("userInfo") != null) {
                    UserInfo userInfo = (UserInfo) params.get("userInfo");
                    if (userInfo.getUserName() != null && !userInfo.getUserName().equals("")) {
                        WHERE("  userName LIKE CONCAT ('%',#{userInfo.userName},'%') ");
                    }
                }
            }
        }.toString();
        if (params.get("pager") != null) {
            sql += " limit #{pager.firstLimitParam} , #{pager.perPageRows}  ";
        }
        return sql;
    }

    // 根据条件动态查询总记录数
    public String count(final Map<String, Object> params) {
        return new SQL() {
            {
                SELECT("count(*)");
                FROM("user_info");
                if (params.get("userInfo") != null) {
                    UserInfo userInfo = (UserInfo) params.get("userInfo");
                    if (userInfo.getUserName() != null && !userInfo.getUserName().equals("")) {
                        WHERE("  userName LIKE CONCAT ('%',#{userInfo.userName},'%') ");
                    }
                }
            }
        }.toString();
    }

}


