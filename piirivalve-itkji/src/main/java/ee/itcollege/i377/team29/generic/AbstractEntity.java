package ee.itcollege.i377.team29.generic;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;

@MappedSuperclass
public abstract class AbstractEntity {
	
	protected static org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(AbstractEntity.class);
	
	@Size(min = 0, max = 255)
	private String kommentaar;
	
	@NotNull
	@Size(min = 0, max = 32)
	private String avaja;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date avatud;
	
	@Size(min = 0, max = 32)
	private String sulgeja;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date suletud;
	
	@NotNull
	@Size(min = 0, max = 32)
	private String muutja;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date muudetud;
	
    @PrePersist
    public void recordCreated() {
    	Date currentDate = new Date();
        setAvatud(currentDate);
        setMuudetud(currentDate);
        
        String author = SecurityContextHolder.getContext().getAuthentication().getName();
        setAvaja(author);
        setMuutja(author);
        
        setSuletud(Common.getSurrogateDate());
    }

    @PreUpdate
    public void recordModified() {
        setMuudetud(new Date());
        setMuutja(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @PreRemove
    public void preventRemove() {
        throw new SecurityException("True removal of entities is not permitted!");
    }
    
	public String getKommentaar() {
		return kommentaar;
	}
	
	public void setKommentaar(String kommentaar) {
		this.kommentaar = kommentaar;
	}
	
	public String getAvaja() {
		return avaja;
	}

	public void setAvaja(String avaja) {
		this.avaja = avaja;
	}

	public Date getAvatud() {
		return avatud;
	}

	public void setAvatud(Date avatud) {
		this.avatud = avatud;
	}

	public String getSulgeja() {
		return sulgeja;
	}

	public void setSulgeja(String sulgeja) {
		this.sulgeja = sulgeja;
	}

	public Date getSuletud() {
		return suletud;
	}

	public void setSuletud(Date suletud) {
		this.suletud = suletud;
	}

	public String getMuutja() {
		return muutja;
	}

	public void setMuutja(String muutja) {
		this.muutja = muutja;
	}

	public Date getMuudetud() {
		return muudetud;
	}

	public void setMuudetud(Date muudetud) {
		this.muudetud = muudetud;
	}
}
