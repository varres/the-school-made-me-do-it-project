package ee.itcollege.i377.team29.web;

import ee.itcollege.i377.team29.entities.Objekt_intsidendis;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "objekt_intsidendises", formBackingObject = Objekt_intsidendis.class)
@RequestMapping("/objekt_intsidendises")
@Controller
public class Objekt_intsidendisController {
}
