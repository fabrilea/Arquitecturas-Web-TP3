package edu.tudai.tp3_integrador.service;

import edu.tudai.tp3_integrador.dto.CarreraDto;
import edu.tudai.tp3_integrador.dto.EstudianteCarreraDto;
import edu.tudai.tp3_integrador.model.Carrera;
import edu.tudai.tp3_integrador.model.Estudiante;
import edu.tudai.tp3_integrador.model.EstudianteCarrera;
import edu.tudai.tp3_integrador.model.EstudianteCarreraPK;
import edu.tudai.tp3_integrador.repository.EstudianteCarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteCarreraService {

    @Autowired
    private EstudianteCarreraRepository estudianteCarreraRepository;

    public List<EstudianteCarrera> getAllEstudianteCarrera() {
        return estudianteCarreraRepository.findAll();
    }

    public Optional<EstudianteCarrera> getEstudianteCarreraById(EstudianteCarreraPK id) {
        return estudianteCarreraRepository.findById(id);
    }

    public EstudianteCarrera saveEstudianteCarrera(EstudianteCarrera estudianteCarrera) {
        return estudianteCarreraRepository.save(estudianteCarrera);
    }

    public void deleteEstudianteCarrera(EstudianteCarreraPK id) {
        estudianteCarreraRepository.deleteById(id);
    }

    public Optional<EstudianteCarrera> obtenerEstudianteCarreraPorId(EstudianteCarreraPK id){
        return estudianteCarreraRepository.findById(id);
    }

    public List<EstudianteCarreraDto> generarReporteCarreras() {
        return estudianteCarreraRepository.generarReporteCarreras();
    }
}
