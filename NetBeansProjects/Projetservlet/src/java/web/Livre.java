public class Livre {
    private int idLivre;
    private String titre;
    private String auteur;
    private boolean disponible;

    // Constructor
    public Livre(int idLivre, String titre, String auteur, boolean disponible) {
        this.idLivre = idLivre;
        this.titre = titre;
        this.auteur = auteur;
        this.disponible = disponible;
    }

    // Getters and setters
    public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
