package ee.itcollege.i377.team29.web;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    	ints.setIntsidendi_liik(new Intsidendi_liik());
    	ints.setPiiriloik(new Piiriloik());
    	
    	uiModel.addAttribute("intsident", ints);
    	
    	return "intsidentcreate/create";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String post(ModelMap modelMap, @Valid Intsident intsident, BindingResult binding) {
    	
    	// Fakk ei, ausalt ei viitsi jamada sinuga Spring - põle maha
    	// if(binding.hasErrors()) {
    		//return "intsidentcreate/create";
    	//}
    	int debug = 3;
    	if(intsident == null || 
    			intsident.getKirjeldus() == null || 
    			intsident.getKirjeldus().trim().equals("") ||
    			intsident.getNimetus() == null || 
    			intsident.getNimetus().trim().equals("") ||
    			intsident.getKood() == null || 
    			intsident.getKood().trim().equals("") ||
    			intsident.getPiiriloik().getPiiriloik_ID() == null ||
    			intsident.getPiiriloik().getPiiriloik_ID() <  1) { // good enuff 
    															   
    		return "intsidentcreate/create";
    	}
    	
    	try {
    		Intsident ints = new Intsident();
    		ints.setKirjeldus(intsident.getKirjeldus());
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
