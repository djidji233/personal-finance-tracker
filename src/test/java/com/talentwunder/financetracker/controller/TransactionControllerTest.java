package com.talentwunder.financetracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talentwunder.financetracker.model.entity.TransactionType;
import com.talentwunder.financetracker.model.request.TransactionCreateRequest;
import com.talentwunder.financetracker.model.request.TransactionUpdateRequest;
import com.talentwunder.financetracker.model.request.UserCreateRequest;
import com.talentwunder.financetracker.model.response.TransactionResponse;
import com.talentwunder.financetracker.model.response.TransactionSummaryResponse;
import com.talentwunder.financetracker.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = TransactionController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserCreateRequest userCreateRequest;
    private TransactionCreateRequest createRequest;
    private TransactionUpdateRequest updateRequest;
    private TransactionResponse response;
    private TransactionSummaryResponse summaryResponse;

    @BeforeEach
    public void init() {
        createRequest = TransactionCreateRequest.builder()
                .type(TransactionType.INCOME)
                .amount(new BigDecimal(1))
                .description("test").build();
        updateRequest = TransactionUpdateRequest.builder()
                .amount(new BigDecimal(22))
                .description("updated").build();
    }

    @Test
    public void TransactionController_CreateTransaction_ReturnStatus() throws Exception {
        ResultActions response = mockMvc.perform(post("/transaction")
                .param("userId", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequest)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void TransactionController_UpdateTransaction_ReturnStatus() throws Exception {
        TransactionResponse response = TransactionResponse.builder()
                .id(1L)
                .userId(1L)
                .type(TransactionType.INCOME)
                .amount(new BigDecimal(22))
                .description("updated").build();
        when(transactionService.update(1L, updateRequest)).thenReturn(response); //TODO fix tests

        ResultActions resultActions = mockMvc.perform(patch("/transaction/1")
                .param("userId", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequest)));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void TransactionController_DeleteTransaction_ReturnStatus() throws Exception {
        doNothing().when(transactionService).delete(1L); //TODO fix tests

        ResultActions response = mockMvc.perform(delete("/transaction/1")
                .param("userId", "1"));

        response.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void TransactionController_FindAll_ReturnStatus() throws Exception {
        TransactionSummaryResponse summaryResponse = TransactionSummaryResponse.builder()
                .balance(new BigDecimal(1))
                .totalIncome(new BigDecimal(1))
                .totalExpense(new BigDecimal(0)).build();
        when(transactionService.findAll(null)).thenReturn(summaryResponse); //TODO fix tests

        ResultActions response = mockMvc.perform(get("/transaction")
                .param("userId", "1"));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

}
