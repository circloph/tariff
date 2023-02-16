package com.quarkus.repository;

import com.quarkus.model.Tariff;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TariffRepository implements PanacheRepository<Tariff> {
    public Tariff findByName(String name) {
        return find("name", name).firstResult();
    }
}
