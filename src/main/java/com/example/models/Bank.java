package com.example.models;

public class Bank {

    private String bankId;
    private String name;

    public Bank() {
    	bankId = "";
        name = "";
    }

    public Bank(String bankId, String name) {
    	this.bankId = bankId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
}
