// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ee.itcollege.i377.team29.web;

import ee.itcollege.i377.team29.entities.Intsident;
import ee.itcollege.i377.team29.entities.Piirivalvur_intsidendis;
import ee.itcollege.i377.team29.entities.Vahtkond;
import ee.itcollege.i377.team29.entities.Vahtkond_intsidendis;
import java.io.UnsupportedEncodingException;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect Vahtkond_intsidendisController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST)
    public String Vahtkond_intsidendisController.create(@Valid Vahtkond_intsidendis vahtkond_intsidendis, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("vahtkond_intsidendis", vahtkond_intsidendis);
            addDateTimeFormatPatterns(uiModel);
            return "vahtkond_intsidendises/create";
        }
        uiModel.asMap().clear();
        vahtkond_intsidendis.persist();
        return "redirect:/vahtkond_intsidendises/" + encodeUrlPathSegment(vahtkond_intsidendis.getPiirivalvur_intsidendis_ID().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String Vahtkond_intsidendisController.createForm(Model uiModel) {
        uiModel.addAttribute("vahtkond_intsidendis", new Vahtkond_intsidendis());
        addDateTimeFormatPatterns(uiModel);
        List dependencies = new ArrayList();
        if (Intsident.countIntsidents() == 0) {
            dependencies.add(new String[]{"intsident", "intsidents"});
        }
        if (Vahtkond.countVahtkonds() == 0) {
            dependencies.add(new String[]{"vahtkond", "vahtkonds"});
        }
        uiModel.addAttribute("dependencies", dependencies);
        return "vahtkond_intsidendises/create";
    }
    
    @RequestMapping(value = "/{piirivalvur_intsidendis_ID}", method = RequestMethod.GET)
    public String Vahtkond_intsidendisController.show(@PathVariable("piirivalvur_intsidendis_ID") Long piirivalvur_intsidendis_ID, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("vahtkond_intsidendis", Vahtkond_intsidendis.findVahtkond_intsidendis(piirivalvur_intsidendis_ID));
        uiModel.addAttribute("itemId", piirivalvur_intsidendis_ID);
        return "vahtkond_intsidendises/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String Vahtkond_intsidendisController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            uiModel.addAttribute("vahtkond_intsidendises", Vahtkond_intsidendis.findVahtkond_intsidendisEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Vahtkond_intsidendis.countVahtkond_intsidendises() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("vahtkond_intsidendises", Vahtkond_intsidendis.findAllVahtkond_intsidendises());
        }
        addDateTimeFormatPatterns(uiModel);
        return "vahtkond_intsidendises/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String Vahtkond_intsidendisController.update(@Valid Vahtkond_intsidendis vahtkond_intsidendis, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("vahtkond_intsidendis", vahtkond_intsidendis);
            addDateTimeFormatPatterns(uiModel);
            return "vahtkond_intsidendises/update";
        }
        uiModel.asMap().clear();
        vahtkond_intsidendis.merge();
        return "redirect:/vahtkond_intsidendises/" + encodeUrlPathSegment(vahtkond_intsidendis.getPiirivalvur_intsidendis_ID().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{piirivalvur_intsidendis_ID}", params = "form", method = RequestMethod.GET)
    public String Vahtkond_intsidendisController.updateForm(@PathVariable("piirivalvur_intsidendis_ID") Long piirivalvur_intsidendis_ID, Model uiModel) {
        uiModel.addAttribute("vahtkond_intsidendis", Vahtkond_intsidendis.findVahtkond_intsidendis(piirivalvur_intsidendis_ID));
        addDateTimeFormatPatterns(uiModel);
        return "vahtkond_intsidendises/update";
    }
    
    @RequestMapping(value = "/{piirivalvur_intsidendis_ID}", method = RequestMethod.DELETE)
    public String Vahtkond_intsidendisController.delete(@PathVariable("piirivalvur_intsidendis_ID") Long piirivalvur_intsidendis_ID, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Vahtkond_intsidendis.findVahtkond_intsidendis(piirivalvur_intsidendis_ID).remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/vahtkond_intsidendises";
    }
    
    @ModelAttribute("intsidents")
    public Collection<Intsident> Vahtkond_intsidendisController.populateIntsidents() {
        return Intsident.findAllIntsidents();
    }
    
    @ModelAttribute("piirivalvur_intsidendises")
    public Collection<Piirivalvur_intsidendis> Vahtkond_intsidendisController.populatePiirivalvur_intsidendises() {
        return Piirivalvur_intsidendis.findAllPiirivalvur_intsidendises();
    }
    
    @ModelAttribute("vahtkonds")
    public Collection<Vahtkond> Vahtkond_intsidendisController.populateVahtkonds() {
        return Vahtkond.findAllVahtkonds();
    }
    
    @ModelAttribute("vahtkond_intsidendises")
    public Collection<Vahtkond_intsidendis> Vahtkond_intsidendisController.populateVahtkond_intsidendises() {
        return Vahtkond_intsidendis.findAllVahtkond_intsidendises();
    }
    
    void Vahtkond_intsidendisController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("vahtkond_intsidendis_avatud_date_format", "yyyy-MM-dd");
        uiModel.addAttribute("vahtkond_intsidendis_suletud_date_format", "yyyy-MM-dd");
        uiModel.addAttribute("vahtkond_intsidendis_muudetud_date_format", "yyyy-MM-dd");
        uiModel.addAttribute("vahtkond_intsidendis_alates_date_format", "yyyy-MM-dd");
        uiModel.addAttribute("vahtkond_intsidendis_kuni_date_format", "yyyy-MM-dd");
    }
    
    String Vahtkond_intsidendisController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        }
        catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
