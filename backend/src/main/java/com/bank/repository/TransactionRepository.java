package com.bank.repository;

import com.bank.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TransactionRepository {
    private final List<Transaction> transactions = new CopyOnWriteArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public Transaction save(Transaction transaction) {
        transaction.setId(idCounter.getAndIncrement());
        transactions.add(transaction);
        return transaction;
    }

    public List<Transaction> findAll() {
        return new ArrayList<>(transactions);
    }

    public void clear() {
        transactions.clear();
        idCounter.set(1);
    }
}
