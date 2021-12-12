import java.io.Serializable;
import javax.sound.midi.ControllerEventListener;
import java.util.Scanner;

public class Menu implements Serializable{


	public static final long serialVersionUID = 8490729875L;
	private int loggedUser;
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
		System.out.println("Veuillez entrer votre code d'identification:");
		Scanner scan1 = new Scanner(System.in);
		Benevole benevoleTrouve = new Benevole();
		Employe employeTrouve = new Employe();
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
				int cherResult = controleurEquipier.chercherEquipier(codeIdentification);
				if (cherResult == 0){
//					benevoleTrouve = controleurEquipier.getBenevolAModifier();
					loggedUser = 0;
//					isEmploye = false;
					break;
				}else if (cherResult == 1){
//					employeTrouve = controleurEquipier.getEmployeLogin();
					loggedUser = 1;
//					isEmploye = true;
					break;
				}else {
					System.out.println("Invalide. Ressayez SVP.");
				}
			}
		}
		System.out.println("Veuillez entrer votre mot de passe:");
		Scanner scan2 = new Scanner(System.in);
		while (scan2.hasNextLine()) {
			String motDePasse = scan2.nextLine();
			if (!motDePasse.isEmpty()) {
//				if (isEmploye == true) {
//					if (employeTrouve.getMotDePasse().equals(motDePasse)) {
//						printEmployeMenu();
//						break;
//					}
//				} else {
//					if (benevoleTrouve.getMotDePasse().equals(motDePasse)) {
//						printBenevoleMenu();
//						break;
//					}
//				}
				int loginResult = controleurEquipier.login(motDePasse,loggedUser);
				if (loginResult==0){
					printBenevoleMenu();
					break;
				}else if (loginResult==1){
					printEmployeMenu();
					break;
				}else {
					System.out.println("Mot de passe incorrect.");
				}
			}
		}
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

	public void confirmerRDV() {
		// TODO - implement Menu.confirmerRDV
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
		throw new UnsupportedOperationException();
	}

}