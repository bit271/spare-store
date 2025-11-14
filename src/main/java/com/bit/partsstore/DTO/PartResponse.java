package com.bit.partsstore.DTO;

import org.springframework.web.multipart.MultipartFile;

public class PartResponse {
    private Integer id;
    private String  name;
    private Integer price;
    private Integer availableCount;
    private String  carName;
    private String  categoryName;
    // optionally (can be null)
    private String  catalogNum;
    private String  description;
    private String imageName;

    public PartResponse() {}

    public PartResponse(
            Integer id,
            String name,
            Integer price,
            Integer availableCount,
            String carName,
            String categoryName,
            String catalogNum,
            String description,
            String image
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.availableCount = availableCount;
        this.carName = carName;
        this.categoryName = categoryName;
        this.catalogNum = catalogNum;
        this.description = description;
        this.imageName = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(Integer availableCount) {
        this.availableCount = availableCount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
