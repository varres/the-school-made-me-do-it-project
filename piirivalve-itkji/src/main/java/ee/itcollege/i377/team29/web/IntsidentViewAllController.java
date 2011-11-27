package ee.itcollege.i377.team29.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ee.itcollege.i377.team29.commands.intsidentviewall.FilterPiiriloikCommand;
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
		
		Common.ADD_TEST_DATA_IF_FIRST_RUN(); // REMOVE IN "PRODUCTION" 
		
    	if(command != null && command.getPiiriloikSelectedId() != null) {
    		
    		boolean hasErrors = binding.hasErrors();
    		
    		_log.debug("Has errors: " + hasErrors);
    		_log.debug("id: " + command.getPiiriloikSelectedId());
    		_log.debug("begin: " + command.getBegin());
    		_log.debug("end: " + command.getEnd());
    		
    		List<PiirivalvurIntsidentsTuple> tuple = 
    				Common.groupByPiirivalvur(Intsident.findAllIntsidents(
    						command.getPiiriloikSelectedId(), 
    						command.getBegin(), 
    						command.getEnd()));
    		
    		uiModel.addAttribute("intsidentList", tuple);
    	}

    	return "intsidentviewall/index";
    }
	
	/*
    @RequestMapping(method = RequestMethod.GET)
    public String get(Model uiModel, @RequestParam(value = "piiriloikSelectedId", required = false) String piiriloikSelectedId, @RequestParam(value = "begin", required = false) String begin,  @RequestParam(value = "end", required = false) String end) {
    	uiModel.addAttribute("intsidentViewAllCommand", new FilterPiiriloikCommand());
    	_log.debug("Begin :: " + begin);
    	_log.debug("End :: " + end);
    	_log.debug("piiriloikSelectedId :: " + piiriloikSelectedId);
    	
    	if(Common.isLong(piiriloikSelectedId)) {
    		Long piiriloikId = Long.parseLong(piiriloikSelectedId);
    		
    		List<Intsident> intsidents = Intsident.findAllIntsidents(piiriloikId, Common.parsePersistenceDate(begin), Common.parsePersistenceDate(end));
    		_log.debug("Found intsidents: " + intsidents);
    		
    		uiModel.addAttribute("intsidentList", intsidents);
    	}
    	
    	return "intsidentviewall/index";
    }*/
    
    //@RequestMapping(method = RequestMethod.POST)
    //public String post(@Valid FilterPiiriloikCommand command, )

    /*
    @RequestMapping(value = "/index/{intsident_ID}", method = RequestMethod.GET)
    public String post(@Valid FilterPiiriloikCommand command,@PathVariable("intsident_ID") Long intsident_ID, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
    	_log.debug("Command :: Begin " + command.getBegin());
    	_log.debug("Command :: End " + command.getEnd());
    	_log.debug("Command :: PiiriloikIdSelected " + command.getPiiriloikSelectedId());
    	
    	if(bindingResult.hasErrors()) {
    		uiModel.addAttribute("intsidentViewAllCommand", command);
    		return "intsidentviewall/index";
    	} else {
    		return getFilteredUrl(command.getPiiriloikSelectedId(), command.getBegin(), command.getEnd());
    	}
    	
    	
    }
    */
    
    
    /*
    private String getFilteredUrl(Long piiriloikId, Date begin, Date end) {
		SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyy-MM-dd");

		StringBuilder sb = new StringBuilder(300);
		sb.append("intsidentviewall/index?piiriloik_id=");
		sb.append(piiriloikId);
		sb.append("&begin=");
		if(begin != null) {
			sb.append(yyyymmdd.format(begin));
		}
		sb.append("&end=");
		if(end != null) {
			sb.append(yyyymmdd.format(end));
		}
		
		return sb.toString();
    }
    */
    /*
     *     @RequestMapping(value = "/{intsident_ID}", method = RequestMethod.DELETE)
    public String IntsidentController.delete(@PathVariable("intsident_ID") Long intsident_ID, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Intsident.findIntsident(intsident_ID).remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/intsidents";
    }
     *
     * 
     *     @RequestMapping(params = "form", method = RequestMethod.GET)
    public String IntsidentController.createForm(Model uiModel) {
        uiModel.addAttribute("intsident", new Intsident());
        addDateTimeFormatPatterns(uiModel);
        List dependencies = new ArrayList();
        if (Intsidendi_liik.countIntsidendi_liiks() == 0) {
            dependencies.add(new String[]{"intsidendi_liik", "intsidendi_liiks"});
        }
        uiModel.addAttribute("dependencies", dependencies);
        return "intsidents/create";
    }
    
    @RequestMapping(value = "/{intsident_ID}", method = RequestMethod.GET)
    public String IntsidentController.show(@PathVariable("intsident_ID") Long intsident_ID, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("intsident", Intsident.findIntsident(intsident_ID));
        uiModel.addAttribute("itemId", intsident_ID);
        return "intsidents/show";
    }
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
