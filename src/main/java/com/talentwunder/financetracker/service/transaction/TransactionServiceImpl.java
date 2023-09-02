package com.talentwunder.financetracker.service.transaction;

import com.talentwunder.financetracker.exception.ApiException;
import com.talentwunder.financetracker.mapper.TransactionMapper;
import com.talentwunder.financetracker.model.entity.Transaction;
import com.talentwunder.financetracker.model.entity.TransactionType;
import com.talentwunder.financetracker.model.entity.User;
import com.talentwunder.financetracker.model.request.TransactionCreateRequest;
import com.talentwunder.financetracker.model.request.TransactionUpdateRequest;
import com.talentwunder.financetracker.model.response.TransactionResponse;
import com.talentwunder.financetracker.model.response.TransactionSummaryResponse;
import com.talentwunder.financetracker.repository.TransactionRepository;
import com.talentwunder.financetracker.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;
    private final TransactionMapper mapper;
    private final UserRepository userRepository;

    public TransactionServiceImpl(TransactionRepository repository, TransactionMapper mapper, UserRepository userRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public TransactionResponse create(Long userId, TransactionCreateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "User with that ID doesn't exist", "Transaction - create"));
        Transaction entity = mapper.mapCreateRequestToEntity(new Transaction(), request);
        entity.setUser(user);
        repository.save(entity);
        return mapper.mapEntityToResponse(entity);
    }

    @Override
    @Transactional
    public TransactionResponse update(Long transactionId, TransactionUpdateRequest request) {
        Transaction entity = repository.findById(transactionId).orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Transaction with that ID doesn't exist", "Transaction - update"));
        entity = mapper.mapUpdateRequestToEntity(entity, request);
        try {
            repository.save(entity);
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while communicating with database", "Transaction - update");
        }
        return mapper.mapEntityToResponse(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Transaction entity = repository.findById(id).orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Transaction with that ID doesn't exist", "Transaction - delete"));
        try {
            repository.delete(entity);
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while communicating with database", "Transaction - delete");
        }
    }

    @Override
    public List<TransactionResponse> findAll() {
        List<Transaction> entities;
        try {
            entities = repository.findAll();
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while communicating with database", "Transaction - findAll");
        }
        return entities.stream().map(mapper::mapEntityToResponse).collect(Collectors.toList());
    }

    @Override
    public TransactionSummaryResponse getSummary() {
        List<Transaction> entities;
        try {
            entities = repository.findAll();
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while communicating with database", "Transaction - findAll");
        }
        double income = 0;
        double expense = 0;
        for (Transaction t : entities) {
            if (t.getType() == TransactionType.INCOME) {
                income += t.getAmount();
            } else if (t.getType() == TransactionType.EXPENSE) {
                expense += t.getAmount();
            }
        }

        return TransactionSummaryResponse.builder()
                .totalIncome(income)
                .totalExpense(expense)
                .balance(income - expense)
                .build();
    }
}
