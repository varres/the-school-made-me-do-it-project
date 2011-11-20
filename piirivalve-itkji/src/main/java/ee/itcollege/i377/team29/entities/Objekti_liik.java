package ee.itcollege.i377.team29.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.tostring.RooToString;

import ee.itcollege.i377.team29.generic.AbstractEntity;

@Entity
@RooEntity
@RooToString
public class Objekti_liik extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)   
	@Id
	private Long objekt_liik_ID;
	private String kood;
	@NotNull
	private String nimetus;
	
	@OneToMany(mappedBy = "objekti_liik")
	private Collection<Objekt> objekt;

	public Objekti_liik() {
		super();
	}

	public Long getObjekt_liik_ID() {
		return objekt_liik_ID;
	}

	public void setObjekt_liik_ID(Long objekt_liik_ID) {
		this.objekt_liik_ID = objekt_liik_ID;
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

	public Collection<Objekt> getObjekt() {
	    return objekt;
	}

	public void setObjekt(Collection<Objekt> param) {
	    this.objekt = param;
	}

   
}
