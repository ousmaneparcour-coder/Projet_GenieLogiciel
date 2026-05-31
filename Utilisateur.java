package MonLogiciel;

public abstract class Utilisateur {
    private String id;
    private String nom;
    private String prenom;
    private String sexe;
    private String dateNaissance;

    public Utilisateur(String id, String nom, String prenom, String sexe, String dateNaissance) {
        this.id = id;
        this.nom = nom.toUpperCase();
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
    }

    public abstract void afficherProfil();

    public String getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getSexe() { return sexe; }
    public String getDateNaissance() { return dateNaissance; }
}