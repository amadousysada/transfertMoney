package ss.com.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import ss.com.entite.Admin;
import ss.com.entite.Agence;
import ss.com.entite.Localite;
import ss.com.entite.Tarif;
import ss.com.entite.Transfert;
import ss.com.entite.Ville;

public interface ITransfertDao {
	
	public void transferer(Transfert t);
	public void recuperTransfer(Transfert t);
	public Transfert search(int phone);
	public void creer_admin(Admin ad);
	public void creer_ville(String v);
	public void creer_loc(String nom,Ville v);
	public void cree_agence(String nom,Localite l);
	public void generer_tarif(Transfert t);
	public Date generer_date() throws ParseException;
	public List<Transfert> allTransfers();
	public List<Transfert> allTransfertIn(Agence a);
	public List<Transfert> allTransfertOut(Agence a);
	public List<Ville> allVilles();
	public List<Admin> allAdmins();
	public List<Agence> allAgences();
	public List<Localite> allLocalites();
	public Ville getVilleByName(String v);
	public Localite getLocaliteByName(String v);
	public Agence getAgenceByName(String a);
	public String login(String username,String password);
	public Admin getAdminById(String id);
	public void logout();
	public List<Tarif> allTarif();

}
