package com.talentwunder.financetracker.mapper;

import com.talentwunder.financetracker.model.entity.Transaction;
import com.talentwunder.financetracker.model.request.TransactionCreateRequest;
import com.talentwunder.financetracker.model.request.TransactionUpdateRequest;
import com.talentwunder.financetracker.model.response.TransactionResponse;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public Transaction mapCreateRequestToEntity(Transaction entity, TransactionCreateRequest request) {
        //TODO mozda moze da bude sve 1 metod koji prima ili create ili update request object
        entity.setType(request.getType());
        entity.setAmount(request.getAmount());
        entity.setDescription(request.getDescription());
        return entity;
    }

    public Transaction mapUpdateRequestToEntity(Transaction entity, TransactionUpdateRequest request) {
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
