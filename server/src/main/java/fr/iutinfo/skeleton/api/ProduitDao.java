package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface ProduitDao {
    @SqlUpdate("create table produits(idproduit integer primary key autoincrement, name varchar(100),description varchar(100),prix float,allergies varchar(100),stock int, search varchar(1024))")
    void createProduitTable();

    @SqlUpdate("Insert into produits(name,description,prix,allergies,stock,search) values (:name, :description,:prix,:allergies,:stock, :search)")
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

    @SqlUpdate("delete from produits where id = :id")
    void delete(@Bind("id") int id);

    @SqlQuery("select * from produits order by id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Produit> all();

    @SqlQuery("select * from produits where id = :id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Produit findById(@Bind("id") int id);

    void close();
}
