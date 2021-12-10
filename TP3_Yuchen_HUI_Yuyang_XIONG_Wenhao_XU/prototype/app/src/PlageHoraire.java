import java.io.Serializable;

public class PlageHoraire extends Date implements Serializable{


	public static final long serialVersionUID = 842509387968904L;
	private String heure;

	public String getHeure() {
		return this.heure;
	}

	/**
	 * 
	 * @param Heure
	 */
	public void setHeure(String Heure) {
		this.heure = Heure;
	}

}