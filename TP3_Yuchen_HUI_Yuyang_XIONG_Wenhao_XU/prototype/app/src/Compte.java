import java.io.Serializable;
import java.lang.reflect.Constructor;

public class Compte implements Serializable{
	public static final long serialVersionUID = 83257923L;
	private String numeroDeCompte;
	private String nom;
	private String prenom;
	private Date dateDeNaissance;
	private String adresseCourriel;
	private String numeroDeTelephone;
	private ProfilVaccination[] listeProfil = {null,null};

	
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

	/**
	 * setter for attribute listeProfil
	 * @param profil
	 */
	public void setProfil(ProfilVaccination[] profils) {
		this.listeProfil = profils;
	}

	public ProfilVaccination[] getProfil() {
		return this.listeProfil;
	}
	
	public ProfilVaccination getProfilNewest(){
		if (this.listeProfil[1] != null){
			return this.listeProfil[1];
		}else{
			if (this.listeProfil[0] != null){
				return this.listeProfil[0];
			}
		}
		return null;
	}

}