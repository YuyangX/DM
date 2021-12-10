import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Controller {

	/**
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean isValid(String key, String value) {
		switch (key){
			case "nomReserve":
			case "codePost":
				return verifierChiffre(6, value);
			case "prenom":
			case "nom":
			case "ville":
				return verifierLimit(50, value);
			case "dateVisite":
			case "dateNe" :
				return verifierDate(value);
			case "heureVisite":
				return verifierHeure(value);
			case "typeDose":
				if (value.equals("1")||value.equals("2")){
					return true;
				}else {
					return false;
				}
			case "numCompte":
				return verifierChiffre(12, value);
			case "numAssurMal":
				if (value.indexOf(" ")!=-1){
					return false;
				}else {
					return true;
				}
			case "recu1Dose":
			case "allergie":
			case "symtomeCovid":
			case "procederVaccin":
				if (value.equals("Oui")||value.equals("Non")){
					return true;
				}else {
					return false;
				}
			case "vaccinVoulu":
			case "vaccinType":
				if (value.equals("Moderna")||value.equals("Pfizer")
						||value.equals("AstraZeneca")||value.equals("Janssen")){
					return true;
				}else {
					return false;
				}
			case "vaccinCode":
				return verifierLimit(24,value);
			case "codeIden":
				return verifierChiffre(9,value);
			case "motDePasse":

			case "numTele":
				return verifierChiffre(10,value);
			case "adresse":

			case "email":

			default: return false;
		}
	}

	/**
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str){
		try {
			Integer.parseInt(str);
			return true;
		}catch (NumberFormatException e){
			return false;
		}
	}

	public static boolean verifierChiffre(int numChiffre,String entry){
		if (!isNum(entry)){
			return false;
		}
		if (entry.length()!=numChiffre){
			return false;
		}
		return true;
	}

	public static boolean verifierLimit(int numCaractere,String entry){
		if (entry.length()>numCaractere){
			return false;
		}
		return true;
	}

	public static boolean verifierDate(String str){
		String regex = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
		Pattern pattern = Pattern. compile(regex);
		Matcher m = pattern.matcher(str);
		boolean dateFlag = m.matches();
		if (!dateFlag) {
			return false;
		}
		DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD" ) ;
		formatter.setLenient(false);
		try{
			Date date = formatter.parse(str);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	public static boolean verifierHeure(String str){
		if (str.matches("(0\\d{1}|1\\d{1}|2[0-3]):([0-5]\\d{1})")) {
			return true;
		}else{
			return false;

		}
	}

}