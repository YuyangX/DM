import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ControlleurCompte extends Controller implements Serializable{

	public static final long serialVersionUID = 8437L;
	private RepertoireCompte repertoire;
	private String[] infosPreremplies;
	private Compte compteAModifier;

	/**
	 * generate a count number and creat a count to
	 * add in repository.
	 * @param infos informations entered by user.
	 */
	public void creerCompte(HashMap<String,String> infos) {
		Compte compte = new Compte();
		String num = this.repertoire.generateNumCompte();
		compte.setNumeroDeCompte(num);
		boolean tf = infoScanner(infos, compte);
		if (tf == false){
			System.out.println("pattern not matched in creer Compte");
		}
		repertoire.ajouterCompte(compte);

	}

	/**
	 * modify a objet of type Compte using informations offered
	 * by HashMap "infos" passed by arguments
	 * @param infos
	 */
	public void modifierCompte(HashMap<String,String> infos) {
        
		boolean tf = infoScanner(infos, this.compteAModifier);
		if (tf == false){
			System.out.println("pattern not matched in modifierCompte");
		}
		repertoire.modifierCompte(compteAModifier);
	}

	/**
	 * 
	 * @param numeroDeCompte
	 */
	public int supprimerCompte(int numeroDeCompte) {
		// TODO - implement ControlleurCompte.supprimerCompte
		throw new UnsupportedOperationException();
	}

	public void envoyerPreuve() {
		// TODO - implement ControlleurCompte.envoyerPreuve
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param numeroDeCompte
	 */
	public Boolean preremplir(String numeroDeCompte) {
		// TODO - implement ControlleurCompte.preremplir
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param infosRestantes
	 */
	public void imprimerFormulaire(String infosRestantes) {
		// TODO - implement ControlleurCompte.imprimerFormulaire
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param email
	 * @param dateDeNaissance
	 */
	public String retrouverCompte(String email, String dateDeNaissance) {
		// TODO - implement ControlleurCompte.retrouverCompte
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param numCompte
	 */
	public int chercherCompte(String numCompte) {
		Compte compte = repertoire.getCompte(numCompte);
		if (compte == null){return -1;}
		
	}

	/**
	 * 
	 * @param infos
	 */
	public Boolean modifierProfil(String[] infos) {
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param info
	 */

/**
   Change attributes of a Compte type object by analysing entry
   pairs stored in a hashmap which is used to register
   information entered by terminal.
 * @param infos  hashmap
 * @param compte objet to modify
 * @return if all the keys in Hashmap match the pattern
 */
public boolean infoScanner(HashMap<String,String> infos, Compte compte){
   for (Map.Entry<String,String> info: infos.entrySet()){
      switch (info.getKey()){
         case "numCompte":compte.setNumeroDeCompte(info.getValue());
         case "prenom":compte.setPrenom(info.getValue());
         case "nom":compte.setNom(info.getValue());
         case "dateNe":compte.setDateDeNaissance(info.getValue());
         case "email":compte.setAdresseCourriel(info.getValue());
         case "numTele":compte.setNumeroDeTelephone(info.getValue());
         default:
            System.out.println("Compte pattern not matched");
            return false;
      }
   }
   return true;
}
}
