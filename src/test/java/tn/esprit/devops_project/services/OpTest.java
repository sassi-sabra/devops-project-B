package tn.esprit.devops_project.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)

public class OpTest {

    @InjectMocks
    private OperatorServiceImpl operatorService;

    @Mock
    private OperatorRepository operatorRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllOperators() {
        // Create a list of operators for testing
        List<Operator> operators = new ArrayList<>();
        Operator operator1 = new Operator();
        operator1.setIdOperateur(1L);
        operator1.setFname("Operator 1");
        operators.add(operator1);

        Operator operator2 = new Operator();
        operator2.setIdOperateur(2L);
        operator2.setFname("Operator 2");
        operators.add(operator2);

        when(operatorRepository.findAll()).thenReturn(operators);

        List<Operator> result = operatorService.retrieveAllOperators();

        assertEquals(2, result.size());

        System.err.println("Test retrieveAllOperators SUCCESS");
    }

    @Test
    public void testAddOperator() {
        // Create an operator for testing
        Operator operator = new Operator();
        operator.setIdOperateur(1L);
        operator.setFname("Operator 1");

        when(operatorRepository.save(operator)).thenReturn(operator);

        Operator addedOperator = operatorService.addOperator(operator);

        assertNotNull(addedOperator);

        System.err.println("Test addOperator SUCCESS");
    }

    @Test
    public void testDeleteOperator() {
        Long operatorId = 1L;

        operatorService.deleteOperator(operatorId);

        verify(operatorRepository).deleteById(operatorId);

        System.err.println("Test deleteOperator SUCCESS");
    }

    @Test
    public void testUpdateOperator() {
        Long operatorId = 1L;
        Operator updatedOperator = new Operator();
        updatedOperator.setIdOperateur(operatorId);
        updatedOperator.setFname("Updated Operator");

        when(operatorRepository.save(updatedOperator)).thenReturn(updatedOperator);

        Operator result = operatorService.updateOperator(operatorId, updatedOperator);

        assertEquals("Updated Operator", result.getFname());

        System.err.println("Test updateOperator SUCCESS");
    }

    @Test
    public void testRetrieveOperator_ValidId() {
        Long operatorId = 1L;
        Operator operator = new Operator();
        operator.setIdOperateur(operatorId);
        operator.setFname("Operator 1");

        when(operatorRepository.findById(operatorId)).thenReturn(Optional.of(operator));

        Operator retrievedOperator = operatorService.retrieveOperator(operatorId);

        assertEquals("Operator 1", retrievedOperator.getFname());

        System.err.println("Test retrieveOperator (ValidId) SUCCESS");
    }

    @Test(expected = NullPointerException.class)
    public void testRetrieveOperator_InvalidId() {
        Long invalidOperatorId = 999L;

        when(operatorRepository.findById(invalidOperatorId)).thenReturn(Optional.empty());

        operatorService.retrieveOperator(invalidOperatorId);

        System.err.println("Test retrieveOperator (InvalidId) SUCCESS");
    }
}