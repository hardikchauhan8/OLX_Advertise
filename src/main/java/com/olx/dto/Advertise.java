package com.olx.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.olx.utils.LocalDateDeserializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

@ApiModel(value = "Advertise Model")
public class Advertise {

    @ApiModelProperty(value = "Unique identifier of the advertise")
    private int id;

    @ApiModelProperty(value = "Title of the advertise")
    private String title;

    @ApiModelProperty(value = "Current price of the advertise")
    private double price;

    @JsonIgnoreProperties(allowGetters = true)
    @ApiModelProperty(value = "Category id of the advertise")
    private int categoryId;

    @ApiModelProperty(value = "Category of the advertise")
    private Category category;

    @ApiModelProperty(value = "Description of the advertise")
    private String description;

    @ApiModelProperty(value = "Username of a user who has posted the advertise")
    private String username;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @ApiModelProperty(value = "Date when the advertise is created")
    private LocalDate createdDate;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @ApiModelProperty(value = "Date when the advertise is last updated")
    private LocalDate modifiedDate;

    @JsonIgnoreProperties(allowGetters = true)
    @ApiModelProperty(value = "Current statusId of the advertise")
    private int statusId;

    @ApiModelProperty(value = "Current status of the advertise")
    private Status status;

    public Advertise() {

    }

    public Advertise(int id, String title, double price, int categoryId, String description, String username, LocalDate createdDate, LocalDate modifiedDate, int statusId) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.categoryId = categoryId;
        this.description = description;
        this.username = username;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.statusId = statusId;
    }

    public Advertise(int id, String title, double price, int categoryId, Category category, String description, String username, LocalDate createdDate, LocalDate modifiedDate, int statusId, Status status) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.categoryId = categoryId;
        this.category = category;
        this.description = description;
        this.username = username;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.statusId = statusId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String
    toString() {
        return "Advertise { " +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", categoryId=" + categoryId +
                ", category=" + "Category{ " + "id=" + id + ", category='" + category + "' }" +
                ", description='" + description + '\'' +
                ", username='" + username + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", statusId=" + statusId +
                ", status=" + "Status{ " + "id=" + id + ", status='" + status + "' }'" +
                " }";
    }
}