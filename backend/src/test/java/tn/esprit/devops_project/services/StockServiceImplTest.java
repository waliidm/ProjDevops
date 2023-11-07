package tn.esprit.devops_project.services;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @Test
    void testAddStock() {
        Stock stock = new Stock();
        when(stockRepository.save(stock)).thenReturn(stock);
        Stock addedStock = stockService.addStock(stock);
        assertEquals(stock, addedStock);
    }

    @Test
    void testRetrieveStock() {
        Stock stock = new Stock();
        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));
        Stock retrievedStock = stockService.retrieveStock(1L);
        assertEquals(stock, retrievedStock);
    }

    @Test
    void testRetrieveStock_NotFound() {
        when(stockRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NullPointerException.class, () -> stockService.retrieveStock(1L));
    }

    @Test
    void testRetrieveAllStock() {
        List<Stock> stockList = Collections.singletonList(new Stock());
        when(stockRepository.findAll()).thenReturn(stockList);
        List<Stock> retrievedStocks = stockService.retrieveAllStock();
        assertEquals(stockList.size(), retrievedStocks.size());
    }
}
