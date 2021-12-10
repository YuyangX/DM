import java.io.Serializable;

public class Formulaire implements Serializable{
	
	
	public static final long serialVersionUID = 183290482134L;

	private String numeroDeCompte;
	private String nom;
	private String prenom;
	private Date dateDeNaissance;
	private String numeroDeCarteAssuranceMaladie;
	private Date dateDeVisite;
	private Boolean premiemeDoseRecu;
	private Boolean contracterCOVID;
	private Boolean symptome;
	private Boolean allergie;
	private Vaccin vaccin;
	private Boolean procedeALaVaccination;
	private Vaccin nomDuVaccin;
	private String codeDuVaccin;
	private String lotDuVaccin;

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

	public Date getDateDeNaissance() {
		return this.dateDeNaissance;
	}

	/**
	 * 
	 * @param dateDeNaissance
	 */
	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public String getNumeroDeCarteAssuranceMaladie() {
		return this.numeroDeCarteAssuranceMaladie;
	}

	/**
	 * 
	 * @param numeroDeCarteAssuranceMaladie
	 */
	public void setNumeroDeCarteAssuranceMaladie(String numeroDeCarteAssuranceMaladie) {
		this.numeroDeCarteAssuranceMaladie = numeroDeCarteAssuranceMaladie;
	}

	public Date getDateDeVisite() {
		return this.dateDeVisite;
	}

	/**
	 * 
	 * @param dateDeVisite
	 */
	public void setDateDeVisite(Date dateDeVisite) {
		this.dateDeVisite = dateDeVisite;
	}

	public Boolean getPremiemeDoseRecu() {
		return this.premiemeDoseRecu;
	}

	/**
	 * 
	 * @param premiemeDoseRecu
	 */
	public void setPremiemeDoseRecu(Boolean premiemeDoseRecu) {
		this.premiemeDoseRecu = premiemeDoseRecu;
	}

	public Boolean getContracterCOVID() {
		return this.contracterCOVID;
	}

	/**
	 * 
	 * @param contracterCOVID
	 */
	public void setContracterCOVID(Boolean contracterCOVID) {
		this.contracterCOVID = contracterCOVID;
	}

	public Boolean getSymptome() {
		return this.symptome;
	}

	/**
	 * 
	 * @param symptome
	 */
	public void setSymptome(Boolean symptome) {
		this.symptome = symptome;
	}

	public Boolean getAllergie() {
		return this.allergie;
	}

	/**
	 * 
	 * @param allergie
	 */
	public void setAllergie(Boolean allergie) {
		this.allergie = allergie;
	}

	public Vaccin getVaccin() {
		return this.vaccin;
	}

	/**
	 * 
	 * @param vaccin
	 */
	public void setVaccin(Vaccin vaccin) {
		this.vaccin = vaccin;
	}

	public Boolean getProcedeALaVaccination() {
		return this.procedeALaVaccination;
	}

	/**
	 * 
	 * @param procedeALaVaccination
	 */
	public void setProcedeALaVaccination(Boolean procedeALaVaccination) {
		this.procedeALaVaccination = procedeALaVaccination;
	}

	public String getNomDuVaccin() {
		// TODO - implement Formulaire.getNomDuVaccin
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nomDuVaccin
	 */
	public void setNomDuVaccin(String nomDuVaccin) {
		// TODO - implement Formulaire.setNomDuVaccin
		throw new UnsupportedOperationException();
	}

	public String getCodeDuVaccin() {
		return this.codeDuVaccin;
	}

	/**
	 * 
	 * @param codeDuVaccin
	 */
	public void setCodeDuVaccin(String codeDuVaccin) {
		this.codeDuVaccin = codeDuVaccin;
	}

	public String getLotDuVaccin() {
		return this.lotDuVaccin;
	}

	/**
	 * 
	 * @param lotDuVaccin
	 */
	public void setLotDuVaccin(String lotDuVaccin) {
		this.lotDuVaccin = lotDuVaccin;
	}

}