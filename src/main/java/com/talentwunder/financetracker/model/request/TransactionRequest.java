package com.talentwunder.financetracker.model.request;

import com.talentwunder.financetracker.model.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    private TransactionType type;
    private Long amount;
    private String description;
}
