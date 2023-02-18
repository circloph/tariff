package com.quarkus.model;

public class QueryParams {

    private String name;
    private String category;
    private Boolean isArchive;

    public QueryParams() {
    }

    public QueryParams(String name, String category, Boolean isArchive) {
        this.name = name;
        this.category = category;
        this.isArchive = isArchive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getArchive() {
        return isArchive;
    }

    public void setArchive(Boolean archive) {
        isArchive = archive;
    }
}