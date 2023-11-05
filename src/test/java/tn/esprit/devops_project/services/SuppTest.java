package tn.esprit.devops_project.services;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.SupplierServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;



public class SuppTest {

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Mock
    private SupplierRepository supplierRepository;

    @Before
    public void setup() {
        // You can set up your mock objects or initialize data here
        MockitoAnnotations.initMocks(this);
        supplierService = new SupplierServiceImpl(this.supplierRepository);
    }

    @Test
    public void testRetrieveAllSuppliers() {
        // Create a list of suppliers for testing
        List<Supplier> suppliers = new ArrayList<>();
        Supplier s = new Supplier ();
        s.setIdSupplier(10L);
        s.setLabel("Supplier");
        Supplier s1 = new Supplier ();
        s1.setIdSupplier(10L);
        s1.setLabel("Supplier 1");
        suppliers.add(s);
        suppliers.add(s1);


        when(supplierRepository.findAll()).thenReturn(suppliers);

        List<Supplier> result = supplierService.retrieveAllSuppliers();

        assertEquals(2, result.size());

        System.err.println("Test SUCCESS");
    }

//    @Test
//    public void testAddSupplier() {
//        // arrange
//        Supplier supplier = new Supplier ();
//        supplier.setIdSupplier(10L);
//        supplier.setLabel("New Supplier");
//
//        when(supplierService.addSupplier(any(Supplier.class))).thenReturn(supplier);
//
//        // Act
//        Supplier addedSupplier = supplierService.addSupplier(supplier);
//
//        // Assert
//        assertEquals("New Supplier", addedSupplier.getLabel());
//    }

//    @Test
//    public void testUpdateSupplier() {
//        Supplier supplier = new Supplier ();
//        supplier.setIdSupplier(10L);
//        supplier.setLabel("Updated Supplier");
//
//        when(supplierRepository.save(any(Supplier.class))).thenReturn(supplier);
//
//        Supplier updatedSupplier = supplierService.updateSupplier(supplier);
//
//        assertEquals("Updated Supplier", updatedSupplier.getLabel());
//    }

    @Test
    public void testDeleteSupplier() {
        Long supplierId = 1L;

        supplierService.deleteSupplier(supplierId);

        // Verify that the delete method was called with the specified supplierId
        Mockito.verify(supplierRepository).deleteById(supplierId);
    }

    @Test
    public void testRetrieveSupplier() {
        Long supplierId = 1L;
        Supplier supplier = new Supplier ();
        supplier.setIdSupplier(10L);
        supplier.setLabel("Supplier 1");

        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));

        Supplier retrievedSupplier = supplierService.retrieveSupplier(supplierId);

        assertEquals("Supplier 1", retrievedSupplier.getLabel());
    }
}
