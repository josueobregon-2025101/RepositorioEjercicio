package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Administradores;
import com.inprax.demo.Service.AdministradoresService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/administradores")
public class AdministradoresController {

    private final AdministradoresService administradoresService;

    public AdministradoresController(AdministradoresService administradoresService) {
        this.administradoresService = administradoresService;
    }

    @GetMapping("/lista")
    public String mostrarAdministradores(Model model) {
        List<Administradores> listar = administradoresService.getAllAdministradores();
        model.addAttribute("administrador", listar);
        return "Index/administradores";
    }

    @GetMapping("/administradores")
    public String mostrarAdministrador() {
        return "Index/administradores";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdministradorById(@PathVariable @Valid Integer id) {
        Administradores administrador = administradoresService.getAdministradorById(id);
        if (administrador != null) {
            return ResponseEntity.ok(administrador);
        } else {
            return ResponseEntity.status(404).body("No existe el administrador");
        }
    }

    @PostMapping
    public ResponseEntity<?> createAdministrador(@Valid @RequestBody Administradores administrador) {
        try {
            Administradores creado = administradoresService.saveAdministrador(administrador);
            return ResponseEntity.ok().body(creado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/upgradear/{id}")
    public String updateAdministrador(@PathVariable Integer id, @Valid @RequestBody Administradores administrador) {
        try {
            Administradores actualizado = administradoresService.updateAdministrador(id, administrador);
            if (actualizado != null) {
                return "redirect:/Index/administradores";
            } else {
                return "No existe el administrador";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/eliminar/{id}")
    public String deleteAdministrador(@PathVariable @Valid Integer id) {
        Administradores administrador = administradoresService.getAdministradorById(id);
        if (administrador != null) {
            administradoresService.deleteAdministrador(id);
            return "redirect:/Index/administradores";
        } else {
            return "No existe el administrador";
        }
    }
}