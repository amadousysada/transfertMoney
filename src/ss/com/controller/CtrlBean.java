/**
 * 
 */
package ss.com.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ss.com.entite.Admin;
import ss.com.entite.Transfert;
import ss.com.entite.Ville;
import ss.com.service.ITransfertService;

/**
 * @author SY
 *
 */

public class CtrlBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
    ITransfertService service;
	String username;
	String password;
	Transfert t;
	String nom;
	String phone1;
	String phone2;
	int somme;
	BarChartModel barModel;
	BarChartModel barModel2;
	String v;
	Boolean connected=false;
	Boolean succes=false;
	int error=0;
	private List<Transfert> filteredtransferts;
	private Transfert selectedTransfert;
	private Admin admin;
	private Boolean sup=false;
    public CtrlBean() {
		super();
	}
    
	public ITransfertService getService() {
		return service;
	}
	public void setService(ITransfertService service) {
		this.service = service;
	}
	public String getNom1() throws DataAccessException {
		
		return service.getAdminById("12020").getNom();
	}
	
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Admin getAdmin() {
		return admin;
	}
	public  void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public int getSomme() {
		return somme;
	}
	public void setSomme(int somme) {
		this.somme = somme;
	}
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
	
	public List<Transfert> getFilteredtransferts() {
		return filteredtransferts;
	}

	public void setFilteredtransferts(List<Transfert> filteredtransferts) {
		this.filteredtransferts = filteredtransferts;
	}
	
	

	public Transfert getSelectedTransfert() {
		return selectedTransfert;
	}

	public void setSelectedTransfert(Transfert selectedTransfert) {
		this.selectedTransfert = selectedTransfert;
	}
	
	public Boolean getConnected() {
		return connected;
	}

	public void setConnected(Boolean connected) {
		this.connected = connected;
	}

	public Boolean getSup() {
		return sup;
	}

	public void setSup(Boolean sup) {
		this.sup = sup;
	}

	public Boolean getSucces() {
		return succes;
	}

	public void setSucces(Boolean succes) {
		this.succes = succes;
	}
	
	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

   public BarChartModel getBarModel() {
       return barModel;
   }
   
   

	public BarChartModel getBarModel2() {
	return barModel2;
}

