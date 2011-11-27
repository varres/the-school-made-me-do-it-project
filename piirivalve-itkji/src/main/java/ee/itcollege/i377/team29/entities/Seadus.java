package ee.itcollege.i377.team29.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
public class Seadus extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)   
	@Id
	private Long seadus_ID;
	@NotNull
	private String kood;
	@NotNull
	private String nimetus;
	@NotNull
	private String kehtiv_alates;
	@NotNull
	private String kehtiv_kuni;
	
	@OneToMany(mappedBy = "seadus")
	private Collection<Seaduse_punkt> seaduse_punkt;

	public Collection<Seaduse_punkt> getSeaduse_punkt() {
		return seaduse_punkt;
	}

	public void setSeaduse_punkt(Collection<Seaduse_punkt> seaduse_punkt) {
		this.seaduse_punkt = seaduse_punkt;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	public Long getSeadus_ID() {
		return seadus_ID;
	}

	public void setSeadus_ID(Long seadus_ID) {
		this.seadus_ID = seadus_ID;
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

	public String getKehtiv_alates() {
		return kehtiv_alates;
	}

	public void setKehtiv_alates(String kehtiv_alates) {
		this.kehtiv_alates = kehtiv_alates;
	}

	public String getKehtiv_kuni() {
		return kehtiv_kuni;
	}

	public void setKehtiv_kuni(String kehtiv_kuni) {
		this.kehtiv_kuni = kehtiv_kuni;
	}

}
