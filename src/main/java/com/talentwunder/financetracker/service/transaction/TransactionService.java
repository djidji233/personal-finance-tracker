package com.talentwunder.financetracker.service.transaction;

import com.talentwunder.financetracker.model.entity.TransactionType;
import com.talentwunder.financetracker.model.request.TransactionCreateRequest;
import com.talentwunder.financetracker.model.request.TransactionUpdateRequest;
import com.talentwunder.financetracker.model.response.TransactionResponse;
import com.talentwunder.financetracker.model.response.TransactionSummaryResponse;


public interface TransactionService {
    TransactionResponse create(Long userId, TransactionCreateRequest request);

    TransactionResponse update(Long userId, Long transactionId, TransactionUpdateRequest request);

    void delete(Long userId, Long transactionId);

    TransactionSummaryResponse findAll(TransactionType type, Long userId);
}
