package com.quarkus.repository;

import com.quarkus.model.Package;
import com.quarkus.model.Tariff;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PackageRepository  implements PanacheRepository<Package> {
}
