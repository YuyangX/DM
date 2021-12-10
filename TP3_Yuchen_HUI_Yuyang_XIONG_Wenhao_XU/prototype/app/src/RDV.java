import java.io.Serializable;

public class RDV implements Serializable{

	public static final long serialVersionUID = 8475293847598439L;
	private String numeroDeReservation;
	private String nom;
	private String prenom;
	private PlageHoraire plageHoraire;
	private Type typeDeDose;
	private String email;
	private Boolean confirmation;

	public String getNumeroDeReservation() {
		return this.numeroDeReservation;
	}

	/**
	 * 
	 * @param numeroDeReservation
	 */
	public void setNumeroDeReservation(String numeroDeReservation) {
		this.numeroDeReservation = numeroDeReservation;
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
		return this.plageHoraire;
	}

	/**
	 * 
	 * @param plageHoraire
	 */
	public void setPlageHoraire(PlageHoraire plageHoraire) {
		this.plageHoraire = plageHoraire;
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

	public String getEmail() {
		return this.email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}