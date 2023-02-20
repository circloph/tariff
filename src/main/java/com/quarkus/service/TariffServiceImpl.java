package com.quarkus.service;

import com.quarkus.dto.PackageDtoRequest;
import com.quarkus.dto.PackageDtoResponse;
import com.quarkus.dto.TariffDtoRequest;
import com.quarkus.dto.TariffDtoResponse;
import com.quarkus.exception.ErrorCode;
import com.quarkus.exception.CustomValidationException;
import com.quarkus.model.Package;
import com.quarkus.model.QueryParams;
import com.quarkus.model.Tariff;
import com.quarkus.repository.PackageRepository;
import com.quarkus.repository.TariffRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TariffServiceImpl implements TariffService {

    private TariffRepository tariffRepository;
    private PackageRepository packageRepository;

    @Inject
    public TariffServiceImpl(TariffRepository tariffRepository, PackageRepository packageRepository) {
        this.tariffRepository = tariffRepository;
        this.packageRepository = packageRepository;
    }


    @Override
    public List<TariffDtoResponse> getAllTariffs() {
        List<TariffDtoResponse> tariffDtoResponses = new ArrayList<>();
        List<Tariff> tariffs = tariffRepository.findAll().list();
        for (Tariff tariff : tariffs) {
            List<PackageDtoResponse> responses = new ArrayList<>();
            for (Package p : tariff.getPackages()) {
                responses.add(new PackageDtoResponse(p.getId(), p.getDateCreated(), p.getName(), p.getCategory(), p.getMeaning(), p.getDeleted()));
            }
            tariffDtoResponses.add(new TariffDtoResponse(tariff.getId(), tariff.getDateCreated(),
                    tariff.getName(), tariff.getArchived(), tariff.getDeleted(), responses));
        }
        return tariffDtoResponses;
    }

    @Override
    public TariffDtoResponse addTariff(TariffDtoRequest request) throws CustomValidationException {
        Tariff tariff = tariffRepository.findByName(request.getName());
        if (tariff != null && tariff.getArchived().equals(request.getArchived())) {
            throw new CustomValidationException(ErrorCode.NAME_TAKEN);
        }
        Tariff t = new Tariff(request.getName(), request.getArchived(), request.getDeleted(), new ArrayList<>());
        tariffRepository.persist(t);
        return new TariffDtoResponse(t.getId(), t.getDateCreated(), t.getName(), t.getArchived(), t.getDeleted());
    }

    @Override
    public TariffDtoResponse updateTariff(Long id, TariffDtoRequest request) throws CustomValidationException {
        Tariff t = tariffRepository.findById(id);
        if (t != null) {
            t.setName(request.getName());
            t.setArchived(request.getArchived());
            t.setDeleted(request.getDeleted());
            tariffRepository.persist(t);
            List<PackageDtoResponse> responses = new ArrayList<>();
            if (t.getPackages() != null) {
                for (Package p : t.getPackages()) {
                    responses.add(new PackageDtoResponse(p.getId(), p.getDateCreated(), p.getName(), p.getCategory(), p.getMeaning(), p.getDeleted()));
                }
            }
            return new TariffDtoResponse(t.getId(), t.getDateCreated(), t.getName(), t.getArchived(), t.getDeleted(), responses);
        }
        throw new CustomValidationException(ErrorCode.INVALID_TARIFF_ID);
    }

    @Override
    public boolean deleteTariffById(Long id) throws CustomValidationException {
        if (tariffRepository.findById(id) != null) {
            return tariffRepository.deleteById(id);
        }
        throw new CustomValidationException(ErrorCode.INVALID_TARIFF_ID);
    }

    @Override
    public TariffDtoResponse addPackageToTariff(Long id, PackageDtoRequest request) throws CustomValidationException {
        Package p = new Package(request.getName(), request.getCategory(), request.getMeaning(), request.getDeleted());

        Tariff t = tariffRepository.findById(id);
        if (t == null) {
            throw new CustomValidationException(ErrorCode.INVALID_TARIFF_ID);
        }
        p.setTariff(t);
        packageRepository.persist(p);
        t.getPackages().add(p);

        List<PackageDtoResponse> packages = new ArrayList<>();
        tariffRepository.persist(t);

        Tariff fromDB = tariffRepository.findById(id);

        for (Package pack : fromDB.getPackages()) {
            packages.add(new PackageDtoResponse(pack.getId(), pack.getDateCreated(), pack.getName(), pack.getCategory(), pack.getMeaning(), pack.getDeleted()));
        }
        return new TariffDtoResponse(t.getId(), t.getDateCreated(), t.getName(), t.getArchived(), t.getDeleted(), packages);
    }

    @Override
    public List<TariffDtoResponse> getTariffs(QueryParams queryParams) {
        List<TariffDtoResponse> tariffDtoResponses = new ArrayList<>();
        List<Tariff> tariffs = tariffRepository.getTariffs(queryParams);
        for (Tariff tariff : tariffs) {
            List<PackageDtoResponse> responses = new ArrayList<>();
            for (Package p : tariff.getPackages()) {
                responses.add(new PackageDtoResponse(p.getId(), p.getDateCreated(), p.getName(), p.getCategory(), p.getMeaning(), p.getDeleted()));
            }
            tariffDtoResponses.add(new TariffDtoResponse(tariff.getId(), tariff.getDateCreated(),
                    tariff.getName(), tariff.getArchived(), tariff.getDeleted(), responses));
        }
        return tariffDtoResponses;
    }
}
