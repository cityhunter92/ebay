package ebay;


public class AlerteReserveAtteint extends Alerte{

	
	@Override
	public void message(Utilisateur user) {
		// TODO Auto-generated method stub
		user.addNotification("Le prix de r�serve a �t� atteint par une offre d'un autre utilisateur");
		
	}

}
