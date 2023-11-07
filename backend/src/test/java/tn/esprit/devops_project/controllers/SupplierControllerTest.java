package tn.esprit.devops_project.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.services.Iservices.ISupplierService;

import java.util.Collections;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
@WebMvcTest(SupplierController.class)
public class SupplierControllerTest {

    @Mock
    private ISupplierService supplierService;

    @InjectMocks
    private SupplierController supplierController;

    @MockBean
    private ISupplierService iSupplierService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(supplierController).build();
    }

    @Test
    public void testGetSuppliers() throws Exception {
        Supplier supplier = new Supplier();
        when(supplierService.retrieveAllSuppliers()).thenReturn(Collections.singletonList(supplier));

        mockMvc.perform(get("/supplier"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testRetrieveSupplier() throws Exception {
        Supplier supplier = new Supplier();
        when(supplierService.retrieveSupplier(1L)).thenReturn(supplier);

        mockMvc.perform(get("/supplier/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testAddSupplier() throws Exception {
        Supplier supplier = new Supplier();
        when(supplierService.addSupplier(any(Supplier.class))).thenReturn(supplier);

        mockMvc.perform(post("/supplier")
                        .content(new ObjectMapper().writeValueAsString(supplier))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testRemoveSupplier() throws Exception {
        doNothing().when(supplierService).deleteSupplier(1L);
        mockMvc.perform(delete("/supplier/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testModifySupplier() throws Exception {
        Supplier supplier = new Supplier();
        when(supplierService.updateSupplier(any(Supplier.class))).thenReturn(supplier);
        mockMvc.perform(put("/supplier")
                        .content(new ObjectMapper().writeValueAsString(supplier))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
