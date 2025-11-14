package com.bit.partsstore.models;

import jakarta.persistence.*;

@Entity
@Table(name = "parts")
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private String name;

    @Column(name = "available_count", nullable = false)
    private Integer availableCount;

    @Column(name = "catalog_num")
    private String catalogNum = null;

    private String description = null;

    @Column(name = "image_name")
    private String imageName = null;

    @Column(nullable = false)
    private Integer price;

    public Part() {}

    public Part(
            Car      car,
            Category category,
            String   name,
            Integer  availableCount,
            Integer  price, String   catalogNum,
            String   description,
            String   imageName
    ) {
        this.car =  car;
        this.category = category;
        this.name = name;
        this.availableCount = availableCount;
        this.catalogNum = catalogNum;
        this.description = description;
        this.imageName = imageName;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String image) {
        this.imageName = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
