package ee.itcollege.i377.team29.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.tostring.RooToString;

import ee.itcollege.i377.team29.generic.AbstractEntity;

@Entity
@RooToString
@RooEntity
public class Piiriloik extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)   
	@Id
	private Long piiriloik_ID;
	private String kood;
	private String nimetus;
	private String GPS_koordinaadid;

	@OneToMany(mappedBy = "piiriloik")
	private Collection<Intsident> intsident;

	@OneToMany(mappedBy = "piiriloik")
	private Collection<Vahtkond_piiriloigul> vahtkond_piiriloigul;
	
	public Long getPiiriloik_ID() {
		return piiriloik_ID;
	}
	
	public void setPiiriloik_ID(Long piiriloik_ID) {
		this.piiriloik_ID = piiriloik_ID;
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
	
	public String getGPS_koordinaadid() {
		return GPS_koordinaadid;
	}
	
	public void setGPS_koordinaadid(String gPS_koordinaadid) {
		GPS_koordinaadid = gPS_koordinaadid;
	}
	
	public Collection<Intsident> getIntsident() {
	    return intsident;
	}
	
	public void setIntsident(Collection<Intsident> param) {
	    this.intsident = param;
	}
	
	public Collection<Vahtkond_piiriloigul> getVahtkond_piiriloigul() {
	    return vahtkond_piiriloigul;
	}
	
	public void setVahtkond_piiriloigul(Collection<Vahtkond_piiriloigul> param) {
	    this.vahtkond_piiriloigul = param;
	}
	
}
