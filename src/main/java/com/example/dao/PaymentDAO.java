package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;

import com.example.models.Payment;


public class PaymentDAO {
    private static final String DB_URL = "jdbc:h2:mem:pms;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "password";

    public PaymentDAO() {
    	try {
            Class.forName("org.h2.Driver");
        } 
        catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load H2 JDBC driver.", e);
        }
        createPaymentsTable();
    }

	public void addPayment(Payment payment) {
        String query = "INSERT INTO payments (paymentid, senderaccountid, receiveraccountid, amount, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setString(1, payment.getPaymentId());
	            preparedStatement.setString(2, payment.getSenderAccountId());
	            preparedStatement.setString(3, payment.getReceiverAccountId());
	            preparedStatement.setDouble(4, payment.getAmount());
	            preparedStatement.setString(5, payment.getStatus());
	
	            preparedStatement.executeUpdate();
        } 
        catch (SQLException e) {
        	throw new RuntimeException("Error saving payment: " + e.getMessage(), e);
        }
    }
	
	public List<Payment> getAllPayments() {
		List<Payment> payments = new ArrayList<>();
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			String query = "SELECT paymentid, senderaccountid, receiveraccountid, amount, status FROM payments";
			try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                	String paymentId = resultSet.getString("paymentid");
	                String senderAccountId = resultSet.getString("senderaccountid");
	                String receiverAccountId = resultSet.getString("receiveraccountid");
	                double amount = resultSet.getDouble("amount");
	                String status = resultSet.getString("status");
	
	                Payment payment = new Payment(paymentId, senderAccountId, receiverAccountId, amount, status);
	                payments.add(payment);
                }
			}
		}
		catch (SQLException e) {
            throw new RuntimeException("Error retrieving banks: " + e.getMessage(), e);
        }
		return payments;
	}

    public Payment getPaymentById(String paymentId) {
    	Payment payment = new Payment();
        String query = "SELECT * FROM payments WHERE paymentid = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setString(1, paymentId);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            	if (resultSet.next()) {
	                    String senderAccountId = resultSet.getString("senderaccountid");
	                    String receiverAccountId = resultSet.getString("receiveraccountid");
	                    double amount = resultSet.getDouble("amount");
	                    String status = resultSet.getString("status");
	
	                    payment = new Payment(paymentId, senderAccountId, receiverAccountId, amount, status);
                }
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return payment;
    }
    
    public String getPaymentStatus(String paymentId) {
    	String retreivedStatus = " ";
        String query = "SELECT status FROM payments WHERE paymentid = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, paymentId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                	retreivedStatus = resultSet.getString("status");
                	System.out.println("The status is: " + retreivedStatus);
                } 
                else {
                    return null;
                }
            }
        } 
        catch (SQLException e) {
            throw new RuntimeException("Error retrieving payment status: " + e.getMessage(), e);
        }
        return retreivedStatus;
    }

    public List<Payment> getPaymentHistoryDetailsForAccount(String senderAccountId) {
        List<Payment> paymentHistory = new ArrayList<>();
        String query = "SELECT paymentid, senderaccountid, receiveraccountid, amount, status FROM payments WHERE senderaccountid = ?";
        
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, senderAccountId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Payment payment = new Payment();
                    payment.setPaymentId(resultSet.getString("paymentid"));
                    payment.setSenderAccountId(resultSet.getString("senderaccountid"));
                    payment.setReceiverAccountId(resultSet.getString("receiveraccountid"));
                    payment.setAmount(resultSet.getDouble("amount"));
                    payment.setStatus(resultSet.getString("status"));
                    paymentHistory.add(payment);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving payment history for account: " + senderAccountId, e);
        }
        
        return paymentHistory;
    }

    
    public void updatePaymentStatus(Payment payment) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "UPDATE payments SET status = ? WHERE paymentId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, payment.getStatus());
                preparedStatement.setString(2, payment.getPaymentId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Private helper functions
    private void createPaymentsTable() {
    	if (!checkIfTableExists()) {
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "CREATE TABLE payments (paymentid VARCHAR(4) Primary Key, senderaccountid VARCHAR(100) Not Null, receiveraccountid VARCHAR(100) Not Null, amount DOUBLE Not Null, status VARCHAR(100))";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.executeUpdate();
                    System.out.println("PAYMENTS TABLE HAS BEEN CREATED.");
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error creating PAYMENTS table: " + e.getMessage(), e);
            }
        }
	}
    
    private boolean checkIfTableExists() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'PAYMENTS'";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking if table exists: " + e.getMessage(), e);
        }
    }
    
//    public static Map<String, Account> fetchAccountsData() {
//        Map<String, Account> accountsData = new HashMap<>();
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery("SELECT * FROM accounts");
//
//            while (resultSet.next()) {
//                String accountId = resultSet.getString("accountid");
//                String bankid = resultSet.getString("bankid");
//                double balance = resultSet.getDouble("balance");
//                String currency = resultSet.getString("currency");
//
//                Account account = new Account(accountId, bankid, balance, currency);
//                accountsData.put(account.getAccountId(), account);
//            }
//        } 
//        catch (SQLException e) {
//            e.printStackTrace();
//        } 
//        finally {
//            try {
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//                if (statement != null) {
//                    statement.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } 
//            catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return accountsData;
//    }
}