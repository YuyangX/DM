public class Compte {

	private String numeroDeCompte;
	private String nom;
	private String prenom;
	private String dateDeNaissance;
	private String adresseCourriel;
	private int numeroDeTelephone;
	private ProfilVaccination[] listeProfil;

	public String getNumeroDeCompte() {
		return this.numeroDeCompte;
	}

	/**
	 * 
	 * @param numeroDeCompte
	 */
	public void setNumeroDeCompte(String numeroDeCompte) {
		this.numeroDeCompte = numeroDeCompte;
	}

	public String getNom() {
		return this.nom;
	}

	/**
	 * 
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	/**
	 * 
	 * @param prenom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDateDeNaissance() {
		return this.dateDeNaissance;
	}

	/**
	 * 
	 * @param dateDeNaissance
	 */
	public void setDateDeNaissance(String dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public String getAdresseCourriel() {
		return this.adresseCourriel;
	}

	/**
	 * 
	 * @param adresseCourriel
	 */
	public void setAdresseCourriel(String adresseCourriel) {
		this.adresseCourriel = adresseCourriel;
	}

	public int getNumeroDeTelephone() {
		return this.numeroDeTelephone;
	}

	/**
	 * 
	 * @param numeroDeTelephone
	 */
	public void setNumeroDeTelephone(int numeroDeTelephone) {
		this.numeroDeTelephone = numeroDeTelephone;
	}

	/**
	 * 
	 * @param profil
	 */
	public void addProfil(ProfilVaccination profil) {
		// TODO - implement Compte.addProfil
		throw new UnsupportedOperationException();
	}

	public ProfilVaccination[] getProfil() {
		// TODO - implement Compte.getProfil
		throw new UnsupportedOperationException();
	}

}