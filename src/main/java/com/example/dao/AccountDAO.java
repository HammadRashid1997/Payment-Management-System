package com.example.dao;

import java.sql.*;
import java.util.*;
import com.example.models.Account;

public class AccountDAO {
	private static final String DB_URL = "jdbc:h2:mem:pms;DB_CLOSE_DELAY=-1\n"
    		+ "";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "password";

    Connection connection;
    public AccountDAO() {
    	 try {
             Class.forName("org.h2.Driver");
         } 
         catch (ClassNotFoundException e) {
             throw new RuntimeException("Failed to load H2 JDBC driver.", e);
         }
        createAccountsTable();
    }
    
    public AccountDAO(Connection connection) {
    	this.connection = connection;
    }

    public void saveAccount(Account account) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO accounts (accountid, username, bankid, balance, currency) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, account.getAccountId());
                statement.setString(2, account.getUsername());
                statement.setString(3, account.getBankId());
                statement.setDouble(4, account.getBalance());
                statement.setString(5, account.getCurrency());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving account: " + e.getMessage(), e);
        }
    }


    public List<Account> retrieveAccounts() {
        List<Account> accounts = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT accountid, username, bankid, balance, currency FROM accounts";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String accountId = resultSet.getString("accountid");
                    String username = resultSet.getString("username");
                    String bankId = resultSet.getString("bankid");
                    double balance = resultSet.getDouble("balance");
                    String currency = resultSet.getString("currency");
                    Account account = new Account(accountId, username, bankId, balance, currency);
                    accounts.add(account);
                }
            }
        } 
        catch (SQLException e) {
        	//throw new RuntimeException("Error saving account: " + e.getMessage(), e);
            e.printStackTrace();
        }
        return accounts;
    }

    public Account getAccountDetails(String accountId) {
        Account account = new Account(); 

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT accountid, username, bankid, balance, currency FROM accounts WHERE accountid = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, accountId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String username = resultSet.getString("username");
                        String bankId = resultSet.getString("bankid");
                        double balance = resultSet.getDouble("balance");
                        String currency = resultSet.getString("currency");
                        account = new Account(accountId, username, bankId, balance, currency);
                    }
                    else {
                    	 System.out.println("Account not found for accountId: " + accountId);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving account: " + e.getMessage(), e);
        }

        return account;
    }


    public void updateTheAccount(Account account) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE accounts SET balance = ?, currency = ? WHERE accountid = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setDouble(1, account.getBalance());
                statement.setString(2, account.getCurrency());
                statement.setString(3, account.getAccountId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating account: " + e.getMessage(), e);
        }
    }

    public void deleteAccount(String accountId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM accounts WHERE accountid = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, accountId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting account: " + e.getMessage(), e);
        }
    }
    
    private void createAccountsTable() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "CREATE TABLE IF NOT EXISTS ACCOUNTS (accountid VARCHAR(10) PRIMARY KEY, username VARCHAR(100) Not Null, bankid VARCHAR(10) Not Null, balance DOUBLE, currency VARCHAR(5) Not Null)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.executeUpdate();
            }
        } 
        catch (SQLException e) {
            throw new RuntimeException("Error creating ACCOUNTS table: " + e.getMessage(), e);
        }
    }
    
    public void updateBalance(Account account) {
        String query = "UPDATE accounts SET balance = ? WHERE accountid = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setString(2, account.getAccountId());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating account balance: " + e.getMessage(), e);
        }
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT accountid, currency, balance FROM accounts";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String accountId = resultSet.getString("accountid");
                double balance = resultSet.getDouble("balance");
                String currency = resultSet.getString("currency");
                Account account = new Account(accountId, balance, currency);
                accounts.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving accounts: " + e.getMessage(), e);
        }
        return accounts;
    }
    
    public Account getAccountById(String accountId) {
    	Account account = new Account();
        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM accounts WHERE accountid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, accountId);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                account.setAccountId(resultSet.getString("accountid"));
	                account.setUsername(resultSet.getString("username"));
	                account.setBankId(resultSet.getString("bankid"));
	                account.setBalance(resultSet.getDouble("balance"));
	                account.setCurrency(resultSet.getString("currency"));
	            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }
    
    public List<Account> getAccountsByUsername(String username) {
        List<Account> accounts = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT accountid, username, bankid, balance, currency FROM accounts WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Account account = new Account();
                        account.setAccountId(resultSet.getString("accountid"));
                        account.setUsername(resultSet.getString("username"));
                        account.setBankId(resultSet.getString("bankid"));
                        account.setBalance(resultSet.getDouble("balance"));
                        account.setCurrency(resultSet.getString("currency"));
                        accounts.add(account);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }
    
    // Add this method to your Account class
    public static boolean isValidAccount(String accountId) {
        String query = "SELECT COUNT(*) FROM accounts WHERE accountid = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, accountId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }

}


