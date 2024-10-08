package edu.tudai.tp3_integrador.service;

import edu.tudai.tp3_integrador.dto.CarreraDto;
import edu.tudai.tp3_integrador.dto.EstudianteCarreraDto;
import edu.tudai.tp3_integrador.model.Carrera;
import edu.tudai.tp3_integrador.model.Estudiante;
import edu.tudai.tp3_integrador.model.EstudianteCarrera;
import edu.tudai.tp3_integrador.model.EstudianteCarreraPK;
import edu.tudai.tp3_integrador.repository.EstudianteCarreraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EstudianteCarreraService {

    private final EstudianteCarreraRepository estudianteCarreraRepository;

    @Transactional(readOnly = true)
    public List<EstudianteCarrera> getAllEstudianteCarrera() {
        return estudianteCarreraRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<EstudianteCarrera> getEstudianteCarreraById(EstudianteCarreraPK id) {
        return estudianteCarreraRepository.findById(id);
    }

    @Transactional
    public EstudianteCarrera saveEstudianteCarrera(EstudianteCarrera estudianteCarrera) {
        return estudianteCarreraRepository.save(estudianteCarrera);
    }

    @Transactional
    public void deleteEstudianteCarrera(EstudianteCarreraPK id) {
        estudianteCarreraRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<EstudianteCarreraDto> generarReporteCarreras() {
        return estudianteCarreraRepository.generarReporteCarreras();
    }
}
