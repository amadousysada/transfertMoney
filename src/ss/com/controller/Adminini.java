/**
 * 
 */
package ss.com.controller;

import java.io.Serializable;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;


import ss.com.entite.Admin;
import ss.com.service.ITransfertService;

/**
 * @author SY
 *
 */

public class Adminini implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Autowired
    ITransfertService service; 
	private CtrlBean Bean; 
	private String id_ad;//le code de l'admin
	private String nom;
	private String degre;
	private String prenom;
	
	
	public ITransfertService getService() {
		return service;
	}
	public void setService(ITransfertService service) {
		this.service = service;
	}
	
	public CtrlBean getBean() {
		return Bean;
	}
	public void setBean(CtrlBean bean) {
		Bean = bean;
	}
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

	
	public void setDegre(String degre) {
		this.degre = degre;
	}
	public String getDegre() {
		return degre;
	}
	public void creerAdmin(){
    	Admin ad1=new Admin();
    	ad1.setNom(getNom());
    	ad1.setPrenom(getPrenom());
    	ad1.setAgence(Bean.getAdmin().getAgence());
    	ad1.setId_ad(getId_ad());
    	ad1.setDegre("simple");
    	service.creer_admin(ad1);
    	FacesContext fc = FacesContext.getCurrentInstance();  
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"SUCCES" , null));
    }
	
}
