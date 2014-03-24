package ebay;

import java.util.ArrayList;
import java.util.Calendar;


public class EncheresManager {
 static ArrayList<Enchere> encheres = new ArrayList<Enchere>();

 public static boolean update(Calendar reference){
	 for(Enchere enchere : encheres)
	 {
		 if(enchere.getDate_limite().getTime().compareTo(reference.getTime())<0)
		 {
			 enchere.setEtat(EtatEnchere.Finished);
			 return true;
		 }
	 }
	 return false;
 }

 public static boolean add(Enchere enchere){
	 return encheres.add(enchere);
 }
 public ArrayList<Enchere> getEncheresByUser(Utilisateur user){

	 ArrayList<Enchere> encheres_tmp = new ArrayList<Enchere>();
	 for(Enchere enchere : encheres){
		 if (enchere.getEmetteur()==user)
			 encheres_tmp.add(enchere);
	 }
	 return encheres_tmp;
 }

}
