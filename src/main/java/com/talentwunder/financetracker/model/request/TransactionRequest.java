package com.talentwunder.financetracker.model.request;

import com.talentwunder.financetracker.model.entity.TransactionType;

import java.math.BigDecimal;

public interface TransactionRequest {
    TransactionType getType();
    BigDecimal getAmount();
    String getDescription();
}
