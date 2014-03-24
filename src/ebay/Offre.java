package ebay;

public class Offre {

	private int prix;
	private String login;

	public Offre(String login, int prix) {
		this.login = login;
		this.prix = prix;
		// TODO Auto-generated constructor stub
	}

	int getPrix() {
		return prix;
	}

	void setPrix(int prix) {
		this.prix = prix;
	}

	String getLogin() {
		return login;
	}

}
