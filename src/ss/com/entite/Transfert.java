package ss.com.entite;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Transfert {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CODE_TR")
	private int id_tr;
	
	@Column(name="statut")
	private int statut;
	
	@Column(name="SOMME")
	private int somme;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_T")
	private Date date_t;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_R")
	private Date date_r;
	
	@ManyToOne
	@JoinColumn(name="CODE_VP")
	private Ville villep;
	
	@ManyToOne
	@JoinColumn(name="CODE_VD")
	private Ville villed;
	
	@Column(name="NOM_EX")
	private String nom;
	
	@Column(name="PHONE_EX")
	private String id_per;
	
	@Column(name="PHONE_PER")
	private String phone;

	public String getId_per() {
		return id_per;
	}

	public void setId_per(String id_per) {
		this.id_per = id_per;
	}

	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/*@OneToOne
	@JoinColumn(name="CODE_TAR")
	private Tarif tarif;*/
	
	@ManyToOne
	@JoinColumn(name="CODE_AD")
	private Admin admin;
	
	
	
	public int getId_tr() {
		return id_tr;
	}

	public void setId_tr(int id_tr) {
		this.id_tr = id_tr;
	}

	public int getSomme() {
		return somme;
	}

	public void setSomme(int somme) {
		this.somme = somme;
	}

	public Date getDate_t() {
		return date_t;
	}
	
	public void setDate_t(Date date_t) {
		this.date_t = date_t;
	}
	
	
	
	public Date getDate_r() {
		return date_r;
	}

	public void setDate_r(Date date_r) {
		this.date_r = date_r;
	}

	public int getStatut() {
		return statut;
	}
	
	public void setStatut(int statut) {
		this.statut = statut;
	}

	public Ville getVillep() {
		return villep;
	}

	public void setVillep(Ville villep) {
		this.villep = villep;
	}

	public Ville getVilled() {
		return villed;
	}

	public void setVilled(Ville villed) {
		this.villed = villed;
	}

	/*public Tarif getTarif() {
		return tarif;
	}

	public void setTarif(Tarif tarif) {
		this.tarif = tarif;
	}*/

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	
	
}
