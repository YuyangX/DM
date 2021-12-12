import java.io.Serializable;

public class ProfilVaccination implements Serializable{

	public static final long serialVersionUID = 4736598324795L;
	private String numeroDeCompte;
	private Date dateDeVaccination;
	private String typeDeDose;
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

	public Date getDateDeVaccination() {
		return this.dateDeVaccination;
	}

	/**
	 * 
	 * @param dateDeVaccination
	 */
	public void setDateDeVaccination(String dateDeVaccination) {
		this.dateDeVaccination = new Date(dateDeVaccination);
	}

	public String getTypeDeDose() {
		return this.typeDeDose;
	}

	/**
	 * 
	 * @param typeDeDose
	 */
	public void setTypeDeDose(String yOrN) {
		this.typeDeDose = (yOrN.equals("Oui")? "deux" : "un");
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
		String result = "";
		result += "    -Numéro de compte : " + this.numeroDeCompte + "\n"
		+ "    -Date de vaccination : " + this.dateDeVaccination + "\n"
		+ "    -Type de dose : " + 
		(this.typeDeDose.equals("deux")? "Dexième dose" : "Première dose")+ "\n" 
		+ "    -Nom du vaccin : " + this.nomDuVaccin +"\n"
		+ "    -Code du vaccin : " + this.codeDuVaccin + "\n"
		+ "    -Lot du vaccin : " + this.lotDuVaccin + "\n";
		return result;
		
		
	}

}