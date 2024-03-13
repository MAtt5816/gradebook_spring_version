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
import pai.final_project.enums.UserRoles;

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

//    @GetMapping("/profile")
//    public String profilePage(Model m, Principal principal) {
////dodanie do modelu aktualnie zalogowanego użytkownika:
//        m.addAttribute("user", dao.findByLogin(principal.getName()));
////zwrócenie nazwy widoku profilu użytkownika - profile.html
//        return "profile";
//    }
//
//    @GetMapping("/users")
//    public String getAllUsers(Model m, Principal principal){
//        //definicja metody, która zwróci do widoku users.html listę
////użytkowników z bd
//        m.addAttribute("usersy", dao.findAll());
//        return "users";
//    }
//
//    @GetMapping("/editUser")
//    public String editUser(Model m, Principal principal){
//        m.addAttribute("user", dao.findByLogin(principal.getName()));
//        return "edit";
//    }
//
//    @PostMapping("/editUser")
//    public String editUserPUT(@Valid User user, BindingResult binding){
//        if (binding.hasErrors()){
//            return "edit";
//        }
//
//        User user1 = dao.findByLogin(user.getLogin());
//        user1.setName(user.getName());
//        user1.setSurname(user.getSurname());
//        user1.setPassword(passwordEncoder.encode(user.getPassword()));
//        dao.save(user1);
//        return "redirect:/profile";
//    }
//
//    @GetMapping("/deleteUser")
//    public String deleteUser(Model m, Principal principal){
//        m.addAttribute("user", dao.findByLogin(principal.getName()));
//        return "delete";
//    }
//
//    @PostMapping("deleteUser")
//    public String deleteUserDELETE(@ModelAttribute User user){
//        User user1 = dao.findByLogin(user.getLogin());
//        dao.delete(user1);
//        return "redirect:/logout";
//    }
}