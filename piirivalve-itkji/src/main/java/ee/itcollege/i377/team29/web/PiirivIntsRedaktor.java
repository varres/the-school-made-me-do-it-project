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
import ee.itcollege.i377.team29.entities.Piirivalvuri_seadus_intsidendi;

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
    	
    	// boolean isWrong = binding.hasErrors();
    	// Entity refide alleshoidmisega jama. Isegi kui pathid refidele talletada view-s l2bi n2htamatute fieldide,
    	// saadakse tagasi (vist) roo toString() tulem refereeritud entity asemel ja vastav attribuut s2testatakse nulliks. 
    	
    	if(valvurIntsidendis != null && valvurIntsidendis.getKirjeldus() != null && !valvurIntsidendis.getKirjeldus().trim().equals("")) { // good enuff
    		boolean isUpdate = true;
    		Piirivalvur_intsidendis newValvurIntsidendis = (Piirivalvur_intsidendis) valvurIntsidendis.updateDeleteHistoricalEntity(isUpdate);
    		mapSeadusEntities(newValvurIntsidendis);
    		return "redirect:/intsidentedit/edit/" + newValvurIntsidendis.getIntsident().getIntsident_ID();
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
    		boolean isUpdate = true;
    		Piirivalvur_intsidendis pi = (Piirivalvur_intsidendis) pIntsidendis.updateDeleteHistoricalEntity(isUpdate);
    		
    		mapSeadusEntities(pi);
    		
    		piirivalvurIntsidentId = pi.getIdHistoricalWrapper();
    		
    		
    	}
    	
    	return "redirect:/piirivintsredaktor/edit/" + piirivalvurIntsidentId;
    }
    
    /**
     * See ebaloogiline loogika mapib (jah, kontrolleri meetodi sees isegi) uue piirivalvur_intsidendi seadustega.
     * Vist peaks ka uued seaduse jmt olemid tegema kuid aus6na - lihtsalt ei ole jaksu enam jamada.
     */
    private void mapSeadusEntities(Piirivalvur_intsidendis pi) {
		for(Piirivalvuri_seadus_intsidendi psi : pi.getPiirivalvuri_seadus_intsidendi()) {
			Piirivalvur_intsidendis oldPi = psi.getPiirivalvur_intsidendis();
			oldPi.setPiirivalvuri_seadus_intsidendi(null);
			oldPi.merge();
			
			psi.setPiirivalvur_intsidendis(pi);
			psi.merge();
		}
    }
    
}
