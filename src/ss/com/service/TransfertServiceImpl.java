package ss.com.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ss.com.dao.ITransfertDao;
import ss.com.entite.Admin;
import ss.com.entite.Agence;
import ss.com.entite.Localite;
import ss.com.entite.Tarif;
import ss.com.entite.Transfert;
import ss.com.entite.Ville;

public class TransfertServiceImpl implements ITransfertService{
	@Autowired
	private ITransfertDao dao;
	public void setDao(ITransfertDao dao) {
		this.dao = dao;
	}

	@Override
	public void transferer(Transfert t) {
		// TODO Auto-generated method stub
		dao.transferer(t);
		
	}

	

	@Override
	public void recuperTransfer(Transfert t) {
		// TODO Auto-generated method stub
		dao.recuperTransfer(t);
	}

	@Override
	public Transfert search(int phone) {
		// TODO Auto-generated method stub
		return dao.search(phone);
	}

	@Override
	public void creer_admin(Admin ad) {
		// TODO Auto-generated method stub
		dao.creer_admin(ad);
	}

	@Override
	public void creer_ville(String v) {
		// TODO Auto-generated method stub
		dao.creer_ville(v);
	}

	@Override
	public void creer_loc(String nom, Ville v) {
		// TODO Auto-generated method stub
		dao.creer_loc(nom, v);
	}

	@Override
	public void cree_agence(String nom, Localite l) {
		// TODO Auto-generated method stub
		dao.cree_agence(nom, l);
	}

	@Override
	public void generer_tarif(Transfert t) {
		// TODO Auto-generated method stub
		dao.generer_tarif(t);
	}

	@Override
	public Date generer_date() throws ParseException {
		// TODO Auto-generated method stub
		return dao.generer_date();
	}

	@Override
	public List<Transfert> allTransfers() {
		// TODO Auto-generated method stub
		return dao.allTransfers();
	}

	@Override
	public List<Ville> allVilles() {
		// TODO Auto-generated method stub
		return dao.allVilles();
	}

	@Override
	public List<Admin> allAdmins() {
		// TODO Auto-generated method stub
		return dao.allAdmins();
	}

	@Override
	public List<Agence> allAgences() {
		// TODO Auto-generated method stub
		return dao.allAgences();
	}

	@Override
	public Ville getVilleByName(String v){
		// TODO Auto-generated method stub
		return dao.getVilleByName(v);
	}

	@Override
	public List<Localite> allLocalites() {
		// TODO Auto-generated method stub
		return dao.allLocalites();
	}

	@Override
	public Localite getLocaliteByName(String v) {
		// TODO Auto-generated method stub
		return dao.getLocaliteByName(v);
	}
	
	@Override
	public Agence getAgenceByName(String a) {
		// TODO Auto-generated method stub
		return dao.getAgenceByName(a);
	}
	
	
	@Override
	public String login(String username, String password) {
		// TODO Auto-generated method stub
		return dao.login(username, password);
	}
	@Override
	public Admin getAdminById(String id) {
		// TODO Auto-generated method stub
		return dao.getAdminById(id);
	}
	@Override
	public List<Transfert> allTransfertIn(Agence a) {
		// TODO Auto-generated method stub
		return dao.allTransfertIn(a);
	}
	@Override
	public List<Transfert> allTransfertOut(Agence a) {
		// TODO Auto-generated method stub
		return dao.allTransfertOut(a);
	}
	@Override
	public List<Tarif> allTarif() {
		// TODO Auto-generated method stub
		return dao.allTarif();
	}
	public void logout(){
		dao.logout();
		
	}
}
