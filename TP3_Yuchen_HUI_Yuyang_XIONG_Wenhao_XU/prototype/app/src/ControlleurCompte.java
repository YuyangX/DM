import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Yuchen hui
 */
public class ControlleurCompte extends Controller implements Serializable{

	// pour qu'il soit serializable
	public static final long serialVersionUID = 8437L;
	private RepertoireCompte repertoire;
	private Compte compteAModifier;

	/**
	 * Generate a account number and creat a account to
	 * add in repository.
	 * @param infos informations entered by user.
	 * @return account number generated automatically 
	 * (without repetition with those already exist).
	 */
	public String creerCompte(HashMap<String,String> infos) {
		Compte compte = new Compte();
		String num = this.repertoire.generateNumCompte();
		compte.setNumeroDeCompte(num);
		boolean tf = info2Compte(infos, compte);
		if (tf == false){
			System.out.println("pattern not matched in creer Compte");
		}
		repertoire.ajouterCompte(compte);
		return num;

	}

	/**
	 * Modify a objet of type Compte using informations offered
	 * by HashMap "infos" passed by arguments
	 * @param infos
	 */
	public void modifierCompte(HashMap<String,String> infos) {
        
		boolean tf = info2Compte(infos, this.compteAModifier);
		if (tf == false){
			System.out.println("pattern not matched in modifierCompte");
		}
		repertoire.modifierCompte(compteAModifier);
	}

	/**
	 * Delete a account by its accountnumber. If not found return false,
	 * otherwise return true.
	 * @param numeroDeCompte pas besoin de commentaire, le nom d'identifiant est
	 * déjà clair!
	 */
	public boolean supprimerCompte(String numeroDeCompte) {
		return this.repertoire.supprimerCompte(numeroDeCompte);
	}

	public void envoyerPreuve() {
		// TODO - implement ControlleurCompte.envoyerPreuve
		throw new UnsupportedOperationException();
	}

    public remplirFormulaire(HashMap<String,String> infos){
		Formulaire fToPrint = new Formulaire();
		ProfilVaccination newProfile = new ProfilVaccination();
	    info2Formulaire(infos, fToPrint);
		info2Profile(infos, newProfile);
		this.repertoire.;
	}
	public completerProfil
	/**
	 * 
	 * @param infosRestantes
	 */
	public void imprimerFormulaire(Formulaire fToPrint) {
		// TODO - implement ControlleurCompte.imprimerFormulaire
		throw new UnsupportedOperationException();
	}

	/**
	 * Look for a account in repository by email and birthday.
	 * If not found return false, otherwise return true.
	 * It will also store the object found in attribute
	 * "CompteAModifier"
	 * @param email email
	 * @param dateDeNaissance birthday
	 */
	public boolean retrouverCompte(String email, String dateDeNaissance) {
		Compte compte = repertoire.getCompteByEmailetBirth(email,dateDeNaissance);
		if (compte == null){return true;}
		this.compteAModifier = compte;
		return true;
	}

	/**
	 * Look for a account in repository by accountnumber. 
	 * If not found return false, otherwise return true.
	 * It will also store the object found in attribute
	 * "CompteAModifier"
	 * @param numCompte how can I explain more?
	 */
	public boolean chercherCompte(String numCompte) {
		Compte compte = repertoire.getCompte(numCompte);
		if (compte == null){return true;}
		this.compteAModifier = compte;
		return true;
	}

	/**
	 * 
	 * @param infos
	 */
	public Boolean modifierProfil(String[] infos) {
		throw new UnsupportedOperationException();
	}

/**
   Change attributes of a Compte type object by analysing entry
   pairs stored in a hashmap which is used to register
   information entered by terminal.
 * @param infos  hashmap
 * @param compte objet to modify
 * @return if all the keys in Hashmap match the pattern
 */
public boolean info2Compte(HashMap<String,String> infos, Compte compte){
   for (Map.Entry<String,String> info: infos.entrySet()){
      switch (info.getKey()){
         case "numCompte":compte.setNumeroDeCompte(info.getValue());
         case "prenom":compte.setPrenom(info.getValue());
         case "nom":compte.setNom(info.getValue());
         case "dateNe":compte.setDateDeNaissance(info.getValue());
         case "email":compte.setAdresseCourriel(info.getValue());
         case "numTele":compte.setNumeroDeTelephone(info.getValue());
         default:
            System.out.println("Compte pattern not matched");
            return false;
      }
   }
   return true;
}

/** Change attributes of a Formulaire type object by analysing entry
   pairs stored in a hashmap which is used to register
   information entered by terminal.
 * @param infos  hashmap
 * @param f formualaire to modify
 * @return if all the keys in Hashmap match the pattern
 */
public boolean info2Formulaire(HashMap<String,String> infos, Formulaire f){
	
   for (Map.Entry<String,String> info: infos.entrySet()){
      switch (info.getKey()){
         case "numCompte":f.setNumeroDeCompte(info.getValue());
         case "prenom":f.setPrenom(info.getValue());
         case "nom":f.setNom(info.getValue());
         case "dateNe":f.setDateDeNaissance(info.getValue());
		 case "numAssurMal" : f.setNumeroDeCarteAssuranceMaladie(info.getValue());
		 case "dateVisite" : f.setDateDeVisite(info.getValue());	
		 case "recu1Dose" : f.setPremiemeDoseRecu(info.getValue());
		 case "contracteCovid" : f.setContracterCOVID(info.getValue());
		 case "symtomeCovid" : f.setSymptome(info.getValue());//typo qu'on va vivre avec,OK.
		 case "allergie" : f.setAllergie(info.getValue());
		 case "vaccinVoulu" : f.setVaccinVoulu(info.getValue());
		 case "procederVaccin" : f.setProcedeALaVaccination(info.getValue());
		 case "vaccinType" : f.setNomDuVaccin(info.getValue());
		 case "vaccinCode" : f.setCodeDuVaccin(info.getValue());
		 case "vaccinLot" : f.setLotDuVaccin(info.getValue());

         default:
            System.out.println("formulaire pattern not matched");
            return false;
      }
   }
   return true;
}

/** Change attributes of a ProfileDeVaccination type object by analysing entry
   pairs stored in a hashmap which is used to register
   information entered by terminal.
 * @param infos  hashmap
 * @param f Profile to modify
 * @return if all the keys in Hashmap match the pattern
 */
public boolean info2Profile(HashMap<String,String> infos, ProfilVaccination p){

   for (Map.Entry<String,String> info: infos.entrySet()){
      switch (info.getKey()){
         case "numCompte":p.setNumeroDeCompte(info.getValue());
		 case "dateVisite" : p.setDateDeVaccination(info.getValue());	
		 case "recu1Dose" : p.setTypeDeDose(info.getValue());
		 case "vaccinType" : p.setNomDuVaccin(info.getValue());
		 case "vaccinCode" : p.setCodeDuVaccin(info.getValue());
		 case "vaccinLot" : p.setLotDuVaccin(info.getValue());

         default:
            System.out.println("formulaire pattern not matched");
            return false;
		}
	} 
	return true;
}
}
