package edu.tudai.tp3_integrador.controller;

import edu.tudai.tp3_integrador.dto.EstudianteCarreraDto;
import edu.tudai.tp3_integrador.model.EstudianteCarrera;
import edu.tudai.tp3_integrador.model.EstudianteCarreraPK;
import edu.tudai.tp3_integrador.service.EstudianteCarreraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/estudiante-carrera")
public class EstudianteCarreraControllerJpa {

    private final EstudianteCarreraService estudianteCarreraService;


    @GetMapping
    public List<EstudianteCarrera> getAllEstudianteCarrera() {
        return estudianteCarreraService.getAllEstudianteCarrera();
    }

    @GetMapping("/{estudianteId}/{carreraId}")
    public ResponseEntity<EstudianteCarrera> getEstudianteCarreraById(@PathVariable Long estudianteId, @PathVariable Long carreraId) {
        EstudianteCarreraPK pk = new EstudianteCarreraPK(estudianteId, carreraId);
        Optional<EstudianteCarrera> estudianteCarrera = estudianteCarreraService.getEstudianteCarreraById(pk);
        return estudianteCarrera.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EstudianteCarrera createEstudianteCarrera(@RequestBody EstudianteCarrera estudianteCarrera) {
        return estudianteCarreraService.saveEstudianteCarrera(estudianteCarrera);
    }

    @DeleteMapping("/{estudianteId}/{carreraId}")
    public void deleteEstudianteCarrera(@PathVariable Long estudianteId, @PathVariable Long carreraId) {
        EstudianteCarreraPK pk = new EstudianteCarreraPK(estudianteId, carreraId);
        estudianteCarreraService.deleteEstudianteCarrera(pk);
    }

    // Generar reporte de carreras (inscriptos y egresados por año)
    @GetMapping("/reporte-carreras")
    public ResponseEntity<List<EstudianteCarreraDto>> generarReporteCarreras() {
        List<EstudianteCarreraDto> reporte = estudianteCarreraService.generarReporteCarreras();
        return ResponseEntity.ok(reporte);
    }
}
