/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_de_bibliotheque;

/**
 *
 * @author asma
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivreDAO {
    private Connection connection;

    public LivreDAO(Connection connection) {
        this.connection = connection;
    }

    public void addLivre(Livre livre) throws SQLException {
        String sql = "INSERT INTO livre (titre, auteur, disponible) VALUES ( ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
          
            statement.setString(2, livre.getTitre());
            statement.setString(3, livre.getAuteur());
            statement.setBoolean(4, livre.isDisponible());
            statement.executeUpdate();
        }
    }

    public List<Livre> getAllLivres() throws SQLException {
        List<Livre> livres = new ArrayList<>();
        String sql = "SELECT * FROM livre";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id_livre = resultSet.getInt("livre_id");
                String titre = resultSet.getString("titre");
                String auteur = resultSet.getString("auteur");
                boolean disponibilite = resultSet.getBoolean("disponible");
                Livre livre = new Livre(id_livre,titre, auteur, disponibilite);
                livres.add(livre);
            }
        }
        return livres;
    }

    // Add methods for updating and deleting Livres as needed
}
