package ebay;


public class AlerteOffreEnchere extends Alerte{

	@Override
	public void message(Utilisateur user) {
		// TODO Auto-generated method stub
		user.addNotification("Une offre a �t� �mise sur le produit dont l'identifiant est : "+ this.getEnchere().getProduit().getIdentifiant());
	}

}
