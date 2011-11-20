package ee.itcollege.i377.team29.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.tostring.RooToString;

import ee.itcollege.i377.team29.generic.HistoricalEntity;

/**
 * Entity implementation class for Entity: Vahtkonna_liige
 * 
 */
@Entity
@RooEntity
@RooToString
public class Vahtkonna_liige extends HistoricalEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long vahtkonna_liige_ID;
	
	@ManyToOne
	private Vahtkond vahtkond;

	@ManyToOne
	private Piirivalvur piirivalvur;

	public Vahtkonna_liige() {
		super();
	}

	public Long getVahtkonna_liige_ID() {
		return vahtkonna_liige_ID;
	}

	public void setVahtkonna_liige_ID(Long vahtkonna_liige_ID) {
		this.vahtkonna_liige_ID = vahtkonna_liige_ID;
	}

	public Vahtkond getVahtkond() {
		return vahtkond;
	}

	public void setVahtkond(Vahtkond param) {
		this.vahtkond = param;
	}

	public void setPiirivalvur(Piirivalvur param) {
		this.piirivalvur = param;
	}

	public Piirivalvur getPiirivalvur() {
		return piirivalvur;
	}

}
