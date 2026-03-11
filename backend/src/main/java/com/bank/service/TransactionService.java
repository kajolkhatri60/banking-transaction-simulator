package com.bank.service;

import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;

@Service
public class TransactionService {
    private final Map<Long, Account> accounts;
    private final TransactionRepository transactionRepository;
    private final ExecutorService executorService;
    private final Random random;

    public TransactionService(TransactionRepository transactionRepository) {
        this.accounts = new ConcurrentHashMap<>();
        this.accounts.put(1L, new Account(1L, 5000.0));
        this.accounts.put(2L, new Account(2L, 3000.0));
        this.transactionRepository = transactionRepository;
        this.executorService = Executors.newFixedThreadPool(5);
        this.random = new Random();
    }

    public Future<Double> deposit(double amount) {
        return deposit(1L, amount);
    }

    public Future<Double> deposit(Long accountId, double amount) {
        return executorService.submit(() -> {
            Account account = accounts.get(accountId);
            if (account == null) {
                throw new IllegalArgumentException("Account not found");
            }
            account.deposit(amount);
            String threadName = Thread.currentThread().getName();
            Transaction transaction = new Transaction(null, "DEPOSIT", amount, threadName);
            transactionRepository.save(transaction);
            return account.getBalance();
        });
    }

    public Future<String> withdraw(double amount) {
        return withdraw(1L, amount);
    }

    public Future<String> withdraw(Long accountId, double amount) {
        return executorService.submit(() -> {
            Account account = accounts.get(accountId);
            if (account == null) {
                return "ACCOUNT_NOT_FOUND";
            }
            String threadName = Thread.currentThread().getName();
            boolean success = account.withdraw(amount);
            
            if (success) {
                Transaction transaction = new Transaction(null, "WITHDRAW", amount, threadName);
                transactionRepository.save(transaction);
                return "SUCCESS";
            } else {
                return "INSUFFICIENT_BALANCE";
            }
        });
    }

    public Future<String> transfer(Long fromAccountId, Long toAccountId, double amount) {
        return executorService.submit(() -> {
            Account fromAccount = accounts.get(fromAccountId);
            Account toAccount = accounts.get(toAccountId);
            
            if (fromAccount == null || toAccount == null) {
                return "ACCOUNT_NOT_FOUND";
            }
            
            String threadName = Thread.currentThread().getName();
            boolean success = fromAccount.transfer(toAccount, amount);
            
            if (success) {
                Transaction transaction = new Transaction(null, "TRANSFER", amount, threadName);
                transactionRepository.save(transaction);
                return "SUCCESS";
            } else {
                return "INSUFFICIENT_BALANCE";
            }
        });
    }

    public List<Map<String, Object>> simulateConcurrentTransactions() throws InterruptedException {
        List<Future<Map<String, Object>>> futures = new ArrayList<>();
        
        for (int i = 0; i < 20; i++) {
            Future<Map<String, Object>> future = executorService.submit(() -> {
                Map<String, Object> result = new HashMap<>();
                String threadName = Thread.currentThread().getName();
                
                // Randomly choose transaction type
                int transactionType = random.nextInt(3);
                double amount = 100 + random.nextInt(900); // Random amount between 100-1000
                
                try {
                    Thread.sleep(random.nextInt(100)); // Simulate processing time
                    
                    if (transactionType == 0) {
                        // Deposit
                        accounts.get(1L).deposit(amount);
                        Transaction transaction = new Transaction(null, "DEPOSIT", amount, threadName);
                        transactionRepository.save(transaction);
                        result.put("type", "DEPOSIT");
                        result.put("status", "SUCCESS");
                    } else if (transactionType == 1) {
                        // Withdraw
                        boolean success = accounts.get(1L).withdraw(amount);
                        if (success) {
                            Transaction transaction = new Transaction(null, "WITHDRAW", amount, threadName);
                            transactionRepository.save(transaction);
                            result.put("status", "SUCCESS");
                        } else {
                            result.put("status", "INSUFFICIENT_BALANCE");
                        }
                        result.put("type", "WITHDRAW");
                    } else {
                        // Transfer
                        boolean success = accounts.get(1L).transfer(accounts.get(2L), amount);
                        if (success) {
                            Transaction transaction = new Transaction(null, "TRANSFER", amount, threadName);
                            transactionRepository.save(transaction);
                            result.put("status", "SUCCESS");
                        } else {
                            result.put("status", "INSUFFICIENT_BALANCE");
                        }
                        result.put("type", "TRANSFER");
                    }
                    
                    result.put("amount", amount);
                    result.put("thread", threadName);
                    
                } catch (InterruptedException e) {
                    result.put("status", "ERROR");
                    Thread.currentThread().interrupt();
                }
                
                return result;
            });
            
            futures.add(future);
        }
        
        // Wait for all transactions to complete
        List<Map<String, Object>> results = new ArrayList<>();
        for (Future<Map<String, Object>> future : futures) {
            try {
                results.add(future.get());
            } catch (ExecutionException e) {
                Map<String, Object> errorResult = new HashMap<>();
                errorResult.put("status", "ERROR");
                errorResult.put("message", e.getMessage());
                results.add(errorResult);
            }
        }
        
        return results;
    }

    public double getBalance() {
        return getBalance(1L);
    }

    public double getBalance(Long accountId) {
        Account account = accounts.get(accountId);
        return account != null ? account.getBalance() : 0.0;
    }

    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getRecentTransactions(int limit) {
        List<Transaction> allTransactions = transactionRepository.findAll();
        int size = allTransactions.size();
        int fromIndex = Math.max(0, size - limit);
        return allTransactions.subList(fromIndex, size);
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
