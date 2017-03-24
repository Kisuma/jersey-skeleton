package fr.iutinfo.skeleton.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.ProduitDto;

public class Produit{
    final static Logger logger = LoggerFactory.getLogger(Produit.class);
    private static Produit anonymous = new Produit(-1, "Anonymous", "anonym");
    private int idproduit = 0;
    private String name;
    private float prix;
    private String description;
    private String allergies;
    private int stock=0;
    private String search;


    public Produit(int idproduit, String name) {
        this.idproduit = idproduit;
        this.name = name;
    }

    public Produit(int idproduit, String name, String description) {
        this.idproduit = idproduit;
        this.name = name;
        this.description = description;
    }

    public Produit(String nom, String description, float prix, String allergies, int stock, int id) {
		super();
		this.name = nom;
		this.description = description;
		this.prix = prix;
		this.allergies = allergies;
		this.stock = stock;
		this.idproduit = id;
	}

	public Produit() { }

    public static Produit getAnonymousProduit() {
        return anonymous;
    }
    @Override
    public boolean equals(Object arg) {
        if (getClass() != arg.getClass())
            return false;
        Produit produit = (Produit) arg;
        return name.equals(produit.name) && description.equals(produit.description) && prix==produit.prix && allergies.equals(produit.allergies) && stock==produit.stock;
    }
    @Override
	public String toString() {
		return "Produit [idproduit=" + idproduit + ", name=" + name + ", prix=" + prix + ", description=" + description + ", allergies="
				+ allergies + ", stock=" + stock + "]";
	}

    public boolean isInProduitGroup() {
        return !(idproduit == anonymous.getIdproduit());
    }

    public boolean isAnonymous() {
        return this.getIdproduit() == getAnonymousProduit().getIdproduit();
    }

    public String getSearch() {
        search = name + " " + description + " " ;
        return search;
    }

    public void initFromDto(ProduitDto dto) {
        this.setIdproduit(dto.getIdproduit());
        this.setName(dto.getName());
        this.setDescription(dto.getDescription());
        this.setAllergies(dto.getAllergies());
        this.setPrix(dto.getPrix());
        this.setStock(dto.getStock());
    }

    public ProduitDto convertToDto() {
        ProduitDto dto = new ProduitDto();
        dto.setIdproduit(this.getIdproduit());
        dto.setName(this.getName());
        dto.setAllergies(this.getAllergies());
        dto.setDescription(this.getDescription());
        dto.setPrix(this.getPrix());
        dto.setStock(this.getStock());
        return dto;
    }

    
    //**********GETTERS SETTERS**********//
    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setSearch(String search) {
		this.search = search;
	}
    
}
