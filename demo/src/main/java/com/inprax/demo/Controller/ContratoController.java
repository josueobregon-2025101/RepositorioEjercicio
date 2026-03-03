package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Contrato;
import com.inprax.demo.Service.ContratoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Contrato")
public class ContratoController {
    public final ContratoService contratoService;
    public ContratoController(ContratoService contratoService) {
        this.contratoService = contratoService;
    }

    @GetMapping
    public List<Contrato> GetAllContratos(){
        return contratoService.getAllContratos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> GetContratoById(@PathVariable @Valid Integer id){
        try {
            Contrato contrato = contratoService.getContratoById(id);
            return ResponseEntity.ok().body(contrato);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createContrato(@RequestBody @Valid Contrato contrato){
        try{
            Contrato creado = contratoService.saveContrato(contrato);
            return ResponseEntity.ok().body(creado);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContrato(@PathVariable Integer id,@RequestBody @Valid Contrato contrato){
        try {
            Contrato update = contratoService.updateContrato(id,contrato);
            if(update!= null){
                return ResponseEntity.ok(update);
            }else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContratoById(@PathVariable @Valid Integer id){
        Contrato contrato = contratoService.getContratoById(id);
        if(contrato != null){
            contratoService.deleteContratoById(id);
            return ResponseEntity.ok().body("Se elimino el contrato");
        }else{
            return ResponseEntity.status(404).body("No existe el contrato");
        }
    }
}
