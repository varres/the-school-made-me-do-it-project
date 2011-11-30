package ee.itcollege.i377.team29.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.tostring.RooToString;

import ee.itcollege.i377.team29.generic.AbstractEntity;

@Entity
@RooToString
@RooEntity
public class Piirivalvur extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long piirivalvur_ID;
	@NotNull
	private String isikukood;
	@NotNull
	private String eesnimed;
	@NotNull
	private String perekonnanimi;
	@NotNull
	private Character sugu;
	@NotNull
	private String soduri_kood;

	@OneToMany(mappedBy = "piirivalvur")
	private Collection<Vahtkonna_liige> vahtkonna_liige;

	@OneToMany(mappedBy = "piirivalvur")
	private Collection<Piirivalvur_intsidendis> piirivalvur_intsidendis;
	
	public static List<Piirivalvur> findPiirivalvurListByNotInIntsident(Long intsident_ID) {
		return entityManager().createQuery("SELECT o FROM Piirivalvur o WHERE o NOT IN " +
						"(SELECT o1 FROM Piirivalvur o1 JOIN o1.piirivalvur_intsidendis pi WHERE pi.intsident=:ints)",
							Piirivalvur.class)
							.setParameter("ints", Intsident.findIntsident(intsident_ID))
							.getResultList();
    }
	
	public Long getPiirivalvur_ID() {
		return this.piirivalvur_ID;
	}

	public void setPiirivalvur_ID(Long piirivalvur_ID) {
		this.piirivalvur_ID = piirivalvur_ID;
	}

	public String getIsikukood() {
		return this.isikukood;
	}

	public void setIsikukood(String isikukood) {
		this.isikukood = isikukood;
	}

	public String getEesnimed() {
		return this.eesnimed;
	}

	public void setEesnimed(String eesnimed) {
		this.eesnimed = eesnimed;
	}

	public String getPerekonnanimi() {
		return this.perekonnanimi;
	}

	public void setPerekonnanimi(String perekonnanimi) {
		this.perekonnanimi = perekonnanimi;
	}

	public Character getSugu() {
		return this.sugu;
	}

	public void setSugu(Character sugu) {
		this.sugu = sugu;
	}

	public String getSoduri_kood() {
		return this.soduri_kood;
	}

	public void setSoduri_kood(String soduri_kood) {
		this.soduri_kood = soduri_kood;
	}

	public Collection<Vahtkonna_liige> getVahtkonna_liige() {
	    return vahtkonna_liige;
	}

	public void setVahtkonna_liige(Collection<Vahtkonna_liige> param) {
	    this.vahtkonna_liige = param;
	}

}
