package com.mycompany.gestion_de_bibliotheque;
import com.mycompany.gestion_de_bibliotheque.Session;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class BiblioPanel extends JPanel {
    private JButton emprunterButton;
    private JTable livresTable;
    private DefaultTableModel tableModel;
    private Bibliotheque bibliotheque;
    private Personne connectedUser; 
    public BiblioPanel(Bibliotheque bibliotheque) {
        this.bibliotheque = bibliotheque;
        this.connectedUser = Session.getInstance().getConnectedUser(); // Récupération de l'utilisateur connecté depuis Session

        setLayout(new BorderLayout());

        // Retrieve available books from the library
        List<Livre> livresDisponibles = bibliotheque.getLivresDisponibles();

        // Create the table with book data
        String[] columnNames = {"Id" ,"Titre", "Auteur"};
        Object[][] rowData = new Object[livresDisponibles.size()][3];
        for (int i = 0; i < livresDisponibles.size(); i++) {
            Livre livre = livresDisponibles.get(i);
             rowData[i][0] = livre.getId();
            rowData[i][1] = livre.getTitre();
            rowData[i][2] = livre.getAuteur();
            
        }
        tableModel = new DefaultTableModel(rowData, columnNames);
        livresTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(livresTable);
        add(scrollPane, BorderLayout.CENTER);

        // Borrow button
        emprunterButton = new JButton("Emprunter");
        emprunterButton.addActionListener(e -> {
            // Retrieve selected books
          // Obtenez l'id_personne de l'utilisateur connecté

            int[] selectedRows = livresTable.getSelectedRows();
            for (int row : selectedRows) {
                Livre livre = livresDisponibles.get(row);
                // Example action: borrow the book via the library
                Personne user = Session.getInstance().getConnectedUser(); 
// Example User object
                 int id_livre = livre.getId(); // Supposons que votre Livre a une méthode getId() pour obtenir l'ID
                  // Supposons que votre Livre a une méthode getId() pour obtenir l'ID
                 boolean disponibiliteModifiee = modifierDisponibiliteLivre(id_livre, false); // Modifie la disponibilité du livre

              if (connectedUser != null) {
                    int id_personne = connectedUser.getId(); 
                    Date dateEmprunt = new Date(); // Exemple de date d'emprunt
                    Date dateRetour = dateEmprunt; // Définir la date de retour selon vos besoins
            System.out.println(id_livre +"    "+disponibiliteModifiee);

            // Insérer l'emprunt dans la base de données
            if (insertEmprunt(id_personne, livre.getId(), dateEmprunt, dateRetour) ) {
                
                // Mettre à jour l'interface ou afficher un message de succès
                tableModel.removeRow(row); // Supprimer la ligne du livre emprunté de l'interface
                JOptionPane.showMessageDialog(null, "Livre emprunté avec succès !");
            } else {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'emprunt du livre.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez vous connecter pour emprunter des livres.");
        }
    }
              
            
            // Example: display borrow and return dates
        });

        add(emprunterButton, BorderLayout.SOUTH);
    }

    private boolean insertEmprunt(int id_personne, int id_livre, Date dateEmprunt, Date dateRetour) {
          try (Connection conn = Jdbc.getConnection()) {
        String query = "INSERT INTO emprunt (personne_id, livre_id, date_emprunt, date_retour) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id_personne);
            statement.setInt(2, id_livre);
            statement.setDate(3, new java.sql.Date(dateEmprunt.getTime()));
            statement.setDate(4, new java.sql.Date(dateRetour.getTime()));
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        }
    } catch (SQLException ex) {
        System.out.println("Error inserting emprunt: " + ex.getMessage());
        return false;
    }
    }
    
    private boolean modifierDisponibiliteLivre(int id_livre, boolean disponibilite) {
    try (Connection conn = Jdbc.getConnection()) {
        String query = "UPDATE livre SET disponible = ? WHERE livre_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setBoolean(1, disponibilite);
            statement.setInt(2,id_livre);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
    } catch (SQLException ex) {
        System.out.println("Error updating book availability: " + ex.getMessage());
        return false;
    }
}
}
