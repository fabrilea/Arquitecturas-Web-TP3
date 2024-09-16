package edu.tudai.tp3_integrador.controller;

import edu.tudai.tp3_integrador.model.Estudiante;
import edu.tudai.tp3_integrador.model.EstudianteCarrera;
import edu.tudai.tp3_integrador.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteControllerJpa {

    @Autowired
    private EstudianteService estudianteService;

    // Alta de un nuevo estudiante
    @PostMapping("/alta")
    public ResponseEntity<Estudiante> darAltaEstudiante(@RequestBody Estudiante estudiante) {
        Estudiante nuevoEstudiante = estudianteService.darAltaEstudiante(estudiante);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstudiante);
    }

    // Recuperar todos los estudiantes, ordenados por apellido
    @GetMapping("/todos")
    public ResponseEntity<List<Estudiante>> obtenerTodosEstudiantes() {
        List<Estudiante> estudiantes = estudianteService.obtenerTodosEstudiantes();
        return ResponseEntity.ok(estudiantes);
    }

    @GetMapping("/orderByApellido")
    public ResponseEntity<List<Estudiante>> ordenarPorApellido(){
        List<Estudiante> estudiantes = estudianteService.obtenerEstudiantesPorApellido();
        return ResponseEntity.ok(estudiantes);
    }

    // Recuperar estudiante por número de libreta universitaria
    @GetMapping("/{lu}")
    public ResponseEntity<Estudiante> obtenerEstudiantePorLU(@PathVariable Long lu) {
        Estudiante estudiante = estudianteService.obtenerEstudiantePorLU(lu);
        return ResponseEntity.ok(estudiante);
    }

    // Recuperar estudiantes por género
    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<Estudiante>> obtenerEstudiantesPorGenero(@PathVariable String genero) {
        List<Estudiante> estudiantes = estudianteService.obtenerEstudiantesPorGenero(genero);
        return ResponseEntity.ok(estudiantes);
    }

    // Matricular un estudiante en una carrera
    @PostMapping("/matricular")
    public ResponseEntity<EstudianteCarrera> matricularEstudiante(
            @RequestParam Long estudianteId,
            @RequestParam Long carreraId,
            @RequestParam Integer antiguedad,
            @RequestParam Date fechaInscripcion) {

        EstudianteCarrera matriculacion = estudianteService.matricularEstudiante(estudianteId, carreraId, antiguedad, fechaInscripcion);
        return ResponseEntity.ok(matriculacion);
    }
}

