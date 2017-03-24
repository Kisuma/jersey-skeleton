package fr.iutinfo.skeleton.api;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class CommandeDaoTest {
	@Before
	public void init() {
		Helper.initDb();
	}
	
	@Test
	public void should_return_all_commandes() {
		CommandeDAO commandeDAO = BDDFactory.getDbi().open(CommandeDAO.class);
		Helper.createSampleCommande();
		
		List<Commande> commandes = commandeDAO.all();
		Assert.assertEquals(1, commandes.size());
	}


}
