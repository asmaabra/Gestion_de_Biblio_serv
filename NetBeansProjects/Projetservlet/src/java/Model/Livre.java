/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author asma
 */

public class Livre {
    private int idLivre; // Changed variable name to camelCase
    private String titre;
    private String auteur;
    private boolean disponible; // Changed variable name to camelCase

    public Livre(int idLivre ,String titre, String auteur, boolean disponible) {
        this.idLivre= idLivre;
        this.titre = titre;
        this.auteur = auteur;
        this.disponible = disponible;
    }
    
     public Livre(String titre, String auteur){
          
          this.titre = titre;
          this.auteur = auteur;
     }

    public Livre(int id, String titre, String auteur) {
         this.idLivre= idLivre;
        this.titre = titre;
        this.auteur = auteur;
    }
public int getIdLivre() {
        return idLivre;
    }
    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }
    
    public boolean isdisponible() { // Changed method name to follow convention
        return disponible;
    }


    
}

