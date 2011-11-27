package ee.itcollege.i377.team29.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import ee.itcollege.i377.team29.generic.AbstractEntity;

import javax.persistence.Basic;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@RooToString
@RooEntity
public class Seaduse_punkt extends AbstractEntity implements Serializable {
	public Seadus getSeadus() {
		return seadus;
	}

	public void setSeadus(Seadus seadus) {
		this.seadus = seadus;
	}

	public Collection<Piirivalvuri_seadus_intsidendi> getPiirivalvuri_seadus_intsidendi() {
		return piirivalvuri_seadus_intsidendi;
	}

	public void setPiirivalvuri_seadus_intsidendi(
			Collection<Piirivalvuri_seadus_intsidendi> piirivalvuri_seadus_intsidendi) {
		this.piirivalvuri_seadus_intsidendi = piirivalvuri_seadus_intsidendi;
	}

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long seaduse_punkt_ID;
	
	@NotNull
	@Size(min = 0, max = 20)
	private String paragrahv;
	
	@NotNull
	@Size(min = 0, max = 20)
	private String pais;
	
	@NotNull
	@Size(min = 0, max = 20)
	private String text;
	
	@NotNull
	@Size(min = 0, max = 20)
	private String kehtiv_alates;
	
	@NotNull
	@Size(min = 0, max = 20)
	private String kehtiv_kuni;
	

	@ManyToOne
	Seaduse_punkt ylem_seaduse_punkt_ID;

	@ManyToOne
	Seadus seadus;
	
	
	@OneToMany(mappedBy = "seaduse_punkt")
	private Collection<Piirivalvuri_seadus_intsidendi> piirivalvuri_seadus_intsidendi;
	
	public Long getSeaduse_punkt_ID() {
		return seaduse_punkt_ID;
	}

	public void setSeaduse_punkt_ID(Long seaduse_punkt_ID) {
		this.seaduse_punkt_ID = seaduse_punkt_ID;
	}

	public String getParagrahv() {
		return paragrahv;
	}

	public void setParagrahv(String paragrahv) {
		this.paragrahv = paragrahv;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public Seaduse_punkt getYlem_seaduse_punkt_ID() {
		return ylem_seaduse_punkt_ID;
	}

	public void setYlem_seaduse_punkt_ID(Seaduse_punkt ylem_seaduse_punkt_ID) {
		this.ylem_seaduse_punkt_ID = ylem_seaduse_punkt_ID;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
