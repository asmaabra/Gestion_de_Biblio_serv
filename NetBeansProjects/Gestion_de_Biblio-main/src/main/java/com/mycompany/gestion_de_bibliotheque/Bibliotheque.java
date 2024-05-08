package com.mycompany.gestion_de_bibliotheque;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bibliotheque {
    private List<Livre> catalogue;
    private List<Emprunt> emprunts;
    private List<User> utilisateurs;

    public Bibliotheque() {
        this.catalogue = new ArrayList<>();
        this.emprunts = new ArrayList<>();
        this.utilisateurs = new ArrayList<>();
    }

    public void ajouterLivre(Livre livre) {
        catalogue.add(livre);
    }

    public void supprimerLivre(Livre livre) {
        catalogue.remove(livre);
    }

    public void ajouterEmprunt(Emprunt emprunt) {
        emprunts.add(emprunt);
    }

    public void supprimerEmprunt(Emprunt emprunt) {
        emprunts.remove(emprunt);
    }
   
    public List<Livre> getLivresDisponibles() {
        List<Livre> livresDisponibles = new ArrayList<>();
        for (Livre livre : catalogue) {
            if (livre.isDisponible()) {
                livresDisponibles.add(livre);
            }
        }
        return livresDisponibles;
    }

    public void emprunterLivre(Livre livre, Personne user, Date dateEmprunt, Date dateRetour) {
        livre.setDisponible(false);
        emprunts.add(new Emprunt(livre, dateEmprunt, dateRetour));
    }
    public List<Livre> getCatalogue() {
        return catalogue;
    }

    public List<Emprunt> getEmprunts() {
        return emprunts;
    }

    public List<User> getUtilisateurs() {
        return utilisateurs;
    }
    
    
    
}
