package be.ap.birde_observe_app.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class LoginService {
    public String hello(Model model) {
        model.addAttribute("title", "Hello");
        return "hello";
    }

    
}
