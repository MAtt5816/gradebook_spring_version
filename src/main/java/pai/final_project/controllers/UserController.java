package pai.final_project.controllers;
import pai.final_project.dao.UserDao;
import pai.final_project.entity.User;
import java.security.Principal;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDao dao;
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @GetMapping("/register")
    public String registerPage(Model m) {
        m.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/register")
    public String registerPagePOST(@Valid User user, BindingResult binding) {
        if (binding.hasErrors()){
            return "register";
        }
        User user1 = dao.findByLogin(user.getLogin());
        if (user1 == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            dao.save(user);
        }
        else{
            return "register";
        }
        return "redirect:/login";
    }
}