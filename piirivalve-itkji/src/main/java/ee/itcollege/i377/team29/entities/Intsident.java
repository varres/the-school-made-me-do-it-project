package ee.itcollege.i377.team29.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.tostring.RooToString;

import ee.itcollege.i377.team29.generic.AbstractEntity;
import ee.itcollege.i377.team29.generic.Common;
import ee.itcollege.i377.team29.generic.PiirivalvurIntsidentsTuple;

@Entity
@RooToString
@RooEntity
public class Intsident extends AbstractEntity implements Serializable {

	private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(Intsident.class);
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long intsident_ID;
	@Size(min = 0, max = 20)
	@NotNull
	private String kood;
	@Size(min = 0, max = 100)
	@NotNull
	private String nimetus;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date toimumise_algus;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date toimumise_lopp;
	@Size(min = 1, max = 255)
	@NotNull
	private String kirjeldus;
	@Digits(integer=9, fraction=5)
	private BigDecimal GPS_longituud;
	@Digits(integer=9, fraction=5)
	private BigDecimal GPS_latituud;

	
	@ManyToOne
	private Piiriloik piiriloik;

	@NotNull
	@ManyToOne
	private Intsidendi_liik intsidendi_liik;

	@OneToMany(mappedBy = "intsident")
	private Collection<Objekt_intsidendis> objekt_intsidendis;

	@OneToMany(mappedBy = "intsident")
	private Collection<Isik_intsidendis> isik_intsidendis;
	
	@OneToMany(mappedBy = "intsident")
	private Collection<Vahtkond_intsidendis> vahtkond_intsidendis;
	
	@OneToMany(mappedBy = "intsident")
	private Collection<Piirivalvur_intsidendis> piirivalvur_intsidendis;


	@SuppressWarnings("unchecked")
	public static List<Intsident> findAllIntsidents(Long piiriloikId, Date begin, Date end, boolean dontSelectSuletud) {
    	Query query = findAllConstructQuery(piiriloikId, begin, end, dontSelectSuletud);
    	return query.getResultList();
    }
	

	/**
	 * Returns a tuple list of each piirivalvur being associated
	 * to a given number of Intsidents.
	 * 
	 * @param intsidents Intsidents to group by Piirivalvur
	 * @return
	 */
	public static List<PiirivalvurIntsidentsTuple> findAllGroupByPiirivalvur(List<Intsident> intsidents, boolean dontSelectSuletud) {
		List<PiirivalvurIntsidentsTuple> tupleList = new ArrayList<PiirivalvurIntsidentsTuple>();
		
		for(Intsident i : intsidents) {
			addIntsidentByPiirivalvur(tupleList, i);
		}
		
		return tupleList;
	}
	
	/**
	 * Chooses the correct association logic for the new Intsident and the existing
	 * tuples of Piirivalvur :: IntsidentList.
	 * 
	 * @param tupleList
	 * @param i
	 */
	private static void addIntsidentByPiirivalvur(List<PiirivalvurIntsidentsTuple> tupleList, Intsident i) {
		List<Piirivalvur> valvuridIntsidendist = extractValvurid(i);
		
		for(Piirivalvur valvur : valvuridIntsidendist) 
		{
			
			List<Piirivalvur> valvuridOlemas = extractValvurid(tupleList);
			if(valvuridOlemas.contains(valvur)) 
			{
				insertIntsToExistingPiirivalvur(tupleList, i, valvur);
			} 
			else 
			{
				insertNewPiirIntsTuple(tupleList, i, valvur);
			}
		}
	}
	
	/**
	 * Adds a new Intsident to the existing Intsident list, already associated with 'Piirivalvur' entity.
	 * 
	 * @param tupleList
	 * @param newIntsident
	 * @param existingValvur
	 */
	private static void insertIntsToExistingPiirivalvur(List<PiirivalvurIntsidentsTuple> tupleList, Intsident newIntsident, Piirivalvur existingValvur) {
		for(PiirivalvurIntsidentsTuple tuple : tupleList) {
			if(tuple.getPiirivalvur() == existingValvur) {
						tuple.getIntsidents().add(newIntsident);
			}
		}
	}
	
	/**
	 * Inserts a new tuple to the tuple list.
	 * 
	 * @param tupleList
	 * @param i
	 * @param v
	 */
	private static void insertNewPiirIntsTuple(List<PiirivalvurIntsidentsTuple> tupleList, Intsident i, Piirivalvur v) {

				PiirivalvurIntsidentsTuple newTuple = new PiirivalvurIntsidentsTuple();
				List<Intsident> intsidentList = new ArrayList<Intsident>();
				intsidentList.add(i);
				
				newTuple.setPiirivalvur(v);
				newTuple.setIntsidents(intsidentList);
				
				tupleList.add(newTuple);
	}
	
	/**
	 * Gets all associated PValvur entities from the Intsident entity
	 * @param i
	 * @return
	 */
	private static List<Piirivalvur> extractValvurid(Intsident i) {
		List<Piirivalvur> v2rdjad = new ArrayList<Piirivalvur>();
		for(Piirivalvur_intsidendis valvur : Piirivalvur_intsidendis.findAllPiirivalvurIntsidendis(i)) {
			v2rdjad.add(valvur.getPiirivalvur());
		}
		return v2rdjad;
	}
	
