package com.mycompany.gestion_de_bibliotheque;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Signin extends JFrame {
    Color lightGreen = new Color(144, 238, 144);
    Color beige = new Color(245, 245, 220);

    public Signin() {
        setTitle("Sign up");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load the image from the file
        ImageIcon backgroundImage = new ImageIcon("images/Accueil.jpeg");

        // Create a JLabel with the background image
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());

        // Create a panel to organize the components
        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        setContentPane(contentPane);
        JPanel contentPane1 = new JPanel();
        JLabel messageLabel = new JLabel("Create your account:");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Increased font size for better visibility

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        JPasswordField confirmPasswordField = new JPasswordField(20);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(e -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            char[] password = passwordField.getPassword();
            char[] confirmPassword = confirmPasswordField.getPassword();

            // Perform validation and registration logic here
            // For demonstration, just printing the values
            System.out.println("Username: " + username);
            System.out.println("Email: " + email);
            System.out.println("Password: " + new String(password));
            System.out.println("Confirm Password: " + new String(confirmPassword));

            // Add user to the database
            addUserToDatabase(username, new String(password), email); // Pass email parameter
        });

        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordField);
        panel.add(new JLabel());
        panel.add(signUpButton);
        panel.add(new JLabel());
        panel.add(new JLabel());

        JLabel signupLabel = new JLabel("Already a member? Log in here!");
        Font linkFont = new Font("Arial", Font.BOLD, 14);
        signupLabel.setFont(linkFont);
        signupLabel.setForeground(Color.DARK_GRAY);
        signupLabel.setHorizontalAlignment(SwingConstants.CENTER);
        signupLabel.setBounds(200, 300, 400, 30);
        contentPane1.add(signupLabel);

        signupLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                signupLabel.setForeground(lightGreen);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                signupLabel.setForeground(Color.DARK_GRAY);
            }
        });

        contentPane1.add(messageLabel, BorderLayout.CENTER);

        contentPane1.add(panel, BorderLayout.CENTER);
        contentPane1.add(signupLabel, BorderLayout.CENTER);

        contentPane1.setPreferredSize(new Dimension(550, 350));

        contentPane.add(contentPane1, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void display() {
        setVisible(true);
    }

    private void addUserToDatabase(String username, String password, String email) {
    try (Connection conn = Jdbc.getConnection()) {
        String query = "INSERT INTO personne (nom, password, email, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.setString(4, "user");

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User added successfully.");
            } else {
                System.out.println("Failed to add user.");
            }
        }
    } catch (SQLException e) {
        System.out.println("Error adding user to database: " + e.getMessage());
    }
}

  
}
