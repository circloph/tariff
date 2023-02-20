package com.quarkus.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tariffs", uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "archived" }) })
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Date dateCreated;

    @NotNull
    @Size(max = 128)
    @Column(name = "name")
    private String name;

    @Column(name="archived")
    @ColumnDefault(value = "false")
    private Boolean archived;

    @Column(name="deleted")
    @ColumnDefault(value = "false")
    private Boolean deleted;

    @OneToMany(mappedBy = "tariff", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Package> packages;

    public Tariff() {
    }

    public Tariff(String name, Boolean archived, Boolean deleted) {
        this.name = name;
        this.archived = archived;
        this.deleted = deleted;
    }

    public Tariff(String name, Boolean archived, Boolean deleted, List<Package> packages) {
        this.name = name;
        this.archived = archived;
        this.deleted = deleted;
        this.packages = packages;
    }

    public Tariff(Long id, Date dateCreated, String name, Boolean archived, Boolean deleted) {
        this.id = id;
        this.dateCreated = dateCreated;
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
