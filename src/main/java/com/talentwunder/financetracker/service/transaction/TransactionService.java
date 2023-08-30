package com.talentwunder.financetracker.service.transaction;

import com.talentwunder.financetracker.model.request.TransactionRequest;
import com.talentwunder.financetracker.model.response.TransactionResponse;

import java.util.List;

public interface TransactionService {
    TransactionResponse create(TransactionRequest request);
    TransactionResponse update(Long transactionId, TransactionRequest request);
    void delete(Long id);
    List<TransactionResponse> findAll();
}
