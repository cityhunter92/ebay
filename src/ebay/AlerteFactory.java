package ebay;

public class AlerteFactory {

	public static boolean creerAlerteReserveAtteint(Utilisateur user, Enchere enchere){
	AlerteReserveAtteint tmp =new AlerteReserveAtteint();
	tmp.setUser(user);
	tmp.setEnchere(enchere);
	tmp.setType(TypeAlerte.ReserveAtteint);
	AlertesManager.add(tmp);
	return true;
	}
	
	public static boolean creerAlerteEnchereAnnulee(Utilisateur user, Enchere enchere){
	AlerteEnchereAnnulee tmp =new AlerteEnchereAnnulee();
	tmp.setUser(user);
	tmp.setEnchere(enchere);
	tmp.setType(TypeAlerte.EnchereAnnulee);
	AlertesManager.add(tmp);
	return true;
	}
	public static boolean creerAlerteOffreSuperieure(Utilisateur user, Enchere enchere){
	AlerteOffreSuperieure tmp =new AlerteOffreSuperieure();
	tmp.setUser(user);
	tmp.setEnchere(enchere);
	tmp.setType(TypeAlerte.OffreSuperieure);
	AlertesManager.add(tmp);
	return true;
	}
	public static boolean creerAlerteOffreEnchere(Utilisateur user, Enchere enchere){
	AlerteOffreEnchere tmp =new AlerteOffreEnchere();
	tmp.setUser(user);
	tmp.setEnchere(enchere);
	tmp.setType(TypeAlerte.OffreEmise);
	AlertesManager.add(tmp);
	return true;
	}
}
