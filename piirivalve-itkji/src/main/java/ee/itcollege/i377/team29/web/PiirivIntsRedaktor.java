package ee.itcollege.i377.team29.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ee.itcollege.i377.team29.commands.UpdatePiirivalvurCommand;
import ee.itcollege.i377.team29.entities.Piirivalvur;
import ee.itcollege.i377.team29.entities.Piirivalvur_intsidendis;

@RequestMapping("/piirivintsredaktor/**")
@Controller
public class PiirivIntsRedaktor {

	private org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(getClass());
	
    @RequestMapping(method = RequestMethod.GET, value = "edit/{piirivalvurIntsidendisId}")
    public String get(@PathVariable Long piirivalvurIntsidendisId, Model uiModel) {
    	uiModel.addAttribute("piiriloikSelectedId", new Long(1));

    	if(piirivalvurIntsidendisId < 1) {
    		return "piirivintsredaktor/edit";
    	}
    	
    	Piirivalvur_intsidendis valvurIntsidendis = Piirivalvur_intsidendis.findPiirivalvur_intsidendis(piirivalvurIntsidendisId);
    	uiModel.addAttribute("valvurIntsidendis", valvurIntsidendis);
    	return "piirivintsredaktor/edit";
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "edit/{piirivalvurIntsidendisId}")
    public String put(@PathVariable Long piirivalvurIntsidendisId, Model uiModel, @Valid Piirivalvur_intsidendis valvurIntsidendis, BindingResult binding) {
    	if(valvurIntsidendis != null && valvurIntsidendis.getKirjeldus() != null && !valvurIntsidendis.getKirjeldus().trim().equals("")) { // good enuff
    		Piirivalvur_intsidendis attatched = Piirivalvur_intsidendis.findPiirivalvur_intsidendis(piirivalvurIntsidendisId);
    		attatched.setKirjeldus(valvurIntsidendis.getKirjeldus());
    		attatched.setKommentaar(valvurIntsidendis.getKommentaar());
    		attatched.merge();
    		return "redirect:/intsidentedit/edit/" + attatched.getIntsident().getIntsident_ID();
    	}
    	
    	return "redirect:/piirivintsredaktor/edit/" + piirivalvurIntsidendisId;
    }
    
    
    
    
    
    @RequestMapping(method = RequestMethod.GET, value = "/updatePiirivalvur/{piirivalvurIntsidentId}")
    public String getPiirivalvuridIntsidentides(@PathVariable Long piirivalvurIntsidentId, Model uiModel) {
    	Long intsidentID = Piirivalvur_intsidendis.findPiirivalvur_intsidendis(piirivalvurIntsidentId).getIntsident().getIntsident_ID();
    	uiModel.addAttribute("piirivalvurList", Piirivalvur.findPiirivalvurListByNotInIntsident(intsidentID));
    	uiModel.addAttribute("command", new UpdatePiirivalvurCommand());
    	
    	return "piirivintsredaktor/updatePiirivalvur";
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "/updatePiirivalvur/{piirivalvurIntsidentId}")
    public String putPiirivalvuridIntsidentides(@PathVariable Long piirivalvurIntsidentId, Model uiModel, @Valid UpdatePiirivalvurCommand command, BindingResult binding) {
    	
    	Piirivalvur_intsidendis pIntsidendis = Piirivalvur_intsidendis.findPiirivalvur_intsidendis(piirivalvurIntsidentId);
    	if(command != null && 
    			command.getPiirivalvurID() != null && 
    			command.getPiirivalvurID() > 0 && 
    			command.getPiirivalvurID() != pIntsidendis.getPiirivalvur().getPiirivalvur_ID()) // good enuff
    	{
        	pIntsidendis.setPiirivalvur(Piirivalvur.findPiirivalvur(command.getPiirivalvurID()));
        	pIntsidendis.merge();
    	}
    
    	return "redirect:/piirivintsredaktor/edit/" + piirivalvurIntsidentId;
    }
    
}
