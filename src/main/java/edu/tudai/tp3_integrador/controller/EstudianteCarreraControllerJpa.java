package edu.tudai.tp3_integrador.controller;

import edu.tudai.tp3_integrador.dto.EstudianteCarreraDto;
import edu.tudai.tp3_integrador.model.Carrera;
import edu.tudai.tp3_integrador.model.Estudiante;
import edu.tudai.tp3_integrador.model.EstudianteCarrera;
import edu.tudai.tp3_integrador.model.EstudianteCarreraPK;
import edu.tudai.tp3_integrador.service.CarreraService;
import edu.tudai.tp3_integrador.service.EstudianteCarreraService;
import edu.tudai.tp3_integrador.service.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/estudiante-carrera")
public class EstudianteCarreraControllerJpa {

    private final EstudianteCarreraService estudianteCarreraService;
    private final EstudianteService estudianteService;
    private final CarreraService carreraService;

    public static class EstudianteCarreraRequest {
        public Long estudianteId;
        public Long carreraId;
        public Date fechaInscripcion;
        public Date fechaGraduacion;
        public boolean estaGraduado;
    }

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
    public ResponseEntity<EstudianteCarrera> createEstudianteCarrera(@RequestBody EstudianteCarreraRequest request) {
        // Paso 1: Obtener el estudiante por ID
        Optional<Estudiante> estudianteOpt = estudianteService.getEstudianteById(request.estudianteId);
        if (!estudianteOpt.isPresent()) {
            return ResponseEntity.badRequest().body(null); // Estudiante no encontrado
        }

        // Paso 2: Obtener la carrera por ID
        Optional<Carrera> carreraOpt = carreraService.getCarreraById(request.carreraId);
        if (!carreraOpt.isPresent()) {
            return ResponseEntity.badRequest().body(null); // Carrera no encontrada
        }

        // Ahora tenemos el estudiante y la carrera
        Estudiante estudiante = estudianteOpt.get();
        Carrera carrera = carreraOpt.get();

        // Crear la clave compuesta (EstudianteCarreraPK)
        EstudianteCarreraPK estudianteCarreraPK = new EstudianteCarreraPK(request.estudianteId, request.carreraId);

        // Crear la instancia de EstudianteCarrera
        EstudianteCarrera estudianteCarrera = new EstudianteCarrera(
                estudianteCarreraPK,
                request.fechaInscripcion,
                request.fechaGraduacion,
                request.estaGraduado,
                estudiante,
                carrera
        );

        // Guardar la relación en la base de datos
        EstudianteCarrera created = estudianteCarreraService.saveEstudianteCarrera(estudianteCarrera);

        // Retornar la respuesta con la relación creada
        return ResponseEntity.ok(created);
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
