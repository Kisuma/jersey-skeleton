package fr.iutinfo.skeleton.api;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface UtilisateurDao {
    @SqlUpdate("create table utilisateurs(iduser integer autoincrement, nom varchar(100), prenom varchar(100), email varchar(100),address varchar(100),tel varchar(10), role varchar(50),nbCommandes int, passwdHash varchar(64), salt varchar(64), search varchar(1024), primary key(iduser)")
    void createUtilisateurTable();

    @SqlUpdate("insert into utilisateurs(nom,prenom, email,address,tel,role,nbCommandes, passwdHash, salt, search) values (:nom, :prenom, :email, :address, :tel, :role, :nbCommandes, :passwdHash, :salt, :search)")
    @GetGeneratedKeys
    int insert(@BindBean() Utilisateur user);

    @SqlQuery("select * from utilisateurs where email = :email")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Utilisateur findByName(@Bind("email") String name);

    @SqlQuery("select * from utilisateurs where search like :email")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Utilisateur> search(@Bind("email") String name);

    @SqlUpdate("drop table if exists utilisateurs")
    void dropUtilisateurTable();

    @SqlUpdate("delete from utilisateurs where id = :id")
    void delete(@Bind("id") int id);

    @SqlQuery("select * from utilisateurs order by id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Utilisateur> all();

    @SqlQuery("select * from utilisateurs where id = :id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Utilisateur findById(@Bind("id") int id);

    void close();
}
