package edu.tudai.tp3_integrador.controller;

import edu.tudai.tp3_integrador.model.Estudiante;
import edu.tudai.tp3_integrador.service.EstudianteCarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiante-carrera")
public class EstudianteCarreraControllerJpa {

    @Autowired
    private EstudianteCarreraService estudianteCarreraService;

    // Obtener estudiantes de una carrera filtrados por ciudad
    @GetMapping("/carrera/{nombre}/ciudad/{ciudad}")
    public ResponseEntity<List<Estudiante>> obtenerEstudiantesPorCarreraYCiudad(@PathVariable String nombre, @PathVariable String ciudad) {
        List<Estudiante> estudiantes = estudianteCarreraService.obtenerEstudiantesPorCarreraYCiudad(nombre, ciudad);
        return ResponseEntity.ok(estudiantes);
    }
}
