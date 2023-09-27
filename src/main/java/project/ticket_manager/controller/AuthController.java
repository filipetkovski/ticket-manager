package project.ticket_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.ticket_manager.dto.RegistrationDto;
import project.ticket_manager.model.UserEntity;
import project.ticket_manager.service.UserService;

@Controller
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("register")
    public String getRegisterForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "/register";
    }

    @PostMapping("/register/save")
    public String register(@ModelAttribute("user") RegistrationDto user, BindingResult result, Model model) {
        UserEntity existedUserEmail = userService.findByEmail(user.getEmail());
        if(existedUserEmail != null && existedUserEmail.getEmail() != null && !existedUserEmail.getEmail().isEmpty()) {
            return "redirect:/register?fail";
        }

        UserEntity existedUserUsername = userService.findByUsername(user.getUsername());
        if(existedUserUsername != null && existedUserUsername.getUsername() != null && !existedUserUsername.getUsername().isEmpty()) {
            return "redirect:/register?fail";
        }

        if(result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }

        userService.saveUser(user);
        return "redirect:/ticket/create?success";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
