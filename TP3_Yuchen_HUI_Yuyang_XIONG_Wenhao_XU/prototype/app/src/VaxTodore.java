import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class VaxTodore {

	public static void main(String[] args) {
		RepertoireEquipier repertoireEquipier = new RepertoireEquipier();
		ControlleurEquipier controlleurEquipier = new ControlleurEquipier();
		ControlleurVisiteur controlleurVisiteur = new ControlleurVisiteur();
		ControlleurCompte controlleurCompte = new ControlleurCompte();

		controlleurEquipier.setRepertoire(repertoireEquipier);

		// Employe a1 = new Employe("54321", "Kiron", "Xu", "qwert", "exuwenhao", "udem", "h3t1v7", "montreal", "yuyangxiong23@gmail.com", "4389411386", "2000-11-01");
		// Benevole b1 = new Benevole("12345", "Yuyang", "Xiong", "xyy1023", "bxiongyuyang", "udem", "h3t1v7", "montreal", "yuyangxiong23@gmail.com", "4389411386", "1999-09-22");
		try {
			
			ObjectInputStream oosCompte,oosVisiteur,oosEquipier = null;
			oosCompte = new ObjectInputStream(new FileInputStream("compte.dat"));
			oosVisiteur = new ObjectInputStream(new FileInputStream("visiteur.dat"));
			oosEquipier = new ObjectInputStream(new FileInputStream("equipier.dat"));
			
			controlleurCompte = (ControlleurCompte)oosCompte.readObject();
			controlleurVisiteur = (ControlleurVisiteur)oosVisiteur.readObject();
			controlleurEquipier = (ControlleurEquipier)oosEquipier.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// repertoireEquipier.ajouterBenevole(b1);
		// repertoireEquipier.ajouterEmploye(a1);

		Menu menu = new Menu(controlleurEquipier, controlleurCompte, controlleurVisiteur);
		int loginResult = menu.loginMenu();

		if (loginResult==0){
			menu.benevoleMenu();
		}else if (loginResult==1){
			menu.employeMenu();
		}else {
			System.out.println("LoginResult ERROR.");
			System.exit(0);
		}

		
	}



}