package ee.itcollege.i377.team29.web;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ee.itcollege.i377.team29.commands.FilterPiiriloikCommand;
import ee.itcollege.i377.team29.entities.Intsidendi_liik;
import ee.itcollege.i377.team29.entities.Intsident;
import ee.itcollege.i377.team29.entities.Piiriloik;
import ee.itcollege.i377.team29.generic.Common;
import ee.itcollege.i377.team29.generic.PiirivalvurIntsidentsTuple;


@RequestMapping("/intsidentviewall/**")
@Controller
public class IntsidentViewAllController {

	private org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(getClass());

    @ModelAttribute(value = "piiriloikList")
    public Collection<Piiriloik> getPiiriloikList() {
    	return Piiriloik.findAllPiiriloiks();
    }
    
    @ModelAttribute(value = "intsidentViewAllCommand")
    public FilterPiiriloikCommand getFilterCommand() {
    	return new FilterPiiriloikCommand();
    }
	
	@RequestMapping(method = RequestMethod.GET)
    public String get(Model uiModel, @Valid FilterPiiriloikCommand command, BindingResult binding) {
		Common.ADD_TEST_DATA_IF_FIRST_RUN(); 
		
    	if(command != null && command.getPiiriloikSelectedId() != null) {
    		
    		_log.debug("id: " + command.getPiiriloikSelectedId());
    		_log.debug("begin: " + command.getBegin());
    		_log.debug("end: " + command.getEnd());
    		
    		List<PiirivalvurIntsidentsTuple> tuple = 
    				Intsident.findAllGroupByPiirivalvur(Intsident.findAllIntsidents(
    						command.getPiiriloikSelectedId(), 
    						command.getBegin(), 
    						command.getEnd()));
    		
    		uiModel.addAttribute("intsidentList", tuple);
    	}

    	return "intsidentviewall/index";
    }
}
