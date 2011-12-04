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

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.tostring.RooToString;

import ee.itcollege.i377.team29.generic.HistoricalEntity;

@Entity
@RooEntity
@RooToString
public class Vaeosa extends HistoricalEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)   
	@Id
	private Long vaeosa_ID ;
	@NotNull
	private String kood;
	@NotNull
	private String nimetus;	
	
	@ManyToOne
	@NotNull
	private Riigi_admin_yksus riigi_admin_yksus;
	
	@OneToMany(mappedBy = "vaeosa")
	private Collection<Vahtkond> vahtkond;

	public Vaeosa() {
		super();
	}

	public Long getVaeosa_ID() {
		return vaeosa_ID;
	}

	public void setVaeosa_ID(Long vaeosa_ID) {
		this.vaeosa_ID = vaeosa_ID;
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

	public Riigi_admin_yksus getRiigi_admin_yksus() {
	    return riigi_admin_yksus;
	}

	public void setRiigi_admin_yksus(Riigi_admin_yksus param) {
	    this.riigi_admin_yksus = param;
	}

	public Collection<Vahtkond> getVahtkond() {
	    return vahtkond;
	}

	public void setVahtkond(Collection<Vahtkond> param) {
	    this.vahtkond = param;
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
