package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SupplierServiceImplTest {

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Test
    void testRetrieveAllSuppliers() {
        List<Supplier> suppliers = Collections.singletonList(new Supplier());
        when(supplierRepository.findAll()).thenReturn(suppliers);

        List<Supplier> retrievedSuppliers = supplierService.retrieveAllSuppliers();

        assertEquals(suppliers, retrievedSuppliers);
    }

    @Test
    void testAddSupplier() {
        Supplier supplier = new Supplier();

        when(supplierRepository.save(supplier)).thenReturn(supplier);

        Supplier addedSupplier = supplierService.addSupplier(supplier);

        assertEquals(supplier, addedSupplier);
    }

    @Test
    void testUpdateSupplier() {
        Supplier supplier = new Supplier();

        when(supplierRepository.save(supplier)).thenReturn(supplier);

        Supplier updatedSupplier = supplierService.updateSupplier(supplier);

        assertEquals(supplier, updatedSupplier);
    }

    @Test
    void testDeleteSupplier() {
        supplierService.deleteSupplier(1L);

        verify(supplierRepository).deleteById(1L);
    }

    @Test
    void testRetrieveSupplier() {
        Supplier supplier = new Supplier();
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));

        Supplier retrievedSupplier = supplierService.retrieveSupplier(1L);

        assertEquals(supplier, retrievedSupplier);
    }

    @Test
    void testRetrieveSupplier_NotFound() {
        when(supplierRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> supplierService.retrieveSupplier(1L));
    }
}
