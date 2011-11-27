package ee.itcollege.i377.team29.web;

import ee.itcollege.i377.team29.entities.Vahtkond_intsidendis;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "vahtkond_intsidendises", formBackingObject = Vahtkond_intsidendis.class)
@RequestMapping("/vahtkond_intsidendises")
@Controller
public class Vahtkond_intsidendisController {
}
