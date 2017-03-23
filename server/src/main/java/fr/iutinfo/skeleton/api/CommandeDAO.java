package fr.iutinfo.skeleton.api;

import java.sql.Timestamp;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface CommandeDAO {
	@SqlUpdate("create table commandes(idcommande int not null primary key autoincrement, iduser int not null, idproduit int not null, nbcommande int autoincrement, datecommande timestamp not null, search varchar(1000), foreign key(iduser) references utilisateurs(iduser), foreign key(idproduit) references produits(idproduit)")
	void createCommandeTable();
	
	@SqlUpdate("insert into commandes(iduser, idproduit, datecommande, nbcommande) values (:iduser, :idproduit, :datecommande, :nbcommande)")
	@GetGeneratedKeys
	int insert(@BindBean() Commande commande);
	
	@SqlQuery("select * from commandes where datecommande = :datecommande")
	@RegisterMapperFactory(BeanMapperFactory.class)
	Commande findByDate(@Bind("datecommande") Timestamp date);
	
	@SqlQuery("select * from commandes where search like :datecommande")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Commande> search(@Bind("datecommande") Timestamp date);
	
	
	
	//Recherche un client, un produit et une date à partir du numéro de commande
	/*@SqlQuery("select * from commandes where search like :nbcommande")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Commande> search(@Bind("datecommande") int nbcommande);*/
	
	@SqlUpdate("drop table if exists commandes")
	void dropCommandeTable();
	
	@SqlUpdate("delete from commandes where nbcommande = :nbcommande")
	void delete(@Bind("nbcommande") int nbcommande);
	
	@SqlQuery("select * from commandes order by idcommande, iduser, idproduit")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Commande> all();
	
	@SqlQuery("select * from commandes where nbcommande = :nbcommande")
	@RegisterMapperFactory(BeanMapperFactory.class)
	Commande findByNBCommande(@Bind("nbcommande") int nbcommande);
	
	void close();
}
