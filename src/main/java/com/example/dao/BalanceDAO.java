package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.models.Balance;

public class BalanceDAO {
    private static final String DB_URL = "jdbc:h2:mem:pms;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "password";

    private Connection connection;

    public BalanceDAO() {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            createBalancesTable();
        } 
        catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Failed to initialize H2 JDBC connection.", e);
        }
    }

    public Balance retrieveAccountBalance(String accountId) {
        Balance balance = null;
        String query = "SELECT balance FROM accounts WHERE accountid = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, accountId);
           
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    double balanceValue = resultSet.getDouble("balance");
                    balance = new Balance(accountId, balanceValue);
                } else {
                    throw new RuntimeException("Balance for the Account ID not found.");
                }
            }
        } 
        catch (SQLException e) {
            throw new RuntimeException("Error retrieving account balance: " + e.getMessage(), e);
        }
        return balance;
    }

    public void createBalancesTable() {
        try (Statement statement = connection.createStatement()) {
            String checkTableQuery = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'BALANCES'";
            try (ResultSet resultSet = statement.executeQuery(checkTableQuery)) {
                if (resultSet.next() && resultSet.getInt(1) == 0) {
                    String createTableQuery = "CREATE TABLE balances (accountid VARCHAR(10) Primary key, currency VARCHAR(5) Not Null, balance DOUBLE)";
                    statement.execute(createTableQuery);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating BALANCES table: " + e.getMessage(), e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
