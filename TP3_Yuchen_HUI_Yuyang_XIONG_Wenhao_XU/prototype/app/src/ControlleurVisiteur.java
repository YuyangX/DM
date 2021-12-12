import java.util.HashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class ControlleurVisiteur implements Controller {

	private RepertoireVisiteur repertoire;

	public ControlleurVisiteur() {}


	public ConcurrentSkipListMap<Integer, Integer> getCalendar() {
		return repertoire.getCalendar();
	}

	/**
	 * 
	 * @param infos
	 */
	public String createRDV(HashMap<String, String> infos) {
		String heureVisite = infos.get("heureVisite");
		String dateVisite = infos.get("dateVisite");
		PlageHoraire plageHoraire = new PlageHoraire(heureVisite, dateVisite);
		String numReservation = repertoire.generateNumReservation();
		repertoire.addRDV(new RDV(numReservation, infos.get("nom"), infos.get("prenom"),
				plageHoraire, infos.get("typeDeDose"), infos.get("email"), false));
		return numReservation;
	}

	/**
	 * 
	 * @param infos
	 */
	public Boolean addWalkin(HashMap<String, String> infos)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param numeroDeCompte
	 */
	public int removeRDV(String numeroDeCompte) {
		// TODO - implement ControlleurVisiteur.removeRDV
		throw new UnsupportedOperationException();
	}

	public void envoyerRappel() {
		// TODO - implement ControlleurVisiteur.envoyerRappel
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param numeroDeReservation
	 */
	public String[] confirmerRDV(String numeroDeReservation) {
		// TODO - implement ControlleurVisiteur.confirmerRDV
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nom
	 * @param tel
	 */
	public String[] confirmerWalkin(String nom, String tel) {
		// TODO - implement ControlleurVisiteur.confirmerWalkin
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param info
	 */
	@Override
	public Boolean isValid(String[] info) {
		return null;
	}

	public RepertoireVisiteur getRepertoire() {
		return repertoire;
	}

	public void setRepertoire(RepertoireVisiteur repertoire) {
		this.repertoire = repertoire;
	}
}