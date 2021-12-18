import com.sun.net.httpserver.Authenticator;

import java.io.Serializable;
import javax.sound.midi.ControllerEventListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentSkipListMap;

public class Menu implements Serializable{


	public static final long serialVersionUID = 8490729875L;
	private int loggedUser; // 0 représente bénévole, 1 représente employé
	private Boolean isEmploye;
	private ControlleurEquipier controleurEquipier;
	private ControlleurCompte controleurCompte;
	private ControlleurVisiteur controleurVisiteur;

	public Menu(ControlleurEquipier controleurEquipier,
				ControlleurCompte controleurCompte,
				ControlleurVisiteur controleurVisiteur) {
		this.controleurEquipier = controleurEquipier;
		this.controleurCompte = controleurCompte;
		this.controleurVisiteur = controleurVisiteur;
	}

	public void processus() {
		loginMenu();

	}

	public Boolean getEmploye() {
		return isEmploye;
	}

	public void setEmploye(Boolean employe) {
		isEmploye = employe;
	}

	public void printBenevoleMenu() {
		System.out.println(
				"************ Menu Bénévole ***********\n"+
				"1.Vérifier RDV\n"+
				"2.Ajouter un visiteur spontané au calendrier\n"+
				"0. EXIT\n"+
				"Veuillez saisir le numéro du service que vous voulez faire:");
	}

	public void printEmployeMenu() {
		System.out.println(
				"************ Menu Employé ************\n"+
						"1. Gestion Bénévole:\n" +
						"2. Gestion des comptes:\n" +
						"3. Gestion des RDVs:\n" +
						"4. Envoyer des notifications\n" +
						"5. Remplir et imprimer formulaire\n"+
						"6. Envoyer preuve de vaccination\n" +
						"7. Confirm visiteur\n"+
						"0. EXIT\n"+
						"Veuillez saisir le numéro du service que vous voulez faire:");
	}

	/**
	 * Login the account before access to menu
	 */
	public int loginMenu() {
		System.out.println("Veuillez entrer votre code d'identification, ou pressez 0 à quitter l'App:");
		Scanner scan1 = new Scanner(System.in);
//		Benevole benevoleTrouve = new Benevole();
//		Employe employeTrouve = new Employe();
		int loginResult = -1;
		while (scan1.hasNextLine()) {
			String codeIdentification = scan1.nextLine();
			if (!codeIdentification.isEmpty()) {
//				if (codeIdentification.substring(0,1).equals("b")) {
//					benevoleTrouve = controleurEquipier.getRepertoire().getBenevole(codeIdentification);
//					isEmploye = false;
//					break;
//				} else if (codeIdentification.substring(0,1).equals("e")) {
//					employeTrouve = controleurEquipier.getRepertoire().getEmploye(codeIdentification);
//					isEmploye = true;
//					break;
//				} else {
//					System.out.println("Invalide. Ressayez SVP.");
				if (codeIdentification.equals("0")) {
					System.out.println("Exiting system...Exited!");
					System.exit(0);
				}
				int cherResult = controleurEquipier.chercherEquipier(codeIdentification);
				if (cherResult == 0){
					loggedUser = 0;
					break;
				}else if (cherResult == 1){
					loggedUser = 1;
					break;
				}else {
					System.out.println("Invalide. Ressayez SVP. (Pressez 0 à quitter)");
				}
			}
		}
		System.out.println("Veuillez entrer votre mot de passe, ou pressez 0 à quitter:");
		Scanner scan2 = new Scanner(System.in);
		while (scan2.hasNextLine()) {
			String motDePasse = scan2.nextLine();
			if (!motDePasse.isEmpty()) {
				if (motDePasse.equals("0")) {
					System.out.println("Exiting system...Exited!");
					System.exit(0);
				}
				loginResult = controleurEquipier.login(motDePasse,loggedUser);
				if (loginResult==0){
					printBenevoleMenu();
					break;
				}else if (loginResult==1){
					printEmployeMenu();
					break;
				}else {
					System.out.println("Mot de passe incorrect. Ressayez SVP. (Pressez 0 à quitter)");
				}
			}
		}
		return loginResult;
	}
	public void benevoleMenu(){
		Scanner myReader = new Scanner(System.in);
		while (myReader.hasNextLine()) {
			String order = myReader.nextLine();
			if (!order.isEmpty()) {
				switch (order) {
					case "1":
						verifier();
						printBenevoleMenu();
						break;
					case "2":
						sponCal();
						printBenevoleMenu();
						break;
					case "0":
						System.out.println("Exiting system...Exited!");
						System.exit(0);
						break;
					default:
						System.out.println("Sorry, enter invalide :( Please re-enter");
						break;
				}
			}
		}
	}

