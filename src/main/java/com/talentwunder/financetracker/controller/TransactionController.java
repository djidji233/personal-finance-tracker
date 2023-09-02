package com.talentwunder.financetracker.controller;

import com.talentwunder.financetracker.model.request.TransactionCreateRequest;
import com.talentwunder.financetracker.model.request.TransactionUpdateRequest;
import com.talentwunder.financetracker.model.response.TransactionResponse;
import com.talentwunder.financetracker.model.response.TransactionSummaryResponse;
import com.talentwunder.financetracker.service.transaction.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> create(
            @RequestParam Long userId,
            @Valid @RequestBody TransactionCreateRequest request
    ) {
        return new ResponseEntity<>(service.create(userId, request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<TransactionSummaryResponse> findAll(
            @RequestParam Long userId
    ) {
        return new ResponseEntity<>(service.findAll(userId), HttpStatus.OK);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<?> delete(
            @RequestParam Long userId,
            @PathVariable Long transactionId
    ) {
        service.delete(userId, transactionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{transactionId}")
    public ResponseEntity<TransactionResponse> update(
            @RequestParam Long userId,
            @PathVariable Long transactionId,
            @Valid @RequestBody TransactionUpdateRequest request
    ) {
        return new ResponseEntity<>(service.update(userId, transactionId, request), HttpStatus.OK);
    }

}
