import java.io.Serializable;

public class Benevole extends Equipier implements Serializable{

	public static final long serialVersionUID = 05236520275L;
	private Date[] listDispo;

	public Benevole() {
	}

	public Benevole(String numeroDeCompte, String nom, String prenom, String motDePasse,
					String codeIdentification, String adresse, String codePostal, String ville,
					String adresseCourriel, String numeroDeTelephone) {
		super(numeroDeCompte, nom, prenom, motDePasse, codeIdentification, adresse, codePostal,
				ville, adresseCourriel, numeroDeTelephone);
	}

	public Date[] getList() {
		return listDispo;
	}

}