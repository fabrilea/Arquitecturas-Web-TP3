package edu.tudai.tp3_integrador.controller;

import edu.tudai.tp3_integrador.dto.CarreraDto;
import edu.tudai.tp3_integrador.model.Carrera;
import edu.tudai.tp3_integrador.service.CarreraService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/carreras")
public class CarreraControllerJpa {

    private final CarreraService carreraService;

    @GetMapping
    public List<Carrera> getAllCarreras() {
        return carreraService.getAllCarreras();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrera> getCarreraById(@PathVariable Long id) {
        Optional<Carrera> carrera = carreraService.getCarreraById(id);
        return carrera.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Carrera createCarrera(@RequestBody Carrera carrera) {
        return carreraService.saveCarrera(carrera);
    }

    @DeleteMapping("/{id}")
    public void deleteCarrera(@PathVariable Long id) {
        carreraService.deleteCarrera(id);
    }

    // Recuperar carreras con estudiantes inscritos
    @GetMapping("/carreras-con-estudiantes")
    public ResponseEntity<List<CarreraDto>> obtenerCarrerasConEstudiantes() {
        List<CarreraDto> carreras = carreraService.obtenerCarrerasConEstudiantesInscriptos();
        return ResponseEntity.ok(carreras);
    }
}

