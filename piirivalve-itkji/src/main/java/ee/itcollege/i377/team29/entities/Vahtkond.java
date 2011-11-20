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

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.tostring.RooToString;

import ee.itcollege.i377.team29.generic.HistoricalEntity;

@Entity
@RooToString
@RooEntity
public class Vahtkond extends HistoricalEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long vahtkond_ID;
	private String kood;
	private String nimetus;

	@OneToMany(mappedBy = "vahtkond")
	private Collection<Vahtkonna_liige> vahtkonna_liige;

	@ManyToOne
	private Vaeosa vaeosa;

	@OneToMany(mappedBy = "vahtkond")
	private Collection<Vahtkond_piiriloigul> vahtkond_piiriloigul;

	@ManyToOne
	private Piiripunkt piiripunkt;

	public Vahtkond() {
		super();
	}

	public Long getVahtkond_ID() {
		return vahtkond_ID;
	}

	public void setVahtkond_ID(Long vahtkond_ID) {
		this.vahtkond_ID = vahtkond_ID;
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

	public Collection<Vahtkonna_liige> getVahtkonna_liige() {
		return vahtkonna_liige;
	}

	public void setVahtkonna_liige(Collection<Vahtkonna_liige> param) {
		this.vahtkonna_liige = param;
	}

	public Vaeosa getVaeosa() {
		return vaeosa;
	}

	public void setVaeosa(Vaeosa param) {
		this.vaeosa = param;
	}

	public Collection<Vahtkond_piiriloigul> getVahtkond_piiriloigul() {
		return vahtkond_piiriloigul;
	}

	public void setVahtkond_piiriloigul(Collection<Vahtkond_piiriloigul> param) {
		this.vahtkond_piiriloigul = param;
	}

	public void setPiiripunkt(Piiripunkt param) {
		this.piiripunkt = param;
	}

	public Piiripunkt getPiiripunkt() {
		return piiripunkt;
	}

}
