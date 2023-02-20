package com.quarkus.repository;

import com.quarkus.model.Package;
import com.quarkus.model.PackageCategory;
import com.quarkus.model.QueryParams;
import com.quarkus.model.Tariff;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@QuarkusTest
@Transactional
public class TariffRepositoryTest {

    @Inject
    private TariffRepository tarriffRepository;
    @Inject
    private PackageRepository packageRepository;

    @BeforeEach
    void uploadData() {
        CleanDB cleanDB = new CleanDB();
        cleanDB.clean();
        Tariff tariff1 = new Tariff("big tariff", false, false);
        tariff1.setPackages(new ArrayList<>());
        Package p1 = new Package("internet", PackageCategory.INTERNET, -1L, false);
        Package p2 = new Package("calls", PackageCategory.CALLS, -1L, false);
        p1.setTariff(tariff1);
        p2.setTariff(tariff1);

        Tariff tariff2 = new Tariff("small tariff", false, false);
        Package p3 = new Package("calls", PackageCategory.CALLS, -1L, false);
        p3.setTariff(tariff2);
        tariff2.setPackages(new ArrayList<>());

        Tariff tariff3 = new Tariff( "large tariff", false, false);
        Package p4 = new Package("calls", PackageCategory.SMS, 23L, false);
        p4.setTariff(tariff3);
        tariff3.setPackages(new ArrayList<>());

        tarriffRepository.persist(tariff1);
        packageRepository.persist(p1);
        packageRepository.persist(p2);

        tarriffRepository.persist(tariff2);
        packageRepository.persist(p3);

        tarriffRepository.persist(tariff3);
        packageRepository.persist(p4);

    }

    @Test
    void getTariffsWithoutParams() {
        List<Tariff> tariffs = tarriffRepository.getTariffs(new QueryParams());
        Assertions.assertEquals(3, tariffs.size());
    }

    @Test
    void getTariffsWithParamWithUnLimitedInternet() {
        List<Tariff> tariffs = tarriffRepository.getTariffs(new QueryParams(null, "INTERNET", null));
        Assertions.assertEquals(1, tariffs.size());
    }

    @Test
    void getTariffsWithParamWithUnLimitedCalls() {
        List<Tariff> tariffs = tarriffRepository.getTariffs(new QueryParams(null, "CALLS", null));
        Assertions.assertEquals(2, tariffs.size());
    }

    @Test
    void getTariffsWithParamsUnLimitedCallsAndName() {
        List<Tariff> tariffs = tarriffRepository.getTariffs(new QueryParams("tar", "CALLS", null));
        Assertions.assertEquals(2, tariffs.size());
    }

    @Test
    void getTariffsWithParamsUnLimitedCallsAndNameAndStatusArchived() {
        List<Tariff> tariffs = tarriffRepository.getTariffs(new QueryParams("tar", "INTERNET", false));
        Assertions.assertEquals(1, tariffs.size());
    }

    @Test
    void getTariffsWithParamName() {
        List<Tariff> tariffs = tarriffRepository.getTariffs(new QueryParams("qwe", null, null));
        Assertions.assertEquals(0, tariffs.size());
    }

}
