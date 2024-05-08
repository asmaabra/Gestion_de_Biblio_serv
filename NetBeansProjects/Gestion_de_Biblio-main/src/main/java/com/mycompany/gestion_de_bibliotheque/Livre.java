package com.mycompany.gestion_de_bibliotheque;

public class Livre {
    private int idLivre; // Changed variable name to camelCase
    private String titre;
    private String auteur;
    private boolean disponibilite; // Changed variable name to camelCase

    public Livre(int idLivre ,String titre, String auteur, boolean disponibilite) {
        this.idLivre= idLivre;
        this.titre = titre;
        this.auteur = auteur;
        this.disponibilite = disponibilite;
    }
public int getId() {
        return idLivre;
    }
    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public boolean isDisponible() { // Changed method name to follow convention
        return disponibilite;
    }

    public void setDisponible(boolean disponibilite) { // Changed method name to follow convention
        this.disponibilite = disponibilite;
    }
}
