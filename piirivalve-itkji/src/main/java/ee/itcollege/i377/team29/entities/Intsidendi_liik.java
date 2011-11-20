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
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.tostring.RooToString;

import ee.itcollege.i377.team29.generic.AbstractEntity;

@Entity
@RooToString
@RooEntity
public class Intsidendi_liik extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)   
	@Id
	private Long intsidendi_liik_ID;
	@Size(min = 0, max = 18)
	private String kood;
	@Size(min = 0, max = 60)
	private String nimetus;

	@OneToMany(mappedBy = "intsidendi_liik")
	private Collection<Intsident> intsident;
	
	public Long getIntsidendi_liik_ID() {
		return intsidendi_liik_ID;
	}
	
	public void setIntsidendi_liik_ID(Long intsidendi_liik_ID) {
		this.intsidendi_liik_ID = intsidendi_liik_ID;
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
	
	public Collection<Intsident> getIntsident() {
	    return intsident;
	}
	
	public void setIntsident(Collection<Intsident> param) {
	    this.intsident = param;
	}
	
}
