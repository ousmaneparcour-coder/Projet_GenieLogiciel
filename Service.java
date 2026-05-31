package MonLogiciel;

public class Service {
    private String nom;
    private String responsable;
    private String agents;
    private String horaires;
    private String prestations;
    private String explicationFonction;

    public Service(String nom, String responsable, String agents, String horaires, String prestations, String explicationFonction) {
        this.nom = nom;
        this.responsable = responsable;
        this.agents = agents;
        this.horaires = horaires;
        this.prestations = prestations;
        this.explicationFonction = explicationFonction;
    }

    public void recevoirCitoyen() {
        System.out.println("Le service " + nom + " reçoit actuellement un citoyen.");
    }

    public void ajouterRendezVous() {
        System.out.println("Nouveau rendez-vous ajouté au planning.");
    }

    public void afficherDetails() {
        System.out.println("\n--- [" + nom.toUpperCase() + "] ---");
        System.out.println("Fonction globale : " + explicationFonction);
        System.out.println("Responsable      : " + responsable);
        System.out.println("Effectifs        : " + agents);
        System.out.println("Horaires         : " + horaires);
        System.out.println("Prestations      : " + prestations);
    }

    public String getNom() { return nom; }
}