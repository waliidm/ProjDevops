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
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.services.Iservices.IOperatorService;

import java.util.Collections;
import java.util.List;
import static org.mockito.Mockito.any;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(OperatorController.class)
class OperatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IOperatorService operatorService;

    @Test
    void testGetOperators() throws Exception {
        List<Operator> operators = Collections.singletonList(new Operator());
        when(operatorService.retrieveAllOperators()).thenReturn(operators);

        mockMvc.perform(get("/operator"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testRetrieveOperator() throws Exception {
        Operator operator = new Operator();
        when(operatorService.retrieveOperator(1L)).thenReturn(operator);

        mockMvc.perform(get("/operator/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testAddOperator() throws Exception {
        Operator operator = new Operator();
        operator.setIdOperateur(1L);
        operator.setFname("John");
        operator.setLname("Doe");
        operator.setPassword("examplePassword");
        when(operatorService.addOperator(any(Operator.class))).thenReturn(operator);
        mockMvc.perform(post("/operator")
                        .content(new ObjectMapper().writeValueAsString(operator))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testRemoveOperator() throws Exception {
        // Mocking the deletion process
        doNothing().when(operatorService).deleteOperator(1L);

        // Testing the DELETE request
        mockMvc.perform(delete("/operatot/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testModifyOperator() throws Exception {
        Operator operator = new Operator();
        operator.setIdOperateur(1L);
        operator.setFname("test");
        operator.setLname("test");
        operator.setPassword("test");

        when(operatorService.updateOperator(any(Operator.class))).thenReturn(operator);

        mockMvc.perform(put("/operator")
                        .content(new ObjectMapper().writeValueAsString(operator))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(new ObjectMapper().writeValueAsString(operator)));
    }
}
