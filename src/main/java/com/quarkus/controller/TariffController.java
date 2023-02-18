package com.quarkus.controller;

import com.quarkus.dto.PackageDtoRequest;
import com.quarkus.dto.TariffDtoRequest;
import com.quarkus.dto.TariffDtoResponse;
import com.quarkus.exception.CustomValidationException;
import com.quarkus.model.QueryParams;
import com.quarkus.model.Tariff;
import com.quarkus.repository.TariffRepository;
import com.quarkus.service.TariffService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import java.util.List;
import java.util.Optional;

@Path("/tariffs")
@Transactional
public class TariffController {
    @Inject
    private TariffService tariffService;

    @GET
    public List<TariffDtoResponse> getAllTariffs() {
        return tariffService.getAllTariffs();
    }

    @POST
    public TariffDtoResponse addTariff(@Valid TariffDtoRequest request) throws CustomValidationException {
        return tariffService.addTariff(request);
    }

    @PUT
    @Path("/{id}")
    public TariffDtoResponse updateTariff(@PathParam(value = "id") Long id, TariffDtoRequest request) {
        return tariffService.updateTariff(id, request);
    }

    @DELETE
    @Path("/{id}")
    public void deleteTariffById(@PathParam(value = "id") Long id) {
        tariffService.deleteTariffById(id);
    }

    @POST
    @Path("/{id}")
    public TariffDtoResponse addPackageToTariff(@PathParam("id") Long id, PackageDtoRequest request) {
        return tariffService.addPackageToTariff(id, request);
    }

    @GET
    @Path("/getTariffs")
    public List<TariffDtoResponse> getTariffs(@QueryParam("name") String name, @QueryParam("category") String category,
                                              @QueryParam("isArchive") Boolean isArchive) {
        return tariffService.getTariffs(new QueryParams(name, category, isArchive));
    }
}
