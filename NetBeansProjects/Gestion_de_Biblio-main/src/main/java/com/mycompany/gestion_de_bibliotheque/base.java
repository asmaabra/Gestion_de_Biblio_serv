/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_de_bibliotheque;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author asma
 */
public class base extends JFrame {
     Color lightGreen = new Color(144, 238, 144);
    Color beige = new Color(245, 245, 220);
public  base(){
    
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
        setContentPane(contentPane);

        // Afficher la fenêtre
        setLocationRelativeTo(null); // Centrer la fenêtre
        setVisible(true);
    
}
 public void display() {
        setVisible(true);
    }
}
