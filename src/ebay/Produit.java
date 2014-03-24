package ebay;

public class Produit {


	  public String identifiant;

	  public String description;
	   
	
	public Produit(String identifiant, String description) {
		this.identifiant = identifiant;
		this.description = description;
	}


	String getIdentifiant() {
		return identifiant;
	}


	String getDescription() {
		return description;
	}


	@Override
	public String toString() {
		return "Produit [identifiant=" + identifiant + "]";
	}
	
}
