package fr.iutinfo.skeleton.api;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.CommandeDto;

public class Commande {

	final static Logger logger = LoggerFactory.getLogger(Commande.class);
	private int iduser = 0;
	private int idproduit = 0;
	private int id = 0;
	private Timestamp datecommande;
	private int nbcommande = 0;
	private String search;

	public Commande(int idcommande, Timestamp datecommande, int nbcommande, int iduser, int idproduit){
		this.setId(idcommande);
		this.iduser = iduser;
		this.idproduit = idproduit;
		this.datecommande = datecommande;
		this.nbcommande = nbcommande;
	}
	
	public Commande() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals(Object arg) {
		if(getClass() != arg.getClass())
			return false;
		Commande commande = (Commande) arg;
		return iduser==commande.iduser && idproduit==commande.idproduit && datecommande.equals(commande.datecommande) && nbcommande==commande.nbcommande;
	}

	@Override
	public String toString() {
		return "Commande [iduser=" + iduser + ", idproduit=" + idproduit + ", datecommande=" + datecommande
				+ ", nbcommande=" + nbcommande + "]";
	}

	public void initFromDto(CommandeDto dto){
		this.setIdproduit(dto.getIdproduit());
		this.setIduser(dto.getIduser());
		this.setDatecommande(dto.getDatecommande());
		this.setNbcommande(dto.getNbcommande());
	}
	
	public CommandeDto convertToDto(){
		CommandeDto dto = new CommandeDto();
		dto.setIdproduit(this.getIdproduit());
		dto.setIduser(this.getIduser());
		dto.setDatecommande(this.getDatecommande());
		dto.setNbcommande(this.getNbcommande());
		return dto;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public int getIdproduit() {
		return idproduit;
	}

	public void setIdproduit(int idproduit) {
		this.idproduit = idproduit;
	}

	public Timestamp getDatecommande() {
		return datecommande;
	}

	public void setDatecommande(Timestamp datecommande) {
		this.datecommande = datecommande;
	}

	public int getNbcommande() {
		return nbcommande;
	}

	public void setNbcommande(int nbcommande) {
		this.nbcommande = nbcommande;
	}

	public String getSearch() {
		search = datecommande.toString();
		return search;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
