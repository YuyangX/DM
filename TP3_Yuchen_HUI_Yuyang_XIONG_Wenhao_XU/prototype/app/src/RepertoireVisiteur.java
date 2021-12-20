import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListMap;

public class RepertoireVisiteur implements Serializable{
    public static final long serialVersionUID = 0236520327L;
	private ArrayList<RDV> listeRDV = new ArrayList<>();
	private ArrayList<Walkin> listeWalkin = new ArrayList<>(); // contient seulement des walkin du jour
	private ArrayList<String> tousLesNumReservation = new ArrayList<>();
	private RDV nouveauRDV;
	private int indexRDV;

	public RDV getNouveauRDV() {
		return nouveauRDV;
	}

	/**
	 * Cette méthode ajoute un visiteur planifié dans notre répertoire de visiteurs
	 * @param rdv un objet RDV qui va être ajouté dans notre répertoire de visiteurs
	 */
	public void addRDV(RDV rdv) {
		listeRDV.add(rdv);
		nouveauRDV = rdv;
	}

	/**
	 * Cette méthode ajoute un visiteur spontané dans notre répertoire de visiteurs
	 * @param walkin un objet Walkin qui va être ajouté dans notre répertoire de visiteurs
	 */
	public void addWalkin(Walkin walkin) {
		listeWalkin.add(walkin);
	}

	/**
	 * Cette méthode enlève un visiteur planifié dans notre répertoire de visiteurs
	 */
	public void removeRDV() {
		listeRDV.remove(indexRDV);
	}

	/**
	 * Cette méthode cherche dans notre répertoire et retourne un RDV avec le numéro de
	 * réservation passé en paramètre.
	 * @param numeroDeReservation le numéro de réservation du RDV qu'on veut trouver
	 * @return un RDV qu'on a trouvé avec le numéro de réservation, null s'il n'y a pas de RDV
	 * correspondant à ce numéro de réservation.
	 */
	public RDV getRDV(String numeroDeReservation) {
		for (int i = 0; i < listeRDV.size(); i++) {
			if (listeRDV.get(i).getNumeroDeReservation().equals(numeroDeReservation)) { // bien trouvé un RDV avec le même numéro de réservation
				this.indexRDV = i;
				return listeRDV.get(i);
			}
		}
		return null;
	}

	/**
	 * Cette méthode cherche dans notre répertoire et retourne un visiteur spontané avec le nom
	 * et le numéro de téléphone passés en paramètre.
	 * @param nom le nom du visiteur spontané qu'on veut trouver
	 * @param tel le numéro de téléphone du visiteur spontané qu'on veut trouver
	 * @return un visiteur spontané qu'on a trouvé, null s'il n'y a pas de visiteur spontané
	 * correspondant à ce nom ou ce numéro de téléphone.
	 */
	public Walkin getWalkin(String nom, String tel) {
		for (Walkin walkin : listeWalkin) {
			// bien trouvé un visiteur avec le même nom et numéro de téléphone
			if (walkin.getNom().equals(nom) && walkin.getTel().equals(tel)) {
				return walkin;
			}
		}
		return null;
	}

	/**
	 * Cette méthode nous aide à procurer tous les RDV qui vont avoir lieu dans moins de 2 jours.
	 * @return une liste de RDV qui vont avoir lieu dans moins de 2 jours
	 */
	public ArrayList<RDV> getRappel() {
		Date date = new Date(); // java.util.Date
		String[] dateArray = date.toString().split(" ");
		int annee = Integer.parseInt(dateArray[5]);
		int mois = transformerMois(dateArray[1]); // transformer de l'anglais en nombre
		int jour = Integer.parseInt(dateArray[2]);
		int[] dateActuelle = {annee, mois, jour};
		ArrayList<RDV> buffer = new ArrayList<>();
		for (RDV rdv : listeRDV) {
			PlageHoraire horaireRDV = rdv.getPlageHoraire();
			int[] dateRDV = {Integer.parseInt(horaireRDV.getAnnee()), Integer.parseInt(horaireRDV.getMois()), Integer.parseInt(horaireRDV.getJour())};
			if (compareDate(dateActuelle, dateRDV)) buffer.add(rdv); // si la date de RDV est dans moins de 2 jours d'ici, on ajoute ce RDV
		}
		return buffer;
	}

	/**
	 * Cette méthode auxiliaire nous aide à transforme le mois de forme anglais en forme nombre.
	 * @param mois mois qu'on veut transformer en nombre
	 * @return un nombre correspondant au mois en paramètre
	 */
	private int transformerMois(String mois) {
		switch (mois) {
			case "Jan": return 1;
			case "Feb": return 2;
			case "Mar": return 3;
			case "Apr": return 4;
			case "May": return 5;
			case "Jun": return 6;
			case "Jul": return 7;
			case "Aug": return 8;
			case "Sept": return 9;
			case "Oct": return 10;
			case "Nov": return 11;
			default: return 12;
		}
	}

