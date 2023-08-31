package com.talentwunder.financetracker.service.transaction;

import com.talentwunder.financetracker.exception.ApiException;
import com.talentwunder.financetracker.mapper.TransactionMapper;
import com.talentwunder.financetracker.model.entity.Transaction;
import com.talentwunder.financetracker.model.request.TransactionRequest;
import com.talentwunder.financetracker.model.response.TransactionResponse;
import com.talentwunder.financetracker.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;
    private final TransactionMapper mapper;

    public TransactionServiceImpl(TransactionRepository repository, TransactionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public TransactionResponse create(TransactionRequest request) {
        Transaction entity = mapper.mapRequestToEntity(new Transaction(), request);
        try {
            repository.save(entity);
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while communicating with database", "Transaction - create");
        }
        return mapper.mapEntityToResponse(entity);
    }

    @Override
    @Transactional
    public TransactionResponse update(Long transactionId, TransactionRequest request) {
        Transaction entity = repository.findById(transactionId).orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Transaction with that ID doesn't exist", "Transaction - update"));
        entity = mapper.mapRequestToEntity(entity, request);
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
}
