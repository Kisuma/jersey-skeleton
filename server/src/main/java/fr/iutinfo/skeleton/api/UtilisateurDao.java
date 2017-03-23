package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface UtilisateurDao {
    @SqlUpdate("create table users (id integer primary key autoincrement, nom varchar(100), prenom varchar(100), email varchar(100),address varchar(100),tel varchar(10), role varchar(50),nbCommandes int, passwdHash varchar(64), salt varchar(64), search varchar(1024))")
    void createUserTable();

    @SqlUpdate("insert into users (nom,prenom, email,address,tel,role,nbCommandes, passwdHash, salt, search) values (:nom, :prenom, :email, :address, :tel, :role, :nbCommandes, :passwdHash, :salt, :search)")
    @GetGeneratedKeys
    int insert(@BindBean() User user);

    @SqlQuery("select * from users where email = :email")
    @RegisterMapperFactory(BeanMapperFactory.class)
    User findByName(@Bind("email") String name);

    @SqlQuery("select * from users where search like :email")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<User> search(@Bind("email") String name);

    @SqlUpdate("drop table if exists users")
    void dropUserTable();

    @SqlUpdate("delete from users where id = :id")
    void delete(@Bind("id") int id);

    @SqlQuery("select * from users order by id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<User> all();

    @SqlQuery("select * from users where id = :id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    User findById(@Bind("id") int id);

    void close();
}
