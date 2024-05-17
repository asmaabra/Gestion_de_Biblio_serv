

public class Personne {
    private int id;
    private String nom;
    private String email;
    private String password;
    private String role;

    public Personne(String nom, String email, String password, String role) {
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.role = role;
    }
      public Personne(String nom, String email, String role) {
        this.nom = nom;
        this.email = email;
       
        this.role = role;
    }
    public Personne(int id,String nom, String email, String password, String role) {
        this.id=id;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getter and setter methods for nom, email, password, and role

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
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
}
