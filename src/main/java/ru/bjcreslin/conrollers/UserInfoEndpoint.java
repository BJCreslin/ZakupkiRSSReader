package ru.bjcreslin.conrollers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserInfoEndpoint {
//    @GetMapping("/hello")
//    public ModelAndView welcome(Principal principal) {
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("welcome");
//        mav.addObject("name", principal.getName());
//        return mav;
//    }

    @PostMapping("/userinfo")
    public Map<String, Object> user() {
        Map<String, Object> map = new HashMap<>();
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        map.put("user_name", name);
        return map;
    }

    @RequestMapping("/user")
    @ResponseBody
    public Principal user(Principal user) {
        return user;
    }

}
