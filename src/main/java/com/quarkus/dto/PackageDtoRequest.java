package com.quarkus.dto;

import com.quarkus.model.PackageCategory;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PackageDtoRequest {

    private String name;
    private PackageCategory category;
    private Long meaning;
    private Boolean deleted;

    public PackageDtoRequest(String name, PackageCategory category, Long meaning, Boolean deleted) {
        this.name = name;
        this.category = category;
        this.meaning = meaning;
        this.deleted = deleted;
    }

    public PackageDtoRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PackageCategory getCategory() {
        return category;
    }

    public void setCategory(PackageCategory category) {
        this.category = category;
    }

    public Long getMeaning() {
        return meaning;
    }

    public void setMeaning(Long meaning) {
        this.meaning = meaning;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
