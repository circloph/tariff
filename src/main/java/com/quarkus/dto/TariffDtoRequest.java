package com.quarkus.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TariffDtoRequest {

    @NotBlank(message="NAME_BLANK")
    private String name;
    private Boolean archived;
    private Boolean deleted;

    public TariffDtoRequest(String name, Boolean archived, Boolean deleted) {
        this.name = name;
        this.archived = archived;
        this.deleted = deleted;
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
