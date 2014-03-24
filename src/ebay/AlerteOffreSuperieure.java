package ebay;

public class AlerteOffreSuperieure extends Alerte{
	
	@Override
	public void message(Utilisateur user) {
		// TODO Auto-generated method stub
		user.addNotification("Votre offre a été dépassée par une offre d'un autre utilisateur");
		
	}
}
