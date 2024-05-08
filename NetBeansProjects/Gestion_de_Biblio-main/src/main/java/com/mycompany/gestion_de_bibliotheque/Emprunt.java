package com.mycompany.gestion_de_bibliotheque;

import java.util.Date;

public class Emprunt {
    private String id_emp; // Assuming this is the ID of the Emprunt
    private Livre livre;
    private Personne personne;
    private Date dateEmprunt;
    private Date dateRetour;

    public Emprunt(Livre livre, Date dateEmprunt, Date dateRetour) {
        this.personne = personne;
        this.livre = livre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }

    public Livre getLivre() {
        return livre;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    // You may want to add setters for id_emp, livre, dateEmprunt, and dateRetour if you intend to modify them later.
    // Otherwise, if these values are set only once upon object creation, you can leave out the setters.
}
