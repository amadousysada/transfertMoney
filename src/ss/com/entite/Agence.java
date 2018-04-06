package ss.com.entite;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Agence {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CODE_AG")
	private int id_ag;
	
	@Column(name="LABEL_AG")
	private String label;
	
	@OneToOne
	@JoinColumn(name="CODE_LOC")
	private Localite localite;
	
	@OneToMany(mappedBy="agence")
	private List<Admin> admins;
	
	public int getId_ag() {
		return id_ag;
	}

	public void setId_ag(int id_ag) {
		this.id_ag = id_ag;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Localite getLocalite() {
		return localite;
	}

	public void setLocalite(Localite localite) {
		this.localite = localite;
	}

	public List<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(List<Admin> admins) {
		this.admins = admins;
	}
	
	
}
