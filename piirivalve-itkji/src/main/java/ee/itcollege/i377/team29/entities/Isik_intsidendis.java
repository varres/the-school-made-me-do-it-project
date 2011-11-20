package ee.itcollege.i377.team29.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.tostring.RooToString;

import ee.itcollege.i377.team29.generic.AbstractEntity;
import ee.itcollege.i377.team29.generic.HistoricalEntity;

@Entity
@RooToString
@RooEntity
public class Isik_intsidendis extends HistoricalEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)   
	@Id
	private Long isik_intsidendis_ID;
	@NotNull
	private String kirjeldus;
	private Intsident intsident_ID;
	private Piiririkkuja piiririkkuja_ID;

	@ManyToOne
	private Intsident intsident;

	@ManyToOne
	private Piiririkkuja piiririkkuja;

	public Isik_intsidendis() {
		super();
	}

	public Long getIsik_intsidendis_ID() {
		return isik_intsidendis_ID;
	}

	public void setIsik_intsidendis_ID(Long isik_intsidendis_ID) {
		this.isik_intsidendis_ID = isik_intsidendis_ID;
	}

	public String getKirjeldus() {
		return kirjeldus;
	}

	public void setKirjeldus(String kirjeldus) {
		this.kirjeldus = kirjeldus;
	}

	public Intsident getIntsident_ID() {
		return intsident_ID;
	}

	public void setIntsident_ID(Intsident intsident_ID) {
		this.intsident_ID = intsident_ID;
	}

	public Piiririkkuja getPiiririkkuja_ID() {
		return piiririkkuja_ID;
	}

	public void setPiiririkkuja_ID(Piiririkkuja piiririkkuja_ID) {
		this.piiririkkuja_ID = piiririkkuja_ID;
	}

	public Intsident getIntsident() {
	    return intsident;
	}

	public void setIntsident(Intsident param) {
	    this.intsident = param;
	}

	public Piiririkkuja getPiiririkkuja() {
	    return piiririkkuja;
	}

	public void setPiiririkkuja(Piiririkkuja param) {
	    this.piiririkkuja = param;
	}

	
}