	/**
	 * Gets all associated PValvur entities from the tuplelist
	 * @param tupleList
	 * @return
	 */
	private static List<Piirivalvur> extractValvurid(List<PiirivalvurIntsidentsTuple> tupleList) {
		List<Piirivalvur> v2rdjad = new ArrayList<Piirivalvur>();
		for(PiirivalvurIntsidentsTuple valvur : tupleList) {
			v2rdjad.add(valvur.getPiirivalvur());
		}
		return v2rdjad;
	}
    
    private static Query findAllConstructQuery(Long piiriloikId, Date begin, Date end, boolean dontSelectSuletud) {
    	Query query = entityManager().createQuery(findAllConstructQueryString(piiriloikId, begin, end, dontSelectSuletud), Intsident.class);
    	if(begin != null) {
    		query.setParameter("begin", begin);
    	}
    	if(end != null) {
    		query.setParameter("end", end);
    	}
    	if(piiriloikId > 0) {
    		query.setParameter("piiriloikID", Piiriloik.findPiiriloik(piiriloikId));
    	}
    	if(dontSelectSuletud) {
    		query.setParameter("current", new Date());
    	}
    	
    	return query;
    }
    
    private static String findAllConstructQueryString(Long piiriloikId, Date begin, Date end, boolean dontSelectSuletud) {
    	boolean isWhereUsed = false;
    	StringBuilder sb = new StringBuilder(300);
    	sb.append("SELECT o FROM Intsident o");
    	
    	if(piiriloikId > 0) {
    		isWhereUsed = true;
    		sb.append(" WHERE o.piiriloik = ");
    		sb.append(":piiriloikID");
    	}
    	
    	if(begin != null) {
    		if(isWhereUsed) {
    			sb.append(" AND o.toimumise_algus >= ");
    		} else {
    			sb.append(" WHERE o.toimumise_algus >= ");
    			isWhereUsed = true;
    		}
    		sb.append(":begin");
    	}
    	
    	if(end != null) {
    		if(isWhereUsed) {
    			sb.append(" AND o.toimumise_lopp <= ");
    		} else {
    			sb.append(" WHERE o.toimumise_lopp <= ");
    		}
    		sb.append(":end");
    	}
    	
    	if(dontSelectSuletud) {
    		if(isWhereUsed) {
    			sb.append(" AND o.suletud > ");
    		} else {
    			sb.append(" WHERE o.suletud > ");
    		}
    		sb.append(":current");
    	}
    	
    	
    	_log.debug("SQL Query: " + sb.toString(), ":begin=" + begin + ":end=" + end + "id=" + piiriloikId);
    	
    	return sb.toString();
    }
	
    public Collection<Vahtkond_intsidendis> getVahtkond_intsidendis() {
		return vahtkond_intsidendis;
	}

	public void setVahtkonnad_intsidendis(
			Collection<Vahtkond_intsidendis> vahtkond_intsidendis) {
		this.vahtkond_intsidendis = vahtkond_intsidendis;
	}

	public Collection<Piirivalvur_intsidendis> getPiirivalvur_intsidendis() {
		return piirivalvur_intsidendis;
	}

	public void setPiirivalvurid_intsidendis(
			Collection<Piirivalvur_intsidendis> piirivalvur_intsidendis) {
		this.piirivalvur_intsidendis = piirivalvur_intsidendis;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Long getIntsident_ID() {
		return intsident_ID;
	}

	public void setIntsident_ID(Long intsident_ID) {
		this.intsident_ID = intsident_ID;
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

	public Date getToimumise_algus() {
		return toimumise_algus;
	}

	public void setToimumise_algus(Date toimumise_algus) {
		this.toimumise_algus = toimumise_algus;
	}

	public Date getToimumise_lopp() {
		return toimumise_lopp;
	}

	public void setToimumise_lopp(Date toimumise_lopp) {
		this.toimumise_lopp = toimumise_lopp;
	}

	public String getKirjeldus() {
		return kirjeldus;
	}

	public void setKirjeldus(String kirjeldus) {
		this.kirjeldus = kirjeldus;
	}

	public BigDecimal getGPS_longituud() {
		return GPS_longituud;
	}

	public void setGPS_longituud(BigDecimal gPS_longituud) {
		GPS_longituud = gPS_longituud;
	}

	public BigDecimal getGPS_latituud() {
		return GPS_latituud;
	}

	public void setGPS_latituud(BigDecimal gPS_latituud) {
		GPS_latituud = gPS_latituud;
	}

	public void setPiiriloik(Piiriloik param) {
		this.piiriloik = param;
	}

	public Piiriloik getPiiriloik() {
		return piiriloik;
	}

	public void setIntsidendi_liik(Intsidendi_liik param) {
		this.intsidendi_liik = param;
		
		Collection<Intsident> intsidents = param.getIntsident();
		if(intsidents == null) {
			intsidents = new ArrayList<Intsident>();
		}
		
		if(!intsidents.contains(this)) {
			intsidents.add(this);
		}
	}

	public Intsidendi_liik getIntsidendi_liik() {
		return intsidendi_liik;
	}

	public Collection<Objekt_intsidendis> getObjekt_intsidendis() {
	    return objekt_intsidendis;
	}

	public void setObjekt_intsidendis(Collection<Objekt_intsidendis> param) {
	    this.objekt_intsidendis = param;
	}

	public Collection<Isik_intsidendis> getIsik_intsidendis() {
	    return isik_intsidendis;
	}

	public void setIsik_intsidendis(Collection<Isik_intsidendis> param) {
	    this.isik_intsidendis = param;
	}
}
