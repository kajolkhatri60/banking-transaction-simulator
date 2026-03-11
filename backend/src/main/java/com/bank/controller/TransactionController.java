package com.bank.controller;

import com.bank.model.Transaction;
import com.bank.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/balance")
    public ResponseEntity<Map<String, Double>> getBalance() {
        Map<String, Double> response = new HashMap<>();
        response.put("balance", transactionService.getBalance());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/deposit")
    public ResponseEntity<Map<String, Object>> deposit(@RequestBody Map<String, Double> request) {
        try {
            double amount = request.get("amount");
            Future<Double> future = transactionService.deposit(amount);
            double newBalance = future.get();
            
            Map<String, Object> response = new HashMap<>();
            response.put("balance", newBalance);
            response.put("message", "Deposit successful");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Deposit failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Map<String, Object>> withdraw(@RequestBody Map<String, Double> request) {
        try {
            double amount = request.get("amount");
            Future<String> future = transactionService.withdraw(amount);
            String result = future.get();
            
            Map<String, Object> response = new HashMap<>();
            
            if ("SUCCESS".equals(result)) {
                response.put("balance", transactionService.getBalance());
                response.put("message", "Withdrawal successful");
                return ResponseEntity.ok(response);
            } else {
                response.put("error", "Insufficient balance");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Withdrawal failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<Map<String, Object>> transfer(@RequestBody Map<String, Object> request) {
        try {
            Long fromAccount = Long.valueOf(request.get("fromAccount").toString());
            Long toAccount = Long.valueOf(request.get("toAccount").toString());
            double amount = Double.parseDouble(request.get("amount").toString());
            
            Future<String> future = transactionService.transfer(fromAccount, toAccount, amount);
            String result = future.get();
            
            Map<String, Object> response = new HashMap<>();
            
            if ("SUCCESS".equals(result)) {
                response.put("balance", transactionService.getBalance(fromAccount));
                response.put("message", "Transfer successful");
                return ResponseEntity.ok(response);
            } else if ("ACCOUNT_NOT_FOUND".equals(result)) {
                response.put("error", "Account not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else {
                response.put("error", "Insufficient balance");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Transfer failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PostMapping("/simulate")
    public ResponseEntity<Map<String, Object>> simulateTransactions() {
        try {
            List<Map<String, Object>> results = transactionService.simulateConcurrentTransactions();
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Simulated 20 concurrent transactions");
            response.put("results", results);
            response.put("balance", transactionService.getBalance());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Simulation failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getTransactions() {
        return ResponseEntity.ok(transactionService.getTransactions());
    }

    @GetMapping("/transactions/recent")
    public ResponseEntity<List<Transaction>> getRecentTransactions(@RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(transactionService.getRecentTransactions(limit));
    }
}
