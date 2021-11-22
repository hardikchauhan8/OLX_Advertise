package com.olx.service;

import com.olx.dto.Category;
import com.olx.dto.Status;

import java.util.List;

interface MasterDataDelegate {
    List<Category> getCategories();

    Category getCategoryById(int categoryId);

    List<Status> getStatus();

    Status getStatusById(int statusId);
}
