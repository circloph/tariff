package com.quarkus.service;

import com.quarkus.dto.PackageDtoRequest;
import com.quarkus.dto.PackageDtoResponse;
import com.quarkus.exception.CustomValidationException;
import com.quarkus.exception.ErrorCode;
import com.quarkus.model.Package;
import com.quarkus.repository.PackageRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PackageServiceImpl implements PackageService {

    @Inject
    private PackageRepository repository;

    @Override
    public List<PackageDtoResponse> getAllPackages() {
        List<PackageDtoResponse> packageDtoResponses = new ArrayList<>();
        List<Package> packages = repository.findAll().list();
        for (Package p : packages) {
            packageDtoResponses.add(new PackageDtoResponse(p.getId(), p.getDateCreated(), p.getName(), p.getCategory(), p.getMeaning(), p.getDeleted()));
        }
        return packageDtoResponses;
    }

    @Override
    public PackageDtoResponse addPackage(PackageDtoRequest request) {
        Package p = new Package(request.getName(), request.getCategory(), request.getMeaning(), request.getDeleted());
        repository.persist(p);
        return new PackageDtoResponse(p.getId(), p.getDateCreated(), p.getName(), p.getCategory(), p.getMeaning(), p.getDeleted());
    }

    @Override
    public PackageDtoResponse updatePackage(Long id, PackageDtoRequest request) {
        Package p = repository.findById(id);
        p.setName(request.getName());
        p.setCategory(request.getCategory());
        p.setMeaning(request.getMeaning());
        p.setDeleted(request.getDeleted());
        repository.persist(p);
        return new PackageDtoResponse(p.getId(), p.getDateCreated(), p.getName(), p.getCategory(), p.getMeaning(), p.getDeleted());
    }

    @Override
    public boolean deletePackageById(Long id) throws CustomValidationException {
        if (repository.findById(id) != null) {
            return repository.deleteById(id);
        }
        throw new CustomValidationException(ErrorCode.INVALID_PACKAGE_ID);
    }
}
