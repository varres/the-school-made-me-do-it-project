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

import ee.itcollege.i377.team29.commands.intsidentviewall.FilterPiiriloikCommand;
import ee.itcollege.i377.team29.entities.Intsident;
import ee.itcollege.i377.team29.entities.Piiriloik;

@RequestMapping("/intsidentedit/**")
@Controller
public class IntsidentEditController {

	private org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(getClass());

    @RequestMapping(method = RequestMethod.GET, value = "{intsidentId}")
    public String get(@PathVariable Long intsidentId, Model uiModel) {
    	if(intsidentId < 1) {
    		return "intsidentedit/edit";
    	}
    	
    	Intsident intsident = Intsident.findIntsident(intsidentId);
    	uiModel.addAttribute("intsident", intsident);
    	
    	return "intsidentedit/edit";
    }

    /*
    @RequestMapping
    public String edit() {
        return "intsidentedit/edit";
    }
    */
    
    /*
     *     @ModelAttribute(value = "piiriloikList")
    public Collection<Piiriloik> getPiiriloikList() {
    	return Piiriloik.findAllPiiriloiks();
    }
    
    @ModelAttribute(value = "intsidentViewAllCommand")
    public FilterPiiriloikCommand getFilterCommand() {
    	return new FilterPiiriloikCommand();
    }
	
	@RequestMapping(method = RequestMethod.GET)
    public String get(Model uiModel, @Valid FilterPiiriloikCommand command, BindingResult binding) {
     */
}
