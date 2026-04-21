package com.inprax.demo.Controller;


import com.inprax.demo.Entity.Documentos;
import com.inprax.demo.Service.DocumentosService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Documentos")
public class DocumentosController {
    private final DocumentosService documentosService;

    public DocumentosController(DocumentosService documentosService) {
        this.documentosService = documentosService;
    }

    @GetMapping
    private List<Documentos> getDocumentos(){
        return documentosService.getAllDocumentos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDocumentosById(@PathVariable @Valid int id){
        Documentos documento = documentosService.getDocumentosById(id);
        if(documento != null){
            return ResponseEntity.ok(documento);
        }else {
            return ResponseEntity.status(404).body("No se encontro el documento");
        }

    }

    @PostMapping
    public ResponseEntity<?> saveDocumentos(@Valid @RequestBody Documentos documentos){
        try {
            Documentos newDocumentos = documentosService.saveDocumentos(documentos);
            if (newDocumentos != null) {
                return ResponseEntity.ok(newDocumentos);
            }else {
                return ResponseEntity.status(402).body("No se creo el documento");
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error al crear el documento");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDocumentos(@PathVariable Integer id,@RequestBody @Valid Documentos documento){
        try {
                Documentos actualizado = documentosService.updateDocumentos(id,documento);
                if (actualizado != null ){
                    return ResponseEntity.ok(actualizado);
                }
                else {
                    return ResponseEntity.status(404).body("No se encontro el documento");
                }

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDocumentosById(@PathVariable @Valid Integer id){
        Documentos documento = documentosService.getDocumentosById(id);
        if(documento != null){
            try {
                documentosService.deleteDocumentosById(id);
                return ResponseEntity.ok("Se elimino el Documento " + id);
            }catch (IllegalArgumentException e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }else{
            return ResponseEntity.status(404).body("No se encontro el documento " + id);
        }

    }
}