	public void employeMenu(){
		Scanner myReader = new Scanner(System.in);
		while (myReader.hasNextLine()) {
			String order = myReader.nextLine();
			if (!order.isEmpty()) {
				switch (order) {
					case "1":
						gererBene();
						printEmployeMenu();
						break;
					case "2":
						gererComte();
						printEmployeMenu();
						break;
					case "3":
						gererRDV();
						printEmployeMenu();
						break;
					case "4":
						rappel();
						printEmployeMenu();
						break;
					case "5":
						formulaire();
						printEmployeMenu();
						break;
					case "6":
						preuve();
						printEmployeMenu();
						break;
					case "7":
						confirmerRDV();
						printEmployeMenu();
						break;
					case "0":
						System.out.println("Exiting system...Exited!");
						System.exit(0);
						break;
					default:
						System.out.println("Sorry, enter invalide :( Please re-enter");
						break;
				}
			}
		}
	}

	public void creerRDV() {
		HashMap<String, String> infosNouveauRDV = new HashMap<>();
		imprimerCalendar();
		System.out.println("Veuillez entrer la date de rdv que vous voulez prendre. Suivez le format 'YYYY-MM-JJ':");
		Scanner scanner1 = new Scanner(System.in);
		while (scanner1.hasNextLine()) {
			String dateRDV = scanner1.nextLine();
			if (!dateRDV.isEmpty()) {
				if (Controller.isValid("dateVisite", dateRDV)) {
					infosNouveauRDV.put("dateVisite", dateRDV);
					imprimerHoraire(dateRDV);
					break;
				} else {
					System.out.println("Format invalide, suivez le format 'YYYY-MM-JJ'. Reessayez SVP.");
				}
			}
		}
		System.out.println("Veuillez entrer l'heure de rdv que vous voulez prendre. Suivez le format 'HH:mm':");
		while (scanner1.hasNextLine()) {
			String heureRDV =scanner1.nextLine();
			if (!heureRDV.isEmpty()) {
				if (Controller.isValid("heureVisite", heureRDV)) {
					infosNouveauRDV.put("heureVisite", heureRDV);
					break;
				} else {
					System.out.println("Format invalide, suivez le format 'HH:mm'. Reessayez SVP.");
				}
			}
		}
		System.out.println("Veuillez entrer votre nom de famille:");
		while (scanner1.hasNextLine()) {
			String nomFamille = scanner1.nextLine();
			if (!nomFamille.isEmpty()) {
				infosNouveauRDV.put("nom", nomFamille);
				break;
			}
		}
		System.out.println("Veuillez entrer votre prenom:");
		while (scanner1.hasNextLine()) {
			String prenom = scanner1.nextLine();
			if (!prenom.isEmpty()) {
				infosNouveauRDV.put("prenom", prenom);
				break;
			}
		}
		System.out.println("Le type de dose que vous voulez prendre, 'un' ou 'deux':");
		while (scanner1.hasNextLine()) {
			String type = scanner1.nextLine();
			if (!type.isEmpty()) {
				if (Controller.isValid("typeDose", type)) {
					infosNouveauRDV.put("typeDeDose", type);
					break;
				} else {
					System.out.println("Format invalide, saisissez 'un' ou 'deux'. Reessayez SVP.");
				}
			}
		}
		System.out.println("Veuillez entrer votre email:");
		while (scanner1.hasNextLine()) {
			String email = scanner1.nextLine();
			if (!email.isEmpty()) {
				if (Controller.isValid("email", email)) {
					infosNouveauRDV.put("email", email);
					break;
				} else {
					System.out.println("Format invalide. Reessayez SVP.");
				}
			}
		}
		controleurVisiteur.createRDV(infosNouveauRDV);
		controleurVisiteur.envoyerCourriel();
	}

