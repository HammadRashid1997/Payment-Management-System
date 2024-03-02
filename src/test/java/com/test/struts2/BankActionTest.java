package com.test.struts2;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import com.example.dao.BankDAO;
import com.example.models.Bank;
import org.junit.Before;
import org.junit.Test;

public class BankActionTest {
    private BankAction bankAction;
    private BankDAO bankDAO;

    @Before
    public void setUp() {
        bankAction = new BankAction();
        bankDAO = mock(BankDAO.class);
        bankAction.setBankDAO(bankDAO);
    }

    @Test
    public void testCreateBankSuccess() {
        Bank newBank = new Bank();
        newBank.setName("Test Bank");
        String result = bankAction.createBank();
        assertEquals("Bank created successfully!", bankAction.getActionMessages().iterator().next());
        assertEquals("success", result);
        verify(bankDAO, times(1)).saveBank(any(Bank.class));
    }

    @Test
    public void testListBanksSuccess() {
        List<Bank> banks = new ArrayList<>();
        banks.add(new Bank("Test Bank 1", "ID1"));
        banks.add(new Bank("Test Bank 2", "ID2"));
        when(bankDAO.retrieveBanks()).thenReturn(banks);
        String result = bankAction.listBanks();
        assertEquals("success", result);
        assertEquals(banks, bankAction.getBanks());
        verify(bankDAO, times(1)).retrieveBanks();
    }

    @Test
    public void testListBanksEmpty() {
        List<Bank> banks = new ArrayList<>();
        when(bankDAO.retrieveBanks()).thenReturn(banks);
        String result = bankAction.listBanks();
        assertEquals("success", result);
        assertEquals(banks, bankAction.getBanks());
        verify(bankDAO, times(1)).retrieveBanks();
    }
}