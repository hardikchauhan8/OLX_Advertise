package com.olx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Category Model")
public class Category {

    @ApiModelProperty(value = "Unique identifier of category")
    private int id;

    @ApiModelProperty(value = "name of a category")
    private String category;

    public Category(){

    }
    public Category(int id, String category) {
        this.id = id;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", category='" + category + '\'' +
                '}';
    }
}
