package ebay;

import java.util.ArrayList;

public class AlertesManager {
 
	static ArrayList<Alerte> alertes = new ArrayList<Alerte>() ;

	static ArrayList<Alerte> getAlertes() {
		return alertes;
	}
	static void add(Alerte alerte){
		alertes.add(alerte);
	}
	
	public static boolean desactiverAlerte(Utilisateur user, Enchere enchere, TypeAlerte type){
			for (Alerte alerte : alertes)
			{
				if(alerte.getUser()==user)
					if(alerte.getEnchere()==enchere)
						if(alerte.getType()==type)
							return alertes.remove(alerte);
			}
			return false;
		}
}
