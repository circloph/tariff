package com.quarkus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "packages")
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Date dateCreated;

    @NotNull
    @Size(max = 128)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name="category")
    private PackageCategory category;

    @NotNull
    @Column(name="meaning")
    private Long meaning;

    @NotNull
    @Column(name="deleted", nullable = false)
    private Boolean deleted;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="tariff_id", nullable = false)
    private Tariff tariff;

    public Package() {
    }

    public Package(String name, PackageCategory category, Long meaning, Boolean deleted) {
        this.name = name;
        this.category = category;
        this.meaning = meaning;
        this.deleted = deleted;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
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
