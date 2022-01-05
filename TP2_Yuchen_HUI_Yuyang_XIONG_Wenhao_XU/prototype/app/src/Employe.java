import java.io.Serializable;

public class Employe extends Equipier implements Serializable{

    public static final long serialVersionUID = 328502L;

    public Employe() {
    }

    public Employe(String numeroDeCompte, String nom, String prenom, String motDePasse, String codeIdentification, String adresse, String codePostal, String ville, String adresseCourriel, String numeroDeTelephone) {
        super(numeroDeCompte, nom, prenom, motDePasse, codeIdentification, adresse, codePostal, ville, adresseCourriel, numeroDeTelephone);
    }
}