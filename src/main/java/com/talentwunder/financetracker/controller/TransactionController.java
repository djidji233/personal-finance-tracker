package com.talentwunder.financetracker.controller;

import com.talentwunder.financetracker.exception.ApiException;
import com.talentwunder.financetracker.model.request.TransactionCreateRequest;
import com.talentwunder.financetracker.model.request.TransactionUpdateRequest;
import com.talentwunder.financetracker.model.response.TransactionResponse;
import com.talentwunder.financetracker.model.response.TransactionSummaryResponse;
import com.talentwunder.financetracker.service.transaction.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> create(@RequestParam Long userId, @Valid @RequestBody TransactionCreateRequest request) {
        return new ResponseEntity<>(service.create(userId, request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/summary")
    public ResponseEntity<TransactionSummaryResponse> getSummary() {
        return new ResponseEntity<>(service.getSummary(), HttpStatus.OK);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<?> delete(@PathVariable Long transactionId) {
        service.delete(transactionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{transactionId}")
    public ResponseEntity<TransactionResponse> update(@PathVariable Long transactionId, @Valid @RequestBody TransactionUpdateRequest request) {
        if (request.isEmpty())
            throw new ApiException(HttpStatus.BAD_REQUEST, "Must provide a request body", "Transaction - update");
        return new ResponseEntity<>(service.update(transactionId, request), HttpStatus.OK);
    }

}
