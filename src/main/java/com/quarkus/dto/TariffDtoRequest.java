package com.quarkus.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@RegisterForReflection
public class TariffDtoRequest {

    @NotBlank(message="NAME_BLANK")
    @Size(max = 128, message = "TOO_LONG_NAME")
    @NotNull(message = "REQUIRED_FIELD")
    private String name;
    @NotNull(message = "REQUIRED_FIELD")
    private Boolean archived;
    @NotNull(message = "REQUIRED_FIELD")
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
