package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.h2.Driver;

import com.example.models.Bank;

public class BankDAO {
    private static final String DB_URL = "jdbc:h2:mem:pms;DB_CLOSE_DELAY=-1\n" + "";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "password";

    public BankDAO() {
        try {
            Class.forName("org.h2.Driver");
        } 
        catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load H2 JDBC driver.", e);
        }
        createBanksTable();
        initializeBanks();
    }

    public void saveBank(Bank bank) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO banks (bankid, name) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, bank.getBankId());
                statement.setString(2, bank.getName());
                System.out.println("Saving bank with bankid: " + bank.getBankId()); 
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving bank: " + e.getMessage(), e);
        }
    }

    public List<Bank> retrieveBanks() {
        List<Bank> banks = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT bankid, name FROM banks";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String code = resultSet.getString("bankid");
                    String bankName = resultSet.getString("name");
                    Bank bank = new Bank(code, bankName);
                    banks.add(bank);
                }
            }
        } 
        catch (SQLException e) {
            throw new RuntimeException("Error retrieving banks: " + e.getMessage(), e);
        }
        return banks;
    }

    // Helper functions
    private void createBanksTable() {
        if (!checkIfTableExists()) {
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "CREATE TABLE banks (bankid VARCHAR(4) Primary Key, name VARCHAR(100) Unique Not Null)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error creating BANKS table: " + e.getMessage(), e);
            }
        }
    }

    private boolean checkIfTableExists() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'BANKS'";
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

    private void initializeBanks() {
        if (!areBanksInitialized()) {
            saveBank(new Bank("MEZN", "MEEZAN BANK"));
            saveBank(new Bank("CHAS", "CHASE BANK"));
            saveBank(new Bank("ABAA", "ABAA BANK"));
            System.out.println("Banks have been initialized");
        }
    }
    
    private boolean areBanksInitialized() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT COUNT(*) FROM banks";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
                else {
                	return false;
                }
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean bankExists(String bankId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM banks WHERE bankid = ?")) {
            statement.setString(1, bankId);
            try (ResultSet resultSet = statement.executeQuery()) {
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
