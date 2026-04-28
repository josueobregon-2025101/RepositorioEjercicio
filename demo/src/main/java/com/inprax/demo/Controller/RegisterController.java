package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Login;
import com.inprax.demo.Service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final LoginService loginService;

    public RegisterController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public String guardarUsuario(@RequestParam String usuarioLogin,
                                 @RequestParam String contrasenaLogin,
                                 @RequestParam String correoLogin,
                                 @RequestParam String roles,
                                 Model model) {
        Login l = loginService.registrarLogin(usuarioLogin, contrasenaLogin, correoLogin, roles);
        if (l == null) {
            model.addAttribute("error", "El usuario ya existe");
            return "Index/register";
        }
        return "redirect:/login";
    }
}
