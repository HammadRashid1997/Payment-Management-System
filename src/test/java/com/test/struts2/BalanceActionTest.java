package com.test.struts2;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.dao.BalanceDAO;
import com.example.models.Balance;
import org.junit.Before;
import org.junit.Test;

public class BalanceActionTest {
    private BalanceAction balanceAction;
    private BalanceDAO balanceDAO;

    @Before
    public void setUp() {
        balanceAction = new BalanceAction();
        balanceDAO = mock(BalanceDAO.class);
        balanceAction.setBalanceDAO(balanceDAO);
    }
    
    @Test
    public void testAccountBalanceForm() {
        String result = balanceAction.accountBalanceForm();
        assertEquals("input", result);
    }

    @Test
    public void testRetrieveAccountBalanceSuccess() {
        String accountId = "A1";
        String currency = "USD";
        Balance balance = new Balance(accountId, currency, 5000.0);
        when(balanceDAO.retrieveAccountBalance(accountId)).thenReturn(balance);
        balanceAction.setAccountId(accountId);
        balanceAction.setCurrency(currency);
        String result = balanceAction.retrieveAccountBalance();
        assertEquals("success", result);
        assertEquals(5000.0, balanceAction.getAccountBalance(), 0.001);
    }

    @Test
    public void testRetrieveAccountBalanceNotFound() {
    	String accountId = "A1";
        String currency = "USD";
        when(balanceDAO.retrieveAccountBalance(accountId)).thenReturn(null);
        balanceAction.setAccountId(accountId);
        balanceAction.setCurrency(currency);
        String result = balanceAction.retrieveAccountBalance();
        assertEquals("error", result);
        assertEquals(0.0, balanceAction.getAccountBalance(), 0.001);
    }

    @Test
    public void testRetrieveAccountBalanceException() {
    	String accountId = "A1";
        String currency = "USD";
        when(balanceDAO.retrieveAccountBalance(accountId)).thenThrow(new RuntimeException("Database connection failed"));
        balanceAction.setAccountId(accountId);
        balanceAction.setCurrency(currency);
        String result = balanceAction.retrieveAccountBalance();
        assertEquals("error", result);
        assertEquals(0.0, balanceAction.getAccountBalance(), 0.001);
    }
    
    @Test
    public void testRetrieveAccountBalanceSuccessEURCurrency() {
        String accountId = "A1";
        String currency = "EUR";
        Balance balance = new Balance(accountId, currency, 3000.0);
        when(balanceDAO.retrieveAccountBalance(accountId)).thenReturn(balance);
        balanceAction.setAccountId(accountId);
        balanceAction.setCurrency(currency);
        String result = balanceAction.retrieveAccountBalance();
        assertEquals("success", result);
        assertEquals(3000.0, balanceAction.getAccountBalance(), 0.001);
    }
    
    @Test
    public void testRetrieveAccountBalanceSuccessPKRCurrency() {
        String accountId = "A1";
        String currency = "PKR";
        Balance balance = new Balance(accountId, currency, 3000.0);
        when(balanceDAO.retrieveAccountBalance(accountId)).thenReturn(balance);
        balanceAction.setAccountId(accountId);
        balanceAction.setCurrency(currency);
        String result = balanceAction.retrieveAccountBalance();
        assertEquals("success", result);
        assertEquals(3000.0, balanceAction.getAccountBalance(), 0.001);
    }
    
    @Test
    public void testRetrieveAccountBalanceExceptionHandling() {
        String accountId = "A1";
        String currency = "USD";
        when(balanceDAO.retrieveAccountBalance(accountId)).thenThrow(new RuntimeException("Database connection failed"));
        balanceAction.setAccountId(accountId);
        balanceAction.setCurrency(currency);
        String result = balanceAction.retrieveAccountBalance();
        assertEquals("error", result);
        assertEquals(0.0, balanceAction.getAccountBalance(), 0.001);
        assertEquals(1, balanceAction.getActionErrors().size());
        assertEquals("Failed to retrieve account balance due to an unexpected error: Database connection failed", balanceAction.getActionErrors().iterator().next());
    }

}
