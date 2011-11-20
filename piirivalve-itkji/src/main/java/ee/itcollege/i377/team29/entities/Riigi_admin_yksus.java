package ee.itcollege.i377.team29.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.tostring.RooToString;

import ee.itcollege.i377.team29.generic.HistoricalEntity;

@Entity
@RooEntity
@RooToString
public class Riigi_admin_yksus extends HistoricalEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)   
	@Id
	private Long riigi_admin_yksus_ID;
	private String kood;
	private String nimetus;	
	
	@ManyToOne
	@NotNull
	private Riigi_admin_yksuse_liik riigi_admin_yksuse_liik;
	
	@OneToMany(mappedBy = "riigi_admin_yksus")
	private Collection<Vaeosa> vaeosa;

	public Riigi_admin_yksus() {
		super();
	}
	public Long getRiigi_admin_yksus_ID() {
		return riigi_admin_yksus_ID;
	}

	public void setRiigi_admin_yksus_ID(Long riigi_admin_yksus_ID) {
		this.riigi_admin_yksus_ID = riigi_admin_yksus_ID;
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

	public Riigi_admin_yksuse_liik getRiigi_admin_yksuse_liik() {
	    return riigi_admin_yksuse_liik;
	}
	public void setRiigi_admin_yksuse_liik(Riigi_admin_yksuse_liik param) {
	    this.riigi_admin_yksuse_liik = param;
	}
	public Collection<Vaeosa> getVaeosa() {
	    return vaeosa;
	}
	public void setVaeosa(Collection<Vaeosa> param) {
	    this.vaeosa = param;
	}	
   
}
