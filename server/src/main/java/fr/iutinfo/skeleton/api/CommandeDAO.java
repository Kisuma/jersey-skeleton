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
	@SqlUpdate("create table commandes(idcommande integer primary key autoincrement, iduser integer not null, idproduit integer not null, datecommande timestamp not null, search varchar(1000), foreign key(iduser) references utilisateurs(iduser), foreign key(idproduit) references produits(idproduit))")
	void createCommandeTable();
	
	@SqlUpdate("insert into commandes(iduser, idproduit, datecommande) values (:iduser, :idproduit, :datecommande)")
	@GetGeneratedKeys
	int insert(@BindBean() Commande commande);
	
	@SqlQuery("select * from commandes where datecommande = :datecommande")
	@RegisterMapperFactory(BeanMapperFactory.class)
	Commande findByDate(@Bind("datecommande") Timestamp date);
	
	@SqlQuery("select * from commandes where search like :name")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Commande> search(@Bind("name") String name);
	
	@SqlUpdate("drop table if exists commandes")
	void dropCommandeTable();
	
	@SqlUpdate("delete from commandes where idcommande = :idcommande")
	void delete(@Bind("idcommande") int idcommande);
	
	@SqlQuery("select * from commandes")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Commande> all();
	
	@SqlQuery("select * from commandes where idcommande = :idcommande")
	@RegisterMapperFactory(BeanMapperFactory.class)
	Commande findByID(@Bind("idcommande") int idcommande);
	
	void close();
}
