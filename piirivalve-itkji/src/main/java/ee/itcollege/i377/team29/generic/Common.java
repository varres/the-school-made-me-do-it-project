package ee.itcollege.i377.team29.generic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ee.itcollege.i377.team29.entities.Intsidendi_liik;
import ee.itcollege.i377.team29.entities.Intsident;
import ee.itcollege.i377.team29.entities.Piiriloik;
import ee.itcollege.i377.team29.entities.Piirivalvur;
import ee.itcollege.i377.team29.entities.Piirivalvur_intsidendis;

public class Common {
	private static final SimpleDateFormat _yyyymmdd = new SimpleDateFormat("yyyy-MM-dd");
	
	protected static org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(Common.class);
	
	public static boolean isLong(String input) {
		try {
			Long.parseLong(input); 
		} catch(Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param possibleDate
	 * @return null if possibleDate is not a valid date
	 */
	public static Date parsePersistenceDate(String possibleDate) {
		if(possibleDate == null || possibleDate.trim().equals("")) {
			return null;
		}
		
		try {
			return _yyyymmdd.parse(possibleDate);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void ADD_TEST_DATA_IF_FIRST_RUN() {
		
		if(Intsidendi_liik.findIntsidendi_liik(new Long(1)) != null) { // sue me
			return;
		}
		_log.error(" ** First run. Adding test data. Remove this method call in production ** ");
		
		Intsidendi_liik intsLiik = new Intsidendi_liik();
		intsLiik.setKood("il007");
		intsLiik.setNimetus("Liiklusõnnetus");
		intsLiik = intsLiik.merge();
		
		Piiriloik piiriloik = new Piiriloik();
		piiriloik.setGPS_koordinaadid("DUMMY_DATA");
		piiriloik.setNimetus("Narva");
		piiriloik = piiriloik.merge();
		
		Intsident ints = new Intsident();
		ints.setPiiriloik(piiriloik);
		ints.setIntsidendi_liik(intsLiik);
		ints.setKood("ints007");
		ints.setNimetus("Põder sai külgpeegliga vastu vahtimist");
		ints = ints.merge();
		
		Piirivalvur valvur = new Piirivalvur();
		valvur.setIsikukood("38003124123");
		valvur.setEesnimed("Marilii Mihkel");
		valvur.setPerekonnanimi("V2rdjas");
		valvur.setSugu('M');
		valvur.setSoduri_kood("valv007");
		valvur = valvur.merge();
		
		Piirivalvur_intsidendis pIntsidendis = new Piirivalvur_intsidendis();
		pIntsidendis.setPiirivalvur(valvur);
		pIntsidendis.setIntsident(ints);
		pIntsidendis.setKirjeldus("Mingi loll põder jäi mulle ette. Peitsin laiba ära, kustutasin GPS seadme ajaloo, pühkisin sõrmejäljed, põletasin riided/jalatsid, hankisin valetunnistajad kes antud ajaperioodil mu asukohta kinnitavad ning viisin auto pesulasse. Sellega on kõik OK!");
		pIntsidendis = pIntsidendis.merge();
		
		
	}
	
	public static List<PiirivalvurIntsidentsTuple> groupByPiirivalvur(List<Intsident> intsidents) {
		List<PiirivalvurIntsidentsTuple> tupleList = new ArrayList<PiirivalvurIntsidentsTuple>();
		
		for(Intsident i : intsidents) {
			addIntsidentByPiirivalvur(tupleList, i);
		}
		
		return tupleList;
	}
	
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
	
	private static void insertIntsToExistingPiirivalvur(List<PiirivalvurIntsidentsTuple> tupleList, Intsident newIntsident, Piirivalvur existingValvur) {
		for(PiirivalvurIntsidentsTuple tuple : tupleList) {
			if(tuple.getPiirivalvur() == existingValvur) {
				tuple.getIntsidents().add(newIntsident);
			}
		}
	}
	
	private static void insertNewPiirIntsTuple(List<PiirivalvurIntsidentsTuple> tupleList, Intsident i, Piirivalvur v) {
		PiirivalvurIntsidentsTuple newTuple = new PiirivalvurIntsidentsTuple();
		List<Intsident> intsidentList = new ArrayList<Intsident>();
		intsidentList.add(i);
		
		newTuple.setPiirivalvur(v);
		newTuple.setIntsidents(intsidentList);
		
		tupleList.add(newTuple);
	}
	
	private static List<Piirivalvur> extractValvurid(Intsident i) {
		List<Piirivalvur> v2rdjad = new ArrayList<Piirivalvur>();
		for(Piirivalvur_intsidendis valvur : Piirivalvur_intsidendis.findAllPiirivalvurIntsidendis(i)) {
			v2rdjad.add(valvur.getPiirivalvur());
		}
		return v2rdjad;
	}
	
	private static List<Piirivalvur> extractValvurid(List<PiirivalvurIntsidentsTuple> tupleList) {
		List<Piirivalvur> v2rdjad = new ArrayList<Piirivalvur>();
		for(PiirivalvurIntsidentsTuple valvur : tupleList) {
			v2rdjad.add(valvur.getPiirivalvur());
		}
		return v2rdjad;
	}

	public static String getPersistenceFormattedDate(Date date) {
		return _yyyymmdd.format(date);
	}
	
}
