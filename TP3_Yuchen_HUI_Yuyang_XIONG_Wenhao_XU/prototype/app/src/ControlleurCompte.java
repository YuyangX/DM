import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yuchen hui
 */
public class ControlleurCompte extends Controller implements Serializable {

	// pour qu'il soit serializable
	public static final long serialVersionUID = 8437L;
	private RepertoireCompte repertoire = new RepertoireCompte();
	private Compte compteAModifier;
	private Formulaire formulairePreremplie;

	/**
	 * Constructor : initialisation des attibuts
	 */
	public ControlleurCompte() {
		this.compteAModifier = new Compte();
		this.formulairePreremplie = new Formulaire();
		this.repertoire = new RepertoireCompte();
	}

	/**
	 * Generate a account number and creat a account to
	 * add in repository. Also fill in a part of profile de
	 * vaccination and send a mail to communicate the account number.
	 * 
	 * @param infos informations entered by user.
	 * @return account number generated automatically
	 *         (without repetition with those already exist).
	 */
	public String creerCompte(HashMap<String, String> infos) {
		// 3 tasks :
		// 1, create a compte
		// 2, remplir la première fois profil de vaccination.
		// 3, mail
		// task1
		Compte compte = new Compte();
		// task2
		// ProfilVaccination newProfil = new ProfilVaccination();
		// generate a compte number
		String num = this.repertoire.generateNumCompte();
		compte.setNumeroDeCompte(num);
		// transferer entree
		boolean tf = info2Compte(infos, compte);
		info2Formulaire(infos, formulairePreremplie);
		if (tf == false) {
			System.out.println("pattern not matched in creer Compte");
		}

		// 2, remplir la première fois profil de vaccination.
		// info2Profile(infos, newProfil);
		// remplirPremiereFoisProfil(newProfil);

		// ajouter dans le repository
		repertoire.ajouterCompte(compte);

		// 3,courriel
		System.out.println(CourrielCreationCompte(compte));

		return num;

	}

	/**
	 * simulation d'envoi d'un courriel pour communiquer
	 * le numero de compte genere automatiquement
	 * 
	 * @param c compte venant d'etre cree
	 * @return String a imprimer a terminal pour afficher le resultat
	 *         de l'envoi
	 */
	public String CourrielCreationCompte(Compte c) {
		String result = "ENVOIE 0%.........\n";
		result += "ENVOIE 23.333333%.........\n";
		result += "ENVOIE 64%.........\n";
		result += "ENVOIE 89%.........\n";
		result += "ENVOIE 100%.........!\n";
		result += "ENVOIE DE NUMERO DE COMPTE A "
				+ c.getAdresseCourriel()
				+ " A ETE FAIT AVEC SUCCES! AU REVOIR! \n";
		return result;
	}

	/**
	 * Modify a objet of type Compte using informations offered
	 * by HashMap "infos" passed by arguments
	 * 
	 * @param infos
	 */
	public void modifierCompte(HashMap<String, String> infos) {

		boolean tf = info2Compte(infos, this.compteAModifier);
		if (tf == false) {
			System.out.println("pattern not matched in modifierCompte");
		}
		repertoire.modifierCompte(compteAModifier);
	}

	/**
	 * Delete a account by its accountnumber. If not found return false,
	 * otherwise return true.
	 * 
	 * @param numeroDeCompte pas besoin de commentaire, le nom d'identifiant est
	 *                       déjà clair!
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
	 * 
	 * @return PDF virtuelle.
	 */
	private String envoyerCourriel() {
		String PDF = generatePDF();
		String result = PDF +
				"ENVOIE 0%.........\n";
		result += "ENVOIE 23.333333%.........\n";
		result += "ENVOIE 64%.........\n";
		result += "ENVOIE 89%.........\n";
		result += "ENVOIE 100%.........!\n";
		result += "ENVOIE DE LA PREUVE A " + compteAModifier.getAdresseCourriel()
				+ " AVEC SUCCES! AU REVOIR! \n";
		return result;

	}

