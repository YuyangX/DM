import java.io.Serializable;

public class ProfilVaccination implements Serializable{

	public static final long serialVersionUID = 4736598324795L;
	private String numeroDeCompte;
	private Date dateDeNaissance;
	private Type typeDeDose;
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

	public Type getTypeDeDose() {
		return this.typeDeDose;
	}

	/**
	 * 
	 * @param typeDeDose
	 */
	public void setTypeDeDose(Type typeDeDose) {
		this.typeDeDose = typeDeDose;
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

}