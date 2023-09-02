package com.talentwunder.financetracker.model.request;

import com.talentwunder.financetracker.model.entity.TransactionType;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionUpdateRequest implements TransactionRequest {

    private TransactionType type;

    @Positive(message = "Amount must be a positive number")
    private Double amount;

    @Size(min = 1, message = "Description must be provided")
    private String description;


    public boolean isEmpty() { // helper method
        return type == null && amount == null && description == null;
    }

}
