package fr.iutinfo.skeleton.api;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.UtilisateurDto;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UtilisateurResource {
    final static Logger logger = LoggerFactory.getLogger(UtilisateurResource.class);
    private static UtilisateurDao dao = getDbi().open(UtilisateurDao.class);

    public UtilisateurResource() throws SQLException {
        if (!tableExist("utilisateur")) {
            logger.debug("Create table utilisateur");
            dao.createUtilisateurTable();
            dao.insert(new Utilisateur(0, "Margaret Thatcher", "la Dame de fer"));
        }
    }

    @POST
    public UtilisateurDto createUtilisateur(UtilisateurDto dto) {
        Utilisateur user = new Utilisateur();
        user.initFromDto(dto);
        user.resetPasswordHash();
        int id = dao.insert(user);
        dto.setId(id);
        return dto;
    }

    @GET
    @Path("/{email}")
    public UtilisateurDto getUtilisateur(@PathParam("email") String email) {
        Utilisateur user = dao.findByName(email);
        if (user == null) {
            throw new WebApplicationException(404);
        }
        return user.convertToDto();
    }

    @GET
    public List<UtilisateurDto> getAllUtilisateur(@QueryParam("q") String query) {
        List<Utilisateur> users;
        if (query == null) {
            users = dao.all();
        } else {
            logger.debug("Search users with query: " + query);
            users = dao.search("%" + query + "%");
        }
        return users.stream().map(Utilisateur::convertToDto).collect(Collectors.toList());
    }

    @DELETE
    @Path("/{id}")
    public void deleteUtilisateur(@PathParam("id") int id) {
        dao.delete(id);
    }

}
