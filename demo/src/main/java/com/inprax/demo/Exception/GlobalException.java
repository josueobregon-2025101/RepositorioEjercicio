package com.inprax.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    // Error 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(Exception ex, WebRequest request) {

        Map<String, Object> error = new HashMap<>();
        error.put("estado", 500);
        error.put("error", "Internal Server Error");
        error.put("mensaje", ex.getMessage());
        error.put("ruta", request.getDescription(false));

        return ResponseEntity
                .status(500)
                .contentType(MediaType.APPLICATION_JSON)
                .body(error);
    }

    //Error 404
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return crearRespuesta(ex.getMessage(), request, HttpStatus.NOT_FOUND);
    }

    //Error 400
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
        String mensajePersonalizado = "Error: El cuerpo de la petición (Body) es obligatorio o tiene un formato inválido.";
        return crearRespuesta(mensajePersonalizado, request, HttpStatus.BAD_REQUEST);
    }

    //Error 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        String mensajeAmigable = ex.getBindingResult().getFieldError().getDefaultMessage();
        return crearRespuesta(mensajeAmigable, request, HttpStatus.BAD_REQUEST);
    }

    //Error 500
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(NullPointerException ex, WebRequest request) {
        String mensajePersonalizado = "Error de sistema: Se intentó acceder a un dato nulo.";
        return crearRespuesta(mensajePersonalizado, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Error 400
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        String mensajePersonalizado = "Error: Los argumentos enviados son inválidos.";
        return crearRespuesta(mensajePersonalizado, request, HttpStatus.BAD_REQUEST);
    }

    //Error 400
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handlleBadRequestException(BadRequestException ex, WebRequest request) {
        return crearRespuesta(ex.getMessage(), request, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<?> crearRespuesta(String mensaje, WebRequest request, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("estado", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("mensaje", mensaje);
        body.put("ruta", request.getDescription(false));

        return new ResponseEntity<>(body, status);
    }

    

}
