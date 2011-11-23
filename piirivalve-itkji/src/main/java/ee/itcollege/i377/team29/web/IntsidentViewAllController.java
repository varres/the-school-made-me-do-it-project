package ee.itcollege.i377.team29.web;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ee.itcollege.i377.team29.commands.IntsidentViewAllCommand;
import ee.itcollege.i377.team29.entities.Intsidendi_liik;
import ee.itcollege.i377.team29.entities.Intsident;
import ee.itcollege.i377.team29.entities.Piiriloik;


@RequestMapping("/intsidentviewall/**")
@Controller
public class IntsidentViewAllController {

    @PersistenceContext
    EntityManager entityManager;
    
    @RequestMapping(method = RequestMethod.GET)
    public String get(Model uiModel) {
    	uiModel.addAttribute("intsidentList", Intsident.findAllIntsidents());
    	uiModel.addAttribute("piiriloikList", Piiriloik.findAllPiiriloiks());
    	uiModel.addAttribute("intsidentViewAllCommand", new IntsidentViewAllCommand());
    	return "intsidentviewall/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String post(@Valid IntsidentViewAllCommand command, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
    	int a = 3;
    	return "intsidentviewall/index";
    }
    
    /*
     * 
     * 
     * 
    IntsidentController.create(@Valid Intsident intsident, BindingResult bindingResult, Model uiModel, 
    HttpServletRequest httpServletRequest)
    
    
    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }
    *.
    
    /*
     * 
    @RequestMapping
    public String index() {
        return "intsidentviewall/index";
    }
    */
    /*

    @RequestMapping(method = RequestMethod.GET)
    public String Intsidendi_liikController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            uiModel.addAttribute("intsidendi_liiks", Intsidendi_liik.findIntsidendi_liikEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Intsidendi_liik.countIntsidendi_liiks() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("intsidendi_liiks", Intsidendi_liik.findAllIntsidendi_liiks());
        }
        addDateTimeFormatPatterns(uiModel);
        return "intsidendi_liiks/list";
    }

	public FeedingRation() {
		
	}
	
	public FeedingRation(Cage cage, List<String[]> items) {
		this.cage = cage;
		this.items = items;
	}

    public static final EntityManager entityManager() {
        EntityManager em = new FeedingRation().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected");
        return em;
    }
	
	
	@SuppressWarnings("unchecked")
	public static FeedingRation findAllFeedingRatioss(Cage c) {
    	Query q = entityManager().createQuery(
        	    "SELECT i.name, SUM(i.amount) " +
        	    "  FROM PedoBear AS b JOIN b.menu AS m JOIN m.ingredients AS i" +
        	    "  WHERE b.cage = :cage" +
        	    "  GROUP BY i.name");
            q.setParameter("cage", c);
            return new FeedingRation(c, q.getResultList());
	}
     */
}
