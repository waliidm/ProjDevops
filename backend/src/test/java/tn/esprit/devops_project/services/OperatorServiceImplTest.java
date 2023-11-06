package tn.esprit.devops_project.services;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OperatorServiceImplTest {

    @Mock
    private OperatorRepository operatorRepository;

    @InjectMocks
    private OperatorServiceImpl operatorService;

    @Test
    void testRetrieveAllOperators() {
        Operator operator = new Operator();
        when(operatorRepository.findAll()).thenReturn(Collections.singletonList(operator));

        List<Operator> operators = operatorService.retrieveAllOperators();

        assertEquals(1, operators.size());
        assertEquals(operator, operators.get(0));
    }

    @Test
    void testAddOperator() {
        Operator operator = new Operator();
        when(operatorRepository.save(operator)).thenReturn(operator);

        Operator addedOperator = operatorService.addOperator(operator);

        assertEquals(operator, addedOperator);
    }

    @Test
    void testDeleteOperator() {
        Long operatorId = 1L;
        operatorService.deleteOperator(operatorId);

        verify(operatorRepository).deleteById(operatorId);
    }

    @Test
    void testUpdateOperator() {
        Operator operator = new Operator();
        when(operatorRepository.save(operator)).thenReturn(operator);

        Operator updatedOperator = operatorService.updateOperator(operator);

        assertEquals(operator, updatedOperator);
    }

    @Test
    void testRetrieveOperator() {
        Long operatorId = 1L;
        Operator operator = new Operator();
        when(operatorRepository.findById(operatorId)).thenReturn(Optional.of(operator));

        Operator retrievedOperator = operatorService.retrieveOperator(operatorId);

        assertEquals(operator, retrievedOperator);
    }

    @Test
    void testRetrieveOperator_NotFound() {
        Long operatorId = 1L;
        when(operatorRepository.findById(operatorId)).thenReturn(Optional.empty());

        assertThrows(NullPointerException.class, () -> operatorService.retrieveOperator(operatorId));
    }
}
