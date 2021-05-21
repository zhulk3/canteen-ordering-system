package com.longkai.canteenorderingsystem.controller;


import com.longkai.canteenorderingsystem.pojo.Pager;
import com.longkai.canteenorderingsystem.pojo.ProductInfo;
import com.longkai.canteenorderingsystem.service.ProductInfoService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/productinfo")
public class ProductInfoController {

    @Autowired
    private ProductInfoService productInfoService;

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(Integer page, Integer rows, ProductInfo productInfo) {
        Pager pager = new Pager();
        pager.setCurPage(page);
        pager.setPerPageRows(rows);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("productInfo", productInfo);
        int totalCount = productInfoService.count(map);
        List<ProductInfo> productInfoList = productInfoService.findProductInfo(productInfo, pager);
        Map<String, Object> result = new HashMap<String, Object>(2);
        result.put("total", totalCount);
        result.put("rows", productInfoList);
        return result;
    }

    @RequestMapping(value = "/imageDownload", method = RequestMethod.GET, produces = "image/png")
    public ResponseEntity<byte[]> fileDownload(@RequestParam("fileName") String fileName, HttpServletRequest httpServletRequest, Model model) throws IOException {
        System.out.println(fileName + "fileName");
        String path = httpServletRequest.getServletContext().getRealPath("/product_images/");
        File file = new File(path + File.separator + fileName);
        HttpHeaders httpHeaders = new HttpHeaders();
        String downloadFileName = new String(fileName.getBytes(StandardCharsets.UTF_8), "UTF-8");
        httpHeaders.setContentDispositionFormData("attachment", downloadFileName);
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            ResponseEntity ans = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), httpHeaders, HttpStatus.CREATED);
            return ans;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/all")
    public List<ProductInfo> getAll() {
        return productInfoService.getAll();
    }

    @RequestMapping(value = "/addProduct", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addProduct(ProductInfo pi, @RequestParam(value = "file", required = false) MultipartFile file,
                             HttpServletRequest request, ModelMap model) {
        // 服务器端upload文件夹物理路径
        String path = request.getSession().getServletContext().getRealPath("product_images");
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 实例化一个File对象，表示目标文件（含物理路径）
        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
            // 将上传文件写到服务器上指定的文件
            file.transferTo(targetFile);
            pi.setPic(fileName);
            productInfoService.addProductInfo(pi);
            return "{\"success\":\"true\",\"message\":\"商品添加成功\"}";
        } catch (Exception e) {
            return "{\"success\":\"false\",\"message\":\"商品添加失败\"}";
        }
    }

    // 修改商品
    @RequestMapping(value = "/updateProduct", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateProduct(ProductInfo pi, @RequestParam(value = "file", required = false) MultipartFile file,
                                HttpServletRequest request, ModelMap model) {
        // 服务器端upload文件夹物理路径
        String path = request.getSession().getServletContext().getRealPath("product_images");
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 实例化一个File对象，表示目标文件（含物理路径）
        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
            // 将上传文件写到服务器上指定的文件
            file.transferTo(targetFile);
            pi.setPic(fileName);
            productInfoService.modifyProductInfo(pi);
            return "{\"success\":\"true\",\"message\":\"商品修改成功\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"success\":\"false\",\"message\":\"商品修改失败\"}";
        }
    }

    // 商品下架(删除商品)
    @RequestMapping(value = "/deleteProduct", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteProduct(@RequestParam(value = "id") String id, @RequestParam(value = "flag") String flag) {
        String str = "";
        try {
            productInfoService.modifyStatus(id.substring(0, id.length() - 1), Integer.parseInt(flag));
            str = "{\"success\":\"true\",\"message\":\"删除成功\"}";
        } catch (Exception e) {
            str = "{\"success\":\"false\",\"message\":\"删除失败\"}";
        }
        return str;
    }

    @ResponseBody
    @RequestMapping("/getOnSaleProduct")
    public List<ProductInfo> getOnSaleProductInfo() {
        List<ProductInfo> productInfoList = productInfoService.getOnSaleProduct();
        return productInfoList;
    }

    @ResponseBody
    @RequestMapping("/getPriceById")
    public String getPriceById(@RequestParam(value = "pid") String pid) {
        if (pid != null && !"".equals(pid)) {
            int productId = Integer.parseInt(pid);
            ProductInfo productInfo = productInfoService.getProductInfoById(productId);
            return productInfo.getPrice() + "";
        } else {
            return "";
        }
    }
}

