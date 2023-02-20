package com.quarkus.service;

import com.quarkus.dto.PackageDtoRequest;
import com.quarkus.dto.PackageDtoResponse;
import com.quarkus.dto.TariffDtoRequest;
import com.quarkus.dto.TariffDtoResponse;
import com.quarkus.exception.CustomValidationException;
import com.quarkus.exception.ErrorCode;
import com.quarkus.model.Package;
import com.quarkus.model.PackageCategory;
import com.quarkus.model.QueryParams;
import com.quarkus.model.Tariff;
import com.quarkus.repository.PackageRepository;
import com.quarkus.repository.TariffRepository;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;

@QuarkusTest
public class TariffServiceImplTest {

    @Inject
    private TariffServiceImpl tariffService;

    @InjectMock
    private TariffRepository tariffRepository;

    @InjectMock
    private PackageRepository packageRepository;

    @Test
    public void shouldAddTariff() throws CustomValidationException {
        TariffDtoRequest tariffDtoRequest = new TariffDtoRequest("Big tariff", false, false);
        Mockito.when(tariffRepository.findByName(Mockito.anyString())).thenReturn(null);
        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            ((Tariff) args[0]).setId(1L);
            ((Tariff) args[0]).setDateCreated(new Date());
            return null;
        }).when(tariffRepository).persist(Mockito.any(Tariff.class));
        TariffDtoResponse tariffDtoResponse = tariffService.addTariff(tariffDtoRequest);
        Assertions.assertEquals("Big tariff", tariffDtoResponse.getName());
        Assertions.assertEquals(false, tariffDtoResponse.getArchived());
    }

    @Test
    public void addTariff_shouldThrowException() throws CustomValidationException {
        TariffDtoRequest tariffDtoRequest = new TariffDtoRequest("Big tariff", false, false);
        Tariff t = new Tariff("Big tariff", false, false);
        t.setId(1L);
        t.setDateCreated(new Date());
        t.setPackages(null);
        Mockito.when(tariffRepository.findByName(Mockito.anyString())).thenReturn(t);
        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            ((Tariff) args[0]).setId(1L);
            ((Tariff) args[0]).setDateCreated(new Date());
            return null;
        }).when(tariffRepository).persist(Mockito.any(Tariff.class));

        CustomValidationException exception = assertThrows(CustomValidationException.class, () -> tariffService.addTariff(tariffDtoRequest));
        assertEquals("NAME_TAKEN", exception.getError().getErrorCode());
    }

    @Test
    public void shouldUpdateTariff() throws CustomValidationException {
        TariffDtoRequest tariffDtoRequest = new TariffDtoRequest("Large tariff", false, false);
        Tariff t = new Tariff("Big tariff", false, false);
        t.setId(1L);
        t.setDateCreated(new Date());
        t.setPackages(null);
        Mockito.when(tariffRepository.findById(Mockito.anyLong())).thenReturn(t);
        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            ((Tariff) args[0]).setId(1L);
            ((Tariff) args[0]).setDateCreated(new Date());
            return null;
        }).when(tariffRepository).persist(Mockito.any(Tariff.class));

        TariffDtoResponse response = tariffService.updateTariff(1L, tariffDtoRequest);

        Assertions.assertEquals("Large tariff", response.getName());
        Assertions.assertEquals(false, response.getArchived());
        Assertions.assertEquals(false, response.getDeleted());
    }

    @Test
    public void UpdateTariff_shouldThrowException() throws CustomValidationException {
        TariffDtoRequest tariffDtoRequest = new TariffDtoRequest("Big tariff", false, false);
        Tariff t = new Tariff("Big tariff", false, false);
        t.setId(1L);
        t.setDateCreated(new Date());
        t.setPackages(null);
        Mockito.when(tariffRepository.findByName(Mockito.anyString())).thenReturn(null);
        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            ((Tariff) args[0]).setId(1L);
            ((Tariff) args[0]).setDateCreated(new Date());
            return null;
        }).when(tariffRepository).persist(Mockito.any(Tariff.class));

        CustomValidationException exception = assertThrows(CustomValidationException.class, () -> tariffService.updateTariff(2L, tariffDtoRequest));
        assertEquals("INVALID_TARIFF_ID", exception.getError().getErrorCode());
    }

    @Test
    public void deleteByTariffId() throws CustomValidationException {
        Tariff t = new Tariff("Big tariff", false, false);
        t.setId(1L);
        t.setDateCreated(new Date());
        t.setPackages(null);
        Mockito.when(tariffRepository.findById(Mockito.anyLong())).thenReturn(t);
        Mockito.when(tariffRepository.deleteById(Mockito.anyLong())).thenReturn(true);

        Boolean isDeleted = tariffService.deleteTariffById(1L);

        Assertions.assertTrue(isDeleted);
    }

    @Test
    public void deleteByTariffId_shouldThrowException() throws CustomValidationException {
        Mockito.when(tariffRepository.findById(Mockito.anyLong())).thenReturn(null);
        Mockito.when(tariffRepository.deleteById(Mockito.anyLong())).thenReturn(true);

        CustomValidationException exception = assertThrows(CustomValidationException.class, () -> tariffService.deleteTariffById(1L));
        assertEquals("INVALID_TARIFF_ID", exception.getError().getErrorCode());
    }

    @Test
    public void addPackageToTariff() throws CustomValidationException {
        Date date = new Date();
        PackageDtoRequest packageDtoRequest = new PackageDtoRequest("internet plus", PackageCategory.INTERNET, -1L, false);
        Package p = new Package(1L, date, packageDtoRequest.getName(), packageDtoRequest.getCategory(),
                packageDtoRequest.getMeaning(), packageDtoRequest.getDeleted());

        Tariff t = new Tariff(1L, new Date(), "Big tariff", false, false);
        t.setPackages(new ArrayList<>());

        Mockito.when(tariffRepository.findById(Mockito.anyLong())).thenReturn(t);
        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            ((Package) args[0]).setId(1L);
            ((Package) args[0]).setDateCreated(date);
            return null;
        }).when(packageRepository).persist(Mockito.any(Package.class));
        doNothing().when(tariffRepository).persist(Mockito.any(Tariff.class));

        Mockito.when(tariffRepository.findById(Mockito.anyLong())).thenReturn(t);
        TariffDtoResponse response = tariffService.addPackageToTariff(1L, packageDtoRequest);

        assertEquals(1, response.getPackages().size());
        assertEquals("Big tariff", response.getName());
        assertEquals(false, response.getDeleted());
        assertEquals("internet plus", response.getPackages().get(0).getName());
    }

    @Test
    public void addPackageToTariff_shouldThrowException() throws CustomValidationException {
        PackageDtoRequest packageDtoRequest = new PackageDtoRequest("internet plus", PackageCategory.INTERNET, -1L, false);
        Mockito.when(tariffRepository.findById(Mockito.anyLong())).thenReturn(null);
        CustomValidationException exception = assertThrows(CustomValidationException.class, () -> tariffService.addPackageToTariff(1L, packageDtoRequest));
        assertEquals("INVALID_TARIFF_ID", exception.getError().getErrorCode());
    }

    @Test void getTariffs() {
        Tariff tariff1 = new Tariff(1L, new Date(), "big tariff", false, false);
        tariff1.setPackages(new ArrayList<>());
        Tariff tariff2 = new Tariff(1L, new Date(), "big tariff", false, false);
        tariff2.setPackages(new ArrayList<>());
        Package p1 = new Package(1L, new Date(), "internet", PackageCategory.INTERNET, -1L, false);
        Package p2 = new Package(2L, new Date(), "calls", PackageCategory.CALLS, -1L, false);
        p1.setTariff(tariff1);
        p2.setTariff(tariff1);
        tariff1.getPackages().add(p1);
        tariff1.getPackages().add(p2);
        List<Tariff> listTariffs = List.of(tariff1, tariff2);

        Mockito.when(tariffRepository.getTariffs(Mockito.any(QueryParams.class))).thenReturn(listTariffs);
        List<TariffDtoResponse> response = tariffService.getTariffs(new QueryParams());

        assertEquals(2, response.size());
    }

}
