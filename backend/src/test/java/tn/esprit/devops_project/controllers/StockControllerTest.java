package tn.esprit.devops_project.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.services.Iservices.IStockService;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockControllerTest {

    @Mock
    private IStockService stockService;

    @InjectMocks
    private StockController stockController;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testAddStock() {
        Stock stock = new Stock();
        when(stockService.addStock(stock)).thenReturn(stock);
        Stock result = stockController.addStock(stock);
        assertEquals(stock, result);
    }

    @Test
    public void testRetrieveStock() {
        Long id = 1L;
        Stock stock = new Stock();
        when(stockService.retrieveStock(id)).thenReturn(stock);
        Stock result = stockController.retrieveStock(id);
        assertEquals(stock, result);
    }

    @Test
    public void testRetrieveAllStock() {
        List<Stock> stockList = new ArrayList<>();
        when(stockService.retrieveAllStock()).thenReturn(stockList);
        List<Stock> result = stockController.retrieveAllStock();
        assertEquals(stockList, result);
    }
}
