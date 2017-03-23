package fr.iutinfo.skeleton.common.dto;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandeDto {
	
	final static Logger logger = LoggerFactory.getLogger(CommandeDto.class);
	private int iduser = 0;
	private int idproduit = 0;
	private int id = 0;
	private Timestamp datecommande;
	private int nbcommande = 0;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
