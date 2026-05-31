package MonLogiciel;

	import java.io.*;
	import java.nio.file.*;
	import java.time.LocalDate;
	import java.time.format.DateTimeFormatter;
	import java.util.Random;
	import java.util.Scanner;

	public class Main {
	    private static final Scanner scanner = new Scanner(System.in);
	    private static final Random random = new Random();
	    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	    private static Utilisateur utilisateurConnecte = null;
	    private static File fichierCitoyen = null;

	    public static void main(String[] args) {
	        SousPrefecture ngaoundere3e = new SousPrefecture("Ngaoundéré 3e", "Ngaoundéré, Adamaoua");

	        Service mairie = new Service(
	            "Mairie", "M. l'Adjoint au Maire", "3 Agents", "08h - 15h",
	            "Légalisation, Actes d'état civil, Mariages",
	            "La Mairie gère l'état civil des citoyens, officialise les unions (mariages) et légalise les documents administratifs officiels."
	        );

	        Service police = new Service(
	            "Poste de Police", "Le Commissaire", "5 Agents", "24h/24",
	            "Sécurité, Maintien de l'ordre, Délivrance CNI",
	            "Le Poste de Police assure la sécurité publique, le maintien de l'ordre dans l'arrondissement et gère les procédures de délivrance de la CNI."
	        );

	        Service sante = new Service(
	            "Centre de Santé", "Le Médecin-Chef", "4 Infirmiers", "07h - 18h",
	            "Soins de première nécessité, Vaccination, Consultations",
	            "Le Centre de Santé offre les soins médicaux de première urgence, gère les campagnes de vaccination et assure les consultations médicales de routine."
	        );

	        ngaoundere3e.ajouterService(mairie);
	        ngaoundere3e.ajouterService(police);
	        ngaoundere3e.ajouterService(sante);

	        System.out.println("=====================================================================");
	        System.out.println("  Système Informatique de Gestion des Services - Ngaoundéré 3e  ");
	        System.out.println("=====================================================================");

	        authentification();

	        boolean executer = true;
	        while (executer) {
	            System.out.println("\n--- APPLICATION CITOYENNE ---");
	            System.out.println("1. Consulter les services disponibles (avec explications)");
	            System.out.println("2. Programmer un Rendez-vous (et gestion des taxes)");
	            System.out.println("3. Consulter ma Fiche sauvegarde (.txt)");
	            System.out.println("4. Quitter le logiciel");
	            System.out.print("Faites votre choix : ");

	            String choix = scanner.nextLine().trim();

	            switch (choix) {
	                case "1":
	                    ngaoundere3e.listerServices();
	                    break;
	                case "2":
	                    programmerRendezVous(mairie, police, sante);
	                    break;
	                case "3":
	                    consulterFicheTexte();
	                    break;
	                case "4":
	                    System.out.println("\n[INFO] Le programme est fermé.");
	                    executer = false;
	                    break;
	                default:
	                    System.out.println("Sélection invalide. Veuillez recommencer.");
	            }

	            if (executer) {
	                executer = demanderRetour("Retourner au menu principal ?");
	                if (!executer) {
	                    System.out.println("\n[INFO] Le programme est fermé.");
	                }
	            }
	        }
	    }

	    private static void authentification() {
	        while (true) {
	            System.out.println("\nÊtes-vous déjà enregistré dans le système ?");
	            System.out.println("1. Oui (Rechercher et charger mon profil)");
	            System.out.println("2. Non (Créer un nouveau profil)");
	            System.out.print("Votre choix : ");
	            String reponse = scanner.nextLine().trim();

	            if (reponse.equals("1")) {
	                System.out.print("Entrez votre NOM de famille : ");
	                String nomRecherche = scanner.nextLine().trim().toUpperCase();

	                fichierCitoyen = new File(nomRecherche + ".txt");

	                if (fichierCitoyen.exists()) {
	                    System.out.println("\n[Succès] Fiche trouvée pour : " + nomRecherche);
	                    utilisateurConnecte = new Citoyen(
	                        "REG-" + random.nextInt(9000), nomRecherche, "Citoyen", "M/F",
	                        "Inconnue", "Ngaoundéré", "000000", "N/A", "N/A", "N/A"
	                    );
	                    break;
	                } else {
	                    System.out.println("[Erreur] Aucun fichier pour '" + nomRecherche + "'. Créez un profil.");
	                }

	            } else if (reponse.equals("2")) {
	                System.out.println("\n--- FORMULAIRE D'INSCRIPTION CITOYEN ---");
	                System.out.print("Nom de famille         : ");
	                String nom = scanner.nextLine().trim().toUpperCase();
	                System.out.print("Prénom(s)              : ");
	                String prenom = scanner.nextLine().trim();
	                System.out.print("Sexe (Masculin/Féminin): ");
	                String sexe = scanner.nextLine().trim();
	                System.out.print("Date de naissance (JJ/MM/AAAA) : ");
	                String dateNais = scanner.nextLine().trim();
	                System.out.print("Adresse complète       : ");
	                String adresse = scanner.nextLine().trim();
	                System.out.print("Numéro de téléphone    : ");
	                String tel = scanner.nextLine().trim();
	                System.out.print("Adresse email (ou 'N/A') : ");
	                String email = scanner.nextLine().trim();
	                System.out.print("Profession actuelle    : ");
	                String prof = scanner.nextLine().trim();
	                System.out.print("Situation familiale    : ");
	                String situationFam = scanner.nextLine().trim();

	                String idGenere = "CIT-" + (random.nextInt(8999) + 1000);

	                utilisateurConnecte = new Citoyen(
	                    idGenere, nom, prenom, sexe, dateNais, adresse, tel, email, prof, situationFam
	                );

	                fichierCitoyen = new File(nom + ".txt");
	                initialiserFichierCitoyen();

	                System.out.println("\n[Félicitations] Votre fiche '" + nom + ".txt' a été créée.");
	                break;
	            } else {
	                System.out.println("Commande incorrecte. Tapez 1 ou 2.");
	            }
	        }
	    }

	    private static void programmerRendezVous(Service mairie, Service police, Service sante) {
	        Citoyen citoyen = (Citoyen) utilisateurConnecte;

	        System.out.println("\n--- PROGRAMMATION D'UN RENDEZ-VOUS ---");
	        System.out.println("1. Mairie\n2. Poste de Police\n3. Centre de Santé");
	        System.out.print("Sélectionnez le service (1-3) : ");
	        String choix = scanner.nextLine().trim();

	        Service serviceCible;
	        if (choix.equals("1"))      serviceCible = mairie;
	        else if (choix.equals("2")) serviceCible = police;
	        else if (choix.equals("3")) serviceCible = sante;
	        else {
	            System.out.println("Choix invalide. Annulation.");
	            return;
	        }

	        System.out.println("\nExposez le motif du rendez-vous (max 150 mots) :");
	        String motif = saisirZoneTexteMax150Mots();

	        System.out.println("\nÊtes-vous en situation de handicap ?\n1. Oui\n2. Non");
	        System.out.print("Votre réponse : ");
	        citoyen.setEstHandicape(scanner.nextLine().trim().equals("1"));

	        if (citoyen.isEstHandicape()) {
	            System.out.println("\n--- ATTRIBUTION D'UN PRIVILÈGE ---");
	            System.out.println("1. Priorité d'accès sans file d'attente\n2. Exonération totale des frais d'urgence");
	            System.out.print("Choisissez votre privilège : ");
	            String choixPriv = scanner.nextLine().trim();

	            if (choixPriv.equals("2"))
	                citoyen.setPrivilegeChoisi("Exonération totale des frais d'urgence");
	            else
	                citoyen.setPrivilegeChoisi("Priorité d'accès sans file d'attente");

	            ecrireLigneDansFichier("[PRIVILÈGE ASSOCIÉ] : " + citoyen.getPrivilegeChoisi());
	        }

	        System.out.print("\nCette demande nécessite-t-elle un traitement d'urgence ? (Oui/Non) : ");
	        boolean requiertUrgence = scanner.nextLine().trim().equalsIgnoreCase("oui");

	        LocalDate dateProposee;
	        int fraisCFA = 0;

	        if (requiertUrgence) {
	            System.out.println("\n--- NIVEAU D'URGENCE ---\n1. Faible\n2. Moyen\n3. Critique");
	            System.out.print("Choisissez le niveau : ");
	            String nivUrgence = scanner.nextLine().trim();

	            int baseTarif = 15000;
	            if (nivUrgence.equals("2")) { baseTarif = 35000; }
	            if (nivUrgence.equals("3")) { baseTarif = 65000; }

	            System.out.println("\nJustifiez l'urgence (max 150 mots) :");
	            String justification = saisirZoneTexteMax150Mots();

	            if (!citoyen.isEstHandicape()) {
	                fraisCFA = baseTarif;

	                // Application stricte des plafonds d'urgence demandés
	                if (serviceCible == police && fraisCFA > 10000) {
	                    fraisCFA = 10000;
	                } else if (serviceCible == sante && fraisCFA > 60000) {
	                    fraisCFA = 60000;
	                } else if (serviceCible == mairie && fraisCFA > 50000) {
	                    fraisCFA = 50000;
	                }

	                System.out.println("\n[Taxe d'urgence calculée et vérifiée] Montant dû : " + fraisCFA + " CFA");
	            } else {
	                System.out.println("\n[Privilège activé] Exonération appliquée. Frais : 0 CFA.");
	            }

	            dateProposee = LocalDate.now().plusDays(random.nextInt(3) + 1);
	            ecrireLigneDansFichier("[URGENCE] Service: " + serviceCible.getNom() + " | Frais appliqués: " + fraisCFA + " CFA | Justification: " + justification);
	        } else {
	            dateProposee = LocalDate.now().plusDays(7);

	            // Plafonnage mariage à la mairie
	            if (serviceCible == mairie && motif.toLowerCase().contains("mariage")) {
	                fraisCFA = 25000;
	                if (fraisCFA > 50000) fraisCFA = 50000;
	                System.out.println("\n[Frais de dossier Mariage] Montant standard requis : " + fraisCFA + " CFA");
	            }
	        }

	        RendezVous rdv = new RendezVous(serviceCible, motif, dateProposee.format(formatter), requiertUrgence);

	        System.out.println("\n=====================================================================");
	        System.out.println("         DÉCISION DE L'AGENT ADMINISTRATIF — NGAOUNDÉRÉ 3e          ");
	        System.out.println("=====================================================================");
	        System.out.print("Décision de l'agent (1: Approuver / 2: Refuser) : ");
	        String decisionAgent = scanner.nextLine().trim();

	        if (decisionAgent.equals("2")) {
	            rdv.annuler();
	            System.out.println("Rendez-vous refusé.");
	        } else {
	            rdv.confirmer();
	            System.out.println("Rendez-vous validé pour le " + rdv.getDateRDV());
	        }

	        ecrireLigneDansFichier("\n[LOG RENDEZ-VOUS]\nService  : " + rdv.getService().getNom() + "\nMotif    : " + rdv.getMotif() + "\nDate     : " + rdv.getDateRDV() + "\nStatut   : " + rdv.getStatut());
	    }

	    private static String saisirZoneTexteMax150Mots() {
	        while (true) {
	            String entree = scanner.nextLine().trim();
	            if (entree.isEmpty()) {
	                System.out.print("Saisie obligatoire : ");
	                continue;
	            }
	            String[] mots = entree.split("\\s+");
	            if (mots.length > 150) {
	                System.out.print("Trop long (" + mots.length + " mots). Recommencez : ");
	            } else {
	                return entree;
	            }
	        }
	    }

	    private static boolean demanderRetour(String message) {
	        while (true) {
	            System.out.print("\n" + message + " (Oui / Non) : ");
	            String rep = scanner.nextLine().trim().toLowerCase();
	            if (rep.equals("oui") || rep.equals("o")) return true;
	            if (rep.equals("non") || rep.equals("n")) return false;
	        }
	    }

	    private static void initialiserFichierCitoyen() {
	        try (
	            FileWriter fw = new FileWriter(fichierCitoyen, false);
	            BufferedWriter bw = new BufferedWriter(fw);
	            PrintWriter out = new PrintWriter(bw)
	        ) {
	            out.println("=================================================");
	            out.println("      FICHE INDIVIDUELLE DU CITOYEN ENREGISTRÉ   ");
	            out.println("=================================================");
	            out.println(utilisateurConnecte.toString());
	            out.println("-------------------------------------------------");
	        } catch (IOException e) {
	            System.out.println("Erreur de persistance : " + e.getMessage());
	        }
	    }

	    private static void ecrireLigneDansFichier(String ligne) {
	        try (
	            FileWriter fw = new FileWriter(fichierCitoyen, true);
	            BufferedWriter bw = new BufferedWriter(fw);
	            PrintWriter out = new PrintWriter(bw)
	        ) {
	            out.println(ligne);
	        } catch (IOException e) {
	            System.out.println("Erreur d'écriture : " + e.getMessage());
	        }
	    }

	    private static void consulterFicheTexte() {
	        System.out.println("\n=== AFFICHAGE DE LA FICHE TEXTE ENREGISTRÉE ===");
	        if (fichierCitoyen == null || !fichierCitoyen.exists()) {
	            System.out.println("Aucune donnée sur le disque.");
	            return;
	        }
	        try {
	            String contenu = Files.readString(fichierCitoyen.toPath());
	            System.out.println(contenu);
	        } catch (IOException e) {
	            System.out.println("Erreur de lecture : " + e.getMessage());
	        }
	    }
	}