package be.ap.birde_observe_app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import be.ap.birde_observe_app.model.Role;
import be.ap.birde_observe_app.model.User;
import be.ap.birde_observe_app.service.IUserManagement;

@Controller
@RequestMapping("/user")
public class UserManagementController {
    @Autowired
    private IUserManagement userManagement;

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/listUser")
    public String getUsers(Model model) {
        List<User> users = userManagement.getAllUser();

        model.addAttribute("title", "Users");
        model.addAttribute("users", users);
        return "listUser";
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/newUser")
    public String getNewUser(Model model) {
        model.addAttribute("title", "New user");
        return "newUser";
    }

    @PostMapping("/newUser")
    public String postNewUser(
        @RequestParam("username") String username,
        @RequestParam("password") String password, 
        @RequestParam("role") String role, 
        Model model
    ) {
        User user = new User();
        Role role1 = new Role();
        List<Role> list = new ArrayList<>();
        role1.setName("ROLE_" + role.toLowerCase());
        list.add(role1);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // String pws = encoder.encode(password);
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpried(true);
        user.setEnabled(true);
        user.setRoles(list);

        boolean existsUser = userManagement.existsUserByNameAndRoller(username, role);
        
        if (existsUser) {
            return "redirect:/user/newUser";
        } else {
            userManagement.saveUser(user);
            return "redirect:/user/listUser";
        }
               
    }
    
}
