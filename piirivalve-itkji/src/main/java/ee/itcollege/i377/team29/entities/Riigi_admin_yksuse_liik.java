package ee.itcollege.i377.team29.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.tostring.RooToString;

import ee.itcollege.i377.team29.generic.HistoricalEntity;

@Entity
@RooEntity
@RooToString
public class Riigi_admin_yksuse_liik extends HistoricalEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)   
	@Id
	private Long riigi_admin_yksuse_liik_ID;
	@NotNull
	private String kood;
	@NotNull
	private String nimetus;

	@OneToMany(mappedBy = "riigi_admin_yksuse_liik")
	private Collection<Riigi_admin_yksus> riigi_admin_yksus;

	public Riigi_admin_yksuse_liik() {
		super();
	}

	public Long getRiigi_admin_yksuse_liik_ID() {
		return riigi_admin_yksuse_liik_ID;
	}

	public void setRiigi_admin_yksuse_liik_ID(Long riigi_admin_yksuse_liik_ID) {
		this.riigi_admin_yksuse_liik_ID = riigi_admin_yksuse_liik_ID;
	}

	public String getKood() {
		return kood;
	}

	public void setKood(String kood) {
		this.kood = kood;
	}

	public String getNimetus() {
		return nimetus;
	}

	public void setNimetus(String nimetus) {
		this.nimetus = nimetus;
	}

	public Collection<Riigi_admin_yksus> getRiigi_admin_yksus() {
	    return riigi_admin_yksus;
	}

	public void setRiigi_admin_yksus(Collection<Riigi_admin_yksus> param) {
	    this.riigi_admin_yksus = param;
	}

	  
}

