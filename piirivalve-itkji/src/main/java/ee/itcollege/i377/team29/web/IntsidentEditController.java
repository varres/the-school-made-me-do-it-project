package ee.itcollege.i377.team29.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ee.itcollege.i377.team29.entities.Intsident;

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
}