	/**
	 * Cette méthode auxiliaire nous aide à décide si un RDV doit être ajouté à la liste de rappel
	 * @param dateActuel la date où l'utilisateur utilise cette app
	 * @param dateRDV la date du RDV qu'on considère si ajouter à la liste ou non
	 * @return une valeur booléan, true représente l'ajouter à la liste, false représente l'ajouter pas
	 */
	private boolean compareDate(int[] dateActuel, int[] dateRDV) {
		int nombreJour1 = dateActuel[0] * 365 + (dateActuel[1] - 1) * 30 + dateActuel[2];
		int nombreJour2 = dateRDV[0] * 365 + (dateRDV[1] - 1) * 30 + dateRDV[2];
		return (nombreJour2 - nombreJour1) <= 2 && (nombreJour2 - nombreJour1) > 0;
	}

	/**
	 * generate a random reservation number never be used.
	 * @return reservation number
	 */
	public String generateNumReservation(){
		String nouveau = generate6String();
		if (!tousLesNumReservation.contains(nouveau)){
			this.tousLesNumReservation.add(nouveau);
			return nouveau;
		}else{
			return generateNumReservation();
		}
	}

	/**
	 * generate a random string presents a account of "6 chiffres"
	 * @return reservation number
	 */
	private String generate6String (){
		Random rand = new Random();
		String Numero ="";
		for(int a = 0;a < 6; a++){
			Numero += rand.nextInt(10);
		}
		return Numero;
	}

	/**
	 * generate for "fixing RDV" a calendar containing everyday quantity of reservations from the RDV list
	 * @return calendar with form: [(20210521, 20), (20210522, 16), ...]
	 */
	public ConcurrentSkipListMap<Integer, Integer> getCalendar() {
		ConcurrentSkipListMap<Integer, Integer> calendar = new ConcurrentSkipListMap<>();
		for (RDV rdv : listeRDV) {
			int date = Integer.parseInt(rdv.getPlageHoraire().getAnnee() +
					rdv.getPlageHoraire().getMois() + rdv.getPlageHoraire().getJour());
			// s'il n'y a pas de RDV à ce jour dans le calendrier, ajoute un RDV à ce jour
			if (!calendar.containsKey(date)) calendar.put(date, 0);
			int numRDV = calendar.get(date);
			numRDV += 1;
			calendar.replace(date, numRDV);
		}
		return calendar;
	}

	/**
	 * generate a list of hours containing quantity of RDV in every hour of the date chosen
	 * @param datePrise date of which we want to get list of hours
	 * @return list of hours with form: [(8, 4), (9, 7), (10, 13), ...]
	 */
	public ConcurrentSkipListMap<Integer, Integer> getHoraires(String datePrise) {
		ConcurrentSkipListMap<Integer, Integer> horairesDatePrise = new ConcurrentSkipListMap<>();
		for (RDV rdv : listeRDV) {
			PlageHoraire plageHoraire = rdv.getPlageHoraire();
			if (plageHoraire.getDate().equals(datePrise)) {
				int hhDeRDV = plageHoraire.getHH();
				if (!horairesDatePrise.containsKey(hhDeRDV)) horairesDatePrise.put(hhDeRDV, 0);
				int numRDV = horairesDatePrise.get(hhDeRDV); // nombre de RDV dans cette heure
				numRDV += 1;
				horairesDatePrise.replace(hhDeRDV, numRDV);
			}
		}
		return horairesDatePrise;
	}

	/**
	 * generate a list of hours containing quantity of people in every hour of today
	 * @param dateDuJour today's date
	 * @return list of hours with form: [(8, 4), (9, 7), (10, 13), ...]
	 */
	public ConcurrentSkipListMap<Integer, Integer> getHorairesDuJour(String dateDuJour) {
		ConcurrentSkipListMap<Integer, Integer> horairesDuJour = getHoraires(dateDuJour);
		for (Walkin walkin : listeWalkin) {
			PlageHoraire plageHoraire = walkin.getPlageHoraire();
			int hhDeRDV = plageHoraire.getHH();
			if (!horairesDuJour.containsKey(hhDeRDV)) horairesDuJour.put(hhDeRDV, 0);
			int numRDV = horairesDuJour.get(hhDeRDV); // nombre de RDV dans cette heure
			numRDV += 1;
			horairesDuJour.replace(hhDeRDV, numRDV);
			}
		return horairesDuJour;
	}

	public ArrayList<RDV> getListeRDV() {
		return listeRDV;
	}

	public ArrayList<Walkin> getListeWalkin() {
		return listeWalkin;
	}

	public ArrayList<String> getTousLesNumReservation() {
		return tousLesNumReservation;
	}

	public int getIndexRDV() {
		return indexRDV;
	}
}