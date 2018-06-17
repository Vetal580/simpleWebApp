package com.springapp.service;

import com.springapp.dao.CategoryDao;
import com.springapp.dao.CategoryDaoImpl;
import com.springapp.dao.ProductDao;
import com.springapp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired ProductDao productDao;
    @Autowired CategoryService categoryService;
    @Autowired CategoryDao categoryDao;

    @Override
    public List<Product> getProductsFromCategory(String id){
        List<Product> products = productDao.getProductByCategory(id);
        products = setImageList(products);
        return products;
    }

    @Override
    public Product getProductById(String id){
        Product product = productDao.getProductById(id);
        product.setImagesList(Arrays.asList(product.getImage().split(",")));
        return product;
    }

    @Override
    public List<Product> getAllProducts(){
        List<Product> productList = productDao.getAllProducts();
        productList = setImageList(productList);
        return productList;
    }

    @Override
    public List<String> productImageUpload(Product product) throws IOException {
        List<String> fileNames = new ArrayList<>();
        CommonsMultipartFile[] commonsMultipartFiles = product.getFiles();

        for (CommonsMultipartFile multipartFile : commonsMultipartFiles){
            FileCopyUtils.copy(multipartFile.getBytes(), new File("D:\\springapp\\src\\main\\webapp\\resources\\images\\products\\"+File.separator +multipartFile.getOriginalFilename()));
            fileNames.add(multipartFile.getOriginalFilename());
        }
        return fileNames;
    }

    @Override
    public LinkedHashMap<String, String> getInStockList(){
        return (LinkedHashMap<String, String>) productDao.getInStockList();
    }

    @Override
    public void updateProduct(Product product){
        productDao.updateProduct(product);
    }

    @Override
    public void deleteProduct(String id){
        productDao.deleteProduct(id);
    }

    @Override
    public List<Product> searchProduct(String productName) {
        List<Product> productList = productDao.productSearch(productName);
        productList = setImageList(productList);
        return productList;
    }

    @Override
    public List<Product> setImageList(List<Product> products) {
        List<Product> productList = products;
        for (int i = 0; i < productList.size(); i++) {
            productList.get(i).setImagesList(Arrays.asList(productList.get(i).getImage().split(",")));
        }
        return productList;
    }
}
