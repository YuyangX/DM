import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class RepertoireEquipier implements Serializable{

    
	public static final long serialVersionUID = 0523652003275L;
	public ArrayList<Employe> employes = new ArrayList<>();
	public ArrayList<Benevole> benevoles = new ArrayList<>();
	private int index;
	private ArrayList<String> tousLesNumCompte;
	private ArrayList<String> tousLesID;

	/**
	 * generate a random account number never be used.
	 * @return account number
	 */
	public String generateNumCompte(){
		String nouveau = generate12String();
		if (tousLesNumCompte.indexOf(nouveau) == -1){
			this.tousLesNumCompte.add(nouveau);
			return nouveau;
		}else{
			return generateNumCompte();
		}
	}
	/**
	 * generate a random string presents a account of "12 chiffres"
	 * @return
	 */
	private String generate12String (){
		Random rand = new Random();
		String numero ="";
		for(int a = 0;a < 12; a++){
			numero += rand.nextInt(10);
		}
		return numero;
	}

	/**
	 * generate a random account number never be used.
	 * @return account number
	 */
	public String generateID(){
		String nouveau = generate9String();
		if (tousLesID.indexOf(nouveau) == -1){
			this.tousLesID.add(nouveau);
			return nouveau;
		}else{
			return generateID();
		}
	}
	/**
	 * generate a random string presents a account of "9 chiffres"
	 * @return
	 */
	private String generate9String (){
		Random rand = new Random();
		String numero ="";
		for(int a = 0;a < 9; a++){
			numero += rand.nextInt(10);
		}
		return numero;
	}

	/**
	 * Supprimer un benevole
	 * @param code  code identification
	 */
	public Boolean supprimerBenevole(String code) {
		Benevole benevoleTrouve = getBenevole(code);
		if (benevoleTrouve == null) {
			return false;
		} else {
			tousLesID.remove(code);
			tousLesNumCompte.remove(benevoleTrouve.getNumeroDeCompte());
			benevoles.remove(benevoleTrouve);
			return true;
		}
	}

	/**
	 * Ajouter un benevole
	 * @param benevole benevole
	 */
	public void ajouterBenevole(Benevole benevole) {
		this.benevoles.add(benevole);
	}

	/**
	 * Ajouter un employe
	 * @param employe employe
	 */
	public void ajouterEmploye(Employe employe) {
		this.employes.add(employe);
	}

	/**
	 * Get a benevole by code identification
	 * @param codeIdentification code identification
	 */
	public Benevole getBenevole(String codeIdentification) {
		int i = 0;
		while (i < benevoles.size()) {
			if (benevoles.get(i).getCodeIdentification().equals(codeIdentification)) {
				index = i;
				return benevoles.get(i);
			}
			i++;
		}
		return null;
	}


	/**
	 * Get a employe code identification
	 * @param codeIdentification code identification
	 */
	public Employe getEmploye(String codeIdentification) {
		int i = 0;
		while (i < employes.size()) {
			if (employes.get(i).getCodeIdentification().equals(codeIdentification)) {
				index = i;
				return employes.get(i);
			}
			i++;
		}
		return null;
	}

	/**
	 * Modify a benevole
	 * @param benevole benevole
	 */
	public void modifierBenevole(Benevole benevole) {
		benevoles.set(index,benevole);
	}

}