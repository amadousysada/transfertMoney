


import java.util.Date;
import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.ParseException;


import ss.com.entite.Admin;
import ss.com.entite.Agence;
import ss.com.entite.Localite;
import ss.com.entite.Transfert;
import ss.com.entite.Ville;
import ss.com.service.ITransfertService;


public class Test {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		
		//AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		ClassPathXmlApplicationContext context=	new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		ITransfertService service=(ITransfertService) context.getBean("service");
		
		service.creer_ville("Nouakchott");
		service.creer_ville("Nouadhibou");
		service.creer_ville("Rosso");
		service.creer_ville("Zoueratte");
		List<Ville> l=service.allVilles();
		service.creer_loc("ARAFAT",service.getVilleByName("Nouakchott"));
		service.creer_loc("CANSADO",service.getVilleByName("Nouadhibou"));
		//System.out.println(service.login("Ahmed","12020FGI"));//.toString());("valide")? "connecte !":"non connecte!";
		
		System.out.println("Villes");
		System.out.println("======Id======Nom======");
		for(Ville v:l){
			//service.creer_loc("ARAFAT", v);
			System.out.println("    "+v.getId_v()+"    "+v.getNom_v()+"     ");
		}
		List<Localite> ll=service.allLocalites();
		System.out.println("LOCALITES");
		System.out.println("======Id======Nom======Ville=======");
		for(Localite li:ll){
			System.out.println("       "+li.getId_loc()+"      "+li.getLabel_loc()+"    "+li.getVille().getNom_v());
		}
		service.cree_agence("GAZA-ARAFAT",service.getLocaliteByName("ARAFAT"));
		service.cree_agence("GAZA-NDB",service.getLocaliteByName("CANSADO"));
		
		List<Agence> la=service.allAgences();
		System.out.println("AGENCES");
		System.out.println("======Id======Nom======Local=======Ville=======");
		for(Agence a:la){
			System.out.println("       "+a.getId_ag()+"      "+a.getLabel()+"    "+a.getLocalite().getLabel_loc()+"    "+a.getLocalite().getVille().getNom_v());
		}
		
		Admin a=new Admin();
		a.setId_ad("12089GI");
		a.setNom("Diallo");
		a.setPrenom("Amadou");
		a.setAgence(service.getAgenceByName("GAZA-NDB"));
		a.setDegre("simple");
		Admin a2=new Admin();
		a2.setId_ad("12020GI");
		a2.setNom("Ahmed");
		a2.setPrenom("ould Dehman");
		a2.setAgence(service.getAgenceByName("GAZA-ARAFAT"));
		a2.setDegre("simple");
		service.creer_admin(a);
		service.creer_admin(a2);
		
		
		Admin admin=new Admin();
		admin.setId_ad("ADMIN");
		admin.setNom("ADMIN");
		admin.setPrenom("ADMIN");
		admin.setAgence(service.getAgenceByName("GAZA-NDB"));
		admin.setDegre("super");
		service.creer_admin(admin);
		Transfert t1=new Transfert();
		t1.setAdmin(a);
		t1.setNom("Amadou Sy");
		t1.setPhone("46702705");
		t1.setSomme(1000000000);
		t1.setId_per("44216199");
		t1.setStatut(0);
		t1.setVillep(a.getAgence().getLocalite().getVille());
		t1.setVilled(service.getVilleByName("Nouakchott"));
		
		Transfert t2=new Transfert();
		t2.setSomme(100000);
		t2.setAdmin(a2);
		t2.setNom("Sadam");
		t2.setPhone("36802605");
		t2.setId_per("34216999");
		t2.setStatut(0);
		t2.setVillep(a.getAgence().getLocalite().getVille());
		t2.setVilled(service.getVilleByName("Nouadhibou"));
		service.transferer(t1);
		service.transferer(t2);
		
		List<Transfert> lt=service.allTransfers();
		System.out.println("TRANSFERTS");
		System.out.println("Id======Date======Expediteur======telephone======Somme=======Vers=======pour");
		for(Transfert t:lt){
			System.out.println(t.getId_tr()+"      "+t.getDate_t()+"    "+t.getNom()+"    "+t.getId_per()+"   "+t.getSomme()+"  "+t.getVilled().getNom_v()+"  "+t.getPhone());
		}
		Admin a1=service.getAdminById("12020GI");
		System.out.println(a1.getNom()+a1.getPrenom());
		System.out.println(service.login("Ahmed","1202"));
		System.out.println(service.login("Ahmed","12020GI"));
		List<Ville> lv=service.allVilles();
		System.out.println("TRANSFERTS");
		System.out.println("Id======Nom");
		for(Ville vt:lv){
			System.out.println(vt.getId_v()+"      "+vt.getNom_v());
		}
		
		/*String test=service.login("ADMIN","ADMIN" );
		System.out.println(test);
		Agence ag=service.getAgenceByName("GAZA-NDB");
		List<Transfert> lt=service.allTransfertIn(ag);
		System.out.println("transfert entrant");
		for(Transfert t:lt){
			System.out.println("transfert n:"+t.getId_tr());
		}
		Date d=null;
		try {
			d=service.generer_date();
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Agence ag1=service.getAgenceByName("GAZA-NDB");
		List<Transfert> lt1=service.allTransfertOut(ag1);
		System.out.println("transfert sortant hier");
		for(Transfert t:lt1){
			if(d.equals(t.getDate_t())){
			System.out.println("transfert n:"+t.getId_tr()+" le"+t.getDate_t());}
		}
		*/
	}

}
