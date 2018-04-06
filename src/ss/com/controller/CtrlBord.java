/**
 * 
 */
package ss.com.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ss.com.entite.Tarif;
import ss.com.entite.Transfert;
import ss.com.service.ITransfertService;

/**
 * @author SY
 *
 */





public class CtrlBord implements Serializable {

   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ITransfertService service;
	private CtrlBean Bean;
	private int se;
	private int ss;
	private int b;
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
	public int getSe() throws ParseException {
		List<Transfert> lt=service.allTransfertIn(getBean().getAdmin().getAgence());
		Date d=service.generer_date();
		int se1=0;
		for(Transfert t:lt){
			if(d.equals(t.getDate_t())){
				se1=se1+t.getSomme();
			}
		}
		se=se1;
		return se;
	}
	public void setSe(int se) {
		this.se = se;
	}
	public int getSs() throws ParseException {
		List<Transfert> lt2=service.allTransfertOut(getBean().getAdmin().getAgence());
		Date d=service.generer_date();
		int ss1=0;
		for(Transfert t:lt2){
			if(d.equals(t.getDate_t())){
				ss1=ss1+t.getSomme();
			}
		}
		ss= ss1;
		return ss;
	}
	public void setSs(int ss) {
		this.ss = ss;
	}
	public int getB() throws ParseException {
		List<Tarif> lt2=service.allTarif();
		Date d=service.generer_date();
		int b1=0;
		for(Tarif t:lt2){
			if(d.equals(t.getTransfert().getDate_t()) && (t.getTransfert().getVilled().getNom_v().equals(Bean.getAdmin().getAgence().getLocalite().getVille().getNom_v())) ){
				
				b1=b1+t.getVal_tar();
			}
		}
		b=b1;
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	

}