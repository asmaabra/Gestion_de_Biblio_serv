/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_de_bibliotheque;

/**
 *
 * @author asma
 */

    public class Session {
    private static Session instance;
    private Personne connectedUser;

    private Session() {
        // Private constructor to prevent instantiation
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public Personne  getConnectedUser() {
        return connectedUser;
    }

    public void setConnectedUser(Personne  connectedUser) {
        this.connectedUser = connectedUser;
    }
}


