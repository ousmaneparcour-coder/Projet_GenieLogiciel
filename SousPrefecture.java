package MonLogiciel;

import java.util.ArrayList;
import java.util.List;

public class SousPrefecture {
    private String nom;
    private String adresse;
    private List<Service> services;

    public SousPrefecture(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
        this.services = new ArrayList<>();
    }

    public void ajouterService(Service service) {
        this.services.add(service);
    }

    public void listerServices() {
        System.out.println("\n=====================================================================");
        System.out.println("     SERVICES ÉTATIQUES DE L'ARRONDISSEMENT DE " + nom.toUpperCase());
        System.out.println("     Adresse : " + adresse);
        System.out.println("=====================================================================");
        for (Service s : services) {
            s.afficherDetails();
        }
        System.out.println("=====================================================================");
    }

    public List<Service> getServices() {
        return services;
    }
}