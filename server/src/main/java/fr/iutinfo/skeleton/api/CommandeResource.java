package fr.iutinfo.skeleton.api;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

import java.sql.SQLException;
import java.sql.Timestamp;
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

import fr.iutinfo.skeleton.common.dto.CommandeDto;

@Path("/commande")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommandeResource {
	
	final static Logger logger = LoggerFactory.getLogger(CommandeResource.class);
	private static CommandeDAO dao = getDbi().open(CommandeDAO.class);
	
	@SuppressWarnings("deprecation")
	public CommandeResource() throws SQLException{
		if(!tableExist("commandes")){
			logger.debug("Create table commandes");
			dao.createCommandeTable();
			dao.insert(new Commande(0,new Timestamp(2017, 03, 23, 14, 05, 20,58),0,0,0));
		}
	}
	
	@POST
	public CommandeDto createCommande(CommandeDto dto){
		Commande commande = new Commande();
		commande.initFromDto(dto);
		int id = dao.insert(commande);
		dto.setId(id);
		return dto;
	}
	
	@GET
	@Path("/{datecommande}")
	public CommandeDto getCommande(@PathParam("datecommande") Timestamp datecommande){
		Commande commande = dao.findByDate(datecommande);
		if(commande == null)
			throw new WebApplicationException(404);
		return commande.convertToDto();
	}
	
	@GET
	@Path("/{nbcommande}")
	public CommandeDto getCommande(@PathParam("nbcommande") int nbcommande){
		Commande commande = dao.findByNBCommande(nbcommande);
		if(commande == null)
			throw new WebApplicationException(404);
		return commande.convertToDto();
	}
	
	@GET
	public List<CommandeDto> getAllCommandes(@QueryParam("q") Timestamp date){
		List<Commande> commandes;
		if(date == null){
			commandes = dao.all();
		} else {
			logger.debug("Search commande with query: " + date.toString());
			commandes = dao.search(date);
		}
		return commandes.stream().map(Commande::convertToDto).collect(Collectors.toList());
	}
	
	@DELETE
	@Path("/{nbcommande}")
	public void deleteCommande(@PathParam("nbcommande") int nbcommande){
		dao.delete(nbcommande);
	}
}
