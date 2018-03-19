package hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SampleController {

    @RequestMapping(value = "/sample", method = RequestMethod.GET)
    public String sample(Model model) {
        model.addAttribute("message", "It's import message");
        return "sample";
    }
}
