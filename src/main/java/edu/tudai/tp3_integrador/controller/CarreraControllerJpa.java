package edu.tudai.tp3_integrador.controller;

import edu.tudai.tp3_integrador.model.Carrera;
import edu.tudai.tp3_integrador.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carreras")
public class CarreraControllerJpa {

    @Autowired
    private CarreraService carreraService;

    // Recuperar carreras con estudiantes inscritos
    @GetMapping("/carreras-con-estudiantes")
    public ResponseEntity<List<Object[]>> obtenerCarrerasConEstudiantes() {
        List<Object[]> carreras = carreraService.obtenerCarrerasConEstudiantesInscritos();
        return ResponseEntity.ok(carreras);
    }

    // Generar reporte de carreras (inscriptos y egresados por año)
    @GetMapping("/reporte-carreras")
    public ResponseEntity<List<Object[]>> generarReporteCarreras() {
        List<Object[]> reporte = carreraService.generarReporteCarreras();
        return ResponseEntity.ok(reporte);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Carrera>> obtenerCarreras() {
        List<Carrera> carreras = carreraService.obtenerCarreras();
        return ResponseEntity.ok(carreras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Carrera>> obtenerCarreraPorId(@PathVariable Long id) {
        Optional<Carrera> carrera = carreraService.obtenerCarreraPorId(id);
        return ResponseEntity.ok(carrera);
    }
}

