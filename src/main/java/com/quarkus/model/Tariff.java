package com.quarkus.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tariffs")
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Date dateCreated;

    @NotNull
    @Size(max = 128)
    @Column(unique = true)
    private String name;

    @NotNull
    @Column(name="archived", nullable = false)
    private Boolean archived;

    @NotNull
    @Column(name="deleted", nullable = false)
    private Boolean deleted;

    @OneToMany(mappedBy = "tariff", fetch = FetchType.EAGER)
    private List<Package> packages;

    public Tariff() {
    }

    public Tariff(String name, Boolean archived, Boolean deleted) {
        this.name = name;
        this.archived = archived;
        this.deleted = deleted;
    }

    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
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
