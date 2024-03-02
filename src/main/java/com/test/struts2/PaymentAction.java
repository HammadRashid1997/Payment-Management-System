package com.test.struts2;

import com.example.dao.AccountDAO;
import com.example.dao.PaymentDAO;
import com.example.models.Account;
import com.example.models.Payment;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

public class PaymentAction extends ActionSupport {
	private List<Payment> paymentList;
    private Payment payment;
    private PaymentDAO paymentDAO;
    private AccountDAO accountDAO;
    
    private String paymentId;
    private String senderAccountId;
    private String receiverAccountId;
    private double paymentAmount;

    public PaymentAction() {
    	paymentList = new ArrayList<>();
    	payment = new Payment();
        paymentDAO = new PaymentDAO();
        accountDAO = new AccountDAO();
    }
    
    public String displayCreatePayment() {
        return "input"; 
    }
    
    public String createPayment() {
        try {
            Account senderAccount = accountDAO.getAccountById(payment.getSenderAccountId());
            Account receiverAccount = accountDAO.getAccountById(payment.getReceiverAccountId());

            if (!isValidAccount(senderAccount) || !isValidAccount(receiverAccount)) {
                addActionError("Sender or receiver account does not exist.");
                payment.setStatus("Rejected");
                paymentDAO.updatePaymentStatus(payment);
                //paymentDAO.addPayment(payment);
                return "error";
            }
            
            if (payment.getAmount() > senderAccount.getBalance()) {
                addActionError("Payment amount exceeds sender's balance.");
                payment.setStatus("Rejected");
                paymentDAO.updatePaymentStatus(payment);
                return "error";
            }
            
            if (payment.getAmount() < 0) {
                addActionError("Payment amount cannot be less than 0.");
                payment.setStatus("Rejected");
                paymentDAO.updatePaymentStatus(payment);
                return "error";
            }

            paymentDAO.addPayment(payment);
            double conversionAmount = getConversionRate(senderAccount.getCurrency(), receiverAccount.getCurrency());
            double convertedAmount = payment.getAmount() * conversionAmount;

            senderAccount.setBalance(senderAccount.getBalance() - payment.getAmount());
            accountDAO.updateBalance(senderAccount);

            receiverAccount.setBalance(receiverAccount.getBalance() + convertedAmount);
            accountDAO.updateBalance(receiverAccount);

            payment.setStatus("Approved"); 
            paymentDAO.updatePaymentStatus(payment);

            addActionMessage("Payment created successfully.");
            return "success";
        } 
        catch (IllegalArgumentException e) {
            addActionError("Failed to create payment: " + e.getMessage());
            e.printStackTrace();
            return "error";
        } 
        catch (Exception e) {
            addActionError("Failed to create payment due to an unexpected error: " + e.getMessage());
            return "error";
        }
    }

    private boolean isValidAccount(Account account) {
    	if(account.getAccountId() == " ") {
    		return false;
    	}
    	else {
    		return true;
    	}
    }
    public String listPayments() {
        try {
            paymentList = paymentDAO.getAllPayments();
            System.out.println("Number of payments retrieved: " + paymentList.size());
            return "success";
        } 
        catch (Exception e) {
            addActionError("Failed to retrieve payments due to an unexpected error: " + e.getMessage());
            return "error";
        }
    }
    
    public String showPaymentDetailsForm() {
    	return "input";
    }
    
    public String getPaymentDetails() {
        try {
            Payment retrievedPayment = paymentDAO.getPaymentById(payment.getPaymentId());
            
            if (retrievedPayment != null) {
                payment = retrievedPayment;
                return "success";
            } 
            else {
                addActionError("Payment details not found for Payment ID: " + payment.getPaymentId());
                return "error";
            }
        } 
        catch (Exception e) {
            addActionError("Failed to retrieve payment details due to an unexpected error: " + e.getMessage());
            return "error";
        }
    }
    
    public String showUserPaymentDetailsForm() {
    	return "input";
    }
    
    public String getUserPaymentDetails() {
        try {
            Payment retrievedPayment = paymentDAO.getPaymentById(payment.getPaymentId());                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
            if (retrievedPayment != null) {
                payment = retrievedPayment; 
                return "success";
            } 
            else {
                addActionError("Payment details not found for Payment ID: " + payment.getPaymentId());
                return "error";
            }
        } 
        catch (Exception e) {
            addActionError("Failed to retrieve payment details due to an unexpected error: " + e.getMessage());
            return "error";
        }
    }
    
    public String showPaymentStatusForm() {
        return "input"; 
    }

    public String getPaymentStatus() {
        try {
            String retrievedStatus = paymentDAO.getPaymentStatus(payment.getPaymentId());
            
            if (retrievedStatus != null) {
            	payment.setStatus(retrievedStatus);	
                addActionMessage("Payment Status for Payment ID " + payment.getPaymentId() + ": " + retrievedStatus);
                return "success";
            } else {
                addActionError("Payment status not found for Payment ID: " + payment.getPaymentId());
                return "error";
            }
        } catch (Exception e) {
            addActionError("Failed to retrieve payment status due to an unexpected error: " + e.getMessage());
            return "error";
        }
    }
    
    public String showAdminPaymentStatusForm() {
        return "input";
    }

    public String getAdminPaymentStatus() {
        try {
            String retrievedStatus = paymentDAO.getPaymentStatus(payment.getPaymentId());
            
            if (retrievedStatus != null) {
            	payment.setStatus(retrievedStatus);
                addActionMessage("Payment Status for Payment ID " + payment.getPaymentId() + ": " + retrievedStatus);
                return "success";
            } else {
                addActionError("Payment status not found for Payment ID: " + payment.getPaymentId());
                return "error";
            }
        } catch (Exception e) {
            addActionError("Failed to retrieve payment status due to an unexpected error: " + e.getMessage());
            return "error";
        }
    }

    // Private Helper functions
    double getConversionRate(String senderCurrency, String receiverCurrency) {
        double conversionRate = 0.0;
        if (senderCurrency.equals("USD") && receiverCurrency.equals("PKR")) {
            conversionRate = 284.59;
        } 
        else if (senderCurrency.equals("USD") && receiverCurrency.equals("EUR")) {
            conversionRate = 0.91;
        } 
        else if (senderCurrency.equals("PKR") && receiverCurrency.equals("EUR")) {
            conversionRate = 0.0032;
        } 
        else if (senderCurrency.equals("PKR") && receiverCurrency.equals("USD")) {
            conversionRate = 0.0035;
        } 
        else if (senderCurrency.equals("EUR") && receiverCurrency.equals("PKR")) {
            conversionRate = 311.39;
        } 
        else if (senderCurrency.equals("EUR") && receiverCurrency.equals("USD")) {
            conversionRate = 1.09;
        }
        else if (senderCurrency.equals(receiverCurrency)) {
            conversionRate = 1;
        }
        return conversionRate;
    }


	public AccountDAO getAccountDAO() {
		return accountDAO;
	}

	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
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

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	
    public PaymentDAO getPaymentDAO() {
        return paymentDAO;
    }

    public void setPaymentDAO(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }
}
