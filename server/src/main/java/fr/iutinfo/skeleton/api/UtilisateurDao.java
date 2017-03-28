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
    @SqlUpdate("create table utilisateurs(iduser integer primary key autoincrement, nom varchar(100), prenom varchar(100), email varchar(100),address varchar(100),tel varchar(10), role varchar(50), entreprise varchar(50),nbCommandes integer, passwdHash varchar(64), salt varchar(64), search varchar(1024))")
    void createUtilisateurTable();

    @SqlUpdate("insert into utilisateurs(nom,prenom, email,address,tel,role,entreprise,nbCommandes, passwdHash, salt, search) values (:nom, :prenom, :email, :address, :tel, :role, :entreprise, :nbCommandes, :passwdHash, :salt, :search)")
    @GetGeneratedKeys
    int insert(@BindBean() Utilisateur user);

    @SqlQuery("select * from utilisateurs where email = :email")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Utilisateur findByMail(@Bind("email") String email);

    @SqlQuery("select * from utilisateurs where search like :email")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Utilisateur> search(@Bind("email") String email);

    @SqlUpdate("drop table if exists utilisateurs")
    void dropUtilisateurTable();

    @SqlUpdate("delete from utilisateurs where iduser = :iduser")
    void delete(@Bind("iduser") int iduser);

    @SqlQuery("select * from utilisateurs order by iduser")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Utilisateur> all();

    @SqlQuery("select * from utilisateurs where iduser = :iduser")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Utilisateur findByID(@Bind("iduser") int iduser);
    
    @SqlQuery("select * from utilisateurs where mail = :email AND passwdHash = :password")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Utilisateur connect(@BindBean() Utilisateur user);

    void close();
}
