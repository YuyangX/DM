import java.util.ArrayList;

public class RepertoireEquipier{

	public ArrayList<Employe> employes = new ArrayList<>();
	public ArrayList<Benevole> benevoles = new ArrayList<>();
	private int index;

	/**
	 * 
	 * @param code
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
	 * 
	 * @param benevole
	 */
	public void ajouterBenevole(Benevole benevole) {
		this.benevoles.add(benevole);
	}

	/**
	 * 
	 * @param employe
	 */
	public void ajouterEmploye(Employe employe) {
		this.employes.add(employe);
	}

	/**
	 * 
	 * @param codeIdentification
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
	 *
	 * @param codeIdentification
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
	 * 
	 * @param benevole
	 */
	public void modifierBenevole(Benevole benevole) {
		benevoles.remove(index);
		benevoles.add(index,benevole);
	}

}