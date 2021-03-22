package com.longkai.canteenorderingsystem.dao.provider;

import com.longkai.canteenorderingsystem.pojo.ProductInfo;
import org.apache.ibatis.jdbc.SQL;
import java.util.Map;


public class ProductInfoDynaSqlProvider {
    // 分页动态查询
    public String selectWithParam(final Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM("product_info");
                if (params.get("productInfo") != null) {
                    ProductInfo productInfo = (ProductInfo) params.get("productInfo");
                    if (productInfo.getCode() != null && !"".equals(productInfo.getCode())) {
                        WHERE(" code = #{productInfo.code} ");
                    }
                    if (productInfo.getName() != null && !productInfo.getName().equals("")) {
                        WHERE("  name LIKE CONCAT ('%',#{productInfo.name},'%') ");
                    }
                    if (productInfo.getType() != null && productInfo.getType().getId() > 0) {
                        WHERE(" tid = #{productInfo.type.id} ");
                    }
                    if (productInfo.getPriceFrom() > 0) {
                        WHERE(" price > #{productInfo.priceFrom} ");
                    }
                    if (productInfo.getPriceTo() > 0) {
                        WHERE(" price <= #{productInfo.priceTo} ");
                    }
                }
            }
        }.toString();
        if (params.get("pager") != null) {
            sql += " limit #{pager.firstLimitParam} , #{pager.perPageRows}  ";
        }
        return sql;
    }

    // 根据条件动态查询商品总记录数
    public String count(final Map<String, Object> params) {
        return new SQL() {
            {
                SELECT("count(*)");
                FROM("product_info");
                if (params.get("productInfo") != null) {
                    ProductInfo productInfo = (ProductInfo) params.get("productInfo");
                    if (productInfo.getCode() != null && !"".equals(productInfo.getCode())) {
                        WHERE(" code = #{productInfo.code} ");
                    }
                    if (productInfo.getName() != null && !productInfo.getName().equals("")) {
                        WHERE("  name LIKE CONCAT ('%',#{productInfo.name},'%') ");
                    }
                    if (productInfo.getType() != null && productInfo.getType().getId() > 0) {
                        WHERE(" tid = #{productInfo.type.id} ");
                    }
                    if (productInfo.getPriceFrom() > 0) {
                        WHERE(" price > #{productInfo.priceFrom} ");
                    }
                    if (productInfo.getPriceTo() > 0) {
                        WHERE(" price <= #{productInfo.priceTo} ");
                    }
                }
            }
        }.toString();
    }

}


