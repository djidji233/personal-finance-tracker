package com.talentwunder.financetracker.service;

import com.talentwunder.financetracker.mapper.TransactionMapper;
import com.talentwunder.financetracker.model.entity.Transaction;
import com.talentwunder.financetracker.model.entity.TransactionType;
import com.talentwunder.financetracker.model.entity.User;
import com.talentwunder.financetracker.model.request.TransactionCreateRequest;
import com.talentwunder.financetracker.model.request.TransactionRequest;
import com.talentwunder.financetracker.model.request.TransactionUpdateRequest;
import com.talentwunder.financetracker.model.response.TransactionResponse;
import com.talentwunder.financetracker.model.response.TransactionSummaryResponse;
import com.talentwunder.financetracker.repository.TransactionRepository;
import com.talentwunder.financetracker.repository.UserRepository;
import com.talentwunder.financetracker.service.impl.TransactionServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TransactionMapper transactionMapper;

    @Test
    public void TransactionService_CreateTransaction_ReturnTransaction() {
        // arrange
        User mockUser = User.builder()
                .id(1L)
                .username("user")
                .password("pass")
                .transactions(new ArrayList<>()).build();
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(mockUser));

        Transaction transaction = Transaction.builder()
                .type(TransactionType.INCOME)
                .amount(new BigDecimal(1))
                .description("test")
                .user(mockUser).build();
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
        when(transactionMapper.mapRequestToEntity(any(Transaction.class), any(TransactionRequest.class))).thenReturn(transaction);

        TransactionResponse mockResponse = TransactionResponse.builder()
                .id(1L)
                .userId(1L)
                .type(TransactionType.INCOME)
                .amount(new BigDecimal(1))
                .description("test").build();
        when(transactionMapper.mapEntityToResponse(any(Transaction.class))).thenReturn(mockResponse);

        // act
        TransactionCreateRequest createRequest = TransactionCreateRequest.builder()
                .type(transaction.getType())
                .amount(transaction.getAmount())
                .description(transaction.getDescription()).build();
        TransactionResponse savedTransaction = transactionService.create(createRequest); //TODO fix tests

        // assert
        Assertions.assertThat(savedTransaction).isNotNull();
        Assertions.assertThat(savedTransaction.getId()).isEqualTo(1);
        Assertions.assertThat(savedTransaction.getUserId()).isEqualTo(1);
        Assertions.assertThat(savedTransaction.getType().toString()).isEqualTo("INCOME");
        Assertions.assertThat(savedTransaction.getAmount()).isEqualTo(new BigDecimal(1));
        Assertions.assertThat(savedTransaction.getDescription()).isEqualTo("test");
    }

    @Test
    public void TransactionService_UpdateTransaction_ReturnTransaction() {
        // arrange
        User mockUser = User.builder()
                .id(1L)
                .username("user")
                .password("pass")
                .transactions(new ArrayList<>()).build();

        Transaction transaction = Transaction.builder()
                .id(1L)
                .type(TransactionType.INCOME)
                .amount(new BigDecimal(1))
                .description("test")
                .user(mockUser).build();
        when(transactionRepository.findById(1L)).thenReturn(Optional.ofNullable(transaction));

        Transaction mockTransaction = Transaction.builder()
                .type(TransactionType.INCOME)
                .amount(new BigDecimal(10))
                .description("updated")
                .user(mockUser).build();
        when(transactionMapper.mapRequestToEntity(Mockito.any(Transaction.class), Mockito.any(TransactionRequest.class)))
                .thenReturn(mockTransaction);

        when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(mockTransaction);

        TransactionResponse mockResponse = TransactionResponse.builder()
                .id(1L)
                .userId(1L)
                .type(TransactionType.INCOME)
                .amount(new BigDecimal(10))
                .description("updated").build();
        when(transactionMapper.mapEntityToResponse(Mockito.any(Transaction.class))).thenReturn(mockResponse);

        // act
        TransactionUpdateRequest updateRequest = TransactionUpdateRequest.builder()
                .amount(new BigDecimal(10))
                .description("updated").build();
        TransactionResponse updatedTransaction = transactionService.update(mockUser.getId(), transaction.getId(), updateRequest);

        // assert
        Assertions.assertThat(updatedTransaction).isNotNull();
        Assertions.assertThat(updatedTransaction.getId()).isEqualTo(1);
        Assertions.assertThat(updatedTransaction.getUserId()).isEqualTo(1);
        Assertions.assertThat(updatedTransaction.getType().toString()).isEqualTo("INCOME");
        Assertions.assertThat(updatedTransaction.getAmount()).isEqualTo(new BigDecimal(10));
        Assertions.assertThat(updatedTransaction.getDescription()).isEqualTo("updated");

    }

    @Test
    public void TransactionService_FindAll_WithoutType() {
        // arrange
        User mockUser = User.builder()
                .id(1L)
                .username("user")
                .password("pass")
                .transactions(new ArrayList<>()).build();
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(mockUser));

        Transaction transaction = Transaction.builder()
                .id(1L)
                .type(TransactionType.INCOME)
                .amount(new BigDecimal(1))
                .description("test")
                .user(mockUser).build();
        Transaction transaction2 = Transaction.builder()
                .id(2L)
                .type(TransactionType.INCOME)
                .amount(new BigDecimal(1))
                .description("test")
                .user(mockUser).build();
        when(transactionRepository.findAllByUserId(1L)).thenReturn(List.of(transaction,transaction2));

        // act
        TransactionSummaryResponse response = transactionService.findAll(null); //TODO fix tests

        // assert
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getBalance()).isEqualTo(new BigDecimal(2));
        Assertions.assertThat(response.getTotalIncome()).isEqualTo(new BigDecimal(2));
        Assertions.assertThat(response.getTotalExpense()).isEqualTo(new BigDecimal(0));
        Assertions.assertThat(response.getTransactions().size()).isEqualTo(2);
    }

    @Test
    public void TransactionService_FindAll_WithType() {
        // arrange
        User mockUser = User.builder()
                .id(1L)
                .username("user")
                .password("pass")
                .transactions(new ArrayList<>()).build();
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(mockUser));

        Transaction transaction = Transaction.builder()
                .id(1L)
                .type(TransactionType.INCOME)
                .amount(new BigDecimal(1))
                .description("test")
                .user(mockUser).build();
        Transaction transaction2 = Transaction.builder()
                .id(2L)
                .type(TransactionType.EXPENSE)
                .amount(new BigDecimal(1))
                .description("test")
                .user(mockUser).build();
        when(transactionRepository.findAllByUserId(1L)).thenReturn(List.of(transaction,transaction2));

        // act
        TransactionSummaryResponse response = transactionService.findAll(transaction.getType()); //TODO fix tests

        // assert
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getBalance()).isEqualTo(new BigDecimal(0));
        Assertions.assertThat(response.getTotalIncome()).isEqualTo(new BigDecimal(1));
        Assertions.assertThat(response.getTotalExpense()).isEqualTo(new BigDecimal(1));
        Assertions.assertThat(response.getTransactions().size()).isEqualTo(1);
    }

    @Test
    public void TransactionService_DeleteTransaction() {
        // arrange
        User mockUser = User.builder()
                .id(1L)
                .username("user")
                .password("pass")
                .transactions(new ArrayList<>()).build();

        Transaction transaction = Transaction.builder()
                .id(1L)
                .type(TransactionType.INCOME)
                .amount(new BigDecimal(1))
                .description("test")
                .user(mockUser).build();
        when(transactionRepository.findById(1L)).thenReturn(Optional.ofNullable(transaction));
        // act & assert
        assertAll(() -> transactionService.delete(transaction.getId())); //TODO fix tests

    }

}
