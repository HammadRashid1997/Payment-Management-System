package com.test.struts2;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.example.dao.AccountDAO;
import com.example.dao.PaymentDAO;
import com.example.models.Payment;
import org.junit.Before;
import org.junit.Test;

public class PaymentActionTest {
    private PaymentAction paymentAction;
    private PaymentDAO paymentDAO;
    
    @Before
    public void setUp() {
        paymentAction = new PaymentAction();
        paymentDAO = mock(PaymentDAO.class);
        mock(AccountDAO.class);
        paymentAction.setPaymentDAO(paymentDAO);
    }

    @Test
    public void testListPaymentsSuccess() {
        List<Payment> mockPaymentList = new ArrayList<>();
        when(paymentDAO.getAllPayments()).thenReturn(mockPaymentList);

        String result = paymentAction.listPayments();
        assertEquals("success", result);
        assertSame(mockPaymentList, paymentAction.getPaymentList());
    }
    
    @Test
    public void testListPaymentsError() {
        when(paymentDAO.getAllPayments()).thenThrow(new RuntimeException("Error retrieving payments"));

        String result = paymentAction.listPayments();
        assertEquals("error", result);
        assertTrue(paymentAction.getActionErrors().contains("Failed to retrieve payments due to an unexpected error: Error retrieving payments"));
    }

    @Test
    public void testGetPaymentDetailsSuccess() {
        Payment retrievedPayment = new Payment();
        retrievedPayment.setPaymentId("1"); 

        when(paymentDAO.getPaymentById(eq("1"))).thenReturn(retrievedPayment);

        paymentAction.getPayment().setPaymentId("1"); 
        String result = paymentAction.getPaymentDetails();

        assertEquals("success", result);
        assertEquals(retrievedPayment, paymentAction.getPayment());
    }

    @Test
    public void testGetPaymentDetailsNotFound() {
        when(paymentDAO.getPaymentById(eq("1"))).thenReturn(null);

        paymentAction.getPayment().setPaymentId("1");
        String result = paymentAction.getPaymentDetails();

        assertEquals("error", result);
        assertTrue(paymentAction.getActionErrors().contains("Payment details not found for Payment ID: 1"));
    }

    @Test
    public void testGetPaymentStatusSuccess() {
        String paymentStatus = "Pending";
        when(paymentDAO.getPaymentStatus(eq("1"))).thenReturn(paymentStatus);

        paymentAction.getPayment().setPaymentId("1"); 
        String result = paymentAction.getPaymentStatus();

        assertEquals("success", result);
        assertEquals(paymentStatus, paymentAction.getPayment().getStatus());
        assertTrue(paymentAction.getActionMessages().contains("Payment Status for Payment ID 1: " + paymentStatus));
    }

    @Test
    public void testGetPaymentStatusNotFound() {
        when(paymentDAO.getPaymentStatus(eq("1"))).thenReturn(null);

        paymentAction.getPayment().setPaymentId("1");
        String result = paymentAction.getPaymentStatus();

        assertEquals("error", result);
        assertTrue(paymentAction.getActionErrors().contains("Payment status not found for Payment ID: 1"));
    }

    @Test
    public void testGetConversionRateUsdToPkr() {
        double expectedConversionRate = 284.59; 
        double conversionRate = paymentAction.getConversionRate("USD", "PKR");
        assertEquals(expectedConversionRate, conversionRate, 0.01); 
    }
    
    @Test
    public void testGetConversionRateUsdToEur() {
        double expectedConversionRate = 0.91;
        double conversionRate = paymentAction.getConversionRate("USD", "EUR");
        assertEquals(expectedConversionRate, conversionRate, 0.01); 
    }

}
