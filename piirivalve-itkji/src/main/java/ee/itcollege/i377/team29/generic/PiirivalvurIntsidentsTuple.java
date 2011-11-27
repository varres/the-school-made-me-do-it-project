package ee.itcollege.i377.team29.generic;

import java.util.List;

import ee.itcollege.i377.team29.entities.Intsident;
import ee.itcollege.i377.team29.entities.Piirivalvur;


public class PiirivalvurIntsidentsTuple {
	private Piirivalvur piirivalvur;
	private List<Intsident> intsidents;
	
	public Piirivalvur getPiirivalvur() {
		return piirivalvur;
	}
	public void setPiirivalvur(Piirivalvur piirivalvur) {
		this.piirivalvur = piirivalvur;
	}
	public List<Intsident> getIntsidents() {
		return intsidents;
	}
	public void setIntsidents(List<Intsident> intsidents) {
		this.intsidents = intsidents;
	}
}
