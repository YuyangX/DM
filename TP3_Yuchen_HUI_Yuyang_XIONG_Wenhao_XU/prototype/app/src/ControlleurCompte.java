import java.io.Serializable;

public class ControlleurCompte extends Controller implements Serializable{

	public static final long serialVersionUID = 8437L;
	private RepertoireCompte repertoire;
	private String[] infosPreremplies;
	private Compte compteAModifier;

	/**
	 * 
	 * @param infos
	 */
	public Boolean creerCompte(String[] infos) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param infos
	 */
	public Boolean modifierCompte(String[] infos) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param numeroDeCompte
	 */
	public int supprimerCompte(int numeroDeCompte) {
		// TODO - implement ControlleurCompte.supprimerCompte
		throw new UnsupportedOperationException();
	}

	public void envoyerPreuve() {
		// TODO - implement ControlleurCompte.envoyerPreuve
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param numeroDeCompte
	 */
	public Boolean preremplir(String numeroDeCompte) {
		// TODO - implement ControlleurCompte.preremplir
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param infosRestantes
	 */
	public void imprimerFormulaire(String infosRestantes) {
		// TODO - implement ControlleurCompte.imprimerFormulaire
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param email
	 * @param dateDeNaissance
	 */
	public String retrouverCompte(String email, String dateDeNaissance) {
		// TODO - implement ControlleurCompte.retrouverCompte
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param numCompte
	 */
	public int chercherCompte(String numCompte) {
		// TODO - implement ControlleurCompte.chercherCompte
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param infos
	 */
	public Boolean modifierProfil(String[] infos) {
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param info
	 */
}