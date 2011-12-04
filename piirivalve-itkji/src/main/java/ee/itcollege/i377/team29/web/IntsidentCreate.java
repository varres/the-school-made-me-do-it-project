package ee.itcollege.i377.team29.web;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ee.itcollege.i377.team29.entities.Intsidendi_liik;
import ee.itcollege.i377.team29.entities.Intsident;
import ee.itcollege.i377.team29.entities.Piiriloik;

@RequestMapping("/intsidentcreate/**")
@Controller
public class IntsidentCreate {
    
	private org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(getClass());
	
    @ModelAttribute(value = "piiriloikList")
    public Collection<Piiriloik> getPiiriloikList() {
    	return Piiriloik.findAllPiiriloiks();
    }
    
    @ModelAttribute(value = "intsidendiLiikList")
    public Collection<Intsidendi_liik> getIntsidentLiikList() {
    	return Intsidendi_liik.findAllIntsidendi_liiks();
    }
	
    @RequestMapping(method = RequestMethod.GET)
    public String get(Model uiModel) {
    	Intsident ints = new Intsident();

    	// Et valideeritavust mitte 2ra solkida, v2ljad not nullid ning autom. sisestatakse neid alles talletamise faasi eel
    	ints.setAvaja("dummy");
    	ints.setAvatud(new Date());
    	ints.setSuletud(new Date());
    	ints.setMuudetud(new Date());
    	ints.setMuutja("dummy");
    	
    	uiModel.addAttribute("intsident", ints);
    	
    	return "intsidentcreate/create";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String post(ModelMap modelMap, @Valid Intsident intsident, BindingResult binding) {
    	
    	if(binding.hasErrors()) {
    		return "intsidentcreate/create";
    	}
    	
    	try {
    		Intsident ints = new Intsident();
    		ints.setKirjeldus(intsident.getKirjeldus());
    		ints.setToimumise_algus(intsident.getToimumise_algus());
    		ints.setToimumise_lopp(intsident.getToimumise_lopp());
    		ints.setNimetus(intsident.getNimetus());
    		ints.setKood(intsident.getKood());
    		ints.setPiiriloik(Piiriloik.findPiiriloik(intsident.getPiiriloik().getPiiriloik_ID()));
    		ints.setIntsidendi_liik(Intsidendi_liik.findIntsidendi_liik(intsident.getIntsidendi_liik().getIntsidendi_liik_ID()));
    		ints.setKommentaar(intsident.getKommentaar());
    		ints.setGPS_latituud(intsident.getGPS_latituud());
    		ints.setGPS_longituud(intsident.getGPS_longituud());
    		
    		ints = ints.merge();
    		
    		return "redirect:/intsidentedit/edit/" + ints.getIntsident_ID(); 
    	} catch(Throwable t) {
    		_log.error("Fakk !", t);
    		return "intsidentcreate/create";
    	}
    }

    @RequestMapping
    public String create() {
        return "intsidentcreate/create";
    }
}
