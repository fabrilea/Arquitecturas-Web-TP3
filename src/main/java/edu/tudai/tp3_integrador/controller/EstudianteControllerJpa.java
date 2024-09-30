package edu.tudai.tp3_integrador.controller;

import edu.tudai.tp3_integrador.dto.EstudianteDto;
import edu.tudai.tp3_integrador.model.Estudiante;
import edu.tudai.tp3_integrador.model.EstudianteCarrera;
import edu.tudai.tp3_integrador.service.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteControllerJpa {

    private final EstudianteService estudianteService;

    @GetMapping
    public List<Estudiante> getAllEstudiantes() {
        return estudianteService.getAllEstudiantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> getEstudianteById(@PathVariable Long id) {
        Optional<Estudiante> estudiante = estudianteService.getEstudianteById(id);
        return estudiante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Alta de un nuevo estudiante
    @PostMapping
    public Estudiante createEstudiante(@RequestBody Estudiante estudiante) {
        return estudianteService.saveEstudiante(estudiante);
    }

    @DeleteMapping("/{id}")
    public void deleteEstudiante(@PathVariable Long id) {
        estudianteService.deleteEstudiante(id);
    }

    // Recuperar todos los estudiantes, ordenados por apellido
    @GetMapping("/orderByApellido")
    public ResponseEntity<List<EstudianteDto>> ordenarPorApellido(){
        List<EstudianteDto> estudiantes = estudianteService.obtenerEstudiantesPorApellido();
        return ResponseEntity.ok(estudiantes);
    }

    // Recuperar estudiante por número de libreta universitaria
    @GetMapping("/lu/{lu}")
    public ResponseEntity<EstudianteDto> obtenerEstudiantePorLU(@PathVariable Long lu) {
        EstudianteDto estudiante = estudianteService.obtenerEstudiantePorLU(lu);
        return ResponseEntity.ok(estudiante);
    }

    // Obtener estudiantes de una carrera filtrados por ciudad
    @GetMapping("/carrera/{nombre}/ciudad/{ciudad}")
    public ResponseEntity<List<EstudianteDto>> obtenerEstudiantesPorCarreraYCiudad(@PathVariable String nombre, @PathVariable String ciudad) {
        List<EstudianteDto> estudiantes = estudianteService.obtenerEstudiantesPorCarreraYCiudad(nombre, ciudad);
        return ResponseEntity.ok(estudiantes);
    }

    // Recuperar estudiantes por género
    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<EstudianteDto>> obtenerEstudiantesPorGenero(@PathVariable String genero) {
        List<EstudianteDto> estudiantes = estudianteService.obtenerEstudiantesPorGenero(genero);
        return ResponseEntity.ok(estudiantes);
    }

    // Matricular un estudiante en una carrera
    @PostMapping("/matricular")
    public ResponseEntity<EstudianteCarrera> matricularEstudiante(
            @RequestParam Long estudianteId,
            @RequestParam Long carreraId,
            @RequestParam Date fechaInscripcion,
            @RequestParam Date fechaGraduacion) {

        EstudianteCarrera matriculacion = estudianteService.matricularEstudiante(estudianteId, carreraId, fechaInscripcion, fechaGraduacion);
        return ResponseEntity.ok(matriculacion);
    }
}

