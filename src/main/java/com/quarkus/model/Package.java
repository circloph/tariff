package com.quarkus.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "package")
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
    @Column(name="value")
    private Long value;

    @NotNull
    @Column(name="deleted", nullable = false)
    private Boolean deleted;

}
