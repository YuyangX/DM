import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Yuchen hui
 */
public class ControlleurCompte extends Controller implements Serializable{

	// pour qu'il soit serializable
	public static final long serialVersionUID = 8437L;
	private RepertoireCompte repertoire = new RepertoireCompte();
	private Compte compteAModifier;
	private Formulaire formulairePreremplie;

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
		info2Formulaire(infos,formulairePreremplie);
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

	/**
	 * Simulation de l'envoie de preuve 
	 */
	public void envoyerPreuve() {
		System.out.println(envoyerCourriel());		
	}

	/**
	 * Simulation de l'envoie de courriel
	 * @return PDF virtuelle.
	 */
	private String envoyerCourriel(){
		String PDF = generatePDF();
		String result = PDF + 
		"ENVOIE 0%.........\n";
		result += "ENVOIE 23.333333%.........\n";
		result += "ENVOIE 64%.........\n";
		result += "ENVOIE 89%.........\n";
		result += "ENVOIE 100%.........!\n";
		result += "ENVOIE DE LA PREUVE A " + compteAModifier.getAdresseCourriel()
		+" AVEC SUCCES! AU REVOIR! \n";
		return result;

	}

	/**
	 * Simulation de la generation de PDF.
	 * @return PDF virtuelle.
	 */
	private String generatePDF(){

		String result = "-------------------------------------------------\n";
		result  +=      "--              Génération de PDF              --\n";
		result  +=      "-------------------------------------------------\n";
		String nom = "Nom : " +compteAModifier.getNom() + "\n";
		String birthday = "Date de naissance : " + 
		compteAModifier.getDateDeNaissance() + "\n";
		String QRcode = generateQRCode();
		String profile = "Profil(s) de Vaccination : \n";
		ProfilVaccination[] plist = compteAModifier.getProfil();
		if (plist[0] != null){
			profile += "\n\nPremière dose : \n";
			profile  += compteAModifier.getProfil()[0].toString();
		}
			
		if (plist[1] != null){
			profile += "\n\nDeuxième dose : \n";
			profile  += compteAModifier.getProfil()[1].toString();
		}
		return result;

	}
	/**
	 * Simulation de la generation de QR code.
	 * @return QRCode virtuelle
	 */
	private String generateQRCode(){

		String QRcode = "Code QR utilisé par VaxiCode : \n";
		for (int i = 0; i < 20; i++) {
			QRcode += "                xxxxxxxxxxxxxxxxxxxxxxxxx\n";
		}
		return QRcode;
	}

	/**
	 * cette fonction a deux taches:
	 * // 1, remplir le formulaire et l'imprimer
	 * // 2, remplir la première fois profil de vaccination.
	 * @param infos informations saisit par l'utilisateur.
	 */
    public void remplirFormulaire(HashMap<String,String> infos){
		// two task :
		// 1, remplir le formulaire et l'imprimer
		// 2, remplir la première fois profil de vaccination.
		ProfilVaccination newProfile = new ProfilVaccination();
	    info2Formulaire(infos, formulairePreremplie);
		info2Profile(infos, newProfile);
		ProfilVaccination[] list = this.compteAModifier.getProfil();

		// which profil?
		if (list[0] == null){
			list[0] = newProfile;
		}else if (list[1] == null){
			list[1] = newProfile;
		}else{
			System.out.println("you can only be vaccinated two times");
		}
		this.compteAModifier.setProfil(list);
		this.repertoire.modifierCompte(compteAModifier);
		// imprimer le formulaire
		imprimerFormulaire(formulairePreremplie);

	}
	/**
	 * cette fonction sera utilisé à la fin d'une journée, quand l'employé
	 * voudrait compléter les profile de vaccinations en ajoutant les
	 * informations remplies par des professionnels.
	 * @param infos information supplémentaire.
	 */
	public void completerProfile(HashMap<String,String> infos){
		ProfilVaccination[] list = this.compteAModifier.getProfil();
		if(!(list[1] == null)){
			info2Profile(infos, list[1]);
		}else if(list[0] != null){
			info2Profile(infos, list[0]);
		}
		this.compteAModifier.setProfil(list);
		this.repertoire.modifierCompte(compteAModifier);
		// envoie de la preuve
		envoyerPreuve();
	}
	/**
	 * Simuler la fonctionnalité d'impression d'un formulaire
	 * qui imprimera un formulaire à la console de terminal.
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
 * @param p Profile to modify
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
