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
@RooToString
@RooEntity
public class Vahtkond_intsidendis extends HistoricalEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long piirivalvur_intsidendis_ID;
	@NotNull
	private String kirjeldus;
	
	@NotNull
	@ManyToOne
	private Intsident intsident;
	
	@NotNull
	@ManyToOne
	private Vahtkond vahtkond;
	
	@OneToMany(mappedBy = "vahtkond_intsidendis")
	private Collection<Piirivalvur_intsidendis> piirivalvurid_intsidendis;

	public Long getPiirivalvur_intsidendis_ID() {
		return piirivalvur_intsidendis_ID;
	}

	public void setPiirivalvur_intsidendis_ID(Long piirivalvur_intsidendis_ID) {
		this.piirivalvur_intsidendis_ID = piirivalvur_intsidendis_ID;
	}

	public String getKirjeldus() {
		return kirjeldus;
	}

	public void setKirjeldus(String kirjeldus) {
		this.kirjeldus = kirjeldus;
	}

	public Intsident getIntsident() {
		return intsident;
	}

	public void setIntsident(Intsident intsident) {
		this.intsident = intsident;
	}

	public Vahtkond getVahtkond() {
		return vahtkond;
	}

	public void setVahtkond(Vahtkond vahtkond) {
		this.vahtkond = vahtkond;
	}

	public Collection<Piirivalvur_intsidendis> getPiirivalvurid_intsidendis() {
		return piirivalvurid_intsidendis;
	}

	public void setPiirivalvurid_intsidendis(Collection<Piirivalvur_intsidendis> piirivalvurid_intsidendis) {
		this.piirivalvurid_intsidendis = piirivalvurid_intsidendis;
	}
}
