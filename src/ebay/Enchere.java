package ebay;

import java.util.ArrayList;
import java.util.Calendar;

public class Enchere {


	  private Produit produit;

	  private Calendar date_limite;

	  private Utilisateur emetteur;

	  private Integer prix_min;

	  private Integer prix_reserve;

	  private EtatEnchere etat;

	  private ArrayList<Offre> offres = new ArrayList<Offre>();


	 ArrayList<Offre> getOffres() {
		return offres;
	}



	void setEtat(EtatEnchere etat) {
		this.etat = etat;
	}



	Enchere(Produit produit, Calendar date_limite, Utilisateur emetteur,
			Integer prix_min, Integer prix_reserve) {
		this.produit = produit;
		this.date_limite = date_limite;
		this.emetteur = emetteur;
		this.prix_min = prix_min;
		this.prix_reserve = prix_reserve;
		this.etat=EtatEnchere.Created;
	}



	@Override
	public String toString() {
		return "Enchere [produit=" + produit 
				+ ", emetteur=" + emetteur + ", prix_min=" + prix_min
				+ ", prix_reserve=" + prix_reserve + ", etat=" + etat
				+ ", offres=" + offres + "]";
	}



	Calendar getDate_limite() {
		
		return date_limite;
	}



	Produit getProduit() {
		return produit;
	}

	void ajouterOffre(Offre offre_entree){
		
		 ArrayList<Alerte> alertes = AlertesManager.getAlertes();
		 boolean bool= false;
		 
		 //Mettre à jour l'offre si elle existe déjà
		for(Offre offre : this.offres)
		{
		 if (offres.isEmpty())
			 break;
			 
		 if(offre.getLogin().equals(offre_entree.getLogin()) && offre_entree.getPrix()>offre.getPrix())
				 {
			     offre.setPrix(offre_entree.getPrix());
			     bool=true;
				 }
		}
		
		if (bool==false)
			offres.add(offre_entree);
		
		for(Offre offre : this.offres)
		{
		 if(offre.getPrix()<offre_entree.getPrix())
			
		  for(Alerte alerte : alertes)
		  {
			  if (alerte.type==TypeAlerte.OffreSuperieure )
				  if(alerte.getEnchere()==this)
					  alerte.message(alerte.getUser());

		  }
		 
		 if(this.getPrix_reserve()<offre_entree.getPrix())
			 for(Alerte alerte : alertes)
		 {
			 if (alerte.type==TypeAlerte.ReserveAtteint )
				  if(alerte.getEnchere()==this)
					  alerte.message(alerte.getUser());
		 }
		}
		for(Alerte alerte : alertes)
		  {

			  if (alerte.type==TypeAlerte.OffreEmise )
				  if(alerte.getUser()==this.emetteur && alerte.getEnchere()==this)
					  alerte.message(alerte.getUser());
		  }
		
	}

	Utilisateur getEmetteur() {
		return emetteur;
	}



	Integer getPrix_min() {
		return prix_min;
	}



	Integer getPrix_reserve() {
		return prix_reserve;
	}



	EtatEnchere getEtat() {
		return etat;
	}



	void publier() {
		this.etat = EtatEnchere.Published;
	  }

}
