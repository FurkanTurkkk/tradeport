package com.furkan.tradeport.dto;

public class CreateCategoryRequest {

    private String name;
    private String parentId;

    public CreateCategoryRequest() {
    }

    public CreateCategoryRequest(String name, String parentId) {
        this.name = name;
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public String getParentId() {
        return parentId;
    }

}
