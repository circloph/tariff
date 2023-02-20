package com.quarkus.service;

import com.quarkus.dto.PackageDtoRequest;
import com.quarkus.dto.PackageDtoResponse;
import com.quarkus.exception.CustomValidationException;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public interface PackageService {

    List<PackageDtoResponse> getAllPackages();

    PackageDtoResponse addPackage(PackageDtoRequest request) throws CustomValidationException;

    PackageDtoResponse updatePackage(Long id, PackageDtoRequest request);

    boolean deletePackageById(Long id) throws CustomValidationException;

}
