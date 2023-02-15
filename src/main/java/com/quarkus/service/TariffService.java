package com.quarkus.service;

import com.quarkus.dto.TariffDtoRequest;
import com.quarkus.dto.TariffDtoResponse;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
@ApplicationScoped
public interface TariffService {

    List<TariffDtoResponse> getAllTariffs();

    TariffDtoResponse addTariff(TariffDtoRequest request);

    TariffDtoResponse updateTariff(Long id, TariffDtoRequest request);

    boolean deleteTariffById(Long id);



}
