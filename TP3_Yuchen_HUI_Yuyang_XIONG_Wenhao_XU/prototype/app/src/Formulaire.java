import java.io.Serializable;

import javax.naming.spi.DirStateFactory.Result;

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
	private String vaccinVoulu;
	private Boolean procedeALaVaccination;
	private String nomDuVaccin;
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
	public void setDateDeNaissance(String dateDeNaissance) {
		this.dateDeNaissance = new Date(dateDeNaissance);
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
	public void setDateDeVisite(String dateDeVisite) {
		this.dateDeVisite = new Date(dateDeVisite) ;
	}

	public Boolean getPremiemeDoseRecu() {
		return this.premiemeDoseRecu;
	}

	/**
	 * 
	 * @param premiemeDoseRecu
	 */
	public void setPremiemeDoseRecu(String yOrN) {
		this.premiemeDoseRecu = (yOrN.equals("Oui")? true : false);
	}

	public Boolean getContracterCOVID() {
		return this.contracterCOVID;
	}

	/**
	 * 
	 * @param contracterCOVID
	 */
	public void setContracterCOVID(String yOrN) {
		this.contracterCOVID = (yOrN.equals("Oui")? true : false);
	}

	public Boolean getSymptome() {
		return this.symptome;
	}

	/**
	 * 
	 * @param symptome
	 */
	public void setSymptome(String yOrN) {
		this.symptome = (yOrN.equals("Oui")? true : false);
	}

	public Boolean getAllergie() {
		return this.allergie;
	}

	/**
	 * 
	 * @param allergie
	 */
	public void setAllergie(String yOrN) {
		this.allergie = (yOrN.equals("Oui")? true : false);
	}

	public String getVaccinVoulu() {
		return this.vaccinVoulu;
	}

	/**
	 * 
	 * @param vaccin
	 */
	public void setVaccinVoulu(String vaccin) {
		this.vaccinVoulu = vaccin;
	}

	public Boolean getProcedeALaVaccination() {
		return this.procedeALaVaccination;
	}

	/**
	 * 
	 * @param procedeALaVaccination
	 */
	public void setProcedeALaVaccination(String yOrN) {
		this.procedeALaVaccination = (yOrN.equals("Oui")? true : false);
	}

	public String getNomDuVaccin() {
		return this.nomDuVaccin;
	}

	/**
	 * 
	 * @param nomDuVaccin
	 */
	public void setNomDuVaccin(String nomDuVaccin) {
		this.nomDuVaccin = nomDuVaccin;
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

	@Override
	public String toString() {
		String result = "" ;
		result  +=      "-------------------------------------------------\n";
		result  +=      "--                Questionnaire                --\n";
		result  +=      "-------------------------------------------------\n";
		result += "Numero de compte : " + this.numeroDeCompte + "\n ";
		result += "Nom : " + this.nom+ "\n ";
		result += "Prenom : " + this.prenom+ "\n ";
		result += "Date de naissance : " + this.dateDeNaissance + "\n ";
		result += "Numero de carte assuranceMaladie : " 
		+ this.numeroDeCarteAssuranceMaladie + "\n ";
		result += "Avez-vous recu la premiere dose ? :  : " + this.dateDeNaissance + "\n ";



		
	}

}