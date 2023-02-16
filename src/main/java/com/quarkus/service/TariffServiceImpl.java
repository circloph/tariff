package com.quarkus.service;

import com.quarkus.dto.TariffDtoRequest;
import com.quarkus.dto.TariffDtoResponse;
import com.quarkus.exception.ErrorCode;
import com.quarkus.exception.CustomValidationException;
import com.quarkus.model.Tariff;
import com.quarkus.repository.TariffRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TariffServiceImpl implements TariffService {

    private TariffRepository repository;

    @Inject
    public TariffServiceImpl(TariffRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TariffDtoResponse> getAllTariffs() {
        List<TariffDtoResponse> tariffDtoResponses = new ArrayList<>();
        List<Tariff> tariffs = repository.findAll().list();
        for (Tariff tariff: tariffs) {
            tariffDtoResponses.add(new TariffDtoResponse(tariff.getId(), tariff.getDateCreated(),
                    tariff.getName(), tariff.getArchived(), tariff.getDeleted()));
        }
        return tariffDtoResponses;
    }

    @Override
    public TariffDtoResponse addTariff(TariffDtoRequest request) throws CustomValidationException {
        if (repository.findByName(request.getName()) != null) {
            throw new CustomValidationException(ErrorCode.NAME_TAKEN);
        }
        Tariff t = new Tariff(request.getName(), request.getArchived(), request.getDeleted());
        repository.persist(t);
        return new TariffDtoResponse(t.getId(), t.getDateCreated(), t.getName(), t.getArchived(), t.getDeleted());
    }

    @Override
    public TariffDtoResponse updateTariff(Long id, TariffDtoRequest request) {
        Tariff t = repository.findById(id);
        t.setName(request.getName());
        t.setArchived(request.getArchived());
        t.setDeleted(request.getDeleted());
        repository.persist(t);
        return new TariffDtoResponse(t.getId(), t.getDateCreated(), t.getName(), t.getArchived(), t.getDeleted());
    }

    @Override
    public boolean deleteTariffById(Long id) {
        return repository.deleteById(id);
    }
}
