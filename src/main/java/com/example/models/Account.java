package com.example.models;

public class Account {
	
    public String accountId;
    public String bankId;
    public String currency;
    public double balance;
    private String username;
    
    public Account() {
    	accountId = " ";
    	username = " ";
    	bankId = " ";
    	balance = 3000;
    	currency = " ";
    }
    
    public Account(String accountId, double balance, String currency) {
    	this.accountId = accountId;
        this.currency = currency;
        this.balance = balance;
    }

    public Account(String accountId, String bankId, double balance, String currency) {
        this.accountId = accountId;
        this.bankId = bankId;
        this.balance = balance;
        this.currency = currency;
    }
    
    public Account(String accountId, String username, String bankId, double balance, String currency) {
        this.accountId = accountId;
        this.username = username;
        this.bankId = bankId;
        this.balance = balance;
        this.currency = currency;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deductBalance(double amount) {
        balance -= amount;
    }
    
    public void addBalance(double amount) {
        balance += amount;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
