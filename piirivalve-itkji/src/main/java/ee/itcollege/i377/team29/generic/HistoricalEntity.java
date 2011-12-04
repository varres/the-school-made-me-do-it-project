package ee.itcollege.i377.team29.generic;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;

import ee.itcollege.i377.team29.entities.Piirivalvur_intsidendis;

@MappedSuperclass
public abstract class HistoricalEntity extends AbstractEntity {
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date alates;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date kuni;
	
    @PrePersist
    public void recordCreated() {
    	Date currentDate = new Date();
        setAlates(currentDate);
        setKuni(Common.getSurrogateDate());
        
        super.recordCreated();
    }
    
    
    /**
     * Wrapper around the obvious. Required for updateDeleteHistoricalEntity().
     * @param id
     * @return
     */
    public abstract HistoricalEntity findByIdHistoricalWrapper(Long id);
    /**
     * Wrapper around the obvious. Required for updateDeleteHistoricalEntity().
     * @param id
     * @return
     */
    public abstract HistoricalEntity persistHistoricalWrapper();
    /**
     * Wrapper around the obvious. Required for updateDeleteHistoricalEntity().
     * @param id
     * @return
     */
    public abstract HistoricalEntity mergeHistoricalWrapper();
    /**
     * Wrapper around the obvious. Required for updateDeleteHistoricalEntity().
     * @param id
     * @return
     */
    public abstract void setIdHistoricalWrapper(Long id);
    /**
     * Wrapper around the obvious. Required for updateDeleteHistoricalEntity().
     * @param id
     * @return
     */
    public abstract Long getIdHistoricalWrapper();
    /**
     * Wrapper around the obvious. Required for updateDeleteHistoricalEntity().
     * @param id
     * @return
     */
    public abstract void detatchHistoricalWrapper();
    
    /**
     * Required for deleteHistoricalEntity().
     * @param copyTo Set the values you wish to be updated. 
     * All values which are provided by user input should be copied. 
     * Values not copied are received from the last known persistant unit.
     */
    public abstract void copyUpdatedValuesHistoricalWrapper(HistoricalEntity copyTo);
    
    /**
     * This method requires the true implementation of all the abstract methods with the "HistoricalWrapper" sufix. <br />
     * Proper implementation of overrides is not required, if you don't intent to use this method.
     * 
     * @param isUpdate Do you want to persist the current, updated entity ?
     * @return Null or updated entity, if isUpdate == true.
     */
    public HistoricalEntity updateDeleteHistoricalEntity(boolean isUpdate) {
		HistoricalEntity old = findByIdHistoricalWrapper(this.getIdHistoricalWrapper());
		Date now = new Date();
		old.setSuletud(now);
		old.setSulgeja(SecurityContextHolder.getContext().getAuthentication().getName());
		old.setKuni(now);
		old.mergeHistoricalWrapper();
		
		if(!isUpdate) {
			return null;
		}
		
		old.detatchHistoricalWrapper();
		old.setIdHistoricalWrapper(null);
		old.setSulgeja(null);
		this.copyUpdatedValuesHistoricalWrapper(old);
		
		return old.persistHistoricalWrapper();
    }
	
	public Date getAlates() {
		return alates;
	}

	public void setAlates(Date alates) {
		this.alates = alates;
	}

	public Date getKuni() {
		return kuni;
	}

	public void setKuni(Date kuni) {
		this.kuni = kuni;
	}
}
