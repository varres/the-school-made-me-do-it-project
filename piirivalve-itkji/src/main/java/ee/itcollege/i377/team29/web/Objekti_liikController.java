package ee.itcollege.i377.team29.web;

import ee.itcollege.i377.team29.entities.Objekti_liik;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "objekti_liiks", formBackingObject = Objekti_liik.class)
@RequestMapping("/objekti_liiks")
@Controller
public class Objekti_liikController {
}
