package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Postulaciones;
import com.inprax.demo.Service.PostulacionesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/api/Postulaciones")
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
        try{
            Postulaciones postulaciones = postulacionesService.getIdPostulaciones(id);
            return ResponseEntity.ok(postulaciones);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
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
        try{
            Postulaciones updatePostulaciones = postulacionesService.updatePostulaciones(id, postulaciones);
            return ResponseEntity.ok(updatePostulaciones);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePostulaciones(@PathVariable Integer id){
        try{
            postulacionesService.deletePostulaciones(id);
            return ResponseEntity.ok("Postulaciones con ID: "+ id +" eliminado correctamente");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}