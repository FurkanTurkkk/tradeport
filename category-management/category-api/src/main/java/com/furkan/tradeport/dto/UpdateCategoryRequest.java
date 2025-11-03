package com.furkan.tradeport.dto;

public class UpdateCategoryRequest {
    private String categoryName;
    private String parentId;

    public UpdateCategoryRequest() {
    }

    public UpdateCategoryRequest(String categoryName, String parentId) {
        this.categoryName = categoryName;
        this.parentId = parentId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getParentId() {
        return parentId;
    }
}
