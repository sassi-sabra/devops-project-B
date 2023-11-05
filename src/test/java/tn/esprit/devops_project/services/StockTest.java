package tn.esprit.devops_project.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)

public class StockTest {
    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;

    @Test
    public void testAddStock() {
        Stock stock = new Stock();
        // Set stock properties

        when(stockRepository.save(stock)).thenReturn(stock);

        Stock addedStock = stockService.addStock(stock);

        assertNotNull(addedStock);

        System.err.println("Test testAddStock SUCCESS");
    }

    @Test
    public void testRetrieveStock_ValidId() {
        Long stockId = 1L;

        // Create a Stock object with the specified ID
        Stock stock = new Stock();
        stock.setIdStock(stockId);

        // Mock the behavior of the StockRepository
        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));

        // Call the service method to retrieve the stock
        Stock retrievedStock = stockService.retrieveStock(stockId);

        // Assert that the retrieved stock matches the expected ID
        assertNotNull(retrievedStock);
        assertEquals(stockId, retrievedStock.getIdStock());

        System.err.println("Test testRetrieveStock_ValidId SUCCESS");
    }

    @Test(expected = NullPointerException.class)
    public void testRetrieveStock_InvalidId() {
        Long invalidStockId = 999L;

        when(stockRepository.findById(invalidStockId)).thenReturn(Optional.empty());

        stockService.retrieveStock(invalidStockId);

        System.err.println("Test testRetrieveStock_InvalidId SUCCESS");
    }

    @Test
    public void testRetrieveAllStock() {
        List<Stock> stockList = new ArrayList<>();
        // Add stock objects to the list

        when(stockRepository.findAll()).thenReturn(stockList);

        List<Stock> result = stockService.retrieveAllStock();

        assertNotNull(result);
        assertEquals(stockList.size(), result.size());

        System.err.println("Test testRetrieveAllStock SUCCESS");
    }
}
