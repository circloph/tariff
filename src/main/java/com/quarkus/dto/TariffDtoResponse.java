package com.quarkus.dto;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class TariffDtoResponse {

    private Long id;
    private Date dateCreated;
    private String name;
    private Boolean archived;
    private Boolean deleted;


    public TariffDtoResponse(Long id, Date dateCreated, String name, Boolean archived, Boolean deleted) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.name = name;
        this.archived = archived;
        this.deleted = deleted;
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

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
