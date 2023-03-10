package com.quarkus.service;

import com.quarkus.dto.PackageDtoRequest;
import com.quarkus.dto.TariffDtoRequest;
import com.quarkus.dto.TariffDtoResponse;
import com.quarkus.exception.CustomValidationException;
import com.quarkus.model.QueryParams;
import com.quarkus.model.Tariff;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
@ApplicationScoped
public interface TariffService {

    List<TariffDtoResponse> getAllTariffs();

    TariffDtoResponse addTariff(TariffDtoRequest request) throws CustomValidationException;

    TariffDtoResponse updateTariff(Long id, TariffDtoRequest request) throws CustomValidationException;

    boolean deleteTariffById(Long id) throws CustomValidationException;

    TariffDtoResponse addPackageToTariff(Long id, PackageDtoRequest request) throws CustomValidationException;

    List<TariffDtoResponse> getTariffs(QueryParams queryParams);
}
