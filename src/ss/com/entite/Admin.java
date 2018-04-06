package ss.com.entite;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Admin {
	
	@Id
	@Column(name="CODE_AD")
	private String id_ad;//le code de l'admin
	
	@Column(name="NOM_AD")
	private String nom;
	
	@Column(name="DEGRE")
	private String degre;
	
	@Column(name="PRENOM_AD")
	private String prenom;
	
	@ManyToOne
	@JoinColumn(name="CODE_AG")
	private Agence agence;
	
	@OneToMany(mappedBy="admin")
	private List<Transfert> transferts;
	
	public String getId_ad() {
		return id_ad;
	}

	public void setId_ad(String id_ad) {
		this.id_ad = id_ad;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	public List<Transfert> getTransferts() {
		return transferts;
	}

	public void setTransferts(List<Transfert> transferts) {
		this.transferts = transferts;
	}
	
	public void setDegre(String degre) {
		this.degre = degre;
	}
	public String getDegre() {
		return degre;
	}
	

}
