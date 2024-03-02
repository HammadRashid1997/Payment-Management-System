package com.test.struts2;

import com.example.dao.BalanceDAO;
import com.example.models.Balance;
import com.opensymphony.xwork2.ActionSupport;

public class BalanceAction extends ActionSupport {
    private BalanceDAO balanceDAO;
    private String accountId;
    private String currency;
    private double accountBalance;

    public BalanceAction() {
    	balanceDAO = new BalanceDAO();
    }
    
    public String accountBalanceForm() {
    	return "input";
    }

    public String retrieveAccountBalance() {
        try {
            Balance balance = getBalanceDAO().retrieveAccountBalance(accountId);

            if (balance != null) {
                accountBalance = balance.getBalance();
                addActionMessage("Account balance retrieved successfully.");
                addActionMessage("Account ID: " + balance.getAccountId());
                addActionMessage("Currency: " + balance.getCurrency());
                addActionMessage("Balance: " + balance.getBalance());
                return "success";
            } else {
                addActionError("Account balance not found for Account ID: " + accountId);
                return "error";
            }
        } catch (Exception e) {
            addActionError("Failed to retrieve account balance due to an unexpected error: " + e.getMessage());
            return "error";
        }
    }
    
    public String adminAccountBalanceForm() {
    	return "input";
    }
    
    public String retrieveAdminAccountBalance() {
        try {
            Balance balance = getBalanceDAO().retrieveAccountBalance(accountId);

            if (balance != null) {
                accountBalance = balance.getBalance();
                addActionMessage("Account balance retrieved successfully.");
                addActionMessage("Account ID: " + balance.getAccountId());
                addActionMessage("Currency: " + balance.getCurrency());
                addActionMessage("Balance: " + balance.getBalance());
                return "success";
            } else {
                addActionError("Account balance not found for Account ID: " + accountId);
                return "error";
            }
        } catch (Exception e) {
            addActionError("Failed to retrieve account balance due to an unexpected error: " + e.getMessage());
            return "error";
        }
    }

	public BalanceDAO getBalanceDAO() {
		return balanceDAO;
	}

	public void setBalanceDAO(BalanceDAO balanceDAO) {
		this.balanceDAO = balanceDAO;
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

	public double getAccountBalance() {
		return accountBalance;
	}

}
