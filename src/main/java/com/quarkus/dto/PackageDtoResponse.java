package com.quarkus.dto;

import com.quarkus.model.PackageCategory;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class PackageDtoResponse {

    private Long id;
    private Date dateCreated;
    private String name;
    private PackageCategory category;
    private Long meaning;
    private Boolean deleted;

    public PackageDtoResponse(Long id, Date dateCreated, String name, PackageCategory category, Long meaning, Boolean deleted) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.name = name;
        this.category = category;
        this.meaning = meaning;
        this.deleted = deleted;
    }

    public PackageDtoResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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
