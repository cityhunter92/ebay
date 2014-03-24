package Main;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import ebay.AlerteFactory;
import ebay.AlertesManager;
import ebay.Enchere;
import ebay.EncheresManager;
import ebay.Produit;
import ebay.TypeAlerte;
import ebay.Utilisateur;

public class MainTest {
	public static Calendar reference = new GregorianCalendar(2014, 03, 19);
	Calendar enchere_1_Date =new GregorianCalendar(2014, 03, 23);
	Utilisateur user1 =new Utilisateur("karim", "jilali", "wesh94");
	Utilisateur user2 =new Utilisateur("John", "Dupont", "accroDu16");
	Utilisateur user3 =new Utilisateur("Marine", "Durant", "soldesLifeStyle");
	Produit voiture = new Produit("abc123", "Belle Mercedes");
	Produit armoire = new Produit("abc124", "armoire");
	Produit tele = new Produit("abc125", "TV LCD");
	Enchere enchere1=user1.creerEnchere(voiture, enchere_1_Date, 25000, 30000);
	Enchere enchere2=user2.creerEnchere(armoire, enchere_1_Date, 1000, 4000);
	Enchere enchere3=user3.creerEnchere(tele, enchere_1_Date, 600, 800);
	
	@Before
	public void setUp() throws Exception {
		
		
		// TODO Auto-generated method stub

	}

	@Test
	public void test() {
		
		//L'utilisateur publie une ench�re => possible
		assertTrue(user1.publierEnchere(enchere1));

	}
	
	@Test
	public void test1(){
		//Utilisateur �met une offre sur une ench�re publi�e par un autre 
		//utilisateur que lui => possible
		assertTrue(user1.publierEnchere(enchere1));
		assertTrue(user2.emettreOffre(enchere1, 26000));
	}
	@Test
	public void test2(){
		//Emettre offre sur une ench�re non publi�e ==> impossible
		assertFalse(user3.emettreOffre(enchere2, 26000));
	}
	@Test
	public void test3(){
		//Emettre offre avec un prix < prix min ==> impossible
		assertFalse(user3.emettreOffre(enchere1, 1000));
	}
	@Test
	public void test4(){
		//Un utilisateur veut voir le prix de r�serve d'une ench�re dont il n'est pas le vendeur 
		//=>impossible ("-1" est le prix renvoy� dans ce cas)
		assertEquals(-1, user2.getprixReserve(enchere1), 0);
	}
	@Test
	public void test4bis(){
		//Un utilisateur veut savoir si le prix de r�serve d'une ench�re  
		//dont il n'est pas le vendeur est atteint => possible
		assertEquals(-1, user2.getprixReserve(enchere1), 0);
		assertTrue(user1.publierEnchere(enchere1));
		assertTrue(user2.publierEnchere(enchere2));
		assertTrue(user2.emettreOffre(enchere1, 31000)); //le prix de r�serve �tant de 30000
		assertTrue(user1.emettreOffre(enchere2, 1500)); //le prix de r�serve �tant de 4000
		assertTrue(user3.prixReserveAtteint(enchere1)); //dans l'enchere1 le prix est atteint
		assertFalse(user3.prixReserveAtteint(enchere2));//dans l'enchere2 ce n'est pas le cas
	}
	@Test
	public void test5(){
		//annuler ench�re alors que prix de r�serve atteint => impossible
		assertTrue(user1.publierEnchere(enchere1));
		assertTrue(user2.emettreOffre(enchere1, 31000)); //le prix de r�serve �tant de 30000
		assertFalse(user1.annulerEnchere(enchere1));
	}
	@Test
	public void test5bis(){
		//annuler ench�re alors que prix de r�serve non atteint => possible
		assertTrue(user1.publierEnchere(enchere1));
		assertTrue(user2.emettreOffre(enchere1, 27000)); //le prix de r�serve �tant de 30000
		assertTrue(user1.annulerEnchere(enchere1));
	}
	@Test
	public void test5ter(){
		//annuler ench�re publi�e par un autre utilisateur => impossible
		assertTrue(user1.publierEnchere(enchere1));
		assertFalse(user2.annulerEnchere(enchere1)); 
	}
	@Test
	public void test6(){
		//emettre offre sur l'enchere par le vendeur lui-m�me => impossible
		assertFalse(user1.emettreOffre(enchere1, 26000));
	}

	@Test
	public void test7(){
		//�mettre une offre sur une ench�re termin�e => impossible
		assertTrue(user1.publierEnchere(enchere1));
		assertTrue(EncheresManager.add(enchere1));
		reference.add(Calendar.DAY_OF_MONTH, 5); // on d�passe la date limite d'un jour 
		assertTrue(EncheresManager.update(reference));//l'update termine l'ench�re d�pass�e
		assertFalse(user2.emettreOffre(enchere1, 26000));

	}
	@Test
	public void test8(){
		//Test de l'alerte automatique sur l'offre sup�rieure
		AlerteFactory.creerAlerteOffreSuperieure(user2, enchere1);
		assertTrue(user1.publierEnchere(enchere1));
		assertTrue(user2.emettreOffre(enchere1, 26000));//user2 �met une offre
		assertTrue(user3.emettreOffre(enchere1, 26500));//user3 �met une offre sup�rieure
		assertEquals(1, user2.nbMessages());//user2 re�oit une notification 
											//comme quoi il y a eu une offre sup�rieure
	}
	@Test
	public void test9(){
		//Test de l'alerte automatique pour le vendeur 
		assertTrue(user1.publierEnchere(enchere1));
		assertTrue(user2.emettreOffre(enchere1, 26000));//user2 �met une offre
		assertTrue(user3.emettreOffre(enchere1, 26500));//user3 �met une offre sup�rieure
		assertEquals(2, user1.nbMessages());//Le vendeur re�oit deux notifications 
											//qui concernent les offres �mises sur l'ench�re
	}
	@Test
	public void test10(){
		//D�sactiver une alerte (par exemple alerte sur offre sup�rieure) => possible
		AlerteFactory.creerAlerteOffreSuperieure(user2, enchere1);
		assertTrue(AlertesManager.desactiverAlerte(user2, enchere1, TypeAlerte.OffreSuperieure));
	}

}