	/**
	 * Simulation de la generation de PDF.
	 * 
	 * @return PDF virtuelle.
	 */
	private String generatePDF() {

		String result = "-------------------------------------------------\n";
		result += "--              Génération de PDF              --\n";
		result += "-------------------------------------------------\n";
		String nom = "Nom : " + compteAModifier.getNom() + "\n";
		String birthday = "Date de naissance : " +
				compteAModifier.getDateDeNaissance() + "\n";
		String QRcode = generateQRCode();
		String profile = "Profil(s) de Vaccination : \n";
		ProfilVaccination[] plist = compteAModifier.getProfil();
		if (plist[0] != null) {
			profile += "\n\nPremière dose : \n";
			profile += compteAModifier.getProfil()[0].toString();
		}

		if (plist[1] != null) {
			profile += "\n\nDeuxième dose : \n";
			profile += compteAModifier.getProfil()[1].toString();
		}
		result += nom + birthday + QRcode + profile;
		return result;

	}

	/**
	 * Simulation de la generation de QR code.
	 * 
	 * @return QRCode virtuelle
	 */
	private String generateQRCode() {

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
	 * 
	 * @param infos informations saisit par l'utilisateur.
	 */
	public void remplirFormulaire(HashMap<String, String> infos) {
		// two task :
		// 1, remplir le formulaire et l'imprimer
		// 2, remplir la première fois profil de vaccination.
		ProfilVaccination newProfile = new ProfilVaccination();
		info2Formulaire(infos, formulairePreremplie);
		info2Profile(infos, newProfile);
		newProfile.setNumeroDeCompte(this.compteAModifier.getNumeroDeCompte());
        formulairePreremplie.setNumeroDeCompte(this.compteAModifier.getNumeroDeCompte());
		remplirPremiereFoisProfil(newProfile);
		this.repertoire.modifierCompte(compteAModifier);
		// imprimer le formulaire
		imprimerFormulaire(formulairePreremplie);

	}

	/**
	 * execute the task of : put the profil in to the compte
	 * during the first time of
	 * 
	 * @param p profile de vaccination
	 */
	public void remplirPremiereFoisProfil(ProfilVaccination p) {
		ProfilVaccination[] list = this.compteAModifier.getProfil();

		// which profil?
		if (list[0] == null) {
			list[0] = p;
		} else if (list[1] == null) {
			list[1] = p;
		} else {
			System.out.println("you can only be vaccinated two times");
		}
		this.compteAModifier.setProfil(list);
	}

	/**
	 * cette fonction sera utilisé à la fin d'une journée, quand l'employé
	 * voudrait compléter les profile de vaccinations en ajoutant les
	 * informations remplies par des professionnels.
	 * 
	 * @param infos information supplémentaire.
	 */
	public void completerProfile(HashMap<String, String> infos) {
		ProfilVaccination[] list = this.compteAModifier.getProfil();
		if (!(list[1] == null)) {
			info2Profile(infos, list[1]);
		} else if (list[0] != null) {
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
	 */
	public void imprimerFormulaire(Formulaire fToPrint) {
		System.out.println(this.formulairePreremplie.toString());
	}

	/**
	 * Look for a account in repository by email and birthday.
	 * If not found return false, otherwise return true.
	 * It will also store the object found in attribute
	 * "CompteAModifier"
	 * 
	 * @param email           email
	 * @param dateDeNaissance birthday
	 * @return numero de compte trouve. Si le compte n'existe pas, return nullllll
	 */
	public String retrouverCompte(String email, String dateDeNaissance) {
		Compte compte = repertoire.getCompteByEmailetBirth(email, dateDeNaissance);
		if (compte == null) {
			return "nullllll";
		}
		this.compteAModifier = compte;
		return compte.getNumeroDeCompte();
	}

	/**
	 * Look for a account in repository by accountnumber.
	 * If not found return false, otherwise return true.
	 * It will also store the object found in attribute
	 * "CompteAModifier"
	 * 
	 * @param numCompte how can I explain more?
	 */
	public boolean chercherCompte(String numCompte) {
		Compte compte = repertoire.getCompte(numCompte);
		if (compte == null) {
			return true;
		}
		this.compteAModifier = compte;
		return true;
	}

	/**
	 * preremplir le formulaire en extrayant les champs
	 * de l'objet "compteAModifier"(un attribut) et utiliser les setters
	 * pour configurer les champs correspondants de l'objet
	 * FormulairePrerempli(un attribut)
	 */
	public void preremplirFormulaire() {
		formulairePreremplie.setNom(compteAModifier.getNom());
		formulairePreremplie.setPrenom(compteAModifier.getPrenom());
		formulairePreremplie.setNumeroDeCompte(compteAModifier.getNumeroDeCompte());
		formulairePreremplie.setDateDeNaissance(compteAModifier.getDateDeNaissance().toString());

	}

	/**
	 * Change attributes of a Compte type object by analysing entry
	 * pairs stored in a hashmap which is used to register
	 * information entered by terminal.
	 * 
	 * @param infos  hashmap
	 * @param compte objet to modify
	 * @return if all the keys in Hashmap match the pattern
	 */
	public boolean info2Compte(HashMap<String, String> infos, Compte compte) {
		for (Map.Entry<String, String> info : infos.entrySet()) {
			switch (info.getKey()) {
				case "numCompte":
					compte.setNumeroDeCompte(info.getValue());
					break;
				case "prenom":
					compte.setPrenom(info.getValue());
					break;
				case "nom":
					compte.setNom(info.getValue());
					break;
				case "dateNe":
					compte.setDateDeNaissance(info.getValue());
					break;
				case "email":
					compte.setAdresseCourriel(info.getValue());
					break;
				case "numTele":
					compte.setNumeroDeTelephone(info.getValue());
					break;
				default:
					System.out.println("Compte pattern not matched");
					break;
			}
		}
		return true;
	}

	/**
	 * Change attributes of a Formulaire type object by analysing entry
	 * pairs stored in a hashmap which is used to register
	 * information entered by terminal.
	 * 
	 * @param infos hashmap
	 * @param f     formualaire to modify
	 * @return if all the keys in Hashmap match the pattern
	 */
	public boolean info2Formulaire(HashMap<String, String> infos, Formulaire f) {

		for (Map.Entry<String, String> info : infos.entrySet()) {
			switch (info.getKey()) {
				case "numCompte":
					f.setNumeroDeCompte(info.getValue());
					break;
				case "prenom":
					f.setPrenom(info.getValue());
					break;
				case "nom":
					f.setNom(info.getValue());
					break;
				case "dateNe":
					f.setDateDeNaissance(info.getValue());
					break;
				case "numAssurMal":
					f.setNumeroDeCarteAssuranceMaladie(info.getValue());
					break;
				case "dateVisite":
					f.setDateDeVisite(info.getValue());
					break;
				case "recu1Dose":
					f.setPremiemeDoseRecu(info.getValue());
					break;
				case "contracteCovid":
					f.setContracterCOVID(info.getValue());
					break;
				case "symtomeCovid":
					f.setSymptome(info.getValue());
					break;// typo qu'on va vivre avec,OK.break;
				case "allergie":
					f.setAllergie(info.getValue());
					break;
				case "vaccinVoulu":
					f.setVaccinVoulu(info.getValue());
					break;
				case "procederVaccin":
					f.setProcedeALaVaccination(info.getValue());
					break;
				case "vaccinType":
					f.setNomDuVaccin(info.getValue());
					break;
				case "vaccinCode":
					f.setCodeDuVaccin(info.getValue());
					break;
				case "vaccinLot":
					f.setLotDuVaccin(info.getValue());
					break;

				default:
					System.out.println("formulaire pattern not matched");
			}
		}
		return true;
	}

	/**
	 * Change attributes of a ProfileDeVaccination type object by analysing entry
	 * pairs stored in a hashmap which is used to register
	 * information entered by terminal.
	 * 
	 * @param infos hashmap
	 * @param p     Profile to modify
	 * @return if all the keys in Hashmap match the pattern
	 */
	public boolean info2Profile(HashMap<String, String> infos, ProfilVaccination p) {

		for (Map.Entry<String, String> info : infos.entrySet()) {
			switch (info.getKey()) {
				case "numCompte":
					p.setNumeroDeCompte(info.getValue());
					break;
				case "dateVisite":
					p.setDateDeVaccination(info.getValue());
					break;
				case "recu1Dose":
					p.setTypeDeDose(info.getValue());
					break;
				case "vaccinType":
					p.setNomDuVaccin(info.getValue());
					break;
				case "vaccinCode":
					p.setCodeDuVaccin(info.getValue());
					break;
				case "vaccinLot":
					p.setLotDuVaccin(info.getValue());
					break;

				default:
				break;
			}
		}
		return true;
	}

	public Compte getCompteAModifier() {
		return compteAModifier;
	}
}
