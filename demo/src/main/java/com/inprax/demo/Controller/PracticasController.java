package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Practicas;
import com.inprax.demo.Service.PracticasService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/Practicas")
public class PracticasController {

    private final PracticasService practicasService;

    public PracticasController(PracticasService practicasService) {
        this.practicasService = practicasService;
    }

    @GetMapping
    public List<Practicas> getAllPracticas(){
        return practicasService.getAllPracticas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getIdPracticas(@PathVariable Integer id){
        try{
            Practicas practicas = practicasService.getIdPracticas(id);
            return ResponseEntity.ok(practicas);
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/practicas")
    public String practicas(Model model){
        List<Practicas> practicas = practicasService.getAllPracticas();
        model.addAttribute("practicas", practicas);
        return "Index/practicas";
    }


    @GetMapping("/practicas-admin")
    public String mostrarPracticas(Model model) {
        List<Practicas> practicas = practicasService.getAllPracticas();
        model.addAttribute("practicas", practicas);
        return "Index/practicas-admin";
    }


    @PostMapping
    public ResponseEntity<Object> createPracticas(@Valid @RequestBody Practicas practicas){
        try{
            Practicas createPracticas = practicasService.savePracticas(practicas);
            return new ResponseEntity<>(createPracticas,HttpStatus.CREATED);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePracticas(@Valid @RequestBody Practicas practicas,
                                                  @PathVariable Integer id){
        try{
            Practicas updatePracticas = practicasService.updatePracticas(practicas, id);
            return ResponseEntity.ok(updatePracticas);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePracticas(@Valid @PathVariable Integer id){
        try{
            practicasService.deletePracticas(id);
            return ResponseEntity.ok("Practica con ID"+ id +" eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
