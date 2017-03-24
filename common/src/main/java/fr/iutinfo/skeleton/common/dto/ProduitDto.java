package fr.iutinfo.skeleton.common.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import fr.iutinfo.skeleton.api.Produit;

import java.security.Principal;

public class ProduitDto implements Principal {
    final static Logger logger = LoggerFactory.getLogger(ProduitDto.class);
    private int idproduit = 0;
    private String name;
    private float prix;
    private String description;
    private String allergies;
    private int stock=0;
    private String pathImage;
    private String type;
    private boolean supplement;
    
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

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean getSupplement() {
		return supplement;
	}

	public void setSupplement(boolean supplement) {
		this.supplement = supplement;
	}
	
}