public void setBarModel2(BarChartModel barModel2) {
	this.barModel2 = barModel2;
}

	public String login() {
		// TODO Auto-generated method stub
		String message = null;
			try{
				message=service.login(getUsername(),getPassword());
				if(message.equalsIgnoreCase("valide")){
					setAdmin(service.getAdminById(password));
					setConnected(true);
					createBarModels();
					if(getAdmin().getDegre().equalsIgnoreCase("super")){
						setSup(true);
					}
				}
				else error=1;
				FacesContext fc = FacesContext.getCurrentInstance(); 
		        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "coordonnees invalide", null));
			}
			catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
				FacesContext fc = FacesContext.getCurrentInstance(); 
		        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "connexion a la base impossible", null));
			}
		
		return message;
		
	}
	
	public List<Transfert> getAllTransfert(){
		return service.allTransfers();
	}
	public List<Ville> getAllVilles(){
		return service.allVilles();
	}
	public void transferer(){
		Transfert t=new Transfert();
		t.setAdmin(admin);
		t.setNom(getNom());
		t.setPhone(getPhone2());
		t.setId_per(getPhone1());
		t.setStatut(0);
		t.setVillep(admin.getAgence().getLocalite().getVille());
		//t.setVilled(service.getVilleByName("Nouakchott"));
		t.setVilled(service.getVilleByName(v));
		t.setSomme(getSomme());
		service.transferer(t);
	}
	public Map<String,String> getAllVille(){
		Map<String,String> res=new LinkedHashMap<String, String>();
		List<Ville> ville=service.allVilles();
		for(Ville v2:ville){
			String v3=v2.getNom_v();
			String v4=admin.getAgence().getLocalite().getVille().getNom_v();
			if(v3.equals(v4)){}
			else{
			res.put(v2.getNom_v(),v2.getNom_v());}
		}
		return res;
	}
	public void recuperer(){
		service.recuperTransfer(selectedTransfert);
		FacesContext fc = FacesContext.getCurrentInstance();  
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "transfert recuperer avec succes", null));
	}
    public void save() {
    	setSucces(false);
        Transfert t=new Transfert();
		t.setAdmin(admin);
		t.setNom(getNom());
		t.setPhone(getPhone2());
		t.setId_per(getPhone1());
		t.setStatut(0);
		try{
			t.setVillep(admin.getAgence().getLocalite().getVille());
			t.setVilled(service.getVilleByName(v));
			t.setSomme(getSomme());
			service.transferer(t);
			setNom("");setPhone2("");setPhone1("");setSomme(0);setSucces(true);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		FacesContext fc = FacesContext.getCurrentInstance();  
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nouveau transfert ajoute avec succes", null));
    
    }
    public List<Transfert> getAllTransfertOut(){
    	return service.allTransfertOut(admin.getAgence());
    }
    public List<Transfert> getAllTransfertIn(){
    	return service.allTransfertIn(admin.getAgence());
    }
    
    /*------------------------------------------------------------*/
	/*-------------creation de tableau de bord-------------------*/
    private BarChartModel initBarModel() throws ParseException {
        BarChartModel model = new BarChartModel();
        List<Transfert> t1=service.allTransfertIn(admin.getAgence());
        List<Transfert> t2=service.allTransfertOut(admin.getAgence());
        /*List<Transfert> t=admin.getTransferts();*/
        int count1=t1.size();
        int count2=t2.size();
        ChartSeries recu = new ChartSeries();
        ChartSeries effectuer = new ChartSeries();
        recu.setLabel("Entrant");
        recu.set("",count1);
        effectuer.setLabel("Sortant");
        effectuer.set("",count2);
        model.addSeries(effectuer);
        model.addSeries(recu);
        
        return model;
    }
    private BarChartModel initBarModel2() throws ParseException {
        BarChartModel model = new BarChartModel();
        List<Transfert> t1=service.allTransfertIn(admin.getAgence());
        int countrec=0;
        int countnrec=0;
        Date date=service.generer_date();
        for(Transfert t5:t1){
     	   if(t5.getStatut()==1)
     	   {
     		   if(date.equals(t5.getDate_r()))
     		   {
     			   countrec++;
     		   }
     	   }
     	  else countnrec++;
        }
        
        ChartSeries recuperer = new ChartSeries();
        ChartSeries nonrecuperer = new ChartSeries();
        recuperer.setLabel("Recuperer");
        recuperer.set("",countrec);
        nonrecuperer.setLabel("Non Recuperer");
        nonrecuperer.set("",countnrec);
        
        model.addSeries(recuperer);
        model.addSeries(nonrecuperer);
        
        return model;
    }
    private void createBarModels() {
        createBarModel();
        
    }
    private void createBarModels2() {
        createBarModel2();
        
    }
     
    private void createBarModel() {
        try {
 		barModel = initBarModel();
 	
         
        barModel.setTitle("TRANSFERT MENSUEL");
        barModel.setLegendPosition("ne");
        Axis xAxis = barModel.getAxis(AxisType.X);
        /*xAxis.setLabel("TRANSFERT");*/
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Frequence");
        yAxis.setMin(0);
        yAxis.setMax(100);
        } catch (ParseException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    		FacesContext fc = FacesContext.getCurrentInstance(); 
         fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "echec", null));
    	}
    }
    private void createBarModel2() {
        try {
 		barModel2 = initBarModel2();
 		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		String d1=sdf.format(new java.util.Date());
        barModel2.setTitle("Situation du "+d1);
        barModel2.setLegendPosition("ne");
         
        Axis xAxis = barModel2.getAxis(AxisType.X);
        xAxis.setLabel("TRANSFERT JOURNALIER");
         
        Axis yAxis = barModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Frequence");
        yAxis.setMin(0);
        yAxis.setMax(100);
        } catch (ParseException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }
    
    public BarChartModel start(){
    	createBarModels();
    	return getBarModel();
    }
    public BarChartModel start2(){
    	createBarModels2();
    	return getBarModel2();
    }
    public String naviguer(){
    	return "bord";
    }
    public String logout(){
    	setAdmin(null);
    	setConnected(false);
    	setSup(false);
    	return  "logout";
    }
    
 
    public List<String> getImages() {
    	List<String> li=new LinkedList<String>();
    	li.add("Nouakchott");
    	li.add("Nouadhibou");
    	li.add("Rosso");
    	li.add("Zoueratte");
        return li;
    }
    

}
