import java.io.Serializable;

import javax.lang.model.util.ElementScanner6;
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
	 * @param yOrN
	 */
	public void setPremiemeDoseRecu(String yOrN) {
		this.premiemeDoseRecu = (yOrN.equals("Oui")? true : false);
	}

	public Boolean getContracterCOVID() {
		return this.contracterCOVID;
	}

	/**
	 * 
	 * @param yOrN
	 */
	public void setContracterCOVID(String yOrN) {
		this.contracterCOVID = (yOrN.equals("Oui")? true : false);
	}

	public Boolean getSymptome() {
		return this.symptome;
	}

	/**
	 * 
	 * @param yOrN
	 */
	public void setSymptome(String yOrN) {
		this.symptome = (yOrN.equals("Oui")? true : false);
	}

	public Boolean getAllergie() {
		return this.allergie;
	}

	/**
	 * 
	 * @param yOrN
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
	 * @param yOrN
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

	private String bool2String(boolean origin){
		if (origin == true)
			return "Oui";
		else if(origin == false)
			return "Non";
		else
			return null;
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
		result  +=      "INFORMATIONS REMPLIES PAR L'EMPLOYÉ\n ";
		result += "Numero de compte : " + this.numeroDeCompte + "\n ";
		result += "Nom : " + this.nom+ "\n ";
		result += "Prenom : " + this.prenom+ "\n ";
		result += "Date de naissance : " + this.dateDeNaissance + "\n ";
		result += "Date de visite : " + this.dateDeVisite + "\n ";
		result += "Numero de carte assuranceMaladie : " 
		+ this.numeroDeCarteAssuranceMaladie + "\n ";
		result += "Quel type de vaccin voulez-vous etre recu ? : " 
		+ this.vaccinVoulu + "\n ";
		result += "Avez-vous des allergies ? :   " 
		+bool2String(this.allergie) + "\n ";
		result += "Avez-vous des symptomes de COVID-19 ? :   " 
		+bool2String(this.symptome) + "\n ";
		result += "Avez-vous recu la premiere dose ? :   " 
		+bool2String(this.premiemeDoseRecu) + "\n ";
		result += "Avez-vous contracte le Covid ? : " + 
		this.bool2String(contracterCOVID) + "\n";
		result += "         ..........\n";
		result += "         ..........\n";
		result += "         ..........\n";
		result += "         ..........\n";
		result += "         ..........\n";
		result += "         ..........\n";
		result += "         ..........\n";
        result += "\n\njuste une simulation donc je n'ai pas mis le tout\n\n";
		result += "INFORMATIONS REMPLIES PAR LE PROFESSIONNEL DE LA \n SANTÉ\n";
		result += "Avez-vous procédé à la vaccination : " + 
		this.bool2String(procedeALaVaccination) + "\n";
		result += "Nom du vaccin procédé : ______________________" + "\n"; 
		result += "Code du vaccin procédé : ______________________" + "\n"; 
		result += "Lot du vaccin procédé : ______________________" + "\n"; 
		result  +=      "-------------------------------------------------\n";
		result  +=      "--            Fin du Questionnaire             --\n";
		result  +=      "-------------------------------------------------\n";
		return result;
		


		
	}

}