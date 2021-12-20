import com.sun.net.httpserver.Authenticator;

import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
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

	public Boolean getEmploye() {
		return isEmploye;
	}

	public void setEmploye(Boolean employe) {
		isEmploye = employe;
	}

	private void printBenevoleMenu() {
		System.out.println(
				"************ Menu Bénévole ***********\n"+
				"1.Vérifier RDV\n"+
				"2.Ajouter un visiteur spontané au calendrier\n"+
				"0. EXIT\n"+
				"Veuillez saisir le numéro du service que vous voulez faire:");
	}

	private void printEmployeMenu() {
		System.out.println(
				"************ Menu Employé ************\n"+
						"1. Gestion Bénévole:\n" +
						"2. Gestion des comptes:\n" +
						"  -- (y compris formulaire et preuve)\n" +
						"3. Gestion des RDVs:\n" +
						"4. Envoyer des rappels\n" +
						"5. Confirm rdv\n"+
						"6. Confirm walkin\n"+
						"0. EXIT\n"+
						"Veuillez saisir le numéro du service que vous voulez faire:");
	}

	/**
	 * Login the account before access to menu
	 */
	public int loginMenu() {
		System.out.println("Veuillez entrer votre code d'identification, ou pressez 0 à quitter l'App:");
		Scanner scan1 = new Scanner(System.in);
		int loginResult = -1;
		while (scan1.hasNextLine()) {
			String codeIdentification = scan1.nextLine();
			if (!codeIdentification.isEmpty()) {
				if (codeIdentification.equals("0")) {
					System.out.println("Exiting system...Exited!");
					serializaiton();
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
					serializaiton();
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


	/**
	 * Manipulation des sélections du menu Benevole
	 */
	public void benevoleMenu(){
		Scanner myReader = new Scanner(System.in);
		while (myReader.hasNextLine()) {
			String order = myReader.nextLine();
			if (!order.isEmpty()) {
				switch (order) {
					case "1":
						verifierRDV();
						printBenevoleMenu();
						break;
					case "2":
						boolean succes = creerWalkin();
						System.out.println("\n");
						if (succes) System.out.println("*** Réussi d'ajouter un walkin ***\n");
						System.out.println("\n");
						printBenevoleMenu();
						break;
					case "0":
						System.out.println("Exiting system...Exited!");
					    serializaiton();
						System.exit(0);
						break;
					default:
						System.out.println("Sorry, enter invalide :( Please re-enter");
						break;
				}
			}
		}
	}

	/**
	 * Manipulation des sélections du menu Employe
	 */
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
						rappelerRDV();
						printEmployeMenu();
						break;
					// case "5":
					// 	formulaire();
					// 	printEmployeMenu();
					// 	break;
					// case "6":
					// 	preuve();
					// 	printEmployeMenu();
					// 	break;
					case "5":
						confirmerRDV();
						printEmployeMenu();
						break;
					case "6":
						confirmerWalkin();
						printEmployeMenu();
						break;
					case "0":
						System.out.println("Exiting system...Exited!");
					    serializaiton();
						System.exit(0);
						break;
					default:
						System.out.println("Sorry, enter invalide :( Please re-enter");
						break;
				}
			}
		}
	}

	/**
	 * Crée un nouveau rdv.
	 * @return true représente qu'un nouveau rdv a bien été créé, false représente qu'on a
	 * interrompu le processus de la création.
	 */
	public boolean creerRDV() {
		HashMap<String, String> infosNouveauRDV = new HashMap<>();
		imprimerCalendar();
		System.out.println("Veuillez entrer la date de rdv que vous voulez prendre. Suivez le format 'YYYY-MM-JJ':");
		System.out.println("(Pressez 0 à retourner au menu)");
		Scanner scanner1 = new Scanner(System.in);
		while (scanner1.hasNextLine()) {
			String dateRDV = scanner1.nextLine();
			if (dateRDV.equals("0")) return false;
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
		System.out.println("(Pressez 0 à retourner au menu)");
		while (scanner1.hasNextLine()) {
			String heureRDV =scanner1.nextLine();
			if (!heureRDV.isEmpty()) {
				if (heureRDV.equals("0")) return false;
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
			if (nomFamille.equals("0")) return false;
			if (!nomFamille.isEmpty()) {
				infosNouveauRDV.put("nom", nomFamille);
				break;
			}
		}
		System.out.println("Veuillez entrer votre prenom:");
		while (scanner1.hasNextLine()) {
			String prenom = scanner1.nextLine();
			if (prenom.equals("0")) return false;
			if (!prenom.isEmpty()) {
				infosNouveauRDV.put("prenom", prenom);
				break;
			}
		}
		System.out.println("Le type de dose que vous voulez prendre, 'un' ou 'deux':");
		while (scanner1.hasNextLine()) {
			String type = scanner1.nextLine();
			if (type.equals("0")) return false;
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
			if (email.equals("0")) return false;
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
		return true;
	}

	/**
	 * Imprime un calendrier, qui montre chaque jour et son nombre de rdvs contenus.
	 */
	private void imprimerCalendar() {
		ConcurrentSkipListMap<Integer, Integer> calendar = controleurVisiteur.getCalendar();
		int compte = 0;
		System.out.println("\n");
		System.out.println("*** Calendrier de rdvs ***\n");
		for (Map.Entry<Integer, Integer> day : calendar.entrySet()) {
			String date = String.valueOf(day.getKey());
			String dateFormelle = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6);
			System.out.println("Date: " + dateFormelle + "  Nombre de personnes: " + day.getValue());
			compte++;
 		}
		if (compte ==0) System.out.println("(Il n'y a pas encore de rdvs enregistrés dans le système.)\n");
		System.out.println("\n");
	}

	/**
	 * Imprime un horaire d'un certain jour, qui montre chaque heure son nombre de rdvs contenus.
	 */
	private void imprimerHoraire(String datePrise) {
		ConcurrentSkipListMap<Integer, Integer> horaires = controleurVisiteur.getHoraireDatePrise(datePrise);
		int compte = 0;
		System.out.println("\n*** Plage horaire de rdvs à ce jour ***\n");
		for (Map.Entry<Integer, Integer> periode : horaires.entrySet()) {
			String heure = String.valueOf(periode.getKey());
			System.out.println("Heure: " + heure + ":00" + "   Nombre de rdvs: " + periode.getValue());
			compte++;
		}
		if (compte == 0) System.out.println("(Il n'y a pas encore de rdvs à ce jour.)\n");
	}

	/**
	 * Supprime un rdv du système.
	 * @return true représente qu'un rdv a bien été supprimé, false représente qu'on a
	 * interrompu le processus d'une suppression.
	 */
	public boolean removeRDV() {
		// TODO - implement Menu.removeRDV
		Scanner scanner = new Scanner(System.in);
		System.out.println("Veuillez entrer votre numéro de réservation:");
		System.out.println("(Pressez 0 à retourner au menu)");
		while (scanner.hasNextLine()) {
			String numReservation = scanner.nextLine();
			if (numReservation.equals("0")) return false;
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
		return true;
	}

	/**
	 * Vérifie si un rdv est dans le système.
	 */
	public void verifierRDV() {
		// TODO - implement Menu.verifierRDV
		Scanner scanner = new Scanner(System.in);
		System.out.println("Veuillez entrer le numéro de réservation:");
		System.out.println("(Pressez 0 à retourner au menu)");
		while (scanner.hasNextLine()) {
			String numReservation = scanner.nextLine();
			if (numReservation.equals("0")) return;
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
					System.out.println("(Pressez 0 à retourner au menu)");
				}
			}
		}
	}

	/**
	 * Change l'état d'un rdv dans le système, de "non confirmé" à "confirmé".
	 */
	public void confirmerRDV() {
		// TODO - implement Menu.confirmerRDV
		Scanner scanner = new Scanner(System.in);
		System.out.println("Veuillez entrer le numéro de réservation:");
		System.out.println("(Pressez 0 à retourner au menu)");
		while (scanner.hasNextLine()) {
			String numReservation = scanner.nextLine();
			if (numReservation.equals("0")) return;
			if (!numReservation.isEmpty()) {
				if (Controller.isValid("nomReserve", numReservation)) {
					boolean resultat = controleurVisiteur.confirmerRDV(numReservation);
					if (resultat) {
						System.out.println("RDV confirmé!\n");
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

	/**
	 * Crée un nouveau walkin.
	 * @return true représente qu'un nouveau walkin a bien été créé, false représente qu'on a
	 * interrompu le processus de la création.
	 */
	public boolean creerWalkin() {
		HashMap<String, String> infosNouveauWalkin = new HashMap<>();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Veuillez entrer la date d'aujourd'hui:");
		System.out.println("(Pressez 0 à retourner au menu)");
		while (scanner.hasNextLine()) {
			String date = scanner.nextLine();
			if (date.equals("0")) return false;
			if (!date.isEmpty()) {
				if (Controller.isValid("dateVisite", date)) {
					imprimerHoraireDuJour(date);
					infosNouveauWalkin.put("dateVisite", date);
					break;
				} else {
					System.out.println("Format invalide, suivez le format 'YYYY-MM-JJ'. Reessayez SVP.");
					System.out.println("(Pressez 0 à retourner au menu)");
				}
			}
		}
		System.out.println("\nVeuillez entrer l'heure de vaccination pour le walkin:");
		System.out.println("(Pressez 0 à retourner au menu)");
		while (scanner.hasNextLine()) {
			String heure = scanner.nextLine();
			if (heure.equals("0")) return false;
			if (!heure.isEmpty()) {
				if (Controller.isValid("heureVisite", heure)) {
					infosNouveauWalkin.put("heureVisite", heure);
					break;
				} else {
					System.out.println("Format invalide, suivez le format 'HH:mm'. Reessayez SVP.");
					System.out.println("(Pressez 0 à retourner au menu)");
				}
			}
		}
		System.out.println("Veuillez entrer le nom de famille du walkin:");
		System.out.println("(Pressez 0 à retourner au menu)");
		while (scanner.hasNextLine()) {
			String nom = scanner.nextLine();
			if (nom.equals("0")) return false;
			if (!nom.isEmpty()) {
				if (Controller.isValid("nom", nom)) {
					infosNouveauWalkin.put("nom", nom);
					break;
				} else {
					System.out.println("Format invalide. Reessayez SVP.");
					System.out.println("(Pressez 0 à retourner au menu)");
				}
			}
		}
		System.out.println("Veuillez entrer le prenom du walkin:");
		System.out.println("(Pressez 0 à retourner au menu)");
		while (scanner.hasNextLine()) {
			String prenom = scanner.nextLine();
			if (prenom.equals("0")) return false;
			if (!prenom.isEmpty()) {
				if (Controller.isValid("prenom", prenom)) {
					infosNouveauWalkin.put("prenom", prenom);
					break;
				} else {
					System.out.println("Format invalide. Réessayez SVP.");
					System.out.println("(Pressez 0 à retourner au menu)");
				}
			}
		}
		System.out.println("Veuillez entrer le numéro de téléphone du walkin:");
		System.out.println("(Pressez 0 à retourner au menu)");
		while (scanner.hasNextLine()) {
			String tel = scanner.nextLine();
			if (tel.equals("0")) return false;
			if (!tel.isEmpty()) {
				if (Controller.isValid("numTele", tel)) {
					infosNouveauWalkin.put("numTele", tel);
					break;
				} else {
					System.out.println("Format invalide, un numéro de téléphone est composé de 10 chiffres. Reessayez SVP.");
					System.out.println("(Pressez 0 à retourner au menu)");
				}
			}
		}
		controleurVisiteur.addWalkin(infosNouveauWalkin);
		return true;
	}

	/**
	 * Imprime un horaire d'aujourd'hui, qui montre chaque heure son nombre de rdvs contenus.
	 */
	private void imprimerHoraireDuJour (String dateDuJour) {
		ConcurrentSkipListMap<Integer, Integer> horaires = controleurVisiteur.getHoraireDuJour(dateDuJour);
		int compte = 0;
		System.out.println("\n*** Plage horaire de rdvs dLaujourd'hui ***\n");
		for (Map.Entry<Integer, Integer> periode : horaires.entrySet()) {
			String heure = String.valueOf(periode.getKey());
			System.out.println("Heure: " + heure + ":00" + "   Nombre de rdvs: " + periode.getValue());
			compte++;
		}
		if (compte == 0) System.out.println("(Il n'y a pas encore de rdvs aujourd'hui.)\n");
	}

	/**
	 * Vérifie si un walkin est dans le système.
	 */
	public void confirmerWalkin() {
		// TODO - implement Menu.confirmerRDV
		Scanner scanner = new Scanner(System.in);
		String nomAValider = null;
		String telAValider = null;
		System.out.println("Veuillez entrer le nom de famille:");
		System.out.println("(Pressez 0 à retourner au menu)");
		while (scanner.hasNextLine()) {
			String nom = scanner.nextLine();
			if (nom.equals("0")) return;
			if (!nom.isEmpty()) {
				if (Controller.isValid("nom", nom)) {
					nomAValider = nom;
				} else {
					System.out.println("Format invalide. Réessayez SVP.");
				}
			}
		}
		System.out.println("Veuillez entrer le numéro de téléphone:");
		System.out.println("(Pressez 0 à retourner au menu)");
		while (scanner.hasNextLine()) {
			String tel = scanner.nextLine();
			if (tel.equals("0")) return;
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

	/**
	 * Envoie un rappel aux visiteurs qui vont avoir un rdv dans 2 jours.
	 */
	public void rappelerRDV() {
		controleurVisiteur.envoyerRappel();
	}

	public void sponCal() {
		// TODO - implement Menu.sponCal
		throw new UnsupportedOperationException();
	}

	private void printGestionMenu(int choix){
		switch (choix){
			case 1:
				System.out.println(
						"**********  Gestion Bénévole ***********\n" +
								"1. Créer un bénévole\n" +
								"2. Modifier un bénévole\n" +
								"3. Supprimer un bénévole\n"+
								"0. EXIT\n"+
								"Veuillez saisir le numéro du service que vous voulez faire:"
				);
				break;
			case 2:
				System.out.println(
						"************ Gestion des comptes *************8\n" +
								"1. Créer un compte (ensuite remplir et imprimer formulaire) \n" +
								"2. Modifier un compte\n" +
								"  -- ( y compris completer profil et remplir formulaire)\n" +
								"3. Supprimer un compte\n"+
								"0. EXIT\n"+
								"Veuillez saisir le numéro du service que vous voulez faire:"
				);
				break;
			case 3:
				System.out.println(
						"******** Gestion des RDVs **********:\n" +
								"1. Créer un RDV\n" +
								"2. Supprimer un RDV\n"+
								"3. Retourner au menu\n"+
								"0. EXIT\n"+
								"Veuillez saisir le numéro du service que vous voulez faire:"
				);
				break;
			default: System.out.println("ErrorMenu.");
		}
	}

	/**
	 * Manipulation des sélections du "Gestion de Benevole"
	 */
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
						//Si les informations sont incomplètes,
						// le système signalera une erreur lors de l'opération suivante.
						System.out.println("Le Benevole doit contenir au moins " +
								"les informations suivantes lors de son ajout : nom, prénom et date de naissance.");
						controleurEquipier.ajouterBenevole(mapScanner());
						System.out.println("Successfully added!");
						break OUT;
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
					    serializaiton();
						System.exit(0);
						break;
					default:
						System.out.println("Sorry, enter invalide :( Please re-enter");
						break;
				}
			}
		}
	}

	private void printMapMenu(){
		System.out.println("Sélectionnez une entrée que vous décidez de faire：\n" +
				"1. Prénom(50 caractère max)\n" +
				"2. Nom(50 caractère max)\n" +
				"3. Date de naissance(yyyy-MM-dd)\n" +
				"4. Adresse courriel(xxx@xxx)\n" +
				"5. Numéro de télephone(10 chiffres)\n" +
				"6. Adresse(numéro, rue)\n" +
				"7. Code postal(6 chiffres)\n" +
				"8. Ville(50 caractère max)\n" +
				"——————————————————————————————————\n" +
				"Visite:\n" +
				"9. Numéro de réservation(6 chiffres)\n" +
				"10. Date de la visite(yyyy-MM-dd)\n" +
				"11. Heure de la visite(HH:mm)\n" +
				"12. Type de dose(reponse accepte: un,deux)\n" +
				"——————————————————————————————————\n" +
				"Formulaire:\n" +
				"(Remplis par employe)\n" + 
				"13. Numéro carte assurance maladie(sans espace)\n" +
				"14. Avez-vous déjà reçu une première dose?(Oui/Non)\n" +
				"15. Avez-vous déjà contracté la COVID?(Oui/Non)\n" +
				"16. Avez-vous des symptômes de la COVID?(Oui/Non)\n" +
				"17. Avez-vous des allergies?(Oui/Non)\n" +
				"18. Quel vaccin souhaitez-vous recevoir?(Moderna/Pfizer/AstraZeneca/Janssen)\n" +
				"(Remplis par personnel)\n" +
				"19. Avez-vous procédé à la vaccination?(Oui/Non)\n" +
				"20. Nom du vaccin(Moderna/Pfizer/AstraZeneca/Janssen)\n" +
				"21. Code du vaccin(24 caractère max)\n" +
				"——————————————————————————————————\n" +
				"0. Finish and exit");
	}

	/**
	 * @return Renvoie un formulaire avec des informations (pour faciliter l'ajout, la modification, etc.)
	 */
	public HashMap<String,String> mapScanner(){

		HashMap<String,String> hash = new HashMap<>();
		printMapMenu();

		Scanner myReader = new Scanner(System.in);
		while (myReader.hasNextLine()) {
			String order = myReader.nextLine();
			if (!order.isEmpty()) {
				LOOP:
				switch (order) {
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
										if (hash.containsKey(choix2Type(order))){
											hash.replace(choix2Type(order),enter);
										}else {
											hash.put(choix2Type(order), enter);
										}
										System.out.println("Success enter: "+enter);
										System.out.println("*************************************************");
										printMapMenu();
										break LOOP;
									case "false":
										System.out.println("Sorry, enter invalide :( Please re-enter");
										break;
								}
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

	private String choix2Type(String choix){
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

	/**
	 * @param key datatype
	 * @param value enter
	 * @return Verifier si l'entrer is valide
	 */
	public boolean enterVerify(String key, String value){
		return Controller.isValid(choix2Type(key),value);
	}

	public void gererComte() {
		printGestionMenu(2);

		String id = null;
		Scanner myReader = new Scanner(System.in);
		OUT:
		while (myReader.hasNextLine()) {
			String order = myReader.nextLine();
			if (!order.isEmpty()) {
				switch (order) {
					case "1":
					    String numDeCompte ;
						numDeCompte = controleurCompte.creerCompte(mapScanner());
						System.out.println("Successfully added! Compte number is : "
											+ numDeCompte 
											+ "\n"
											+ "Press any key + enter to continue to fill in a formular");
					    myReader.nextLine();
						String result = "-------------------------------------------------\n";
						result  +=      "--             Remplir le formulaire           --\n";
						result  +=      "-------------------------------------------------\n";
						System.out.println(result);
						controleurCompte.chercherCompte(numDeCompte);
						HashMap<String,String> temp = mapScanner();
						HashMap<String,String> temp1;
						while (temp.size() < 6){
							System.out.println("pas assez d'entree !\n" + 
						"Veuillez saisir plus d'entre pour generer un formulaire");
                            temp1 = mapScanner(); 
							for (Map.Entry<String,String> e : temp1.entrySet()) {
								temp.put(e.getKey(),e.getValue());
								
							}
						}
						controleurCompte.remplirFormulaire(temp);
						System.out.println("Press any key + enter to continue");
						myReader.nextLine();
						break OUT;
					case "2":
					
						System.out.println("Veuillez entrer le numero de compte " +
								"du compte que vous souhaitez modifier.\n" +
								"Entrez 0 pour revenir au niveau précédent du menu.\n"+
								"Entrez 1 pour retrouver le compte par email et date de naissance\n"
								);
						Scanner codeReader1 = new Scanner(System.in);
						while (codeReader1.hasNextLine()) {
							String code = codeReader1.nextLine();
							if (!code.isEmpty()) {
								if (code.equals("0")){
									printGestionMenu(2);
									break OUT;
								}else if (code.equals("1")){
									System.out.println("veuillez enter le courriel");
									String mail = codeReader1.nextLine();
									System.out.println("veuillez entrer la date de naissance (YYYY-mm-dd)");
									String ne = codeReader1.nextLine();
									code = controleurCompte.retrouverCompte(mail, ne);
									if (code.equals("nullllll")){
										System.out.println("je trouve pas le compte...");
										break OUT;
								}
								
								if (controleurCompte.chercherCompte(code)){
									id = code;
									break;
								}else{
									System.out.println("Compte not found. Please re-enter." +
											"Pressez 0 pour revenir au niveau précédent du menu.");
								}
							}
						}
						System.out.println("Prenom: "+controleurCompte.getCompteAModifier().getPrenom());
						System.out.println("Nom: "+controleurCompte.getCompteAModifier().getNom());
						System.out.println("Date de naissance: "+
								controleurCompte.getCompteAModifier().getDateDeNaissance());
						System.out.println("Confirm? 1-Confirm, 0-Exit.");
						Scanner confirmReader1 = new Scanner(System.in);
						while (confirmReader1.hasNextLine()) {
							String confirm = confirmReader1.nextLine();
							if (!confirm.isEmpty()) {
								switch (confirm){
									case "1" :
										gererModifieCompteSousMenu();
										break OUT;
									case "0" :
										break OUT;
									default:
										System.out.println("Sorry, enter invalide :( Please re-enter");
										break;
								}
							}
						}
					
						break;}
					case "3":
						System.out.println("Veuillez entrer le numero de compte " +
								"du compte que vous souhaitez modifier.\n" +
								"Entrez 0 pour revenir au niveau précédent du menu.\n");
						Scanner codeReader2 = new Scanner(System.in);
						while (codeReader2.hasNextLine()) {
							String code = codeReader2.nextLine();
							if (!code.isEmpty()) {
								if (code.equals("0")){
									printGestionMenu(2);
									break OUT;
								}
								if (controleurCompte.chercherCompte(code)){
									id = code;
								}else{
									System.out.println("Compte not found. Please re-enter." +
											"Entrez 0 pour revenir au niveau précédent du menu.");
								}
							}
						}
						System.out.println("Prenom: "+controleurCompte.getCompteAModifier().getPrenom());
						System.out.println("Nom: "+controleurCompte.getCompteAModifier().getNom());
						System.out.println("Date de naissance: "+
								controleurCompte.getCompteAModifier().getDateDeNaissance());
						System.out.println("Confirm? 1-Confirm, 0-Exit.");
						Scanner confirmReader2 = new Scanner(System.in);
						while (confirmReader2.hasNextLine()) {
							String confirm = confirmReader2.nextLine();
							if (!confirm.isEmpty()) {
								switch (confirm){
									case "1" :
										controleurCompte.supprimerCompte(id);
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
						break OUT;
					default:
						System.out.println("Sorry, enter invalide :( Please re-enter");
						break;
				}
			}
		}
	}

	public void gererRDV() {
		printGestionMenu(3);
	}

	private void gererModifieCompteSousMenu(){
		System.out.println(printModifieCompteSousMenu());
		Scanner choixReader2 = new Scanner(System.in);
		while (choixReader2.hasNextLine()) {
			String confirm = choixReader2.nextLine();
			if (!confirm.isEmpty()) {
				switch (confirm){
					case "1" :
					controleurCompte.preremplirFormulaire();
					controleurCompte.remplirFormulaire(mapScanner()); break;
					case "2" :
					controleurCompte.completerProfile(mapScanner()); break;
				    case "3" :
					controleurCompte.modifierCompte(mapScanner()); break;
					case "0" :
						break ;
					default:
						System.out.println("Sorry, enter invalide :( Please re-enter");
						
				}
				break;
			}
		}

	}
	private String printModifieCompteSousMenu(){
		String result = "**********Modifier Compte submenu*********\n";
		result = "OK! Vous avez des choix ci-dessous en termes de modification\n";
		result = "Entrez 0 pour exit()\n";
		result += "1. Remplir le formulaire \n";
		result += "2. completer le profil \n";
		result += "3. modifier des entrees normales (nom, prenom .. autre que formualaire)\n";
		result += "0. exit()\nVeuillez saisir le numéro du service que vous voulez faire : \n";
		return result;
		
		
	}
	public void rappel() {
		Scanner myReader = new Scanner(System.in);
		while (myReader.hasNextLine()) {
			String order = myReader.nextLine();
			if (!order.isEmpty()) {
				switch (order) {
					case "1":
						boolean succes1 = creerRDV();
						System.out.println("\n");
						if (succes1) System.out.println("*** Réussi d'ajouter un rdv ***\n");
						if (succes1) System.out.println("\n*** Veuillez noter votre numéro de réservation, " +
								"il se trouve dans le courriel. ***\n");
						System.out.println("\n");
						break;
					case "2":
						boolean succes2 = removeRDV();
						System.out.println("\n");
						if (succes2) System.out.println("*** Réussi de supprimer un rdv ***\n");
						System.out.println("\n");
						break;
					case "3":
						break;
					case "0":
						System.out.println("Exiting system...Exited!");
					    serializaiton();
						System.exit(0);
						break;
					default:
						System.out.println("Sorry, entrée invalide :( Réessayez SVP.");
						break;
				}
			}
			break;
		}
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


	public void serializaiton(){
		try {
			
			ObjectOutputStream oosCompte,oosVisiteur,oosEquipier = null;
			oosCompte = new ObjectOutputStream(new FileOutputStream("compte.dat"));
			oosVisiteur = new ObjectOutputStream(new FileOutputStream("visiteur.dat"));
			oosEquipier = new ObjectOutputStream(new FileOutputStream("equipier.dat"));
			oosCompte.writeObject(this.controleurCompte);
			oosCompte.flush();
			oosCompte.close();
			oosVisiteur.writeObject(this.controleurVisiteur);
			oosVisiteur.flush();
			oosVisiteur.close();
			oosEquipier.writeObject(this.controleurEquipier);
			oosEquipier.flush();
			oosEquipier.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{

		}
        

	}
}
