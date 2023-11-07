package tn.esprit.devops_project.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.InvoiceDetailRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.InvoiceServiceImpl;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class InvoiceServiceImplTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private OperatorRepository operatorRepository;

    @Mock
    private InvoiceDetailRepository invoiceDetailRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllInvoices() {
        List<Invoice> testInvoices = Arrays.asList(new Invoice(), new Invoice());
        when(invoiceRepository.findAll()).thenReturn(testInvoices);
        List<Invoice> retrievedInvoices = invoiceService.retrieveAllInvoices();
        assertNotNull(retrievedInvoices);
        assertEquals(2, retrievedInvoices.size());
    }

    @Test
    public void testCancelInvoice() {
        Long invoiceId = 1L;
        Invoice mockInvoice = new Invoice();
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(mockInvoice));
        invoiceService.cancelInvoice(invoiceId);
        verify(invoiceRepository).save(mockInvoice);
    }

    @Test
    public void testRetrieveInvoice() {
        Long invoiceId = 1L;
        Invoice mockInvoice = new Invoice();
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(mockInvoice));
        Invoice retrievedInvoice = invoiceService.retrieveInvoice(invoiceId);
        assertNotNull(retrievedInvoice);
        assertEquals(mockInvoice, retrievedInvoice);
    }

    @Test
    public void testGetInvoicesBySupplier() {
        Long supplierId = 1L;
        Supplier mockSupplier = new Supplier();
        mockSupplier.setInvoices(new HashSet<>());
        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(mockSupplier));
        List<Invoice> retrievedInvoices = invoiceService.getInvoicesBySupplier(supplierId);
        assertNotNull(retrievedInvoices);
        assertEquals(0, retrievedInvoices.size());
    }

    @Test
    public void testAssignOperatorToInvoice() {
        // Given
        Long operatorId = 1L;
        Long invoiceId = 1L;
        Operator mockOperator = new Operator();
        Invoice mockInvoice = new Invoice();
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(mockInvoice));
        when(operatorRepository.findById(operatorId)).thenReturn(Optional.of(mockOperator));

        // When
        invoiceService.assignOperatorToInvoice(operatorId, invoiceId);

        // Then
        verify(operatorRepository).save(mockOperator);
    }

    @Test
    public void testGetTotalAmountInvoiceBetweenDates() {
        // Given
        Date startDate = new Date();
        Date endDate = new Date();
        when(invoiceRepository.getTotalAmountInvoiceBetweenDates(startDate, endDate)).thenReturn(500.0f);

        // When
        float totalAmount = invoiceService.getTotalAmountInvoiceBetweenDates(startDate, endDate);

        // Then
        assertEquals(500.0f, totalAmount, 0.0);
    }
}
