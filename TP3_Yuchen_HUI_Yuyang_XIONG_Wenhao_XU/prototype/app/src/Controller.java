import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Controller implements Serializable{

	public static final long serialVersionUID = 83257823L;
	/**
	 * Vérifier que la chaîne d'entrée est de forme correcte
	 * @param key date type
	 * @param value string to verify format
	 * @return true if correct, false if incorrect
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
			case "dateNe":
				return verifierDate(value);
			case "heureVisite":
				return verifierHeure(value);
			case "typeDose":
				return value.equals("un") || value.equals("deux");
			case "numCompte":
				return verifierChiffre(12, value);
			case "numAssurMal":
				return !value.contains(" ");
			case "recu1Dose":
			case "allergie":
			case "contracteCovid":
			case "symtomeCovid":
			case "procederVaccin":
				return value.equals("Oui") || value.equals("Non");
			case "vaccinVoulu":
			case "vaccinType":
				return value.equals("Moderna") || value.equals("Pfizer")
						|| value.equals("AstraZeneca") || value.equals("Janssen");
			case "vaccinCode":
				return verifierLimit(24,value);
			case "codeIden":
				return verifierChiffre(9,value);
			case "motDePasse":
				return checkPasswordRule(value);
			case "numTele":
				return verifierChiffre(10,value);
			case "adresse":
				return verifierLimit(100, value) && verifierAddress(value);
			case "email":
				return verifierCourriel(value);
			default:
				System.out.println("Pattern not matched.");
				return false;
		}
	}

	/**
	 * Vérifier si l'entrée est un nombre
	 * @param str string to verify
	 * @return true if it's a number, false if it's not.
	 */
	public static boolean isNum(String str){
		try {
			Integer.parseInt(str);
			return true;
		}catch (NumberFormatException e){
			return false;
		}
	}

	/**
	 * Vérifiez que la longueur du chiffre d'entrée est conforme aux exigences.
	 * @param numChiffre num of chiffre
	 * @param entry string to verify
	 * @return true if format correct, false if format incorrect
	 */
	public static boolean verifierChiffre(int numChiffre,String entry){
		if (!isNum(entry)){
			return false;
		}
		if (entry.length()!=numChiffre){
			return false;
		}
		return true;
	}

	/**
	 * Vérifiez que la longueur des caractères saisis est conforme aux exigences.
	 * @param numCaractere num of character
	 * @param entry string to verify
	 * @return true if format correct, false if format incorrect
	 */
	public static boolean verifierLimit(int numCaractere,String entry){
		return entry.length() <= numCaractere;
	}

	/**
	 * Vérifiez que la date saisie est au bon format.
	 * @param str date to verify
	 * @return true if format correct, false if format incorrect
	 */
	public static boolean verifierDate(String str){
		String regex = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
		Pattern pattern = Pattern. compile(regex);
		Matcher m = pattern.matcher(str);
		boolean dateFlag = m.matches();
		if (!dateFlag) {
			return false;
		}
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
		formatter.setLenient(false);
		try{
			Date date = formatter.parse(str);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	/**
	 * Vérifiez que l'heure saisie est au bon format.
	 * @param str heure to verify
	 * @return true if format correct, false if format incorrect
	 */
	public static boolean verifierHeure(String str){
		if (str.matches("(0\\d{1}|1\\d{1}|2[0-3]):([0-5]\\d{1})")) {
			return true;
		}else{
			return false;
		}
	}

	//chiffre
	public static final String REG_NUMBER = ".*\\d+.*";
	//majuscule
	public static final String REG_UPPERCASE = ".*[A-Z]+.*";
	//minuscule
	public static final String REG_LOWERCASE = ".*[a-z]+.*";
	//caractère spécial
	public static final String REG_SYMBOL = ".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*";

	/**
	 * Vérifiez que le mot de passe saisi est au bon format.
	 * @param password password to verify
	 * @return true if format correct, false if format incorrect
	 */
	public static boolean checkPasswordRule(String password){
		//Retournez false si le mot de passe est vide ou s'il comporte moins de 8 chiffres.
		if (password == null || password.length() <8 ) return false;
		int i = 0;
		if (password.matches(REG_NUMBER)) i++;
		if (password.matches(REG_LOWERCASE))i++;
		if (password.matches(REG_UPPERCASE)) i++;
		if (password.matches(REG_SYMBOL)) i++;
		if (i  < 4 )  return false;
		return true;
	}

	/**
	 * Vérifiez que l'adress saisie est au bon format.
	 * @param adrs address to verify
	 * @return true if format correct, false if format incorrect
	 */
	public static boolean verifierAddress(String adrs){
		try {
			String[] adrsList = adrs.split(",");
			Integer.parseInt(adrsList[0]);
			return true;
		}catch (NumberFormatException e){
			return false;
		}
	}

	/**
	 * Vérifiez que le courriel saisi est au bon format.
	 * @param email email to verify
	 * @return true if format correct, false if format incorrect
	 */
	public static boolean verifierCourriel(String email){
		String[] emailList = email.split("@");
		if (emailList.length!=2){
			return false;
		}else {
			return true;
		}
	}

}