package ee.itcollege.i377.team29.generic;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;

@MappedSuperclass
public abstract class HistoricalEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
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

        Calendar surrogate = Calendar.getInstance();
        surrogate.set(9999, Calendar.JANUARY, 1);
        setKuni(surrogate.getTime());
        
        super.recordCreated();
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
