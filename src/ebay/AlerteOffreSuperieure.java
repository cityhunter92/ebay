package ebay;

public class AlerteOffreSuperieure extends Alerte{
	
	@Override
	public void message(Utilisateur user) {
		// TODO Auto-generated method stub
		user.addNotification("Votre offre a �t� d�pass�e par une offre d'un autre utilisateur");
		
	}
}
