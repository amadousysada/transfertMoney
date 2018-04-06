package ss.com.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Localite {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CODE_LOC")
	private int id_loc;
	
	@Column(name="LABEL_LOC")
	private String label_loc;
	
	@OneToOne
	@JoinColumn(name="CODE_V")
	private Ville ville;
	
	/*@OneToOne
	@JoinColumn(name="CODE_AG")
	private Agence agence;*/
	
	public int getId_loc() {
		return id_loc;
	}

	public void setId_loc(int id_loc) {
		this.id_loc = id_loc;
	}

	public String getLabel_loc() {
		return label_loc;
	}

	public void setLabel_loc(String label_loc) {
		this.label_loc = label_loc;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	/*public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}
	*/
	

}
