package com.longkai.canteenorderingsystem.service.impl;

import com.longkai.canteenorderingsystem.dao.ProductInfoDao;
import com.longkai.canteenorderingsystem.pojo.Pager;
import com.longkai.canteenorderingsystem.pojo.ProductInfo;
import com.longkai.canteenorderingsystem.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("productInfoService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    ProductInfoDao productInfoDao;

    @Override
    public List<ProductInfo> findProductInfo(ProductInfo productInfo, Pager pager) {
        Map<String, Object> map = new HashMap<>();
        map.put("productInfo", productInfo);
        int recordCount = productInfoDao.count(map);
        pager.setRowCount(recordCount);
        if (recordCount > 0) {
            map.put("pager", pager);
        }
        return productInfoDao.selectByPager(map);
    }

    @Override
    public Integer count(Map<String, Object> params) {
        return productInfoDao.count(params);
    }

    @Override
    public void addProductInfo(ProductInfo productInfo) {
        productInfoDao.save(productInfo);
    }

    @Override
    public void modifyProductInfo(ProductInfo productInfo) {
        productInfoDao.edit(productInfo);
    }

    @Override
    public void modifyStatus(String ids, int flag) {
        productInfoDao.updateState(ids, flag);
    }

    @Override
    public List<ProductInfo> getOnSaleProduct() {
        return productInfoDao.getOnSaleProduct();
    }

    @Override
    public ProductInfo getProductInfoById(int id) {
        return productInfoDao.getProductInfoById(id);
    }
}

