package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.models.Admin;

public class AdminDAO {
	private static final String DB_URL = "jdbc:h2:mem:pms;DB_CLOSE_DELAY=-1\n" + "";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "password";
    
    public AdminDAO() {
    	try {
            Class.forName("org.h2.Driver");
        } 
        catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load H2 JDBC driver.", e);
        }
    	createAdminsTable();
    	initializeAdminRecord();
    }
    
    public boolean isAdminValid(String username, String password) {
        // Check if the provided username and password match the admin credentials
        return "admin".equals(username) && "admin".equals(password);
    }


//    public boolean isAdminValid(String username, String password) {
//        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
//            String query = "SELECT username, password FROM admins WHERE username = admin AND password = admin";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                preparedStatement.setString(1, username);
//                preparedStatement.setString(2, password);
//
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    if (resultSet.next()) {
//                        return true;
//                    } 
//                    else {
//                        return false;
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    
    // Helper functions
    public void createAdminsTable() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS admins (username VARCHAR(100) PRIMARY KEY,password VARCHAR(100) NOT NULL)";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(createTableQuery)) {
                preparedStatement.executeUpdate();
                System.out.println("admins table created.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void initializeAdminRecord() {
        if (!isAdminRecordExists()) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword("admin");
            System.out.println("Admin record initialized.");
            saveAdmin(admin);
        }
    }

    public void saveAdmin(Admin admin) {
        String query = "INSERT INTO admins (username, password) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, admin.getUsername());
            preparedStatement.setString(2, admin.getPassword());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Admin record inserted successfully.");
            }
            else {
                System.out.println("Failed to insert admin record.");
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

	private boolean isAdminRecordExists() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT COUNT(*) FROM admins WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, "admin");
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
