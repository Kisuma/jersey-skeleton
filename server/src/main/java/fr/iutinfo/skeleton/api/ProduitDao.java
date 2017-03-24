package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface ProduitDao {
    @SqlUpdate("create table produits(idproduit integer primary key autoincrement, name varchar(100),description text,prix float,allergies varchar(100),pathImage varchar(100),type varchar(50),stock int, search varchar(1024))")
    void createProduitTable();

    @SqlUpdate("Insert into produits(name,description,prix,allergies,pathImage,type,stock,search) values (:name, :description,:prix,:allergies,:pathImage,:type,:stock, :search)")
    @GetGeneratedKeys
    int insert(@BindBean() Produit produit);

    @SqlQuery("select * from produits where name = :name")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Produit findByName(@Bind("name") String name);

    @SqlQuery("select * from produits where search like :name")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Produit> search(@Bind("name") String name);

    @SqlUpdate("drop table if exists produits")
    void dropProduitTable();

    @SqlUpdate("delete from produits where idproduit = :idproduit")
    void delete(@Bind("idproduit") int idproduit);

    @SqlQuery("select * from produits order by idproduit")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Produit> all();

    @SqlQuery("select * from produits where idproduit = :idproduit")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Produit findById(@Bind("idproduit") int idproduit);

    void close();
}
