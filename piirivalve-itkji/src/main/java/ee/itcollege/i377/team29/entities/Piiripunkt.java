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
@RooToString
@RooEntity
public class Piiripunkt extends HistoricalEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)   
	@Id
	private Long piiripunkt_ID;
	@NotNull
	private String kood;
	@NotNull
	private String nimetus;
	@NotNull
	private String GPS_longitude;
	@NotNull
	private String GPS_latitude;
	@NotNull
	private String korgus_merepinnast;

	@OneToMany(mappedBy = "piiripunkt")
	private Collection<Vahtkond> vahtkond;
	
	public Piiripunkt() {
		super();
	}

	public Long getPiiripunkt_ID() {
		return piiripunkt_ID;
	}

	public void setPiiripunkt_ID(Long piiripunkt_ID) {
		this.piiripunkt_ID = piiripunkt_ID;
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

	public String getGPS_longitude() {
		return GPS_longitude;
	}

	public void setGPS_longitude(String gPS_longitude) {
		GPS_longitude = gPS_longitude;
	}

	public String getGPS_latitude() {
		return GPS_latitude;
	}

	public void setGPS_latitude(String gPS_latitude) {
		GPS_latitude = gPS_latitude;
	}

	public String getKorgus_merepinnast() {
		return korgus_merepinnast;
	}

	public void setKorgus_merepinnast(String korgus_merepinnast) {
		this.korgus_merepinnast = korgus_merepinnast;
	}

	public Collection<Vahtkond> getVahtkond() {
	    return vahtkond;
	}

	public void setVahtkond(Collection<Vahtkond> param) {
	    this.vahtkond = param;
	}   
	
   
}
