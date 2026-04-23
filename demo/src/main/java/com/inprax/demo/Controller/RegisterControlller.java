package com.inprax.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inprax.demo.Entity.Login;
import com.inprax.demo.Service.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/register")
public class RegisterControlller {
    
    @Autowired
    private LoginService loginService;

    @GetMapping("/register")
    public String registro(Model model) {
        model.addAttribute("login", new Login());
        return "Index/register";
    }
    

    @PostMapping("/registro")
    public String guardarUsuario(@RequestParam String usuarioLogin, @RequestParam String  contrasenaLogin, @RequestParam String correoLogin, 
        @RequestParam String roles, Model model) {
        Login l = loginService.registrarLogin(usuarioLogin, contrasenaLogin, correoLogin, roles);
        if (l == null) {
            model.addAttribute("error", "El usuario ya existe");
            return "Index/register";
        }
        return "redirect:/login/login";
    }
    
    
}
