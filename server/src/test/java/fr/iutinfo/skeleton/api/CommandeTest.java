package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.common.dto.CommandeDto;
import fr.iutinfo.skeleton.common.dto.UserDto;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import static fr.iutinfo.skeleton.api.Helper.*;
import static org.junit.Assert.assertEquals;

public class CommandeTest extends JerseyTest {
	private static final String PATH = "/commande";
	private UserDao dao = BDDFactory.getDbi().open(UserDao.class);

	@Override
	protected Application configure() {
		return new Api();
	}

	@Before
	public void init() {
		Helper.initDb();
	}

	@Test
	public void list_should_return_all_commandes() {
		Helper.createSampleCommande();

		List<CommandeDto> users = target(PATH + "/").request().get(new GenericType<List<CommandeDto>>(){});
		assertEquals(1, users.size());
	}

}
