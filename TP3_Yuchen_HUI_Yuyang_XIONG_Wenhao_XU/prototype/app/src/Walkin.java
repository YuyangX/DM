import java.io.Serializable;

public class Walkin implements Serializable{

	public static final long serialVersionUID = 48484529835837L;
	private String nom;
	private String prenom;
	private PlageHoraire plageHoraire;
	private String tel;

	public Walkin(String nom, String prenom, PlageHoraire plageHoraire, String tel) {
		this.nom = nom;
		this.prenom = prenom;
		this.plageHoraire = plageHoraire;
		this.tel = tel;
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

	public PlageHoraire getPlageHoraire() {
		return plageHoraire;
	}

	/**
	 *
	 * @param plageHoraire
	 */
	public void setPlageHoraire(PlageHoraire plageHoraire) {
		this.plageHoraire = plageHoraire;
	}

	public String getTel() {
		return this.tel;
	}

	/**
	 * 
	 * @param tel
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

}