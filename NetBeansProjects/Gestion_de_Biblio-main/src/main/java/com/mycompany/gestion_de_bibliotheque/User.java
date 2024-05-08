package com.mycompany.gestion_de_bibliotheque;

public class User extends Personne {
    private int id_personne;
    public User( int id_personne,String nom, String email, String password, String role) {
        super(id_personne,nom, email, password, role);
    }


    
}