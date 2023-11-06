package tn.esprit.devops_project.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.services.Iservices.IProductService;

import java.util.Collections;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @MockBean
    private IProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAddProduct() throws Exception {
        Product product = new Product();
        product.setTitle("Test Product");
        product.setPrice(10.0f);

        when(productService.addProduct(any(Product.class), eq(1L))).thenReturn(product);

        mockMvc.perform(post("/product/1")
                        .content(new ObjectMapper().writeValueAsString(product))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(new ObjectMapper().writeValueAsString(product)));
    }


    @Test
    void testRetrieveProduct() throws Exception {
        Product product = new Product();
        when(productService.retrieveProduct(1L)).thenReturn(product);

        mockMvc.perform(get("/product/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testRetrieveAllProduct() throws Exception {
        List<Product> products = Collections.singletonList(new Product());
        when(productService.retreiveAllProduct()).thenReturn(products);

        mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testRetrieveProductStock() throws Exception {
        List<Product> products = Collections.singletonList(new Product());
        when(productService.retreiveProductStock(1L)).thenReturn(products);

        mockMvc.perform(get("/product/stock/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }




    @Test
    void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/product/1"))
                .andExpect(status().isOk());
    }
}
