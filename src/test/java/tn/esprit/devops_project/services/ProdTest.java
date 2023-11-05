package tn.esprit.devops_project.services;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;


public class ProdTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockRepository stockRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddProduct() {
        // Create a Stock for the product
        Stock stock = new Stock();
        stock.setIdStock(1L);

        // Create a Product
        Product product = new Product();
        // Set product properties

        // Mock the behavior of the StockRepository
        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));

        // Mock the behavior of the ProductRepository
        when(productRepository.save(product)).thenReturn(product);

        // Call the service method to add the product
        Product addedProduct = productService.addProduct(product, 1L);

        assertNotNull(addedProduct);

        System.err.println("Test testAddProduct SUCCESS");
    }

    @Test
    public void testRetrieveProduct_ValidId() {
        Long productId = 1L;

        // Create a Product with the specified ID
        Product product = new Product();
        // Set product properties
        product.setIdProduct(productId);

        // Mock the behavior of the ProductRepository
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Call the service method to retrieve the product
        Product retrievedProduct = productService.retrieveProduct(productId);

        assertNotNull(retrievedProduct);
        assertEquals(productId, retrievedProduct.getIdProduct());

        System.err.println("Test testRetrieveProduct_ValidId SUCCESS");
    }

    @Test(expected = NullPointerException.class)
    public void testRetrieveProduct_InvalidId() {
        Long invalidProductId = 999L;

        // Mock the behavior of the ProductRepository to return an empty optional
        when(productRepository.findById(invalidProductId)).thenReturn(Optional.empty());

        // Call the service method with an invalid ID
        productService.retrieveProduct(invalidProductId);

        System.err.println("Test testRetrieveProduct_InvalidId SUCCESS");
    }

    @Test
    public void testRetreiveAllProduct() {
        List<Product> productList = new ArrayList<>();
        // Add product objects to the list

        // Mock the behavior of the ProductRepository to return the list
        when(productRepository.findAll()).thenReturn(productList);

        // Call the service method to retrieve all products
        List<Product> result = productService.retreiveAllProduct();

        assertNotNull(result);
        assertEquals(productList.size(), result.size());

        System.err.println("Test testRetreiveAllProduct SUCCESS");
    }

    // Add similar test methods for other operations as needed
}
