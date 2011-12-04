package ee.itcollege.i377.team29.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.security.core.context.SecurityContextHolder;

import ee.itcollege.i377.team29.generic.HistoricalEntity;

@Entity
@RooEntity
@RooToString
public class Piirivalvur_intsidendis extends HistoricalEntity implements Serializable {
	
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
	private Piirivalvur piirivalvur;
	
	@ManyToOne
	private Vahtkond_intsidendis vahtkond_intsidendis;
	
	public Collection<Piirivalvuri_seadus_intsidendi> getPiirivalvuri_seadus_intsidendi() {
		return piirivalvuri_seadus_intsidendi;
	}

	public void setPiirivalvuri_seadus_intsidendi(
			Collection<Piirivalvuri_seadus_intsidendi> piirivalvuri_seadus_intsidendi) {
		this.piirivalvuri_seadus_intsidendi = piirivalvuri_seadus_intsidendi;
	}

	@OneToMany(mappedBy = "piirivalvur_intsidendis")
	private Collection<Piirivalvuri_seadus_intsidendi> piirivalvuri_seadus_intsidendi;
	
	public static List<Piirivalvur_intsidendis> findAllPiirivalvurIntsidendis(Intsident i) {
		return entityManager()
				.createQuery("SELECT o FROM Piirivalvur_intsidendis o WHERE o.intsident = :ints", 
						Piirivalvur_intsidendis.class)
						.setParameter("ints", i)
						.getResultList(); 
	}
	
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

	public Piirivalvur getPiirivalvur() {
		return piirivalvur;
	}

	public void setPiirivalvur(Piirivalvur piirivalvur) {
		this.piirivalvur = piirivalvur;
	}

	public Vahtkond_intsidendis getVahtkond_intsidendis() {
		return vahtkond_intsidendis;
	}

	public void setVahtkond_intsidendis(Vahtkond_intsidendis vahtkond_intsidendis) {
		this.vahtkond_intsidendis = vahtkond_intsidendis;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public HistoricalEntity findByIdHistoricalWrapper(Long id) {
		return Piirivalvur_intsidendis.findPiirivalvur_intsidendis(id);
	}
	
	

	@Override
	public HistoricalEntity mergeHistoricalWrapper() {
		return this.merge();
	}

	@Override
	public void setIdHistoricalWrapper(Long id) {
		setPiirivalvur_intsidendis_ID(id);
	}

	@Override
	public Long getIdHistoricalWrapper() {
		return getPiirivalvur_intsidendis_ID();
	}

	@Override
	public HistoricalEntity persistHistoricalWrapper() {
		this.persist();
		return this;
	}

	@Override
	public void detatchHistoricalWrapper() {
		Piirivalvur_intsidendis.entityManager().detach(this);
	}

	@Override
	public void copyUpdatedValuesHistoricalWrapper(HistoricalEntity copyTo) {
		Piirivalvur_intsidendis newPvInts = (Piirivalvur_intsidendis)copyTo;
		
		if(this.getKirjeldus() != null) {
			newPvInts.setKirjeldus(this.getKirjeldus());
		}
		if(this.getKommentaar() != null) {
			newPvInts.setKommentaar(this.getKommentaar());
		}
		if(this.getIntsident() != null) {
			newPvInts.setIntsident(this.getIntsident());
		}
		if(this.getVahtkond_intsidendis() != null) {
			newPvInts.setVahtkond_intsidendis(this.getVahtkond_intsidendis());
		}
		if(this.getPiirivalvur() != null) {
			newPvInts.setPiirivalvur(this.getPiirivalvur());
		}
	}
}
