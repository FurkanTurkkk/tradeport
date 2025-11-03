package com.furkan.tradeport.command;

public class UpdateCategoryCommand {

    private String categoryName;
    private String parentId;

    public UpdateCategoryCommand() {
    }

    public UpdateCategoryCommand(String categoryName, String parentId) {
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