	private void imprimerCalendar() {
		ConcurrentSkipListMap<Integer, Integer> calendar = controleurVisiteur.getCalendar();
		for (Map.Entry<Integer, Integer> day : calendar.entrySet()) {
			String date = String.valueOf(day.getKey());
			String dateFormelle = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6);
			System.out.println("Date: " + dateFormelle + "  " + "Nombre de personnes" + day.getValue());
 		}
	}

	private void imprimerHoraire(String datePrise) {
		ConcurrentSkipListMap<Integer, Integer> horaires = controleurVisiteur.getHoraireDatePrise(datePrise);
		for (Map.Entry<Integer, Integer> periode : horaires.entrySet()) {
			String heure = String.valueOf(periode.getKey());
			System.out.println("Heure: " + heure + "   " + "Nombre de personnes" + periode.getValue());
		}
	}

	public void removeRDV() {
		// TODO - implement Menu.removeRDV
		Scanner scanner = new Scanner(System.in);
		System.out.println("Veuillez entrer votre numéro de réservation:");
		while (scanner.hasNextLine()) {
			String numReservation = scanner.nextLine();
			if (!numReservation.isEmpty()) {
				if (Controller.isValid("nomReserve", numReservation)) {
					boolean resultat = controleurVisiteur.removeRDV(numReservation);
					if (resultat) {
						break;
					} else {
						System.out.println("Ce numéro de réservation n'existe pas!");
					}
				} else {
					System.out.println("Format invalide. Reessayez SVP.");
				}
			}
		}
	}

	public void verifierRDV() {
		// TODO - implement Menu.verifierRDV
		Scanner scanner = new Scanner(System.in);
		System.out.println("Veuillez entrer le numéro de réservation:");
		while (scanner.hasNextLine()) {
			String numReservation = scanner.nextLine();
			if (!numReservation.isEmpty()) {
				if (Controller.isValid("nomReserve", numReservation)) {
					String nomEtHeure = controleurVisiteur.verifierRDV(numReservation);
					if (!nomEtHeure.equals("non")) {
						System.out.println(nomEtHeure);
						break;
					} else {
						System.out.println("Ce numéro de réservation n'existe pas! Réessayer SVP.");
					}
				} else {
					System.out.println("Format invalide. Réessayez SVP.");
				}
			}
		}
	}

	public void confirmerRDV() {
		// TODO - implement Menu.confirmerRDV
		Scanner scanner = new Scanner(System.in);
		System.out.println("Veuillez entrer le numéro de réservation:");
		while (scanner.hasNextLine()) {
			String numReservation = scanner.nextLine();
			if (!numReservation.isEmpty()) {
				if (Controller.isValid("nomReserve", numReservation)) {
					boolean resultat = controleurVisiteur.confirmerRDV(numReservation);
					if (resultat) {
						System.out.println("RDV confirmé!");
						break;
					} else {
						System.out.println("Ce numéro de réservation n'existe pas! Réessayer SVP.");
					}
				} else {
					System.out.println("Format invalide. Réessayez SVP.");
				}
			}
		}
	}

	public void confirmerWalkin() {
		// TODO - implement Menu.confirmerRDV
		Scanner scanner = new Scanner(System.in);
		String nomAValider = null;
		String telAValider = null;
		System.out.println("Veuillez entrer le nom de famille:");
		while (scanner.hasNextLine()) {
			String nom = scanner.nextLine();
			if (!nom.isEmpty()) {
				if (Controller.isValid("nom", nom)) {
					nomAValider = nom;
				} else {
					System.out.println("Format invalide. Réessayez SVP.");
				}
			}
		}
		while (scanner.hasNextLine()) {
			String tel = scanner.nextLine();
			if (!tel.isEmpty()) {
				if (Controller.isValid("numTele", tel)) {
					telAValider = tel;
				} else {
					System.out.println("Format invalide. Réessayez SVP.");
				}
			}
		}
		boolean resultat = controleurVisiteur.confirmerWalkin(nomAValider, telAValider);
		if (resultat) {
			System.out.println("Walkin confirmé!");
		} else {
			System.out.println("Ce walkin n'existe pas.");
		}
	}

	public void rappelerRDV() {
		controleurVisiteur.envoyerRappel();
	}

	public void verifier() {
		// TODO - implement Menu.verifier
		throw new UnsupportedOperationException();
	}

	public void sponCal() {
		// TODO - implement Menu.sponCal
		throw new UnsupportedOperationException();
	}

	public void printGestionMenu(int choix){
		switch (choix){
			case 1:
				System.out.println(
						"Gestion Bénévole:\n" +
								"1. Créer un bénévole\n" +
								"2. Modifier un bénévole\n" +
								"3. Supprimer un bénévole\n"+
								"0. EXIT\n"+
								"Veuillez saisir le numéro du service que vous voulez faire:"
				);
				break;
			case 2:
				System.out.println(
						"Gestion des comptes:\n" +
								"1. Créer un compte\n" +
								"2. Modifier un compte\n" +
								"3. Supprimer un compte\n"+
								"0. EXIT\n"+
								"Veuillez saisir le numéro du service que vous voulez faire:"
				);
				break;
			case 3:
				System.out.println(
						"Gestion des RDVs:\n" +
								"1. Créer un RDV\n" +
								"2. Supprimer un RDV\n"+
								"0. EXIT\n"+
								"Veuillez saisir le numéro du service que vous voulez faire:"
				);
				break;
			default: System.out.println("ErrorMenu.");
		}
	}

	public void gererBene() {
		printGestionMenu(1);
		String id = null;
		Scanner myReader = new Scanner(System.in);
		OUT:
		while (myReader.hasNextLine()) {
			String order = myReader.nextLine();
			if (!order.isEmpty()) {
				switch (order) {
					case "1":
						//需添加检测必要信息是否齐全的判断函数
						controleurEquipier.ajouterBenevole(mapScanner());
						break;
					case "2":
						System.out.println("Veuillez entrer le code d'identification " +
								"de benevole que vous souhaitez modifier." +
								"Entrez 0 pour revenir au niveau précédent du menu.");
						Scanner codeReader1 = new Scanner(System.in);
						while (codeReader1.hasNextLine()) {
							String code = codeReader1.nextLine();
							if (!code.isEmpty()) {
								if (code.equals("0")){
									printGestionMenu(1);
									break OUT;
								}
								if (controleurEquipier.chercherBenevole(code)){
									id = code;
									break;
								}else{
									System.out.println("Benevole not found. Please re-enter." +
											"Pressez 0 pour revenir au niveau précédent du menu.");
								}
							}
						}
						System.out.println("Prenom: "+controleurEquipier.getBenevolAModifier().getPrenom());
						System.out.println("Nom: "+controleurEquipier.getBenevolAModifier().getNom());
						System.out.println("Date de naissance: "+
								controleurEquipier.getBenevolAModifier().getDateDeNaissance());
						System.out.println("Confirm? 1-Confirm, 0-Exit.");
						Scanner confirmReader1 = new Scanner(System.in);
						while (confirmReader1.hasNextLine()) {
							String confirm = confirmReader1.nextLine();
							if (!confirm.isEmpty()) {
								switch (confirm){
									case "1" :
										controleurEquipier.modifierBenevole(mapScanner());
										break OUT;
									case "0" :
										break OUT;
									default:
										System.out.println("Sorry, enter invalide :( Please re-enter");
										break;
								}
							}
						}
						break;
					case "3":
						System.out.println("Veuillez entrer le code d'identification " +
								"de benevole que vous souhaitez supprimer." +
								"Entrez 0 pour revenir au niveau précédent du menu.");
						Scanner codeReader2 = new Scanner(System.in);
						while (codeReader2.hasNextLine()) {
							String code = codeReader2.nextLine();
							if (!code.isEmpty()) {
								if (code.equals("0")){
									printGestionMenu(1);
									break OUT;
								}
								if (controleurEquipier.chercherBenevole(code)){
									id = code;
									break;
								}else{
									System.out.println("Benevole not found. Please re-enter." +
											"Entrez 0 pour revenir au niveau précédent du menu.");
								}
							}
						}
						System.out.println("Prenom: "+controleurEquipier.getBenevolAModifier().getPrenom());
						System.out.println("Nom: "+controleurEquipier.getBenevolAModifier().getNom());
						System.out.println("Date de naissance: "+
								controleurEquipier.getBenevolAModifier().getDateDeNaissance());
						System.out.println("Confirm? 1-Confirm, 0-Exit.");
						Scanner confirmReader2 = new Scanner(System.in);
						while (confirmReader2.hasNextLine()) {
							String confirm = confirmReader2.nextLine();
							if (!confirm.isEmpty()) {
								switch (confirm){
									case "1" :
										controleurEquipier.supprimerBenevole(id);
										System.out.println("Supprime avec success.");
										break OUT;
									case "0" :
										break OUT;
									default:
										System.out.println("Sorry, enter invalide :( Please re-enter");
										break;
								}
							}
						}
//						break;
					case "0":
						System.out.println("Exiting system...Exited!");
						System.exit(0);
						break;
					default:
						System.out.println("Sorry, enter invalide :( Please re-enter");
						break;
				}
			}
		}
	}

	public HashMap<String,String> mapScanner(){

		//需添加检测必要信息是否齐全的判断函数

		HashMap<String,String> hash = new HashMap<>();
		System.out.println("Sélectionnez une entrée que vous décidez de faire：\n" +
				"1. Prénom\n" +
				"2. Nom\n" +
				"3. Date de naissance\n" +
				"4. Adresse courriel\n" +
				"5. Numéro de télephone\n" +
				"6. Adresse(numéro, rue)\n" +
				"7. Code postal\n" +
				"8. Ville\n" +
				"——————————————————————————————————\n" +
				"Visite:\n" +
				"9. Numéro de réservation\n" +
				"10. Date de la visite(yyyy-MM-dd)\n" +
				"11. Heure de la visite(HH:mm)\n" +
				"12. Type de dose\n" +
				"——————————————————————————————————\n" +
				"Formulaire:\n" +
				"13. Numéro carte assurance maladie\n" +
				"14. Avez-vous déjà reçu une première dose?\n" +
				"15. Avez-vous déjà contracté la COVID?\n" +
				"16. Avez-vous des symptômes de la COVID?\n" +
				"17. Avez-vous des allergies?\n" +
				"18. Quel vaccin souhaitez-vous recevoir?\n" +
				"19. Avez-vous procédé à la vaccination?\n" +
				"20. Nom du vaccin\n" +
				"21. Code du vaccin\n" +
				"——————————————————————————————————\n" +
				"0. Finish and exit");

		Scanner myReader = new Scanner(System.in);
		LOOP:
		while (myReader.hasNextLine()) {
			String order = myReader.nextLine();
			if (!order.isEmpty()) {
				switch (order) {
//					这里没有添加id之类的自动生成的数据，需手动添加方法
					case "1":
					case "2":
					case "3":
					case "4":
					case "5":
					case "6":
					case "7":
					case "8":
					case "9":
					case "10":
					case "11":
					case "12":
					case "13":
					case "14":
					case "15":
					case "16":
					case "17":
					case "18":
					case "19":
					case "20":
					case "21":
						System.out.println("Please enter:");
						Scanner enterReader = new Scanner(System.in);
						while (enterReader.hasNextLine()) {
							String enter = enterReader.nextLine();
							if (!enter.isEmpty()) {
								switch (String.valueOf(enterVerify(order,enter))){
									case "true":
										hash.put(choix2Type(order),enter);
										System.out.println("Success enter: "+enter);
										break LOOP;
									case "false":
										System.out.println("Sorry, enter invalide :( Please re-enter");
										break;
								}
//								if (enterVerify(order,enter)){
//									hash.put(choix2Type(order),enter);
//									System.out.println("Success modified: "+enter);
//									break LOOP;
//								}else {
//									System.out.println("Sorry, enter invalide :( Please re-enter");
//									break;
//								}
							}
						}
						break;
					case "0":
						System.out.println("Finishing...Finished!");
						return hash;
					default:
						System.out.println("Sorry, wrong type :( Please re-enter");
						break;
				}
			}
		}
		return hash;
	}

	public String choix2Type(String choix){
		String type = null;
		switch (choix){
			case "1": type = "nom"; break;
			case "2": type = "prenom"; break;
			case "3": type = "dateNe"; break;
			case "4": type = "email"; break;
			case "5": type = "numTele"; break;
			case "6": type = "adresse"; break;
			case "7": type = "codePost"; break;
			case "8": type = "ville"; break;
			case "9": type = "numReserve"; break;
			case "10": type = "dateVisite"; break;
			case "11": type = "heureVisite"; break;
			case "12": type = "typeDose"; break;
			case "13": type = "numAssurMal"; break;
			case "14": type = "recu1Dose"; break;
			case "15": type = "contracteCovid"; break;
			case "16": type = "symtomeCovid"; break;
			case "17": type = "allergie"; break;
			case "18": type = "vaccinVoulu"; break;
			case "19": type = "procederVaccin"; break;
			case "20": type = "vaccinType"; break;
			case "21": type = "vaccinCode"; break;
		}
		return type;
	}

	public boolean enterVerify(String key, String value){
		return Controller.isValid(choix2Type(key),value);
	}

	public void gererComte() {
		printGestionMenu(2);
	}

	public void gererRDV() {
		printGestionMenu(3);
	}

	public void rappel() {
		// TODO - implement Menu.rappel
		throw new UnsupportedOperationException();
	}

	public void formulaire() {
		// TODO - implement Menu.formulaire
		throw new UnsupportedOperationException();
	}

	public void preuve() {
		// TODO - implement Menu.preuve
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param choix
	 */
	public void jugeBene(int choix) {
		// TODO - implement Menu.jugeBene
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param choix
	 */
	public void jugeVisi(int choix) {
		// TODO - implement Menu.jugeVisi
		throw new UnsupportedOperationException();
	}

	public void quit() {
		// TODO - implement Menu.quit
		System.exit(0);
	}

}