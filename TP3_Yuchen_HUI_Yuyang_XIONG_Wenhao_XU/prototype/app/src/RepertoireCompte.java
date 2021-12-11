import java.util.ArrayList;
import java.util.Random;
import java.io.Serializable;

import javax.swing.event.TableColumnModelListener;

/**
 * @author Yuchen Hui
 * @version %I% %G%
 */
public class RepertoireCompte implements Serializable{

	public static final long serialVersionUID = 438752983740958L;
	private  ArrayList<Compte> comptes;
	private int index;
	private ArrayList<String> tousLesNumCompte;


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
        String Numero =" ";
        for(int a = 0;a < 12; a++){
        Numero += rand.nextInt(10);
        }
       return Numero;
	}

	/**
	 * cette fonction prend en parametre un objet de type Compte et
	 * l'ajoute dans le arrayList comptes. 
	 * @param compte
	 */
	public void ajouterCompte(Compte compte) {
       this.comptes.add(compte);
	}

	/**
	 * cette fonction va chercher dans la liste des comptes
	 * selon numero de compte et le supprimer s'il existe.
	 * Si non il va retourner false. Elle va aussi supprimer
	 * le numero de Compte correspondant.
	 * @param numCompte
	 */
	public Boolean supprimerCompte(String numCompte) {
		Compte compteASupprimer = this.getCompte(numCompte);
		if (compteASupprimer == null){
			return false;
		}else{
			this.tousLesNumCompte.remove(numCompte);
			this.comptes.remove(this.index);
			return true;
		}


	}

	/**
	 * Search in the ArrayList comptes by accountnumber. 
	 * the function will also register the index of the account searched 
	 * in attribute index, and retrun it.
	 * @param numéroDeCompte
	 */
	public Compte getCompte(String numéroDeCompte) {
		
		int i = comptes.size()-1;
		while (i >= 0) {
			if (comptes.get(i).getNumeroDeCompte().equals(numéroDeCompte)) {
				this.index = i;
				return comptes.get(i);
			}
			i--;
		}
		return null;

	}
    
	/**
	 * Retrouver numero de compte par email et date de naissance.
	 * @param email tel quel.
	 * @param birth date de naissance.
	 * @return le compte trouve.
	 */
	public Compte getCompteByEmailetBirth(String email, String birth){
	
		int i = 0;
		while (i < comptes.size()) {
			if (( comptes.get(i).getDateDeNaissance().equals(new Date(birth)) )
			 && comptes.get(i).getAdresseCourriel().equals(email)){
				this.index = i;
				return comptes.get(i);
			}

			i++;
		}
		return null;
	}

	/**
	 * modifier le compte dont l'index est indique par l'attribut
	 * "index" du repertoire.
	 * @param compte
	 */
	public void modifierCompte(Compte compte) {
		this.comptes.set(this.index, compte);
	}

    
    /**
	 * setter for attribut index
	 * @param index
	 */
	public void setIndex(int index) {
		this.index = index;
		
	}

}