package ee.itcollege.i377.team29.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.tostring.RooToString;

import ee.itcollege.i377.team29.generic.AbstractEntity;

@Entity
@RooToString
@RooEntity
public class Piiririkkuja extends AbstractEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)   
	@Id
	private Long piiririkkuja_ID;
	@NotNull
	private String isikukood;
	@NotNull
	private String eesnimi;
	@NotNull
	private String perek_nimi;
	@NotNull
	private Character sugu;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date synniaeg;

	@ManyToOne
	private Objekt objekt;

	@OneToMany(mappedBy = "piiririkkuja")
	private Collection<Isik_intsidendis> isik_intsidendis;

	public Long getPiiririkkuja_ID() {
		return piiririkkuja_ID;
	}

	public void setPiiririkkuja_ID(Long piiririkkuja_ID) {
		this.piiririkkuja_ID = piiririkkuja_ID;
	}

	public String getIsikukood() {
		return isikukood;
	}

	public void setIsikukood(String isikukood) {
		this.isikukood = isikukood;
	}

	public String getEesnimi() {
		return eesnimi;
	}

	public void setEesnimi(String eesnimi) {
		this.eesnimi = eesnimi;
	}

	public String getPerek_nimi() {
		return perek_nimi;
	}

	public void setPerek_nimi(String perek_nimi) {
		this.perek_nimi = perek_nimi;
	}

	public Character getSugu() {
		return sugu;
	}

	public void setSugu(Character sugu) {
		this.sugu = sugu;
	}

	public Date getSynniaeg() {
		return synniaeg;
	}

	public void setSynniaeg(Date synniaeg) {
		this.synniaeg = synniaeg;
	}
	
	public Piiririkkuja() {
		super();
	}

	public Objekt getObjekt() {
	    return objekt;
	}

	public void setObjekt(Objekt param) {
	    this.objekt = param;
	}

	public Collection<Isik_intsidendis> getIsik_intsidendis() {
	    return isik_intsidendis;
	}

	public void setIsik_intsidendis(Collection<Isik_intsidendis> param) {
	    this.isik_intsidendis = param;
	}

	
   
}
