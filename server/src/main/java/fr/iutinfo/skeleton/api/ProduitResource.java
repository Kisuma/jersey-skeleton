package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.common.dto.ProduitDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

@Path("/produit")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProduitResource {
    final static Logger logger = LoggerFactory.getLogger(ProduitResource.class);
    private static ProduitDao dao = getDbi().open(ProduitDao.class);

    public ProduitResource() throws SQLException {
        if (!tableExist("produits")) {
            logger.debug("Create table produits");
            dao.createProduitTable();
            dao.insert(new Produit(0, "Boeuf Bourguignon", "C'est très bon, mangez-en."));
        }
    }

    @POST
    public ProduitDto createProduit(ProduitDto dto) {
        Produit produit = new Produit();
        produit.initFromDto(dto);
        int id = dao.insert(produit);
        dto.setId(id);
        return dto;
    }

    @GET
    @Path("/{name}")
    public ProduitDto getProduit(@PathParam("name") String name) {
        Produit produit = dao.findByName(name);
        if (produit == null) {
            throw new WebApplicationException(404);
        }
        return produit.convertToDto();
    }

    @GET
    public List<ProduitDto> getAllProduits(@QueryParam("q") String query) {
        List<Produit> produits;
        if (query == null) {
            produits = dao.all();
        } else {
            logger.debug("Search produits with query: " + query);
            produits = dao.search("%" + query + "%");
        }
        return produits.stream().map(Produit::convertToDto).collect(Collectors.toList());
    }

    @DELETE
    @Path("/{id}")
    public void deleteProduit(@PathParam("id") int id) {
        dao.delete(id);
    }

}
