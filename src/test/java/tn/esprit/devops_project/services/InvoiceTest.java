package tn.esprit.devops_project.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.repositories.InvoiceDetailRepository;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class InvoiceTest {

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private OperatorRepository operatorRepository;

    @Mock
    private InvoiceDetailRepository invoiceDetailRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @BeforeEach
    public void init() {
    }

    @Test
    public void testRetrieveAllInvoices() {
        // Mock data
        List<Invoice> mockInvoices = new ArrayList<>();
        mockInvoices.add(new Invoice());
        mockInvoices.add(new Invoice());

        // Mock the repository method
        when(invoiceRepository.findAll()).thenReturn(mockInvoices);

        // Call the service method
        List<Invoice> invoices = invoiceService.retrieveAllInvoices();

        // Verify that the repository method was called and the result matches the expected mock data
        verify(invoiceRepository).findAll();
        assertEquals(2, invoices.size());
    }

    @Test
    public void testCancelInvoice() {
        // Mock data
        Long invoiceId = 1L;
        Invoice mockInvoice = new Invoice();
        mockInvoice.setIdInvoice(invoiceId);

        // Mock the repository methods
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(mockInvoice));

        // Call the service method
        invoiceService.cancelInvoice(invoiceId);

        // Verify that the repository methods were called and the invoice is marked as archived
        verify(invoiceRepository).findById(invoiceId);
        verify(invoiceRepository).save(mockInvoice);
        assertTrue(mockInvoice.getArchived());
    }

    // Similar tests for other methods...

    @Test
    public void testGetTotalAmountInvoiceBetweenDates() {
        // Mock data
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        float expectedTotalAmount = 1000.0f;

        // Mock the repository method
        when(invoiceRepository.getTotalAmountInvoiceBetweenDates(startDate, endDate))
                .thenReturn(expectedTotalAmount);

        // Call the service method
        float totalAmount = invoiceService.getTotalAmountInvoiceBetweenDates(startDate, endDate);

        // Verify that the repository method was called and the result matches the expected total amount
        verify(invoiceRepository).getTotalAmountInvoiceBetweenDates(startDate, endDate);
        assertEquals(expectedTotalAmount, totalAmount, 0.001); // Specify a delta for float comparison
    }
}