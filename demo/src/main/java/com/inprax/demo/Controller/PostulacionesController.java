package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Postulaciones;
import com.inprax.demo.Service.PostulacionesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postulaciones")
public class PostulacionesController {
    private final PostulacionesService postulacionesService;

    public PostulacionesController(PostulacionesService postulacionesService) {
        this.postulacionesService = postulacionesService;
    }

    @GetMapping
    public List<Postulaciones> getAllPostulaciones(){
        return postulacionesService.getAllPostulaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getIdPostulaciones(@PathVariable Integer id){
        Postulaciones postulaciones = postulacionesService.getIdPostulaciones(id);
        if (postulaciones != null) {
            return ResponseEntity.ok(postulaciones);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Postulación no encontrada con ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createPostulaciones(@RequestBody Postulaciones postulaciones){
        try{
            Postulaciones createPostulaciones = postulacionesService.savePostulaciones(postulaciones);
            return new ResponseEntity<>(createPostulaciones, HttpStatus.CREATED);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePostulaciones(@RequestBody Postulaciones postulaciones,
                                                      @PathVariable Integer id){
        Postulaciones updatePostulaciones = postulacionesService.updatePostulaciones(id, postulaciones);
        if (updatePostulaciones != null) {
            return ResponseEntity.ok(updatePostulaciones);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Postulación no encontrada con ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePostulaciones(@PathVariable Integer id){
        try{
            postulacionesService.deletePostulaciones(id);
            return ResponseEntity.ok("Postulación con ID: "+ id +" eliminada correctamente");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}