import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class ControlleurVisiteur extends Controller {

	private RepertoireVisiteur repertoire = new RepertoireVisiteur();

	public ControlleurVisiteur() {}

	/**
	 * envoyer un courriel contenant des informations d'un RDV
	 * @return courriel
	 */
	public void envoyerCourriel() {
		System.out.println(genererCourriel());
	}

	/**
	 * générer les informations d'un RDV
	 * @return informations d'un RDV
	 */
	private String genererCourriel() {
		RDV nouveauRDV = repertoire.getNouveauRDV();
		return "--------- Notification envoyée à " + nouveauRDV.getEmail() + "---------\n"
			 + "NUMERO DE RESERVATION: " + nouveauRDV.getNumeroDeReservation() + "\n"
			 + "PRENOM: " + nouveauRDV.getPrenom() + "\n"
			 + "NOM: " + nouveauRDV.getNom() + "\n"
			 + "DATE DE LA VISITE: " + nouveauRDV.getPlageHoraire().getDate() + "\n"
			 + "HEURE DE LA VISITE: " + nouveauRDV.getPlageHoraire().getHeure() + "\n"
			 + "TYPE DE DOSE: " + nouveauRDV.getTypeDeDose() + "\n"
			 + "-------------------------------------------------------------";
	}

	/**
	 * return a calendar containing everyday quantity of reservations from the RDV list
	 * @return calendar
	 */
	public ConcurrentSkipListMap<Integer, Integer> getCalendar() {
		return repertoire.getCalendar();
	}

	/**
	 * return a list of hours containing quantity of RDV in every hour of the date chosen
	 * @param datePrise la date du RDV qu'un visiteur veut prendre: forme "YYYY-MM-DD"
	 * @return list of hours
	 */
	public ConcurrentSkipListMap<Integer, Integer> getHoraireDatePrise(String datePrise) {
		return repertoire.getHoraires(datePrise);
	}

	/**
	 * créer un nouveau RDV
	 * @param infos informations par rapport au RDV à créer
	 */
	public void createRDV(HashMap<String, String> infos) {
		String heureVisite = infos.get("heureVisite");
		String dateVisite = infos.get("dateVisite");
		PlageHoraire plageHoraire = new PlageHoraire(dateVisite, heureVisite);
		String numReservation = repertoire.generateNumReservation();
		RDV rdvCree = new RDV(numReservation, infos.get("nom"), infos.get("prenom"),
				plageHoraire, infos.get("typeDeDose"), infos.get("email"), false);
		repertoire.addRDV(rdvCree);
	}

	/**
	 * return a list of hours containing quantity of people in every hour of today
	 * @param dateDuJour today's date: form "YYYY-MM-DD"
	 * @return list of hours
	 */
	public ConcurrentSkipListMap<Integer, Integer> getHoraireDuJour(String dateDuJour) {
		return repertoire.getHorairesDuJour(dateDuJour);
	}

	/**
	 * Ajouter un Walkin
	 * @param infos informations par rapport au Walkin à ajouter
	 */
	public void addWalkin(HashMap<String, String> infos) {
		String heureVisite = infos.get("heureVisite");
		String dateVisite = infos.get("dateVisite");
		PlageHoraire plageHoraire = new PlageHoraire(heureVisite, dateVisite);
		repertoire.addWalkin(new Walkin(infos.get("nom"), infos.get("prenom"), plageHoraire, infos.get("tel")));
	}

	/**
	 * Passer un numéro de réservation à "RepertoireVisiteur" pour enlever un rdv
	 * @param numeroDeReservation
	 * @return "false" représente qu'il n'existe pas de rdv correspondant à ce numéro de réservation,
	 * "true" représente qu'il a réussi d'enlever un tel rdv.
	 */
	public boolean removeRDV(String numeroDeReservation) {
		// TODO - implement ControlleurVisiteur.removeRDV
		RDV rdvASupprimer = repertoire.getRDV(numeroDeReservation);
		if (rdvASupprimer == null) return false;
		repertoire.removeRDV();
		return true;
	}

	/**
	 * envoyer des rappels
	 */
	public void envoyerRappel() {
		// TODO - implement ControlleurVisiteur.envoyerRappel
		ArrayList<RDV> rdvsARappeler = repertoire.getRappel();
		for (RDV rdvARappeler : rdvsARappeler) {
			System.out.println(genererRappel(rdvARappeler));
		}
	}

	/**
	 * générer un rappel du rdv
	 * @param rdvARappeler rdv qu'on doit rappeler
	 * @return un rappel
	 */
	private String genererRappel(RDV rdvARappeler) {
		return "--------- Rappel envoyé à " + rdvARappeler.getEmail() + "---------\n"
			 + "Votre RDV: " + rdvARappeler.getNumeroDeReservation() + "," + "va bientôt se passer."
			 + "Temps: " + rdvARappeler.getPlageHoraire().getDate() + ", " + rdvARappeler.getPlageHoraire().getHeure();
	}

	/**
	 * Vérifie s'il existe un rdv dont le numéro de réservation est celui en paramètre
	 * @param numeroDeReservation
	 * @return nom et numéro de téléphone du visiteur. "non" représente que ce numéro de réservation n'existe pas
	 */
	public String verifierRDV(String numeroDeReservation) {
		// TODO - implement ControlleurVisiteur.verifierRDV
		RDV rdvAConfirmer = repertoire.getRDV(numeroDeReservation);
		if (rdvAConfirmer == null) return "non"; // si un tel rdv n'existe pas
		return rdvAConfirmer.getNom() + " " + rdvAConfirmer.getPlageHoraire().getHeure();
	}

	/**
	 * Confirmer s'il existe un rdv dont le numéro de réservation est celui en paramètre
	 * @param numeroDeReservation
	 * @return "true" si ce rdv a bien été confirmé, "false" si ce numéro de réservation n'existe pas
	 */
	public boolean confirmerRDV(String numeroDeReservation) {
		// TODO - implement ControlleurVisiteur.confirmerRDV
		RDV rdvAConfirmer = repertoire.getRDV(numeroDeReservation);
		if (rdvAConfirmer == null) return false; // si un tel rdv n'existe pas
		rdvAConfirmer.confirmerRDV();
		return true;
	}

	/**
	 * Vérifie s'il existe un visiteur spontané dans la liste de walkins d'aujourd'hui
     * en vétifiant le nom et le numéro de téléphone que le visiteur offre
	 * @param nom
	 * @param tel
     * @return temps que le visiteur spontané a fixé avec le bénévole, "null" s'il
     * n'existe pas de tel visiteur
	 */
	public boolean confirmerWalkin(String nom, String tel) {
		// TODO - implement ControlleurVisiteur.confirmerWalkin
		Walkin walkinTrouve =  repertoire.getWalkin(nom, tel);
		return walkinTrouve != null;
	}

	public RepertoireVisiteur getRepertoire() {
		return repertoire;
	}

	public void setRepertoire(RepertoireVisiteur repertoire) {
		this.repertoire = repertoire;
	}
}