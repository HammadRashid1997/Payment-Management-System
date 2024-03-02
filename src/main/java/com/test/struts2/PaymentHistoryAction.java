package com.test.struts2;

import com.example.dao.PaymentDAO;
import com.example.models.Payment;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class PaymentHistoryAction extends ActionSupport {
    private String accountId;
    private List<Payment> paymentHistory;
    private PaymentDAO paymentDAO;

    public PaymentHistoryAction() {
        paymentDAO = new PaymentDAO(); 
    }
    
    public String showPaymentHistoryForm() {
    	return "input";
    }

    public String retrievePaymentHistoryOfAccount() {
        try {
            paymentHistory = (paymentDAO.getPaymentHistoryDetailsForAccount(accountId));
            return "success";
        } catch (Exception e) {
            addActionError("An error occurred while fetching the payment history.");
            return "error";
        }
    }

    
    public String showUserPaymentHistoryForm() {
    	return "input";
    }
    
    public String retrieveUserPaymentHistoryOfAccount() {
    	try {
    		paymentHistory = (paymentDAO.getPaymentHistoryDetailsForAccount(accountId));
    	     return "success";
    	}
    	catch(Exception e) {
    		addActionError("An error occurred while fetching the payment history.");
    		return "error";
    	}  
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

	public List<Payment> getPaymentHistory() {
		return paymentHistory;
	}

	public void setPaymentHistory(List<Payment> paymentHistory) {
		this.paymentHistory = paymentHistory;
	}


}
