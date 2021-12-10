import java.io.Serializable;

public class Walkin implements Serializable{

	public static final long serialVersionUID = 48484529835837L;
	private String nom;
	private String prenom;
	private String heure;
	private String tel;

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

	public String getHeure() {
		return this.heure;
	}

	/**
	 * 
	 * @param heure
	 */
	public void setHeure(String heure) {
		this.heure = heure;
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