/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_de_bibliotheque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {
    Color lightGreen = new Color(144, 238, 144);
    Color beige = new Color(245, 245, 220);

    public Login() {
        setTitle("Login");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Charger l'image depuis le fichier
        ImageIcon backgroundImage = new ImageIcon("images/Accueil.jpeg");

        // Créer un JLabel avec l'image de fond
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());

        // Créer un panneau pour organiser les composants
        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        setContentPane(contentPane);
        JPanel contentPane1 = new JPanel();
        JLabel messageLabel = new JLabel("Entrer vos informations pour accéder à votre compte");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER); // Aligner le texte au centre
        messageLabel.setFont(new Font("Arial", Font.BOLD, 15)); // Définir la police et la taille du texte

        // Créer un JLabel pour le lien d'inscription
        JLabel signupLabel = new JLabel(" Nouveau sur notre plateforme ? Inscrivez-vous ici ");
        Font linkFont = new Font("Arial", Font.ITALIC, 14);
        signupLabel.setFont(linkFont);
        signupLabel.setForeground(Color.DARK_GRAY); // Couleur de lien
        signupLabel.setHorizontalAlignment(SwingConstants.CENTER);
        signupLabel.setBounds(200, 300, 400, 30);
        contentPane.add(signupLabel);

        // Ajouter un gestionnaire d'événements pour le clic sur le lien d'inscription
        signupLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                Signin SigninWindow = new Signin();
                SigninWindow.display();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Changer la couleur du lien lorsqu'il est survolé
                signupLabel.setForeground(lightGreen);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Revenir à la couleur de lien normale lorsque la souris quitte le lien
                signupLabel.setForeground(Color.DARK_GRAY);
            }
        });

        contentPane1.add(messageLabel, BorderLayout.CENTER);

        contentPane1.setBackground(Color.WHITE);

        // Créer un JPanel pour organiser les composants au centre
        JPanel centerPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // 3 lignes, 2 colonnes avec espacement de 10 pixels
        centerPanel.setBackground(Color.WHITE); // Couleur de fond du JPanel

        // Créer les composants : JLabels, JTextFields, JButton
        JLabel userLabel = new JLabel("Nom d'utilisateur:");
        JTextField userField = new JTextField();
        JLabel passwordLabel = new JLabel("Mot de passe:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Se connecter");
        JLabel errorLabel = new JLabel(""); // JLabel pour afficher les erreurs
        errorLabel.setForeground(Color.RED); // Couleur rouge pour les erreurs
        centerPanel.add(userLabel);
        centerPanel.add(userField);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordField);
        centerPanel.add(new JLabel()); // Espace vide pour l'esthétique
        centerPanel.add(loginButton);

        // Ajouter les composants au JPanel
        contentPane1.add(centerPanel, BorderLayout.CENTER);
        contentPane1.add(signupLabel, BorderLayout.CENTER);
        contentPane1.add(errorLabel, BorderLayout.CENTER); // Ajouter le JLabel des erreurs

        contentPane1.setPreferredSize(new Dimension(400, 250)); // Ajuster les dimensions au besoin
        centerPanel.setPreferredSize(new Dimension(400, 170)); // Ajuster les dimensions au besoin

        contentPane.add(contentPane1, BorderLayout.CENTER);

        // Ajouter un gestionnaire d'événements pour le clic sur le bouton de connexion
   /*     loginButton.addActionListener(e -> {
            String username = userField.getText();
            char[] password = passwordField.getPassword();
            
            if (!isValidCredentials(username, new String(password))) {
                errorLabel.setText("Nom d'utilisateur ou mot de passe incorrect");
            } else {
                errorLabel.setText(""); // Effacer le message d'erreur s'il est déjà affiché
                // Ajoutez ici le code pour traiter la connexion réussie
                // Display the ProfilPanel

            }
        });*/
        loginButton.addActionListener(e -> {
            String username = userField.getText();
            char[] password = passwordField.getPassword();

            String role = getUserRole(username, new String(password));
            if (role != null) {
                if (role.equals("user")) {
             Personne connectedUser = Jdbc.getUserByUsername(username);
             Session.getInstance().setConnectedUser(connectedUser);
                         System.out.println(connectedUser.getId());

 // Récupère les détails de l'utilisateur depuis la base de donnée

                    // Redirect to UtilisateurGI class
                    // You can create an instance of UtilisateurGI and display it here
                UtilisateurGI utilisateurGI = new UtilisateurGI(username);
                    utilisateurGI.setVisible(true);
                    
                    dispose(); // Close the login window
                } else {
                    // Redirect to another class for other roles, if needed
                    // For example:
                    // AdminGI adminGI = new AdminGI();
                    // adminGI.setVisible(true);
                }
            } else {
                errorLabel.setText("Nom d'utilisateur ou mot de passe incorrect");
            }
        });

        // Afficher la fenêtre
        setLocationRelativeTo(null); // Centrer la fenêtre
        setVisible(true);
    }
    private String getUserRole(String nom, String password) {
    try (Connection conn = Jdbc.getConnection()) {
        String query = "SELECT role FROM personne WHERE nom = ? AND password = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, nom);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("role");
                }
            }
        }
    } catch (SQLException e) {
        System.out.println("Error retrieving user role: " + e.getMessage());
    }
    return null; // Return null if no role is found or an error occurs
}


    
 public void display() {
   
        setVisible(true);
    }
    private boolean isValidCredentials(String username, String password) {
    try (Connection conn = Jdbc.getConnection()) {
        String query = "SELECT * FROM personne WHERE nom = ? AND password = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // If a row is returned, the credentials are valid
            }
        }
    } catch (SQLException e) {
        System.out.println("Error validating credentials: " + e.getMessage());
        return false;
    }
}

}

