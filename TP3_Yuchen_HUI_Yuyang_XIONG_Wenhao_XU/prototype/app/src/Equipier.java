import java.io.Serializable;

public abstract class Equipier implements Serializable{


	public static final long serialVersionUID = 0523652103275L;
	private String numeroDeCompte;
	private String nom;
	private String prenom;
	private String motDePasse;
	private String codeIdentification;
	private String adresse;
	private String codePostal;
	private String ville;
	private String adresseCourriel;
	private String numeroDeTelephone;

	public Equipier() {}

	public Equipier(String numeroDeCompte, String nom, String prenom, String motDePasse,
					String codeIdentification, String adresse, String codePostal, String ville,
					String adresseCourriel, String numeroDeTelephone) {
		this.numeroDeCompte = numeroDeCompte;
		this.nom = nom;
		this.prenom = prenom;
		this.motDePasse = motDePasse;
		this.codeIdentification = codeIdentification;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.adresseCourriel = adresseCourriel;
		this.numeroDeTelephone = numeroDeTelephone;
	}

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

	public String getMotDePasse() {
		return this.motDePasse;
	}

	/**
	 * 
	 * @param motDePasse
	 */
	public void setMotDePasse(int motDePasse) {
		// TODO - implement Equipier.setMotDePasse
		throw new UnsupportedOperationException();
	}

	public String getCodeIdentification() {
		return this.codeIdentification;
	}

	/**
	 * 
	 * @param codeIdentification
	 */
	public void setCodeIdentification(String codeIdentification) {
		this.codeIdentification = codeIdentification;
	}

	public void getAdresse() {
		// TODO - implement Equipier.getAdresse
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Adresse
	 */
	public void setAdresse(int Adresse) {
		// TODO - implement Equipier.setAdresse
		throw new UnsupportedOperationException();
	}

	public String getCodePostal() {
		return this.codePostal;
	}

	/**
	 * 
	 * @param codePostal
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return this.ville;
	}

	/**
	 * 
	 * @param ville
	 */
	public void setVille(String ville) {
		this.ville = ville;
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

	public String getNumeroDeTelephone() {
		return this.numeroDeTelephone;
	}

	/**
	 * 
	 * @param numeroDeTelephone
	 */
	public void setNumeroDeTelephone(String numeroDeTelephone) {
		this.numeroDeTelephone = numeroDeTelephone;
	}

	public void getAttribute() {
		// TODO - implement Equipier.getAttribute
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param attribute
	 */
	public void setAttribute(int attribute) {
		// TODO - implement Equipier.setAttribute
		throw new UnsupportedOperationException();
	}

}