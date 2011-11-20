package ee.itcollege.i377.team29.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.tostring.RooToString;

import ee.itcollege.i377.team29.generic.HistoricalEntity;

@Entity
@RooToString
@RooEntity
public class Vahtkond_piiriloigul extends HistoricalEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long vahtkond_piiriloigul_ID;

	@NotNull
	@ManyToOne
	private Piiriloik piiriloik;
	
	@NotNull
	@ManyToOne
	private Vahtkond vahtkond;
	
	public Long getVahtkond_piiriloigul_ID() {
		return vahtkond_piiriloigul_ID;
	}
	public void setVahtkond_piiriloigul_ID(Long vahtkond_piiriloigul_ID) {
		this.vahtkond_piiriloigul_ID = vahtkond_piiriloigul_ID;
	}
	
	public Piiriloik getPiiriloik() {
		return piiriloik;
	}
	
	public void setPiiriloik(Piiriloik piiriloik) {
		this.piiriloik = piiriloik;
	}
	
	public Vahtkond getVahtkond() {
		return vahtkond;
	}
	
	public void setVahtkond(Vahtkond vahtkond) {
		this.vahtkond = vahtkond;
	}
	
}
