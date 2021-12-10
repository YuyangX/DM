import java.util.HashMap;

public class ControlleurEquipier extends Controller {

	private RepertoireEquipier repertoire;
	private Benevole benevolAModifier;

	/**
	 * 
	 * @param infos
	 */
	public Boolean ajouterBenevole(String[] infos) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param code
	 */
	public int supprimerBenevole(String code) {
		// TODO - implement ControlleurEquipier.supprimerBenevole
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param infos
	 */
	public Boolean modifierBenevole(String[] infos) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param code
	 * @param motDepasse
	 */
	public int login(String code, String motDepasse) {
		// TODO - implement ControlleurEquipier.login
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param code
	 */
	public int chercherBenevole(String code) {
		for (int i=0;i < repertoire.benevoles.size();i++) {
			if (repertoire.benevoles.get(i).getCodeIdentification().equals(code)) {
				return 1;
			}
		}
		return -1;
	}

	public RepertoireEquipier getRepertoire() {
		return repertoire;
	}

	public void setRepertoire(RepertoireEquipier repertoire) {
		this.repertoire = repertoire;
	}

}