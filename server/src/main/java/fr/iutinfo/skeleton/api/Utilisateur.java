package fr.iutinfo.skeleton.api;

import java.security.Principal;
import java.security.SecureRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

import fr.iutinfo.skeleton.common.dto.UtilisateurDto;

public class Utilisateur implements Principal {
    final static Logger logger = LoggerFactory.getLogger(Utilisateur.class);
    private static Utilisateur anonymous = new Utilisateur(-1, "Anonymous", "anonym");
    private String nom;
    private String prenom;
    private int id = 0;
    private String email;
    private String address;
    private String tel;
    private String role;
    private int nbCommandes;
    private String password;
    private String passwdHash;
    private String salt;
    private String search;

    public Utilisateur(int id, String name) {
        this.id = id;
        this.nom = name;
    }

    public Utilisateur(int id, String name, String prenom) {
        this.id = id;
        this.nom = name;
        this.prenom=prenom;
    }
    

    public Utilisateur(String nom, String prenom, int id, String email, String address, String tel, String role) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.id = id;
		this.email = email;
		this.address = address;
		this.tel = tel;
		this.role = role;
	}

	public Utilisateur() {
    }

    private String buildHash(String password, String s) {
        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putString(password + s, Charsets.UTF_8);
        return hasher.hash().toString();
    }

    public boolean isGoodPassword(String password) {
        if (isAnonymous()) {
            return false;
        }
        String hash = buildHash(password, getSalt());
        return hash.equals(getPasswdHash());
    }

    @Override
    public boolean equals(Object arg) {
        if (getClass() != arg.getClass())
            return false;
        Utilisateur user = (Utilisateur) arg;
        return nom.equals(user.nom) && prenom.equals(user.prenom) && email.equals(user.email) && address.equals(user.address) && tel.equals(tel) && role.equals(user.role) && passwdHash.equals(user.getPasswdHash()) && salt.equals((user.getSalt()));
    }

    @Override
	public String toString() {
		return "User [nom=" + nom + ", prenom=" + prenom + ", id=" + id + ", email=" + email + ", address=" + address
				+ ", tel=" + tel + ", role=" + role + ", nbCommandes=" + nbCommandes+ "]";
	}

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putLong(random.nextLong());
        return hasher.hash().toString();
    }

    public void resetPasswordHash() {
        if (password != null && !password.isEmpty()) {
            setPassword(getPassword());
        }
    }

    public boolean isInUtilisateurGroup() {
        return !(id == anonymous.getId());
    }

    public boolean isAnonymous() {
        return this.getId() == getAnonymousUser().getId();
    }

    public String getSearch() {
        search = nom + " " + prenom + " " + email;
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public void initFromDto(UtilisateurDto dto) {
        this.setPrenom(dto.getPrenom());
        this.setEmail(dto.getEmail());
        this.setAddress(dto.getAddress());
        this.setTel(dto.getTel());
        this.setRole(dto.getRole());
        this.setId(dto.getId());
        this.setName(dto.getName());
        this.setPassword(dto.getPassword());
    }

    public UtilisateurDto convertToDto() {
        UtilisateurDto dto = new UtilisateurDto();
        dto.setPrenom(this.getPrenom());
        dto.setEmail(this.getEmail());
        dto.setAddress(this.getAddress());
        dto.setTel(this.getTel());
        dto.setRole(this.getRole());
        dto.setId(this.getId());
        dto.setName(this.getName());
        dto.setPassword(this.getPassword());
        return dto;
    }

    //***********GETTERS SETTERS***********
    public static Utilisateur getAnonymousUser() {
        return anonymous;
    }

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

    public void setName(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        passwdHash = buildHash(password, getSalt());
        this.password = password;
    }
    public String getPasswdHash() {
        return passwdHash;
    }

    public void setPasswdHash(String passwdHash) {
        this.passwdHash = passwdHash;
    }
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getSalt() {
        if (salt == null) {
            salt = generateSalt();
        }
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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
    
}
