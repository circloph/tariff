package com.quarkus.repository;

import com.quarkus.model.*;
import com.quarkus.model.QPackage;
import com.quarkus.model.QTariff;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class TariffRepository implements PanacheRepository<Tariff> {
    public Tariff findByName(String name) {
        return find("name", name).firstResult();
    }

    public List<Tariff> getTariffs(QueryParams queryParams) {
        QTariff qTariff = QTariff.tariff;
        QPackage qPackage = new QPackage("package");
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (queryParams.getArchive() != null) {
            booleanBuilder.and(qTariff.archived.eq(queryParams.getArchive()));
        }
        if (queryParams.getName() != null) {
            booleanBuilder.and(qTariff.name.like("%" + queryParams.getName() + "%"));
        }
        if (queryParams.getCategory() != null) {
            booleanBuilder.and(qPackage.category.eq(PackageCategory.valueOf(queryParams.getCategory())).and(qPackage.meaning.eq((long) -1)));
        }

        JPAQuery<?> query = new JPAQuery<Void>(Panache.getEntityManager());
        return (List<Tariff>) query.from(qTariff).innerJoin(qTariff.packages, qPackage).where(booleanBuilder).distinct().fetch();
    }

}
