package com.talentwunder.financetracker.mapper;

import com.talentwunder.financetracker.model.entity.Transaction;
import com.talentwunder.financetracker.model.request.TransactionRequest;
import com.talentwunder.financetracker.model.response.TransactionResponse;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public Transaction mapRequestToEntity(Transaction entity, TransactionRequest request) {
        if (request.getType() != null) entity.setType(request.getType());
        if (request.getAmount() != null) entity.setAmount(request.getAmount());
        if (request.getDescription() != null) entity.setDescription(request.getDescription());
        return entity;
    }

    public TransactionResponse mapEntityToResponse(Transaction entity) {
        return TransactionResponse.builder()
                .id(entity.getId())
                .type(entity.getType())
                .amount(entity.getAmount())
                .description(entity.getDescription())
                .build();
    }

}
