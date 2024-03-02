package com.test.struts2;

import com.example.dao.AccountDAO;
import com.example.dao.BankDAO;
import com.example.models.Account;
import com.example.models.Payment;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Arrays;
import java.util.List;

public class AccountAction extends ActionSupport {
    private AccountDAO accountDAO;
    private Account newAccount;
    private List<Account> accounts;
    private String accountId;
    private String username;
    private List<Account> userAccounts;
    private List<Payment> paymentHistory;
    private BankDAO bankDAO;

    public AccountAction() {
        newAccount = new Account();
        accountDAO = new AccountDAO();
        bankDAO = new BankDAO();
    }
    
    public String input() {
        return "input";
    }
    
    public String createAccount() {
        try {
            String inputCurrency = newAccount.getCurrency().toUpperCase();
            String inputBank = newAccount.getBankId(); // Get the input bank name

            if (isValidCurrency(inputCurrency) && isValidBank(inputBank)) {
                if (isCurrencyUnique(inputCurrency, newAccount.getUsername())) {
                    newAccount.setCurrency(inputCurrency);
                    accountDAO.saveAccount(newAccount);
                    addActionMessage("Account created successfully!");
                    return "success";
                } else {
                    addActionError("Currency must be unique for each account.");
                }
            } else {
                addActionError("Invalid currency or bank.");
            }
        } catch (IllegalArgumentException e) {
            addActionError("Failed to create account: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            addActionError("Failed to create account due to an unexpected error.");
            e.printStackTrace();
        }
        return "error";
    }
    
    private boolean isValidBank(String bank) {
        try {
            return bankDAO.bankExists(bank); 
        } 
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isCurrencyUnique(String currency, String username) {
        List<Account> userAccounts = accountDAO.getAccountsByUsername(username);
        return userAccounts.stream().noneMatch(account -> account.getCurrency().equalsIgnoreCase(currency));
    }

    private boolean isValidCurrency(String currency) {
        List<String> allowedCurrencies = Arrays.asList("USD", "PKR", "EUR");
        return allowedCurrencies.contains(currency);
    }

	public void validateCreateAccount() {
        if (newAccount != null) {
            if (newAccount.getAccountId() == null || newAccount.getAccountId().trim().isEmpty()) {
                addFieldError("newAccount.accountId", "Account ID is required.");
            }
            if (newAccount.getUsername() == null || newAccount.getUsername().trim().isEmpty()) {
                addFieldError("newAccount.username", "Username is required.");
            }
            if (newAccount.getBankId() == null || newAccount.getBankId().trim().isEmpty()) {
                addFieldError("newAccount.bankId", "Bank ID is required.");
            }
            if (newAccount.getBalance() == 0 || newAccount.getBalance() < 0) {
                addFieldError("newAccount.balance", "Balance is required.");
            }
            if (newAccount.getCurrency() == null || newAccount.getCurrency().trim().isEmpty()) {
                addFieldError("newAccount.currency", "Currency is required.");
            }
        }
    }
    
    public String listAccounts() {
        accounts = accountDAO.retrieveAccounts();
        addActionMessage("Accounts listed successfully!");
        return "success";
    }
    
    public String showAccountDetailsForm() {
    	return "input";
    }

    public String getAccountInformation() {
    	if (accountId != null && !accountId.trim().isEmpty()) {
            Account account = accountDAO.getAccountDetails(accountId);
            if (account != null) {
                newAccount = account;
                return "success";
            }
        }
        return "error";
    }
    
    public String showAdminAccountDetailsForm() {
    	return "input";
    }
    
    public String getAdminAccountInformation() {
    	if (accountId != null && !accountId.trim().isEmpty()) {
            Account account = accountDAO.getAccountDetails(accountId);
            if (account != null) {
                newAccount = account;
                return "success";
            }
        }
        return "error";
    }
    
    // Action to display update account form
    public String showUpdateAccountForm() {
        return "input";
    }

    public String updateAccount() {
        try {
            Account accountToUpdate = accountDAO.getAccountDetails(accountId);
            if (accountToUpdate != null && accountToUpdate.getAccountId() != "" && newAccount.getBalance() >= 0) {
                String uppercaseCurrency = newAccount.getCurrency().toUpperCase();
                accountToUpdate.setBalance(newAccount.getBalance());
                accountToUpdate.setCurrency(uppercaseCurrency);
                accountDAO.updateTheAccount(accountToUpdate);
                addActionMessage("Account updated successfully.");
                return "success";
            } 
            else {
                if (newAccount.getBalance() < 0) {
                    addActionError("Balance cannot be less than 0.");
                } 
                else {
                    addActionError("Invalid account ID.");
                }
                return "error";
            }
        } catch (Exception e) {
            addActionError("Failed to update the account.");
            return "error";
        }
    }

    
    // Action to display delete account form
    public String showDeleteAccountForm() {
        return "input";
    }

   public String deleteAccount() {
	   try {
		   Account accountToDelete = accountDAO.getAccountDetails(accountId);
	        if (accountToDelete != null) {
	            accountDAO.deleteAccount(accountId);
	            addActionMessage("Account with ID " + accountId + " deleted.");
	            return "success";
	        } 
	        else {
	        	addActionError("Account with ID " + accountId + " not found.");
	            return "error";
	        }
	   }
	   catch(Exception e) {
		   addActionError("Account cannot be deleted.");
		   return "error";
	   }
    }
   
   public String displayUserAccountsForm() {
       return "input";
   }

   public String displayUserAccounts() {
	   System.out.println("Username: " + getUsername());
       if (getUsername() == null || getUsername().trim().isEmpty()) {
           addActionError("Username cannot be empty");
           return "error";
       }
       userAccounts = accountDAO.getAccountsByUsername(getUsername());
       System.out.println("User Accounts size: " + userAccounts.size());
       System.out.println("getUserAccounts(): " + getUserAccounts());
       
       if (getUserAccounts().isEmpty()) {
           addActionMessage("No accounts found for the user: " + getUsername());
           return "error";
       }
       return "success";
   }

   public List<Payment> getPaymentHistory() {
       return paymentHistory;
   }

   public void setPaymentHistory(List<Payment> paymentHistory) {
       this.paymentHistory = paymentHistory;
   }

    public Account getNewAccount() {
        return newAccount;
    }

    public void setNewAccount(Account newAccount) {
        this.newAccount = newAccount;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    
    public List<Account> getAccounts() {
        return accounts;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Account> getUserAccounts() {
		return userAccounts;
	}

	public void setUserAccounts(List<Account> userAccounts) {
		this.userAccounts = userAccounts;
	}

	public BankDAO getBankDAO() {
		return bankDAO;
	}

	public void setBankDAO(BankDAO bankDAO) {
		this.bankDAO = bankDAO;
	}
}
