package com.olx.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ADVERTISES")
public class AdvertiseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "ad_title")
    private String title;

    @Column(name = "ad_price")
    private double price;

    @Column(name = "ad_category_id")
    private int categoryId;

    @Column(name = "ad_description")
    private String description;

    @Column(name = "ad_username")
    private String username;

    @Column(name = "ad_created_date")
    private LocalDate createdDate;

    @Column(name = "ad_modified_date")
    private LocalDate modifiedDate;

    @Column(name = "ad_statusId")
    private int statusId;

    public AdvertiseEntity() {

    }

    public AdvertiseEntity(int id, String title, double price, int categoryId, String description, String username, LocalDate createdDate, LocalDate modifiedDate, int statusId) {
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

    public AdvertiseEntity(String title, double price, int categoryId, String description, String username, LocalDate createdDate, LocalDate modifiedDate, int statusId) {
        this.title = title;
        this.price = price;
        this.categoryId = categoryId;
        this.description = description;
        this.username = username;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.statusId = statusId;
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

    @Override
    public String toString() {
        return "Advertise [id=" + id
                + ", title=" + title
                + ", price=" + price
                + ", categoryId=" + categoryId
                + ", description=" + description
                + ", username=" + username
                + ", createdDate=" + createdDate
                + ", modifiedDate=" + modifiedDate
                + ", statusId=" + statusId + "]";
    }
}
