package tn.esprit.devops_project.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.services.Iservices.IInvoiceService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(InvoiceController.class)
class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IInvoiceService invoiceService;

    @Test
    void testGetInvoices() throws Exception {
        List<Invoice> invoices = Collections.singletonList(new Invoice());
        when(invoiceService.retrieveAllInvoices()).thenReturn(invoices);

        mockMvc.perform(get("/invoice"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testRetrieveInvoice() throws Exception {
        Invoice invoice = new Invoice();
        when(invoiceService.retrieveInvoice(1L)).thenReturn(invoice);

        mockMvc.perform(get("/invoice/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCancelInvoice() throws Exception {
        doNothing().when(invoiceService).cancelInvoice(1L);

        mockMvc.perform(put("/invoice/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetInvoicesBySupplier() throws Exception {
        List<Invoice> invoices = Collections.singletonList(new Invoice());
        when(invoiceService.getInvoicesBySupplier(1L)).thenReturn(invoices);

        mockMvc.perform(get("/invoice/supplier/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testAssignOperatorToInvoice() throws Exception {
        doNothing().when(invoiceService).assignOperatorToInvoice(1L, 1L);

        mockMvc.perform(put("/invoice/operator/1/1"))
                .andExpect(status().isOk());
    }


}
