package com.example.banking.controller;

import com.example.banking.dto.FundTransferRequest;
import com.example.banking.dto.FundTransferResponse;
import com.example.banking.model.Transaction;
import com.example.banking.service.FundTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private FundTransferService fundTransferService;

//    @PostMapping("/transfer")
//    public ResponseEntity<Transaction> transferFunds(@RequestBody FundTransferRequest request) {
//        Transaction transaction = fundTransferService.transferFunds(
//                request.getFromAccount(),
//                request.getToAccount(),
//                request.getAmount(),
//                request.getDescription());
//        return ResponseEntity.ok(transaction);
//    }

    @PostMapping("/transfer")
    public ResponseEntity<FundTransferResponse> transferFunds(@RequestBody FundTransferRequest request) {
        FundTransferResponse response = fundTransferService.transferFunds(request);
        return ResponseEntity.ok(response);
    }

//    @PostMapping("/transfer")
//    public ResponseEntity<String> transferFunds(@RequestBody TransferRequest request) {
//        boolean success = accountService.transferFunds(request);
//        return success ? ResponseEntity.ok("Transfer successful") : ResponseEntity.badRequest().body("Transfer failed");
//    }

    @GetMapping("/history/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable String accountNumber) {
        List<Transaction> transactions = fundTransferService.getTransactionHistory(accountNumber);
        return ResponseEntity.ok(transactions);
    }
}


