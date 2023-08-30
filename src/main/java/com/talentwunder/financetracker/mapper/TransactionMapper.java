package com.talentwunder.financetracker.mapper;

import com.talentwunder.financetracker.model.db.Transaction;
import com.talentwunder.financetracker.model.request.TransactionRequest;
import com.talentwunder.financetracker.model.response.TransactionResponse;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public static Transaction mapRequestToEntity(Transaction entity, TransactionRequest request) {
        entity.setType(request.getType() == null ? entity.getType() : request.getType());
        entity.setAmount(request.getAmount() == null ? entity.getAmount() : request.getAmount());
        entity.setDescription(request.getDescription() == null ? entity.getDescription() : request.getDescription());
        return entity;
    }

    public static TransactionResponse mapEntityToResponse(Transaction entity) {
        return TransactionResponse.builder()
                .id(entity.getId())
                .type(entity.getType())
                .amount(entity.getAmount())
                .description(entity.getDescription())
                .build();
    }

}
