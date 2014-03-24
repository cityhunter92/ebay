package ebay;


public abstract class Alerte {
	
	TypeAlerte type;
	
	TypeAlerte getType() {
		return type;
	}

	void setType(TypeAlerte type) {
		this.type = type;
	}

	Utilisateur getUser() {
		return user;
	}

	void setUser(Utilisateur user) {
		this.user = user;
	}

	Enchere getEnchere() {
		return enchere;
	}

	void setEnchere(Enchere enchere) {
		this.enchere = enchere;
	}

	private Utilisateur user;
	private Enchere enchere;
		  
	public abstract void message(Utilisateur user);	
		  
		  
		
}
