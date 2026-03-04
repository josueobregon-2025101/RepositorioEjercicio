package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Practicas;
import com.inprax.demo.Service.PracticasService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

    @PostMapping
    public ResponseEntity<Object> createPracticas(@Valid @RequestBody Practicas practicas){
        try{
            Practicas createPracticas = practicasService.savePracticas(practicas);
            return new ResponseEntity<>(createPracticas, HttpStatus.CREATED);
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
    public ResponseEntity<Object> deletePracticas(@PathVariable Integer id){
        try{
            practicasService.deletePracticas(id);
            return ResponseEntity.ok("Práctica con ID " + id + " eliminada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}