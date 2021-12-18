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
		// TODO - implement Menu.printBenevoleMenu
		System.out.println(
				"************ Menu Bénévole ***********\n"+
				"1.Vérifier RDV\n"+
				"2.Ajouter un visiteur spontané au calendrier\n"+
				"Veuillez saisir le numéro du service que vous voulez faire:");
	}

	public void printEmployeMenu() {
		// TODO - implement Menu.printEmployeMenu
		System.out.println(
				"************ Menu Employé ************\n"+
						"Gestion Bénévole:\n" +
						"1. Créer un bénévole\n" +
						"2. Modifier un bénévole\n" +
						"3. Supprimer un bénévole\n" +
						"—————————————————————————————\n"+
						"Gestion des comptes:\n" +
						"4. Créer un compte\n" +
						"5. Modifier un compte\n" +
						"6. Supprimer un compte\n" +
						"—————————————————————————————\n"+
						"Gestion des RDVs:\n" +
						"7. Créer un RDV\n" +
						"8. Supprimer un RDV\n" +
						"—————————————————————————————\n"+
						"Autre actions:\n"+
						"9. Envoyer des notifications\n" +
						"10.Remplir et imprimer formulaire\n"+
						"11.Envoyer preuve de vaccination\n" +
						"10.Confirm visiteur\n"+
						"Veuillez saisir le numéro du service que vous voulez faire:");
	}

	/**
	 * Login the account before access to menu
	 */
	public void loginMenu() {
		System.out.println("Veuillez entrer votre code d'identification, ou pressez 0 à quitter l'App:");
		Scanner scan1 = new Scanner(System.in);
		Benevole benevoleTrouve = new Benevole();
		Employe employeTrouve = new Employe();
		while (scan1.hasNextLine()) {
			String codeIdentification = scan1.nextLine();
			if (!codeIdentification.isEmpty()) {
				if (codeIdentification.equals("0")) {
					quit();
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
					quit();
				}
				int loginResult = controleurEquipier.login(motDePasse,loggedUser);
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

	public void gererBene() {
		// TODO - implement Menu.gererBene
		throw new UnsupportedOperationException();
	}

	public void gererVisi() {
		// TODO - implement Menu.gererVisi
		throw new UnsupportedOperationException();
	}

	public void gererRDV() {
		// TODO - implement Menu.gererRDV
		throw new UnsupportedOperationException();
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