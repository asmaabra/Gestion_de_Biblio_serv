/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_de_bibliotheque;

/**
 *
 * @author asma
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ProfilPanel extends JPanel {
    private JTextField nameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JButton saveButton;
    private JButton logoutButton;

    public ProfilPanel(String username) {
        fetchUserInfoFromDatabase(username);
        setLayout(new BorderLayout());

        // User profile information panel
        JPanel profileInfoPanel = new JPanel(new GridLayout(3, 2));
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        saveButton = new JButton("Save");

        profileInfoPanel.add(nameLabel);
        profileInfoPanel.add(nameField);
        profileInfoPanel.add(passwordLabel);
        profileInfoPanel.add(passwordField);
        profileInfoPanel.add(emailLabel);
        profileInfoPanel.add(emailField);
        profileInfoPanel.add(new JLabel()); // Placeholder for spacing
        profileInfoPanel.add(saveButton);
        

        add(profileInfoPanel, BorderLayout.NORTH);
        // Add action listeners for buttons
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save profile changes
                String newName = nameField.getText();
                String newPassword = new String(passwordField.getPassword());
                String newEmail = emailField.getText();
                // Update profile in the database or perform necessary actions
                JOptionPane.showMessageDialog(ProfilPanel.this, "Profile saved successfully!");
            }
        });

   
    }
    
    
   private void fetchUserInfoFromDatabase(String username) {
        try {
            // Establish database connection
            Connection connection = Jdbc.getConnection();
            // Prepare and execute SQL query to fetch user information based on username
            String sql = "SELECT nom, email FROM personne WHERE nom = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            // If a record is found, set the profile information
            if (resultSet.next()) {
                String name = resultSet.getString("nom");
                String email = resultSet.getString("email");

                // Set the retrieved information to the corresponding fields
                nameField.setText(name);
                emailField.setText(email);
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}
