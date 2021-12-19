import java.io.Serializable;
public class Date implements Serializable{

	public static final long serialVersionUID = 12345L;

	private String annee;
	private String mois;
	private String jour;

	/**
	 * a constructor of Class date accepts a string as a argument.
	 * The string is formed like "YYYY-DD-MM", which has already be checked 
	 * by the method "isvalide()" in menu or controller. 
	 * @param date "YYYY-DD-MM"
	 */
	
	public Date(String date){
		//presume date has form of "YYYY-DD-MM"
		String[] dateString = date.split("-");

		this.annee = dateString[0];
		this.mois = dateString[1];
		this.jour = dateString[2];
	}
	public String getAnnee() {
		return this.annee;
	}

	/**
	 * setter
	 * 	 * @param Annee
	 */
	public void setAnnee(String Annee) {
		this.annee = Annee;
	}

	public String getMois() {
		return this.mois;
	}

	/**
	 * setter
	 * @param Mois
	 */
	public void setMois(String Mois) {
		this.mois = Mois;
	}

	public String getJour() {
		return this.jour;
	}
    
	/**
	 * setter
	 * @param Jour
	 */
	public void setJour(String Jour) {
		this.jour = Jour;
	}

    @Override
	public boolean equals(Object obj) {
		Date date = (Date)obj; //safecast
		return  ((this.annee.equals(date.annee)&&
		this.mois.equals(date.mois)
		&&this.jour.equals(date.jour)));
	}
	@Override
	public String toString() {
	    String result = this.annee + "-" + this.mois + "-" + this.jour;
		return result;
	}
}