package com.talentwunder.financetracker.service.transaction;

import com.talentwunder.financetracker.model.request.TransactionCreateRequest;
import com.talentwunder.financetracker.model.request.TransactionUpdateRequest;
import com.talentwunder.financetracker.model.response.TransactionResponse;
import com.talentwunder.financetracker.model.response.TransactionSummaryResponse;

import java.util.List;

public interface TransactionService {
    TransactionResponse create(Long userId, TransactionCreateRequest request);

    TransactionResponse update(Long transactionId, TransactionUpdateRequest request);

    void delete(Long userId, Long transactionId);

    TransactionSummaryResponse findAll(Long userId);
}
