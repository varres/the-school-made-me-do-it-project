// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ee.itcollege.i377.team29.web;

import ee.itcollege.i377.team29.entities.Piiriloik;
import ee.itcollege.i377.team29.entities.Vahtkond;
import ee.itcollege.i377.team29.entities.Vahtkond_piiriloigul;
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

privileged aspect Vahtkond_piiriloigulController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST)
    public String Vahtkond_piiriloigulController.create(@Valid Vahtkond_piiriloigul vahtkond_piiriloigul, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("vahtkond_piiriloigul", vahtkond_piiriloigul);
            addDateTimeFormatPatterns(uiModel);
            return "vahtkond_piiriloiguls/create";
        }
        uiModel.asMap().clear();
        vahtkond_piiriloigul.persist();
        return "redirect:/vahtkond_piiriloiguls/" + encodeUrlPathSegment(vahtkond_piiriloigul.getVahtkond_piiriloigul_ID().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String Vahtkond_piiriloigulController.createForm(Model uiModel) {
        uiModel.addAttribute("vahtkond_piiriloigul", new Vahtkond_piiriloigul());
        addDateTimeFormatPatterns(uiModel);
        List dependencies = new ArrayList();
        if (Piiriloik.countPiiriloiks() == 0) {
            dependencies.add(new String[]{"piiriloik", "piiriloiks"});
        }
        if (Vahtkond.countVahtkonds() == 0) {
            dependencies.add(new String[]{"vahtkond", "vahtkonds"});
        }
        uiModel.addAttribute("dependencies", dependencies);
        return "vahtkond_piiriloiguls/create";
    }
    
    @RequestMapping(value = "/{vahtkond_piiriloigul_ID}", method = RequestMethod.GET)
    public String Vahtkond_piiriloigulController.show(@PathVariable("vahtkond_piiriloigul_ID") Long vahtkond_piiriloigul_ID, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("vahtkond_piiriloigul", Vahtkond_piiriloigul.findVahtkond_piiriloigul(vahtkond_piiriloigul_ID));
        uiModel.addAttribute("itemId", vahtkond_piiriloigul_ID);
        return "vahtkond_piiriloiguls/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String Vahtkond_piiriloigulController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            uiModel.addAttribute("vahtkond_piiriloiguls", Vahtkond_piiriloigul.findVahtkond_piiriloigulEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Vahtkond_piiriloigul.countVahtkond_piiriloiguls() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("vahtkond_piiriloiguls", Vahtkond_piiriloigul.findAllVahtkond_piiriloiguls());
        }
        addDateTimeFormatPatterns(uiModel);
        return "vahtkond_piiriloiguls/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String Vahtkond_piiriloigulController.update(@Valid Vahtkond_piiriloigul vahtkond_piiriloigul, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("vahtkond_piiriloigul", vahtkond_piiriloigul);
            addDateTimeFormatPatterns(uiModel);
            return "vahtkond_piiriloiguls/update";
        }
        uiModel.asMap().clear();
        vahtkond_piiriloigul.merge();
        return "redirect:/vahtkond_piiriloiguls/" + encodeUrlPathSegment(vahtkond_piiriloigul.getVahtkond_piiriloigul_ID().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{vahtkond_piiriloigul_ID}", params = "form", method = RequestMethod.GET)
    public String Vahtkond_piiriloigulController.updateForm(@PathVariable("vahtkond_piiriloigul_ID") Long vahtkond_piiriloigul_ID, Model uiModel) {
        uiModel.addAttribute("vahtkond_piiriloigul", Vahtkond_piiriloigul.findVahtkond_piiriloigul(vahtkond_piiriloigul_ID));
        addDateTimeFormatPatterns(uiModel);
        return "vahtkond_piiriloiguls/update";
    }
    
    @RequestMapping(value = "/{vahtkond_piiriloigul_ID}", method = RequestMethod.DELETE)
    public String Vahtkond_piiriloigulController.delete(@PathVariable("vahtkond_piiriloigul_ID") Long vahtkond_piiriloigul_ID, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Vahtkond_piiriloigul.findVahtkond_piiriloigul(vahtkond_piiriloigul_ID).remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/vahtkond_piiriloiguls";
    }
    
    @ModelAttribute("piiriloiks")
    public Collection<Piiriloik> Vahtkond_piiriloigulController.populatePiiriloiks() {
        return Piiriloik.findAllPiiriloiks();
    }
    
    @ModelAttribute("vahtkonds")
    public Collection<Vahtkond> Vahtkond_piiriloigulController.populateVahtkonds() {
        return Vahtkond.findAllVahtkonds();
    }
    
    @ModelAttribute("vahtkond_piiriloiguls")
    public Collection<Vahtkond_piiriloigul> Vahtkond_piiriloigulController.populateVahtkond_piiriloiguls() {
        return Vahtkond_piiriloigul.findAllVahtkond_piiriloiguls();
    }
    
    void Vahtkond_piiriloigulController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("vahtkond_piiriloigul_avatud_date_format", "yyyy-MM-dd");
        uiModel.addAttribute("vahtkond_piiriloigul_suletud_date_format", "yyyy-MM-dd");
        uiModel.addAttribute("vahtkond_piiriloigul_muudetud_date_format", "yyyy-MM-dd");
        uiModel.addAttribute("vahtkond_piiriloigul_alates_date_format", "yyyy-MM-dd");
        uiModel.addAttribute("vahtkond_piiriloigul_kuni_date_format", "yyyy-MM-dd");
    }
    
    String Vahtkond_piiriloigulController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
