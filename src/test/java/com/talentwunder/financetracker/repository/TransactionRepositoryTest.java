package com.talentwunder.financetracker.repository;

import com.talentwunder.financetracker.model.entity.Transaction;
import com.talentwunder.financetracker.model.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    private User mockUser;

    @BeforeEach
    public void mockUser(){
        mockUser = userRepository.save(User.builder().id(1L).username("user").password("pass").transactions(new ArrayList<>()).build());
    }

    @Test
    public void TransactionRepository_Save_ReturnSavedTransaction() {
        // arrange
        Transaction transaction = Transaction.builder()
                .amount(new BigDecimal(1))
                .description("test")
                .user(mockUser).build();
        // act
        Transaction savedTransaction = transactionRepository.save(transaction);
        // assert
        Assertions.assertThat(savedTransaction).isNotNull();
        Assertions.assertThat(savedTransaction.getId()).isGreaterThan(0L);
    }

    @Test
    public void TransactionRepository_FindAll_ReturnMoreThanOneTransaction(){
        // arrange
        Transaction transaction = Transaction.builder()
                .amount(new BigDecimal(1))
                .description("test")
                .user(mockUser).build();
        Transaction transaction2 = Transaction.builder()
                .amount(new BigDecimal(1))
                .description("test")
                .user(mockUser).build();

        transactionRepository.save(transaction);
        transactionRepository.save(transaction2);
        // act
        List<Transaction> transactions = transactionRepository.findAll();
        // assert
        Assertions.assertThat(transactions).isNotNull();
        Assertions.assertThat(transactions.size()).isEqualTo(2);
    }

    @Test
    public void TransactionRepository_FindById_ReturnTransaction(){
        // arrange
        Transaction transaction = Transaction.builder()
                .amount(new BigDecimal(1))
                .description("test")
                .user(mockUser).build();

        transactionRepository.save(transaction);
        // act
        Transaction savedTransaction = transactionRepository.findById(transaction.getId()).get();
        // assert
        Assertions.assertThat(savedTransaction).isNotNull();
        Assertions.assertThat(savedTransaction.getId()).isGreaterThan(0L);
    }

    @Test
    public void TransactionRepository_FindByUserId_ReturnTransaction(){
        // arrange
        Transaction transaction = Transaction.builder()
                .amount(new BigDecimal(1))
                .description("test")
                .user(mockUser).build();
        Transaction transaction2 = Transaction.builder()
                .amount(new BigDecimal(1))
                .description("test")
                .user(mockUser).build();

        transactionRepository.save(transaction);
        transactionRepository.save(transaction2);
        // act
        List<Transaction> transactions = transactionRepository.findAllByUserId(mockUser.getId());
        // assert
        Assertions.assertThat(transactions).isNotNull();
        Assertions.assertThat(transactions.size()).isEqualTo(2);
    }

    @Test
    public void TransactionRepository_UpdateTransaction_ReturnTransaction(){
        // arrange
        Transaction transaction = Transaction.builder()
                .amount(new BigDecimal(1))
                .description("test")
                .user(mockUser).build();

        transactionRepository.save(transaction);
        Transaction savedTransaction = transactionRepository.findById(transaction.getId()).get();
        savedTransaction.setAmount(new BigDecimal(3));
        savedTransaction.setDescription("update");
        // act
        Transaction updatedTransaction = transactionRepository.save(savedTransaction);
        // assert
        Assertions.assertThat(updatedTransaction).isNotNull();
        Assertions.assertThat(updatedTransaction.getAmount()).isEqualTo(BigDecimal.valueOf(3));
        Assertions.assertThat(updatedTransaction.getDescription()).isEqualTo("update");
    }

    @Test
    public void TransactionRepository_Delete_ReturnTransactionIsEmpty(){
        // arrange
        Transaction transaction = Transaction.builder()
                .amount(new BigDecimal(1))
                .description("test")
                .user(mockUser).build();

        transactionRepository.save(transaction);
        // act
        transactionRepository.deleteById(transaction.getId());
        Optional<Transaction> transactionOptional = transactionRepository.findById(transaction.getId());
        // assert
        Assertions.assertThat(transactionOptional).isEmpty();
    }

}
