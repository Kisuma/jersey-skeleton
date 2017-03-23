package fr.iutinfo.skeleton.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.ProduitDto;

public class Produit{
    final static Logger logger = LoggerFactory.getLogger(Produit.class);
    private static Produit anonymous = new Produit(-1, "Anonymous", "anonym");
    private int id = 0;
    private String name;
    private float prix;
    private String description;
    private String allergies;
    private int stock=0;
    private String search;


    public Produit(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Produit(int id, String name, String description) {
        this.id = id;
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
		this.id = id;
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
		return "Produit [id=" + id + ", name=" + name + ", prix=" + prix + ", description=" + description + ", allergies="
				+ allergies + ", stock=" + stock + "]";
	}

    public boolean isInProduitGroup() {
        return !(id == anonymous.getId());
    }

    public boolean isAnonymous() {
        return this.getId() == getAnonymousProduit().getId();
    }

    public String getSearch() {
        search = name + " " + description + " " ;
        return search;
    }

    public void initFromDto(ProduitDto dto) {
        this.setId(dto.getId());
        this.setName(dto.getName());
    }

    public ProduitDto convertToDto() {
        ProduitDto dto = new ProduitDto();
        dto.setId(this.getId());
        dto.setName(this.getName());
        return dto;
    }

    
    //**********GETTERS SETTERS**********//
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
