package com.talentwunder.financetracker.model.response;

import com.talentwunder.financetracker.model.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {
    private Long id;
    private Long userId;
    private TransactionType type;
    private BigDecimal amount;
    private String description;
}
