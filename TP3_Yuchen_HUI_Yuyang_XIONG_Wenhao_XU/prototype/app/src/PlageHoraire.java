import java.io.Serializable;

public class PlageHoraire extends Date implements Serializable{

	public static final long serialVersionUID = 842509387968904L;
	private String heure; // formre "HH:mm"

	public PlageHoraire(String date, String heure) {
		super(date);
		this.heure = heure;
	}

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

	public String getDate() {
		return getAnnee() + "-" + getMois() + "-" + getJour();
	}

	/**
	 * retourne la partie "HH" d'une heure
	 * @return nombre représantant "HH"
	 */
	public int getHH() {
		return Integer.parseInt((heure.split(":"))[0]);
	}
}