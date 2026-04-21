package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Administradores;
import com.inprax.demo.Repository.EmpresaRepository;
import com.inprax.demo.Repository.EstudiantesRepository;
import com.inprax.demo.Repository.PracticasRepository;
import com.inprax.demo.Service.AdministradoresService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdministradoresController {

    private final AdministradoresService administradoresService;

    public AdministradoresController(AdministradoresService administradoresService) {
        this.administradoresService = administradoresService;
    }

    @Autowired
    public EstudiantesRepository estudiantesRepo;

    @Autowired
    EmpresaRepository empresasRepo;

    @Autowired
    public PracticasRepository practicasRepo;

    @GetMapping("/admin")
    public String dashboard(Model model) {
        model.addAttribute("totalEstudiantes", estudiantesRepo.count());
        model.addAttribute("totalEmpresas", empresasRepo.count());
        model.addAttribute("totalPracticas", practicasRepo.count());
        return "Index/dashboard-admin";
    }

    @GetMapping
    public List<Administradores> getAllAdministradores() {
        return administradoresService.getAllAdministradores();
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

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdministrador(@PathVariable Integer id, @Valid @RequestBody Administradores administrador) {
        try {
            Administradores actualizado = administradoresService.updateAdministrador(id, administrador);
            if (actualizado != null) {
                return ResponseEntity.ok(actualizado);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdministrador(@PathVariable @Valid Integer id) {
        Administradores administrador = administradoresService.getAdministradorById(id);
        if (administrador != null) {
            administradoresService.deleteAdministrador(id);
            return ResponseEntity.ok().body("Se elimino el administrador");
        } else {
            return ResponseEntity.status(404).body("No existe el administrador");
        }
    }

    
}
