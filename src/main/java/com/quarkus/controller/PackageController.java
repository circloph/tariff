package com.quarkus.controller;

import com.quarkus.dto.PackageDtoRequest;
import com.quarkus.dto.PackageDtoResponse;
import com.quarkus.exception.CustomValidationException;
import com.quarkus.service.PackageService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import java.util.List;

@Path("/packages")
@Transactional
public class PackageController {

    @Inject
    private PackageService packageService;

    @GET
    public List<PackageDtoResponse> getAllPackages() {
        return packageService.getAllPackages();
    }

    @POST
    public PackageDtoResponse addPackage(@Valid PackageDtoRequest request) throws CustomValidationException {
        return packageService.addPackage(request);
    }

    @PUT
    @Path("/{id}")
    public PackageDtoResponse updatePackage(@PathParam(value = "id") Long id, PackageDtoRequest request) {
        return packageService.updatePackage(id, request);
    }

    @DELETE
    @Path("/{id}")
    public void deletePackageById(@PathParam(value = "id") Long id) throws CustomValidationException {
        packageService.deletePackageById(id);
    }

}
