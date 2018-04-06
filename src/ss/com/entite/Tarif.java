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
public class Tarif {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CODE_TAR")
	private int id_tar;
	
	@Column(name="VAL_TAR")
	private int val_tar;
	
	@OneToOne
	@JoinColumn(name="CODE_TR")
	private Transfert transfert;
	
	public int getId_tar() {
		return id_tar;
	}

	public void setId_tar(int id_tar) {
		this.id_tar = id_tar;
	}

	public int getVal_tar() {
		return val_tar;
	}

	public void setVal_tar(int val_tar) {
		this.val_tar = val_tar;
	}

	public Transfert getTransfert() {
		return transfert;
	}

	public void setTransfert(Transfert transfert) {
		this.transfert = transfert;
	}
	
	

}
