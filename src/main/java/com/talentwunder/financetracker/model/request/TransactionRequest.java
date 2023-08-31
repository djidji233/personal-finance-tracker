package com.talentwunder.financetracker.model.request;

import com.talentwunder.financetracker.model.entity.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    @NotNull(message = "Transaction type must be provided")
    private TransactionType type;

    @Positive(message = "Amount must be a positive number")
    private Double amount;

    private String description;
}
