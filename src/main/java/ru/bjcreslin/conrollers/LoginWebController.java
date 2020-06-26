package ru.bjcreslin.conrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class LoginWebController {
    @GetMapping("hello")
    public ModelAndView welcome(Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("welcome");
        mav.addObject("name", principal.getName());
        return mav;
    }
}
