import java.io.Serializable;
import java.util.ArrayList;

public class RepertoireEquipier implements Serializable{

	public static final long serialVersionUID = 573287947L;
	private ArrayList<Employe> employes = new ArrayList<>();
	private ArrayList<Benevole> benevoles = new ArrayList<>();
	private int index;

	/**
	 * 
	 * @param code
	 */
	public Boolean supprimerBenevole(String code) {
		// TODO - implement RepertoireEquipier.supprimerBenevole
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
		// TODO - implement RepertoireEquipier.ajouterBenevole
		this.benevoles.add(benevole);
	}

	/**
	 * 
	 * @param employe
	 */
	public void ajouterEmploye(Employe employe) {
		// TODO - implement RepertoireEquipier.ajouterEmploye
		this.employes.add(employe);
	}

	/**
	 * 
	 * @param codeIdentification
	 */
	public Benevole getBenevole(String codeIdentification) {
		// TODO - implement RepertoireEquipier.getBenevole
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
		// TODO - implement RepertoireEquipier.getBenevole
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

	}

}