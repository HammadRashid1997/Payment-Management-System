package com.test.struts2;

import com.example.dao.BankDAO;
import com.example.models.Bank;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

public class BankAction extends ActionSupport {
    private List<Bank> banks;
    private Bank newBank;
    private BankDAO bankDAO;

    public BankAction() {
    	banks = new ArrayList<>();
        newBank = new Bank();
        bankDAO = new BankDAO();
    }
    
    public String createBankForm() {
    	return "input";
    }
    
    public String createBank() {
        try {
        	newBank.setBankId(newBank.getBankId().toUpperCase());
        	newBank.setName(newBank.getName().toUpperCase());
            bankDAO.saveBank(newBank);
            newBank = new Bank();

            addActionMessage("Bank created successfully!");
            return "success";
        } 
        catch (IllegalArgumentException e) {
            addActionError("Failed to create bank: " + e.getMessage());
            e.printStackTrace();
            return "error";
        } 
        catch (Exception e) {
            addActionError("Failed to create bank due to an unexpected error.");
            e.printStackTrace();
            return "error";
        }
    }
    public String listBanks() {
        banks = bankDAO.retrieveBanks();
        addActionMessage("Banks listed successfully!");
        return "success";
    }
    
    public String adminBankList() {
    	banks = bankDAO.retrieveBanks();
    	addActionMessage("Banks listed successfully for admin.");
    	return "success";
    	
    }
    
    public List<Bank> getBanks() {
        return banks;
    }

    public Bank getNewBank() {
        return newBank;
    }

    public void setNewBank(Bank newBank) {
        this.newBank = newBank;
    }

	public void setBankDAO(BankDAO bankDAO) {
		this.bankDAO = bankDAO;
	}
}
