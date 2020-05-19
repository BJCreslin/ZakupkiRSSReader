package ru.bjcreslin.conrollers;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Api
@Controller
@RequestMapping("/")
public class RootWebContoller {

    @GetMapping("/")
    public String getIndex() {
        return "redirect:/swagger-ui.html";
    }
}
