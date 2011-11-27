package ee.itcollege.i377.team29.web;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ee.itcollege.i377.team29.entities.Piiriloik;
import ee.itcollege.i377.team29.entities.Piirivalvur;
import ee.itcollege.i377.team29.entities.Piirivalvur_intsidendis;

@RequestMapping("/piirivintsredaktor/**")
@Controller
public class PiirivIntsRedaktor {

	private org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(getClass());

    @ModelAttribute(value = "piirivalvurList")
    public Collection<Piirivalvur> getPiirivalvurList() {
    	return Piirivalvur.findAllPiirivalvurs();
    }
	
    @RequestMapping(method = RequestMethod.GET, value = "{piirivalvurIntsidendisId}")
    public String get(@PathVariable Long piirivalvurIntsidendisId, Model uiModel) {
    	if(piirivalvurIntsidendisId < 1) {
    		return "piirivintsredaktor/edit";
    	}
    	
    	Piirivalvur_intsidendis valvurIntsidendis = Piirivalvur_intsidendis.findPiirivalvur_intsidendis(piirivalvurIntsidendisId);
    	uiModel.addAttribute("valvurIntsidendis", valvurIntsidendis);
    	
    	return "piirivintsredaktor/edit";
    }
}
