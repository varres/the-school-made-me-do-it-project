package ee.itcollege.i377.team29.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import ee.itcollege.i377.team29.generic.HistoricalEntity;

import javax.persistence.Basic;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@RooToString
@RooEntity
public class Piirivalvuri_seadus_intsidendi extends HistoricalEntity implements Serializable { // See nimi oli nii .png kui sql failis nii
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)   
	@Id
	private Long piirivalvuri_seadus_intsidendi_ID;

	@ManyToOne
	@NotNull
	Seaduse_punkt seaduse_punkt;
	
	@ManyToOne
	@NotNull
	Piirivalvur_intsidendis piirivalvur_intsidendis;
	
	@Size(min = 1, max = 255)
	@NotNull
	private String kirjeldus;

	public Long getPiirivalvuri_seadus_intsidendi_ID() {
		return piirivalvuri_seadus_intsidendi_ID;
	}

	public void setPiirivalvuri_seadus_intsidendi_ID(
			Long piirivalvuri_seadus_intsidendi_ID) {
		this.piirivalvuri_seadus_intsidendi_ID = piirivalvuri_seadus_intsidendi_ID;
	}

	public Seaduse_punkt getSeaduse_punkt() {
		return seaduse_punkt;
	}

	public void setSeaduse_punkt(Seaduse_punkt seaduse_punkt) {
		this.seaduse_punkt = seaduse_punkt;
	}

	public Piirivalvur_intsidendis getPiirivalvur_intsidendis() {
		return piirivalvur_intsidendis;
	}

	public void setPiirivalvur_intsidendis(
			Piirivalvur_intsidendis piirivalvur_intsidendis) {
		this.piirivalvur_intsidendis = piirivalvur_intsidendis;
	}

	public String getKirjeldus() {
		return kirjeldus;
	}

	public void setKirjeldus(String kirjeldus) {
		this.kirjeldus = kirjeldus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public HistoricalEntity findByIdHistoricalWrapper(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HistoricalEntity mergeHistoricalWrapper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIdHistoricalWrapper(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getIdHistoricalWrapper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HistoricalEntity persistHistoricalWrapper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void detatchHistoricalWrapper() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void copyUpdatedValuesHistoricalWrapper(HistoricalEntity copyTo) {
		// TODO Auto-generated method stub
		
	}
}
