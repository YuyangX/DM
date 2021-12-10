import java.io.Serializable;

public class RepertoireCompte implements Serializable{

	public static final long serialVersionUID = 438752983740958L;
	private Compte[] comptes;
	private int index;

	/**
	 * 
	 * @param compte
	 */
	public Boolean ajouterCompte(Compte compte) {
		// TODO - implement Q.ajouterCompte
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param numCompte
	 */
	public Boolean supprimerCompte(String numCompte) {
		// TODO - implement Q.supprimerCompte
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param numéroDeCompte
	 */
	public Compte getCompte(String numéroDeCompte) {
		// TODO - implement Q.getCompte
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param compte
	 */
	public void modifierCompte(Compte compte) {

	}

	public ProfilVaccination[] getProfilList() {
		// TODO - implement Q.getProfilList
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ProfilVaccination
	 */
	public void setProfilList(int[] ProfilVaccination) {
		// TODO - implement Q.setProfilList
		throw new UnsupportedOperationException();
	}

	public void setIndex() {
		// TODO - implement Q.setIndex
		throw new UnsupportedOperationException();
	}

}