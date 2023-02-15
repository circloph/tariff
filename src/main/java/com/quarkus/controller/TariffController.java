package com.quarkus.controller;

import com.quarkus.dto.TariffDtoRequest;
import com.quarkus.dto.TariffDtoResponse;
import com.quarkus.service.TariffService;
import org.jboss.resteasy.annotations.Body;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
    public TariffDtoResponse addTariff(TariffDtoRequest request) {
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
}
