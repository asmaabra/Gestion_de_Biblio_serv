/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_de_bibliotheque;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author asma
 */
public class Jdbc {
    
    

    private static final String URL = "jdbc:mysql://localhost:3306/bd_gb2";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
            System.out.println("Connected to the database");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
            throw new SQLException("Database connection error.");
        }
        return conn;
    }
    
     public static Personne getUserByUsername(String username) {
        Personne user = null;
        try (Connection conn = getConnection()) {
            String query = "SELECT * FROM personne WHERE nom = ?";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("personne_id");
                        String name = resultSet.getString("nom");
                        String email = resultSet.getString("email");
                        String password = resultSet.getString("password");
                        String role = resultSet.getString("role");

                        user = new Personne(id,name, email, password, role);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée
        }
        return user;
    }


}
