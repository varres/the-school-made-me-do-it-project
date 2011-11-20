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
@RooEntity
@RooToString
public class Objekt extends AbstractEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)   
	@Id
	private Long objekt_ID;
	private String nimetus;	
		
	@ManyToOne
	@NotNull
	private Objekti_liik objekti_liik;

	@OneToMany(mappedBy = "objekt")
	private Collection<Objekt_intsidendis> objekt_intsidendis;

	@OneToMany(mappedBy = "objekt")
	private Collection<Piiririkkuja> piiririkkuja;

	public Objekt() {
		super();
	}

	public Long getObjekt_ID() {
		return objekt_ID;
	}

	public void setObjekt_ID(Long objekt_ID) {
		this.objekt_ID = objekt_ID;
	}

	public String getNimetus() {
		return nimetus;
	}

	public void setNimetus(String nimetus) {
		this.nimetus = nimetus;
	}

	public Objekti_liik getObjekti_liik() {
	    return objekti_liik;
	}

	public void setObjekti_liik(Objekti_liik param) {
	    this.objekti_liik = param;
	}

	public Collection<Objekt_intsidendis> getObjekt_intsidendis() {
	    return objekt_intsidendis;
	}

	public void setObjekt_intsidendis(Collection<Objekt_intsidendis> param) {
	    this.objekt_intsidendis = param;
	}

	public Collection<Piiririkkuja> getPiiririkkuja() {
	    return piiririkkuja;
	}

	public void setPiiririkkuja(Collection<Piiririkkuja> param) {
	    this.piiririkkuja = param;
	}

   
}
