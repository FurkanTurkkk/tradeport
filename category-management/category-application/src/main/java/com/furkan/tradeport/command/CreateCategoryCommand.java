package com.furkan.tradeport.command;

public class CreateCategoryCommand {

    private String name;
    private String parentId;

    public CreateCategoryCommand(String name, String parentId) {
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
