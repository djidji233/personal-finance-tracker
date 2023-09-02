package com.talentwunder.financetracker.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionSummaryResponse {
    private Double balance;
    private Double totalIncome;
    private Double totalExpense;
    private List<TransactionResponse> transactions;
}
