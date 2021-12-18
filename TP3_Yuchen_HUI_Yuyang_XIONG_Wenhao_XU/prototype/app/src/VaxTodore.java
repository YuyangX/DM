public class VaxTodore {

	public static void main(String[] args) {
		// TODO - implement VaxTodore.main
		ControlleurEquipier controlleurEquipier = new ControlleurEquipier();
		ControlleurVisiteur controlleurVisiteur = new ControlleurVisiteur();
		ControlleurCompte controlleurCompte = new ControlleurCompte();


		Employe a1 = new Employe("54321", "Kiron", "Xu", "qwert", "exuwenhao", "udem", "h3t1v7", "montreal", "yuyangxiong23@gmail.com", "4389411386", "2000-11-01");
		Benevole b1 = new Benevole("12345", "Yuyang", "Xiong", "xyy1023", "bxiongyuyang", "udem", "h3t1v7", "montreal", "yuyangxiong23@gmail.com", "4389411386", "1999-09-22");

		controlleurEquipier.getRepertoire().ajouterBenevole(b1);
		controlleurEquipier.getRepertoire().ajouterEmploye(a1);

		Menu menu = new Menu(controlleurEquipier, controlleurCompte, controlleurVisiteur);
		//menu.processus();
		menu.creerRDV();
	}

}