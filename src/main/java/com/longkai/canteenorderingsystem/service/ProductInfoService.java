package com.longkai.canteenorderingsystem.service;

import com.longkai.canteenorderingsystem.pojo.Pager;
import com.longkai.canteenorderingsystem.pojo.ProductInfo;

import java.util.List;
import java.util.Map;

public interface ProductInfoService {
    List<ProductInfo> findProductInfo(ProductInfo productInfo, Pager pager);

    Integer count(Map<String,Object>params);

    void addProductInfo(ProductInfo productInfo);

    void modifyProductInfo(ProductInfo productInfo);

    void modifyStatus(String ids,int flag);

    List<ProductInfo> getOnSaleProduct();

    ProductInfo getProductInfoById(int id);
}

