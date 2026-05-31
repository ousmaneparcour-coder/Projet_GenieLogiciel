package MonLogiciel;

public class CitoyenHandicape extends Citoyen {
    private String typeHandicap;

    public CitoyenHandicape(String id, String nom, String prenom, String sexe, String dateNaissance,
                            String adresse, String telephone, String email, String profession, 
                            String situationFamiliale, String typeHandicap) {
        super(id, nom, prenom, sexe, dateNaissance, adresse, telephone, email, profession, situationFamiliale);
        this.typeHandicap = typeHandicap;
        this.setEstHandicape(true);
    }

    public void beneficierPrivilege() {
        System.out.println("Application des privilèges d'accès pour handicap : " + typeHandicap);
    }
    
    public void demanderUrgence() {
        System.out.println("Traitement d'urgence prioritaire demandé.");
    }

    public String getTypeHandicap() { return typeHandicap; }
}