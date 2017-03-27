package fr.iutinfo.skeleton.common.dto;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilisateurDto implements Principal {
	final static Logger logger = LoggerFactory.getLogger(UtilisateurDto.class);
	private String nom;
	private String prenom;
	private int id = 0;
	private String email;
	private String address;
	private String tel;
	private String role;
	private String password;
	private String entreprise;
	private int nbCommandes;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return nom;
	}

	public void setName(String name) {
		this.nom = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getNbCommandes() {
		return nbCommandes;
	}

	public void setNbCommandes(int nbCommandes) {
		this.nbCommandes = nbCommandes;
	}

	public String getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}
}
