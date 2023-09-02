package com.talentwunder.financetracker.model.request;

import com.talentwunder.financetracker.model.entity.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCreateRequest {
    //TODO add comments to explain code
    @NotNull(message = "Transaction type must be provided")
    private TransactionType type;

    @NotNull(message = "Amount must be provided")
    @Positive(message = "Amount must be a positive number")
    private Double amount;

    @NotNull(message = "Description must be provided")
    @NotBlank(message = "Description must be provided")
    private String description;
}