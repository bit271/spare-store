package com.bit.partsstore.DTO;

public class ModelResponse {
    private Integer id;
    private String name;
    private Integer brandId;
    private String brandName;

    public ModelResponse(Integer id, String name, Integer brandId, String brandName) {
        this.id = id;
        this.name = name;
        this.brandId = brandId;
        this.brandName = brandName;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public String getBrandName() {
        return brandName;
    }
}
