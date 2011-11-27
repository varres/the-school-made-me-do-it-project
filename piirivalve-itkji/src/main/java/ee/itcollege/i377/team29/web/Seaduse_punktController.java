package ee.itcollege.i377.team29.web;

import ee.itcollege.i377.team29.entities.Seaduse_punkt;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "seaduse_punkts", formBackingObject = Seaduse_punkt.class)
@RequestMapping("/seaduse_punkts")
@Controller
public class Seaduse_punktController {
}
