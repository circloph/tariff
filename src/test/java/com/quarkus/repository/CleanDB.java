package com.quarkus.repository;

import io.quarkus.hibernate.orm.panache.Panache;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class CleanDB {

    @Transactional
    public void clean() {
        Panache.getEntityManager().createNativeQuery("DELETE FROM public.packages").executeUpdate();
        Panache.getEntityManager().createNativeQuery("DELETE FROM public.tariffs").executeUpdate();
    }
}
