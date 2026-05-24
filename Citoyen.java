package MonLogiciel;

public class Citoyen extends Utilisateur {
    private String adresse;
    private String telephone;
    private String email;
    private String profession;
    private String situationFamiliale;
    private boolean estHandicape;
    private String privilegeChoisi;

    public Citoyen(String id, String nom, String prenom, String sexe, String dateNaissance,
                   String adresse, String telephone, String email, String profession, 
                   String situationFamiliale) {
        super(id, nom, prenom, sexe, dateNaissance);
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.profession = profession;
        this.situationFamiliale = situationFamiliale;
        this.estHandicape = false;
        this.privilegeChoisi = "Aucun";
    }

    public void sIdentifier() {
        System.out.println("Citoyen identifié : " + getNom() + " " + getPrenom());
    }

    public void demanderService() {
        System.out.println("Demande de service standard initiée.");
    }

    @Override
    public void afficherProfil() {
        System.out.println("\n>> PROFIL CITOYEN <<");
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "ID             : " + getId() + "\n" +
               "Nom            : " + getNom() + " " + getPrenom() + "\n" +
               "Sexe           : " + getSexe() + "\n" +
               "Né(e) le       : " + getDateNaissance() + "\n" +
               "Adresse        : " + adresse + "\n" +
               "Téléphone      : " + telephone + "\n" +
               "Email          : " + email + "\n" +
               "Profession     : " + profession + "\n" +
               "Situation fam. : " + situationFamiliale + "\n" +
               "Handicapé(e)   : " + (estHandicape ? "Oui" : "Non") + "\n" +
               "Privilège      : " + privilegeChoisi;
    }

    public String getAdresse() { return adresse; }
    public String getTelephone() { return telephone; }
    public boolean isEstHandicape() { return estHandicape; }
    public void setEstHandicape(boolean estHandicape) { this.estHandicape = estHandicape; }
    public String getPrivilegeChoisi() { return privilegeChoisi; }
    public void setPrivilegeChoisi(String privilegeChoisi) { this.privilegeChoisi = privilegeChoisi; }
}