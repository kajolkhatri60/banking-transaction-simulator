package com.bank.model;

import java.time.LocalDateTime;

public class Transaction {
    private Long id;
    private String type;
    private double amount;
    private LocalDateTime timestamp;
    private String threadName;

    public Transaction(Long id, String type, double amount, String threadName) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.threadName = threadName;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }
}
