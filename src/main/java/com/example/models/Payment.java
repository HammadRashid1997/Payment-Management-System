package com.example.models;

import java.util.UUID;

public class Payment {

    private String paymentId;
    private String senderAccountId;
    private String receiverAccountId;
    private double amount;
    private String status;

    public Payment() {
    	paymentId = UUID.randomUUID().toString().substring(0, 4);
        senderAccountId = "";
        receiverAccountId = "";
        amount = 0.0;
        status = "Pending";
    }

    public Payment(String paymentId, String senderAccountId, String receiverAccountId, double amount, String status) {
        this.paymentId = paymentId;
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
        this.amount = amount;
        this.status = status;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(String senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public String getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(String receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
