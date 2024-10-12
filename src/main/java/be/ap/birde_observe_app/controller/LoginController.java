package be.ap.birde_observe_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import be.ap.birde_observe_app.service.LoginService;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "redirect:/hello";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello(Model model){
        return loginService.hello(model);
    }
    

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String getLogout(){
        return "redirect:/";
    }
}
