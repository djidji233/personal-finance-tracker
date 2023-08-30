package com.talentwunder.financetracker.mapper;

import com.talentwunder.financetracker.model.db.Transaction;
import com.talentwunder.financetracker.model.request.TransactionRequest;
import com.talentwunder.financetracker.model.response.TransactionResponse;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public static Transaction mapRequestToEntity(TransactionRequest request){
        return Transaction.builder()
                .type(request.getType())
                .amount(request.getAmount())
                .description(request.getDescription())
                .build();
    }

    public static TransactionResponse mapEntityToResponse(Transaction entity){
        return TransactionResponse.builder()
                .id(entity.getId())
                .type(entity.getType())
                .amount(entity.getAmount())
                .description(entity.getDescription())
                .build();
    }

}
