import java.util.ArrayList;

public class RepertoireEquipier{

	public ArrayList<Employe> employes = new ArrayList<>();
	public ArrayList<Benevole> benevoles = new ArrayList<>();
	private int index;

	/**
	 * Supprimer un benevole
	 * @param code  code identification
	 */
	public Boolean supprimerBenevole(String code) {
		Benevole benevoleTrouve = getBenevole(code);
		if (benevoleTrouve == null) {
			return false;
		} else {
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