package com.bit.partsstore.DTO;

import org.springframework.web.multipart.MultipartFile;

public class PartRequest {
    private Integer carId;           // ID машины
    private Integer categoryId;      // ID категории
    private String  name;
    private Integer availableCount;
    private Integer price;
    // optionally (can be null)
    private String  catalogNum;
    private String  description;
    private MultipartFile image;

    public PartRequest() {
    }

    public PartRequest(Integer carId, Integer categoryId, String name, Integer availableCount, Integer price, String catalogNum, String description, MultipartFile image) {
        this.carId = carId;
        this.categoryId = categoryId;
        this.name = name;
        this.availableCount = availableCount;
        this.price = price;
        this.catalogNum = catalogNum;
        this.description = description;
        this.image = image;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(Integer availableCount) {
        this.availableCount = availableCount;
    }

    public String getCatalogNum() {
        return catalogNum;
    }

    public void setCatalogNum(String catalogNum) {
        this.catalogNum = catalogNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
