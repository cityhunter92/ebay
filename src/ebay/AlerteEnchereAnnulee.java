package ebay;

public class AlerteEnchereAnnulee extends Alerte {

	@Override
	public void message(Utilisateur user) {
		// TODO Auto-generated method stub
		user.addNotification("L'enchère a été annulée");
		
	}

}
