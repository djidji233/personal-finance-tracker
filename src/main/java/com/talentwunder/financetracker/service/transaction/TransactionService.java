package com.talentwunder.financetracker.service.transaction;

import com.talentwunder.financetracker.model.request.TransactionCreateRequest;
import com.talentwunder.financetracker.model.request.TransactionUpdateRequest;
import com.talentwunder.financetracker.model.response.TransactionResponse;

import java.util.List;

public interface TransactionService {
    TransactionResponse create(TransactionCreateRequest request);

    TransactionResponse update(Long transactionId, TransactionUpdateRequest request);

    void delete(Long id);

    List<TransactionResponse> findAll();
}
