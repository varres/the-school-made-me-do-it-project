package ee.itcollege.i377.team29.commands;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import ee.itcollege.i377.team29.entities.Piiriloik;

public class IntsidentViewAllCommand {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date begin;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date end;
	Piiriloik piiriloik;
	
	public Date getBegin() {
		return begin;
	}
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public Piiriloik getPiiriloik() {
		return piiriloik;
	}
	public void setPiiriloik(Piiriloik piiriloik) {
		this.piiriloik = piiriloik;
	}
	
	
}
