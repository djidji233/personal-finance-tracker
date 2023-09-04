package com.talentwunder.financetracker.controller;

import com.talentwunder.financetracker.model.entity.TransactionType;
import com.talentwunder.financetracker.model.request.TransactionCreateRequest;
import com.talentwunder.financetracker.model.request.TransactionUpdateRequest;
import com.talentwunder.financetracker.model.response.TransactionResponse;
import com.talentwunder.financetracker.model.response.TransactionSummaryResponse;
import com.talentwunder.financetracker.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> create(
            @Valid @RequestBody TransactionCreateRequest request
    ) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<TransactionSummaryResponse> findAll(
            @RequestParam(required = false) TransactionType type
    ) {
        return new ResponseEntity<>(service.findAll(type), HttpStatus.OK);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<?> delete(
            @PathVariable Long transactionId
    ) {
        service.delete(transactionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{transactionId}")
    public ResponseEntity<TransactionResponse> update(
            @PathVariable Long transactionId,
            @Valid @RequestBody TransactionUpdateRequest request
    ) {
        return new ResponseEntity<>(service.update(transactionId, request), HttpStatus.OK);
    }

}
