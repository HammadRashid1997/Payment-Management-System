package com.example.models;

public class Balance {
    private String accountId;
    private String currency;
    private double balance;
    
    public Balance() {
    	accountId = "";
    	currency = " ";		
    	balance = 3000.0;
    }

    public Balance(String accountId, double balance) {
    	this.accountId = accountId;
    	this.balance = balance;
    }
    public Balance(String accountId, String currency, double balance) {
        this.accountId = accountId;
        this.currency = currency;
        this.balance = balance;
    }
    
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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
}
