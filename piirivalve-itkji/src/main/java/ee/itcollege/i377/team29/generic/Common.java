package ee.itcollege.i377.team29.generic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import ee.itcollege.i377.team29.entities.Piirivalvuri_seadus_intsidendi;
import ee.itcollege.i377.team29.entities.Riigi_admin_yksus;
import ee.itcollege.i377.team29.entities.Riigi_admin_yksuse_liik;
import ee.itcollege.i377.team29.entities.Seadus;
import ee.itcollege.i377.team29.entities.Seaduse_punkt;
import ee.itcollege.i377.team29.entities.Vaeosa;
import ee.itcollege.i377.team29.entities.Vahtkond;
import ee.itcollege.i377.team29.entities.Vahtkond_intsidendis;

public class Common {
	private static final SimpleDateFormat _yyyymmdd = new SimpleDateFormat("yyyy-MM-dd");
	
	protected static org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(Common.class);
	
	public static void ADD_TEST_DATA_IF_FIRST_RUN() {
		
		if(Intsidendi_liik.findIntsidendi_liik(new Long(1)) != null) { // too drunk, fix later
			return;
		}
		_log.error(" ** First run. Adding test data. Remove this method call in '''production''' ** ");
		
		TEST_DATA1();
		
	}
	
	/**
	 * Returns a tuple list of each piirivalvur being associated
	 * to a given number of Intsidents.
	 * 
	 * @param intsidents Intsidents to group by Piirivalvur
	 * @return
	 */
	public static List<PiirivalvurIntsidentsTuple> groupByPiirivalvur(List<Intsident> intsidents) {
		List<PiirivalvurIntsidentsTuple> tupleList = new ArrayList<PiirivalvurIntsidentsTuple>();
		
		for(Intsident i : intsidents) {
			addIntsidentByPiirivalvur(tupleList, i);
		}
		
		return tupleList;
	}
	
	/**
	 * Chooses correct association logic for the new Intsident and existing
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
		pIntsidendis.setKirjeldus("Mingi loll p6der j2i mulle ette, kuid selle on k6ik OK! Kuna ma olen k6ik senised Dexteri hooajad usinalt 2ra vaadanud, siis polnud laibast lahti saamisega mingeid probleeme. P6der on Soome lahes ja seitsmes tykis.");
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
		
		Seadus seadus = new Seadus();
		seadus.setKood("seadus001");
		seadus.setNimetus("Joobes juhtimine");
		seadus.setKehtiv_alates("T2nasest");
		seadus.setKehtiv_kuni("Homseni");
		seadus.setKommentaar("Joobes juhtimine ei ole hea");
		seadus = seadus.merge();
		
		Seadus seadus2 = new Seadus();
		seadus2.setKood("seadus002");
		seadus2.setNimetus("P6drale seadus");
		seadus2.setKehtiv_alates("T2nasest");
		seadus2.setKehtiv_kuni("Homseni");
		seadus2.setKommentaar("P6drale otsa ei tohi s6ita");
		seadus2 = seadus2.merge();
		
		Seaduse_punkt punn = new Seaduse_punkt();
		punn.setParagrahv("§12.34");
		punn.setPais("lg1");
		punn.setText("lg1_text");
		punn.setKehtiv_alates("T2na");
		punn.setKehtiv_kuni("Homme");
		punn.setKommentaar("Jook ja rool");
		punn.setSeadus(seadus);
		punn.setYlem_seaduse_punkt_ID(punn);
		punn = punn.merge();
		
		Seaduse_punkt punn2 = new Seaduse_punkt();
		punn2.setParagrahv("§14.34");
		punn2.setPais("lg2");
		punn2.setText("lg2_text");
		punn2.setKehtiv_alates("T2na");
		punn2.setKehtiv_kuni("Homme");
		punn2.setKommentaar("P6der ja auto");
		punn2.setSeadus(seadus);
		punn2.setYlem_seaduse_punkt_ID(punn);
		punn2 = punn2.merge();
		
		Piirivalvuri_seadus_intsidendi psi = new Piirivalvuri_seadus_intsidendi();
		psi.setSeaduse_punkt(punn);
		psi.setPiirivalvur_intsidendis(pIntsidendis);
		psi.setKirjeldus("blabla");
		psi = psi.merge();
		
		Piirivalvuri_seadus_intsidendi psi2 = new Piirivalvuri_seadus_intsidendi();
		psi2.setSeaduse_punkt(punn2);
		psi2.setPiirivalvur_intsidendis(pIntsidendis);
		psi2.setKirjeldus("blabla2");
		psi2 = psi2.merge();
	}
}
