package MonLogiciel;
	public class RendezVous {
	    private Service service;
	    private String motif;
	    private String dateRDV;
	    private boolean estUrgent;
	    private String statut;

	    public RendezVous(Service service, String motif, String dateRDV, boolean estUrgent) {
	        this.service = service;
	        this.motif = motif;
	        this.dateRDV = dateRDV;
	        this.estUrgent = estUrgent;
	        this.statut = "En attente";
	    }

	    public void confirmer() { this.statut = "Accepté"; }
	    public void annuler() { this.statut = "Refusé"; }

	    public String getDateRDV() { return dateRDV; }
	    public Service getService() { return service; }
	    public String getMotif() { return motif; }
	    public String getStatut() { return statut; }
	}