package ss.com.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;

//import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ss.com.entite.Admin;
import ss.com.entite.Agence;
import ss.com.entite.Localite;
import ss.com.entite.Tarif;
import ss.com.entite.Transfert;
import ss.com.entite.Ville;

@Transactional
public class TransfertDaoImpl implements ITransfertDao{
	
	private SessionFactory sessionFactory;
	private List<Transfert> listTransfert;
	private List<Ville> listVille;
	private List<Agence> listAgence;
	private List<Admin> listAdmin;
	
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<Transfert> getListTransfert() {
		return listTransfert;
	}

	public void setListTransfert(List<Transfert> listTransfert) {
		this.listTransfert = listTransfert;
	}

	public Session getSession(){
        return sessionFactory.getCurrentSession();
    }
	
	public List<Ville> getListVille() {
		return listVille;
	}

	public void setListVille(List<Ville> listVille) {
		this.listVille = listVille;
	}

	public List<Agence> getListAgence() {
		return listAgence;
	}

	public void setListAgence(List<Agence> listAgence) {
		this.listAgence = listAgence;
	}

	public List<Admin> getListAdmin() {
		return listAdmin;
	}

	public void setListAdmin(List<Admin> listAdmin) {
		this.listAdmin = listAdmin;
	}

	@Override
	public void transferer(Transfert t) {
		// TODO Auto-generated method stub
		getSession().getTransaction().begin();
		generer_tarif(t);
		try {
			t.setDate_t(generer_date());
			t.setDate_r(null);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.setStatut(0);
		getSession().persist(t);
		getSession().getTransaction().commit();
		
		
	}


	@Override
	public void recuperTransfer(Transfert t){
		// TODO Auto-generated method stub
		getSession().getTransaction().begin();
			Transfert t1=(Transfert) getSession().get(Transfert.class, t.getId_tr());
			t1.setStatut(1);
			try {
				t1.setDate_r(generer_date());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		getSession().update(t1);
		getSession().getTransaction().commit();
		
		
	}

	@Override
	public Transfert search(int phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void creer_admin(Admin ad) {
		
		getSession().getTransaction().begin();
		getSession().persist(ad);
		getSession().getTransaction().commit();
		
	}

	@Override
	public void creer_ville(String v) {
		// TODO Auto-generated method stub
		getSession().getTransaction().begin();
		Ville ville=new Ville();
		ville.setNom_v(v);
		getSession().persist(ville);
		getSession().getTransaction().commit();
		
	}

	@Override
	public void creer_loc(String nom, Ville v) {
		// TODO Auto-generated method stub
		getSession().getTransaction().begin();
		Localite loc=new Localite();
		loc.setVille(v);
		loc.setLabel_loc(nom);
		getSession().persist(loc);
		getSession().getTransaction().commit();
		
	}

	@Override
	public void cree_agence(String nom, Localite l) {
		// TODO Auto-generated method stub
		getSession().getTransaction().begin();
		Agence a=new Agence();
		a.setLabel(nom);
		a.setLocalite(l);
		getSession().persist(a);
		getSession().getTransaction().commit();
		
	}

	@Override
	public void generer_tarif(Transfert t) {
		// TODO Auto-generated method stub
		int s=t.getSomme();
		Tarif ta=new Tarif();
		ta.setTransfert(t);
		if(s<100000){
			ta.setVal_tar((s*5/100));
		}
		else{
			ta.setVal_tar(1000);
		}
		getSession().persist(ta);
		
		
	}

	@Override
	public Date generer_date() throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		String d=sdf.format(new java.util.Date());
		Date d1=sdf.parse(d);
		return d1;
	}

	@Override
	public List<Transfert> allTransfers() {
		// TODO Auto-generated method stub
		//String req="from Transfert t where t.statut=0 and t.villed=:v_d";
		getSession().getTransaction().begin();
		//Query q=getSession().createQuery(req);
		//q.setParameter("v_d",v);
		setListTransfert(getSession().createQuery("from Transfert").list());
		getSession().getTransaction().commit();
		return getListTransfert();
	}

	@Override
	public List<Ville> allVilles() {
		// TODO Auto-generated method stub
		getSession().getTransaction().begin();
		setListVille(getSession().createQuery("from Ville").list());
		getSession().getTransaction().commit();
		return getListVille();
	}

	@Override
	public List<Admin> allAdmins() {
		// TODO Auto-generated method stub
		getSession().getTransaction().begin();
		setListAdmin(getSession().createQuery("from Admin").list());
		getSession().getTransaction().commit();
		return getListAdmin();
	}

	@Override
	public List<Agence> allAgences() {
		// TODO Auto-generated method stub
		getSession().getTransaction().begin();
		setListAgence(getSession().createQuery("from Agence").list());
		getSession().getTransaction().commit();
		return getListAgence();
	}

	@Override
	public Ville getVilleByName(String v) {
		// TODO Auto-generated method stub
		Ville vi=new Ville();
		String req="from Ville v where v.nom_v=:v_nom";
		getSession().getTransaction().begin();
		vi=(Ville) getSession().createQuery(req).setParameter("v_nom",v).uniqueResult();
		getSession().getTransaction().commit();
		return vi;
	}

	@Override
	public List<Localite> allLocalites() {
		// TODO Auto-generated method stub
		getSession().getTransaction().begin();
		List<Localite> li=(getSession().createQuery("from Localite").list());
		getSession().getTransaction().commit();
		return li;
		
	}
	
	@Override
	public Localite getLocaliteByName(String v) {
		// TODO Auto-generated method stub
		Localite lo=new Localite();
		String req="from Localite l where l.label_loc=:l_nom";
		getSession().getTransaction().begin();
		lo=(Localite) getSession().createQuery(req).setParameter("l_nom",v).uniqueResult();
		getSession().getTransaction().commit();
		return lo;
	}
	@Override
	public List<Tarif> allTarif() {
		// TODO Auto-generated method stub
		List<Tarif> lt;
		getSession().getTransaction().begin();
		lt=(getSession().createQuery("from Tarif").list());
		getSession().getTransaction().commit();
		return lt;
	}
	
	@Override
	public Agence getAgenceByName(String a) {
		// TODO Auto-generated method stub
		Agence ag=new Agence();
		String req="from Agence a where a.label=:a_nom";
		getSession().getTransaction().begin();
		ag=(Agence) getSession().createQuery(req).setParameter("a_nom",a).uniqueResult();
		getSession().getTransaction().commit();
		return ag;
		
	}
	
	
	@Override
	public String login(String username,String password) {
		// TODO Auto-generated method stub
		String message=null;
		getSession().getTransaction().begin();
		Criteria cr=getSession().createCriteria(Admin.class);
		Criterion id = Restrictions. eq("id_ad" , password);
		Criterion nom = Restrictions.eq("nom" ,username);
		LogicalExpression andExp = Restrictions.and(id, nom);
		cr.add(andExp);
		int m=Integer.parseInt(cr.setProjection(Projections.rowCount()).list().get(0).toString());
		message=(m==1)?"valide":"invalide";
		getSession().getTransaction().commit();
		return message;
	}
	
	public void creerMessage(String m,Severity s) {
		FacesMessage message=new FacesMessage(m);
		message.setSeverity(s);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	@Override
	public Admin getAdminById(String id) {
		// TODO Auto-generated method stub
		String req="from Admin a where a.id_ad=:a_id";
		Admin a = null;
		try {
			getSession().getTransaction().begin();
			//Admin a= (Admin) getSession().get(Admin.class, id);
			a=(Admin) getSession().createQuery(req).setParameter("a_id", id).uniqueResult();
			getSession().getTransaction().commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return a;
		
	}
	@Override
	public List<Transfert> allTransfertIn(Agence a) {
		// TODO Auto-generated method stub
		List<Transfert> lt=null;
		String req="from Transfert t where t.villed.id_v=:v_d";
		getSession().getTransaction().begin();
		Query q=getSession().createQuery(req);
		q.setParameter("v_d",a.getLocalite().getVille().getId_v());
		lt=q.list();
		getSession().getTransaction().commit();
		return lt;
	}
	@Override
	public List<Transfert> allTransfertOut(Agence a) {
		// TODO Auto-generated method stub
		List<Transfert> lt=null;
		String req="from Transfert t where t.villep.id_v=:v_d";
		getSession().getTransaction().begin();
		Query q=getSession().createQuery(req);
		q.setParameter("v_d",a.getLocalite().getVille().getId_v());
		lt=q.list();
		getSession().getTransaction().commit();
		return lt;
	}
	
	public void logout(){
		getSession().clear();
	}
	
}
