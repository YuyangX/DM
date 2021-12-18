import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ControlleurEquipier extends Controller implements Serializable{

	public static final long serialVersionUID = 0523452003275L;
	private RepertoireEquipier repertoire = new RepertoireEquipier();
	private Benevole benevolAModifier;
	private Employe employeLogin;

	/**
	 * Change attributes of a Equipier type object by analysing entry
	 * pairs stored in a hashmap which is used to register
	 * information entered by terminal.
	 * @param infos information d'un equipier
	 * @param benevole benevole
	 * @return true if everything goes well, false if there's a pattern not matched
	 */
	public boolean infoScanner(HashMap<String,String> infos,Benevole benevole){
		for (Map.Entry<String,String> info: infos.entrySet()){
			switch (info.getKey()){
				case "codeIden":benevole.setCodeIdentification(info.getValue());
				case "motDePasse":benevole.setMotDePasse(info.getValue());
				case "numCompte":benevole.setNumeroDeCompte(info.getValue());
				case "prenom":benevole.setPrenom(info.getValue());
				case "nom":benevole.setNom(info.getValue());
				case "dateNe":benevole.setDateDeNaissance(info.getValue());
				case "email":benevole.setAdresseCourriel(info.getValue());
				case "numTele":benevole.setNumeroDeTelephone(info.getValue());
				case "adresse":benevole.setAdresse(info.getValue());
				case "codePost":benevole.setCodePostal(info.getValue());
				case "ville":benevole.setVille(info.getValue());
				default:
					System.out.println("Benevole pattern not matched");
					return false;
			}
		}
		return true;
	}

	/**
	 * Ajouter un benevole
	 * @param infos information d'un benevole
	 */
	public Boolean ajouterBenevole(HashMap<String,String> infos) {
		Benevole benevoleAdd = new Benevole();
		Boolean bool = infoScanner(infos,benevoleAdd);
		repertoire.ajouterBenevole(benevoleAdd);
		return bool;
	}

	/**
	 * Supprimer un benevole
	 * @param code code identification
	 */
	public boolean supprimerBenevole(String code) {
		return repertoire.supprimerBenevole(code);
	}

	/**
	 * Modifier un benevole by information given
	 * @param infos information d'un benevole
	 */
	public void modifierBenevole(HashMap<String,String> infos) {
		infoScanner(infos,benevolAModifier);
		repertoire.modifierBenevole(benevolAModifier);
	}

	/**
	 * Login by checking password
	 * @param motDepasse mot de passe
	 * @param equipierType type d'un equipier
	 * @return -1 Mot de passe incorrect, 0 Benevole, 1 Employe
	 */
	public int login(String motDepasse, int equipierType) {
		if (equipierType==0){
			if (benevolAModifier.getMotDePasse().equals(motDepasse)){
				return 0;
			}
		}else if(equipierType==1){
			if (employeLogin.getMotDePasse().equals(motDepasse)){
				return 1;
			}
		}
		return -1;
	}

	/**
	 * Search for a equipier
	 * @param code code identification
	 * @return -1 Not found, 0 Benevole, 1 Employe
	 */
	public int chercherEquipier(String code){
		for (int i=0;i < repertoire.benevoles.size();i++) {
			if (repertoire.benevoles.get(i).getCodeIdentification().equals(code)) {
				benevolAModifier = repertoire.benevoles.get(i);
				return 0;
			}
		}
		for (int i=0;i < repertoire.employes.size();i++) {
			if (repertoire.employes.get(i).getCodeIdentification().equals(code)) {
				employeLogin = repertoire.employes.get(i);
				return 1;
			}
		}
		return -1;
	}

	/**
	 * Search for a benevole to modify
	 * @param code code identification
	 * @return -1 Not found, 1 found
	 */
	public int chercherBenevole(String code) {
		for (int i=0;i < repertoire.benevoles.size();i++) {
			if (repertoire.benevoles.get(i).getCodeIdentification().equals(code)) {
				benevolAModifier = repertoire.benevoles.get(i);
				return 1;
			}
		}
		return -1;
	}

	/**
	 * @return repertoire
	 */
	public RepertoireEquipier getRepertoire() {
		return repertoire;
	}

	/**
	 * @param repertoire repertoire
	 */
	public void setRepertoire(RepertoireEquipier repertoire) {
		this.repertoire = repertoire;
	}

}