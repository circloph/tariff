package com.quarkus.service;

import com.quarkus.dto.PackageDtoRequest;
import com.quarkus.dto.PackageDtoResponse;
import com.quarkus.exception.CustomValidationException;
import com.quarkus.model.Package;
import com.quarkus.model.PackageCategory;
import com.quarkus.model.Tariff;
import com.quarkus.repository.PackageRepository;
import com.quarkus.repository.TariffRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.runtime.PanacheQueryImpl;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.inject.Inject;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@QuarkusTest
class PackageServiceImplTest {

    @Inject
    private PackageServiceImpl packageService;
    @InjectMock(returnsDeepMocks = true)
    private PackageRepository packageRepository;

    @Test
    void getAllPackages() {
        Package p1 = new Package(1L, new Date(), "internet", PackageCategory.INTERNET, -1L, false);
        Package p2 = new Package(2L, new Date(), "calls", PackageCategory.CALLS, -1L, false);
        Package p3 = new Package(3L, new Date(), "sms", PackageCategory.SMS, 20L, false);
        List<Package> packages = List.of(p1, p2, p3);
        Mockito.when(packageRepository.findAll().list()).thenReturn(packages);

        List<PackageDtoResponse> response = packageService.getAllPackages();

        assertEquals(3, response.size());
    }

    @Test
    void addPackage() {
        PackageDtoRequest packageDtoRequest = new PackageDtoRequest("internetik", PackageCategory.INTERNET, -1L, false);
        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            ((Package) args[0]).setId(1L);
            ((Package) args[0]).setDateCreated(new Date());
            return null;
        }).when(packageRepository).persist(Mockito.any(Package.class));

        PackageDtoResponse p = packageService.addPackage(packageDtoRequest);

        assertNotNull(p.getId());
        assertEquals("internetik", p.getName());
        assertEquals("INTERNET", p.getCategory().name());
        assertEquals(-1, p.getMeaning());
        assertEquals(false, p.getDeleted());
    }

    @Test
    void updatePackage() {
        PackageDtoRequest packageDtoRequest = new PackageDtoRequest("internetik", PackageCategory.INTERNET, -1L, false);
        Package p = new Package(1L, new Date(), "sms", PackageCategory.SMS, 23L, false);
        when(packageRepository.findById(any(Long.class))).thenReturn(p);
        doNothing().when(packageRepository).persist(Mockito.any(Package.class));

        PackageDtoResponse packageDtoResponse = packageService.updatePackage(1L,packageDtoRequest);

        assertNotNull(packageDtoResponse.getId());
        assertEquals("internetik", packageDtoResponse.getName());
        assertEquals("INTERNET", packageDtoResponse.getCategory().name());
        assertEquals(-1, packageDtoResponse.getMeaning());
        assertEquals(false, packageDtoResponse.getDeleted());
    }

    @Test
    void deletePackageById() throws CustomValidationException {
        Package p = new Package(1L, new Date(), "sms", PackageCategory.SMS, 23L, false);
        Mockito.when(packageRepository.findById(Mockito.anyLong())).thenReturn(p);
        Mockito.when(packageRepository.deleteById(Mockito.anyLong())).thenReturn(true);

        Boolean isDeleted = packageService.deletePackageById(1L);

        Assertions.assertTrue(isDeleted);
    }

    @Test
    void deletePackageById_shouldThrowException() {
        Package p = new Package(1L, new Date(), "sms", PackageCategory.SMS, 23L, false);
        Mockito.when(packageRepository.findById(Mockito.anyLong())).thenReturn(null);

        CustomValidationException exception = assertThrows(CustomValidationException.class, () -> packageService.deletePackageById(1L));
        assertEquals("INVALID_PACKAGE_ID", exception.getError().getErrorCode());
    }
}