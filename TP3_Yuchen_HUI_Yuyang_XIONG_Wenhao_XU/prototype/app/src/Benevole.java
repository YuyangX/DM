public class Benevole extends Equipier {

	private Date[] listDispo;

	public Benevole() {
	}

	public Benevole(String numeroDeCompte, String nom, String prenom, String motDePasse,
					String codeIdentification, String adresse, String codePostal, String ville,
					String adresseCourriel, String numeroDeTelephone, String dateDeNaissance) {
		super(numeroDeCompte, nom, prenom, motDePasse, codeIdentification, adresse, codePostal,
				ville, adresseCourriel, numeroDeTelephone, dateDeNaissance);
	}

	/**
	 * @return listDispo
	 */
	public Date[] getList() {
		return listDispo;
	}

}