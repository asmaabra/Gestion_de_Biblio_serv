package com.mycompany.gestion_de_bibliotheque;

public class Personne {
    private int id_personne;
    private String nom;
    private String password;
    private String role;
    String email;

    public Personne(int id_personne,String nom, String email, String password, String role) {
        this.id_personne= id_personne;

        this.nom = nom;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    
    public int getId() {
        return id_personne;
    }

    public void setId(int id_personne) {
        this.id_personne = id_personne;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
