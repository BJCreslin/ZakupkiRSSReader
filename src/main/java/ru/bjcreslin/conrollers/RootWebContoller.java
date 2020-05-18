package ru.bjcreslin.conrollers;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api
@Controller
@RequestMapping("/")
public class RootWebContoller {

    @GetMapping("/")
    public String getIndex() {
        return "/swagger-ui.html";
    }
}
