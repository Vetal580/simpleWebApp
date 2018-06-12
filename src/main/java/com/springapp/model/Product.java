package com.springapp.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

public class Product {
    private int id;
    private String name;
    private String price;
    private String category;
    private String description;
    private String shortDescription;
    private String image;
    private int inStock;
    private List<String> imagesList;
    private CommonsMultipartFile[] files;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public int getInStock() {
        return inStock;
    }
    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public List<String> getImagesList() {
        return imagesList;
    }
    public void setImagesList(List<String> imagesList) {
        this.imagesList = imagesList;
    }

    public String getShortDescription() {
        return shortDescription;
    }
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public CommonsMultipartFile[] getFiles() {
        return files;
    }
    public void setFiles(CommonsMultipartFile[] files) {
        this.files = files;
    }
}
