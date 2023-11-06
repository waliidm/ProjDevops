package tn.esprit.devops_project.services;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testAddProduct() {
        Stock stock = new Stock();
        Product product = new Product();
        product.setStock(stock);

        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));
        when(productRepository.save(product)).thenReturn(product);

        Product addedProduct = productService.addProduct(product, 1L);

        assertEquals(product, addedProduct);
    }

    @Test
    void testRetrieveProduct() {
        Product product = new Product();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product retrievedProduct = productService.retrieveProduct(1L);

        assertEquals(product, retrievedProduct);
    }

    @Test
    void testRetrieveProduct_NotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NullPointerException.class, () -> productService.retrieveProduct(1L));
    }

    @Test
    void testRetreiveAllProduct() {
        List<Product> productList = Collections.singletonList(new Product());
        when(productRepository.findAll()).thenReturn(productList);

        List<Product> retrievedProducts = productService.retreiveAllProduct();

        assertEquals(productList.size(), retrievedProducts.size());
    }

    @Test
    void testRetrieveProductByCategory() {
        ProductCategory category = ProductCategory.ELECTRONICS;
        List<Product> productList = Collections.singletonList(new Product());

        when(productRepository.findByCategory(category)).thenReturn(productList);

        List<Product> retrievedProducts = productService.retrieveProductByCategory(category);

        assertEquals(productList.size(), retrievedProducts.size());
    }

    @Test
    void testDeleteProduct() {
        productService.deleteProduct(1L);
        verify(productRepository).deleteById(1L);
    }

    @Test
    void testRetreiveProductStock() {
        List<Product> productList = Collections.singletonList(new Product());
        when(productRepository.findByStockIdStock(1L)).thenReturn(productList);

        List<Product> retrievedProducts = productService.retreiveProductStock(1L);

        assertEquals(productList.size(), retrievedProducts.size());
    }
}
