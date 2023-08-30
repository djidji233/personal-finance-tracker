package com.talentwunder.financetracker.service.transaction;

import com.talentwunder.financetracker.mapper.TransactionMapper;
import com.talentwunder.financetracker.model.db.Transaction;
import com.talentwunder.financetracker.model.request.TransactionRequest;
import com.talentwunder.financetracker.model.response.TransactionResponse;
import com.talentwunder.financetracker.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Override
    public TransactionResponse create(TransactionRequest request) {
        Transaction entity = TransactionMapper.mapRequestToEntity(new Transaction(), request);
        repository.save(entity);
        return TransactionMapper.mapEntityToResponse(entity);
    }

    @Override
    public TransactionResponse update(Long transactionId, TransactionRequest request) {
        Optional<Transaction> optionalEntity = repository.findById(transactionId);
        if (optionalEntity.isPresent()) {
            Transaction entity = TransactionMapper.mapRequestToEntity(optionalEntity.get(), request);
            repository.save(entity);
            return TransactionMapper.mapEntityToResponse(entity);
        } else {
            return null; //TODO exceptions
        }
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<TransactionResponse> findAll() {
        return repository
                .findAll()
                .stream()
                .map(TransactionMapper::mapEntityToResponse)
                .collect(Collectors.toList());
    }
}
