package ee.itcollege.i377.team29.commands;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotNull;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ee.itcollege.i377.team29.entities.Piiriloik;

public class FilterPiiriloikCommand {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date begin;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date end;
	Long piiriloikSelectedId;
	
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
	public Long getPiiriloikSelectedId() {
		return piiriloikSelectedId;
	}
	public void setPiiriloikSelectedId(Long piiriloikSelectedId) {
		this.piiriloikSelectedId = piiriloikSelectedId;
	}
	
}
