package ebay;

import java.util.ArrayList;
import java.util.Calendar;


public class Utilisateur {

	  private String nom;

	  private String prenom;

	  private String login;
	  
	  private ArrayList <String> messages = new ArrayList<String>();
	  
	  void addNotification(String message){
		  messages.add(message);
	  }
	  
	  public int nbMessages(){
		  return messages.size();
	  }
	  
	  public void voirMessages(){
		  if (messages.isEmpty())
		  {
		  System.out.println("Pas de messages pour le moment");
		  return;
		  }
		  for(String message : messages)
		  {
			  System.out.println(message);
		  }
	  }
	  
	  public Utilisateur(String prenom, String nom, String login) {
		  this.prenom=prenom;
		  this.nom=nom;
		  this.login=login;
	  }

	  String getNom() {
		return nom;
	}

	String getPrenom() {
		return prenom;
	}

	public Enchere creerEnchere(Produit produit, Calendar date_limite, int prix_min, int prix_reserve) {
		  Enchere tmp = new Enchere(produit,date_limite,this, prix_min,prix_reserve);
		  AlerteFactory.creerAlerteOffreEnchere(this, tmp);
		  return tmp;
	  }

	  public boolean emettreOffre(Enchere enchere,int prix) {
		  if (enchere.getPrix_min()>prix || enchere.getEtat()!=EtatEnchere.Published || enchere.getEmetteur()==this)
		  { /* //System.out.println("Ici!");
		  	if(enchere.getEtat()!=EtatEnchere.Published)
		  		//System.out.println("là!");
		  	if( enchere.getEmetteur()==this)
		  		//System.out.println("Ici là!");*/
			  return false;
		  } 
		  //enchere.getEmetteur().messages.alerteOffre(enchere);
		  enchere.ajouterOffre(new Offre(this.login,prix));
		  
		  return true;
	  }

	  public boolean annulerEnchere(Enchere enchere) {
		  ArrayList<Alerte> alertes = AlertesManager.getAlertes();
		  
		  if(!prixReserveAtteint(enchere) && enchere.getEmetteur()==this)
		  {
			  enchere.setEtat(EtatEnchere.Canceled);
			  	   
		  
			  for(Alerte alerte : alertes)
			  {
				  if ( alerte.type==TypeAlerte.EnchereAnnulee )
					  if(alerte.getEnchere()==enchere)
						  alerte.message(alerte.getUser());
						  
			  }
			 return true;
		  }
		  return false;
	  }

	  public boolean prixReserveAtteint(Enchere enchere) {
		  for(Offre offre : enchere.getOffres())
		  {
			  if(offre.getPrix()>enchere.getPrix_reserve())
				  return true;
		  }
	  return false;
	  }

	  public void voirEncheres(ArrayList<Enchere> encheres) {
	   for(Enchere enchere : encheres){
		   if(enchere.getEtat()==EtatEnchere.Published)
		   {
			   System.out.println(enchere.toString());
		   }
	   }
	  }


	  public Integer getprixMin(Enchere enchere) {
	   return enchere.getPrix_min();
	  }

	  public Integer getprixReserve(Enchere enchere) {
	  if(this.isVendeur(enchere))
		  return enchere.getPrix_reserve();
	  else return -1;
	  
	  }

	  public boolean publierEnchere(Enchere enchere) {
		  if(this.isVendeur(enchere)&& enchere.getEtat()==EtatEnchere.Created)
		  { enchere.publier();
		  		return true;}
		  else
		  {	  System.out.println("Vous n'avez pas le droit");
		  		return false;}
	  
	  }

	  
	  private boolean isVendeur(Enchere enchere) {
		  if(enchere.getEmetteur().equals(this))
			  return true;
	  return false;
	  }

	
}
