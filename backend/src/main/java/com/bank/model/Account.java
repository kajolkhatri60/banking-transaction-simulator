package com.bank.model;

public class Account {
    private Long id;
    private double balance;
    private String accountHolder;

    public Account(Long id, double initialBalance) {
        this.id = id;
        this.balance = initialBalance;
        this.accountHolder = "Account " + id;
    }

    public synchronized void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(Thread.currentThread().getName() + 
                " deposited ₹" + amount + " | Balance: ₹" + balance);
        }
    }

    public synchronized boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + 
                " withdrew ₹" + amount + " | Balance: ₹" + balance);
            return true;
        }
        return false;
    }

    public synchronized boolean transfer(Account toAccount, double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            toAccount.deposit(amount);
            System.out.println(Thread.currentThread().getName() + 
                " transferred ₹" + amount + " from Account " + id + 
                " to Account " + toAccount.getId() + " | Balance: ₹" + balance);
            return true;
        }
        return false;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }
}
