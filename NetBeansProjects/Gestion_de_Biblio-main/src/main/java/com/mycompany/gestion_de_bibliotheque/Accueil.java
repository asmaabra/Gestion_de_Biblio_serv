/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_de_bibliotheque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Accueil extends JFrame {
    Color lightGreen = new Color(144, 238, 144);
    Color beige = new Color(245, 245, 220);

    public Accueil() {
        setTitle("Gestion de Bibliothèque");
        setSize(800, 500);
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
        contentPane.setLayout(null);

        // Créer un JLabel pour la phrase d'accueil
        JLabel welcomeLabel = new JLabel("Découvrez un univers de savoirs et d'aventures.");
        welcomeLabel.setFont(new Font("Brush Script MT", Font.ITALIC , 40));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centrer le texte horizontalement
        welcomeLabel.setBounds(50, 50, 700, 50);
        
        
        // Ajouter le JLabel au panneau
        contentPane.add(welcomeLabel);

        
        
 // Créer un JLabel pour le lien d'inscription
        JLabel signupLabel = new JLabel("connectez-vous pour profiter de tous nos services");
        Font linkFont = new Font("Arial", Font.BOLD , 15);
        signupLabel.setFont(linkFont);
        signupLabel.setForeground(Color.WHITE); // Couleur de lien
        signupLabel.setHorizontalAlignment(SwingConstants.CENTER);
        signupLabel.setBounds(200, 300, 400, 30);
        contentPane.add(signupLabel);

        // Ajouter un gestionnaire d'événements pour le clic sur le lien d'inscription
        signupLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                 Login loginWindow = new Login();
                loginWindow.display();
              
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Changer la couleur du lien lorsqu'il est survolé
                signupLabel.setForeground(lightGreen );
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Revenir à la couleur de lien normale lorsque la souris quitte le lien
                signupLabel.setForeground(beige);
            }
        });
     
     
     // Créer un bouton pour l'inscription
/*JButton signupButton = new JButton("S'inscrire");


signupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Changer le curseur en main lorsqu'il survole le bouton
signupButton.setBounds(350, 350, 100, 40); // Position et taille du bouton
contentPane.add(signupButton);

// Ajouter un gestionnaire d'événements pour le clic sur le bouton d'inscription
signupButton.addActionListener(e -> {
    // Ajoutez ici le code pour l'inscription, comme l'ouverture d'une fenêtre d'inscription
    // ou la redirection vers une page d'inscription
    System.out.println("Clic sur le bouton Inscription");
});*/

        // Définir le panneau comme contenu de la fenêtre
        setContentPane(contentPane);

        // Afficher la fenêtre
        setLocationRelativeTo(null); // Centrer la fenêtre
        setVisible(true);
    }

   
}
