package com.talentwunder.financetracker.model.response;

import com.talentwunder.financetracker.model.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {
    private Long id;
    private TransactionType type;
    private Long amount;
    private String description;
}
