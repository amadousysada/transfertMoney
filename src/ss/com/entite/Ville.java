package ss.com.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Ville {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CODE_V")
	private int id_v;
	
	@Column(name="LABEL_V")
	private String nom_v;

	public int getId_v() {
		return id_v;
	}

	public void setId_v(int id_v) {
		this.id_v = id_v;
	}

	public String getNom_v() {
		return nom_v;
	}

	public void setNom_v(String nom_v) {
		this.nom_v = nom_v;
	}
	

}
