package ee.itcollege.i377.team29.generic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ee.itcollege.i377.team29.entities.Intsidendi_liik;
import ee.itcollege.i377.team29.entities.Intsident;
import ee.itcollege.i377.team29.entities.Objekt;
import ee.itcollege.i377.team29.entities.Objekt_intsidendis;
import ee.itcollege.i377.team29.entities.Objekti_liik;
import ee.itcollege.i377.team29.entities.Piiriloik;
import ee.itcollege.i377.team29.entities.Piiririkkuja;
import ee.itcollege.i377.team29.entities.Piirivalvur;
import ee.itcollege.i377.team29.entities.Piirivalvur_intsidendis;
import ee.itcollege.i377.team29.entities.Riigi_admin_yksus;
import ee.itcollege.i377.team29.entities.Riigi_admin_yksuse_liik;
import ee.itcollege.i377.team29.entities.Vaeosa;
import ee.itcollege.i377.team29.entities.Vahtkond;
import ee.itcollege.i377.team29.entities.Vahtkond_intsidendis;

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
		
		TEST_DATA1();
		
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
	
	public static void TEST_DATA1() {
		Intsidendi_liik intsLiik = new Intsidendi_liik();
		intsLiik.setKood("il007");
		intsLiik.setNimetus("Liiklusõnnetus");
		intsLiik = intsLiik.merge();
		
		Piiriloik piiriloik = new Piiriloik();
		piiriloik.setGPS_koordinaadid("DUMMY_DATA");
		piiriloik.setNimetus("Narva");
		piiriloik = piiriloik.merge();
		
		Piiriloik piiriloik2 = new Piiriloik();
		piiriloik2.setGPS_koordinaadid("DUMMY_DATA");
		piiriloik2.setNimetus("V6ru");
		piiriloik2 = piiriloik2.merge();
		
		Intsident ints = new Intsident();
		ints.setPiiriloik(piiriloik);
		ints.setIntsidendi_liik(intsLiik);
		ints.setKood("ints007");
		ints.setNimetus("P6der sai kylgpeegliga vastu vahtimist");
		ints = ints.merge();
		
		Piirivalvur valvur = new Piirivalvur();
		valvur.setIsikukood("38003124123");
		valvur.setEesnimed("Marilii Mihkel");
		valvur.setPerekonnanimi("V2rdjas");
		valvur.setSugu('M');
		valvur.setSoduri_kood("SK00721csa2rd");
		valvur = valvur.merge();
		
		Piirivalvur valvur2 = new Piirivalvur();
		valvur2.setIsikukood("42003124123");
		valvur2.setEesnimed("Apelsin Mandarin");
		valvur2.setPerekonnanimi("Kokteil");
		valvur2.setSugu('N');
		valvur2.setSoduri_kood("SK99921csa2rd");
		valvur2 = valvur2.merge();
		
		Piirivalvur_intsidendis pIntsidendis2 = new Piirivalvur_intsidendis();
		pIntsidendis2.setPiirivalvur(valvur2);
		pIntsidendis2.setIntsident(ints);
		pIntsidendis2.setKirjeldus("Aitasin laipa peita");
		pIntsidendis2 = pIntsidendis2.merge();
		
		Piirivalvur_intsidendis pIntsidendis = new Piirivalvur_intsidendis();
		pIntsidendis.setPiirivalvur(valvur);
		pIntsidendis.setIntsident(ints);
		pIntsidendis.setKirjeldus("Mingi loll p6der jäi mulle ette. Peitsin laiba ära, kustutasin s6idudata, panin bemmile uued kummid, pühkisin sõrmejäljed, põletasin riided/jalatsid, hankisin alibi ning viisin auto pesulasse. Sellega on kõik OK!");
		pIntsidendis = pIntsidendis.merge();
		
		Objekti_liik oLiik = new Objekti_liik();
		oLiik.setNimetus("Mootors6iduk");
		oLiik.setKood("xoliik1992214");
		oLiik = oLiik.merge();
		
		Objekt obj = new Objekt();
		obj.setNimetus("Bemm");
		obj.setObjekti_liik(oLiik);
		obj = obj.merge();
		
		Objekt_intsidendis oInts = new Objekt_intsidendis();
		oInts.setObjekt(obj);
		oInts.setIntsident(ints);
		oInts = oInts.merge();
		
		Piiririkkuja rikkuja = new Piiririkkuja();
		rikkuja.setIsikukood("3900111231212");
		rikkuja.setEesnimi("P6der");
		rikkuja.setPerek_nimi("Potsataja");
		rikkuja.setSugu('M');
		rikkuja.setObjekt(obj);
		rikkuja = rikkuja.merge();
		
		
		Riigi_admin_yksuse_liik adLiik = new Riigi_admin_yksuse_liik();
		adLiik.setKood("adas132");
		adLiik.setNimetus("TAIPOHH!");
		adLiik = adLiik.merge();
		
		Riigi_admin_yksus adYks = new Riigi_admin_yksus();
		adYks.setKood("adm123");
		adYks.setNimetus("lumivalgekesed");
		adYks.setKommentaar("V2rvid on ilusad");
		adYks.setRiigi_admin_yksuse_liik(adLiik);
		adYks = adYks.merge();
		
		Vaeosa osa = new Vaeosa();
		osa.setKood("x321vaeosa2");
		osa.setNimetus("Karvaste v2eosa");
		osa.setRiigi_admin_yksus(adYks);
		osa = osa.merge();
		
		
		Vahtkond vahtkond = new Vahtkond();
		vahtkond.setPiiripunkt(null);
		vahtkond.setKood("SKO412vahtk41");
		vahtkond.setNimetus("Karvased ja pehmed");
		vahtkond.setVaeosa(osa);
		vahtkond = vahtkond.merge();
		
		Vahtkond_intsidendis vIntsis = new Vahtkond_intsidendis();
		vIntsis.setIntsident(ints);
		vIntsis.setVahtkond(vahtkond);
		vIntsis.setKirjeldus("LOLOLOLOLOL");
		vIntsis = vIntsis.merge();
	}
}
