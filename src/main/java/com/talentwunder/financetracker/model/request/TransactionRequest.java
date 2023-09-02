package com.talentwunder.financetracker.model.request;

import com.talentwunder.financetracker.model.entity.TransactionType;

public interface TransactionRequest {
    TransactionType getType();
    Double getAmount();
    String getDescription();
}
