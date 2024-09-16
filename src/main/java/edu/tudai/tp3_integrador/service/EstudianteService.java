package edu.tudai.tp3_integrador.service;

import edu.tudai.tp3_integrador.model.Carrera;
import edu.tudai.tp3_integrador.model.Estudiante;
import edu.tudai.tp3_integrador.model.EstudianteCarrera;
import edu.tudai.tp3_integrador.repository.CarreraRepository;
import edu.tudai.tp3_integrador.repository.EstudianteCarreraRepository;
import edu.tudai.tp3_integrador.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;


@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private CarreraRepository carreraRepository;
    @Autowired
    private EstudianteCarreraRepository estudianteCarreraRepository;

    public Estudiante darAltaEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public List<Estudiante> obtenerEstudiantesPorApellido() {
        return estudianteRepository.ordenarPorApellido();
    }

    public List<Estudiante> obtenerTodosEstudiantes() {
        return estudianteRepository.findAll();
    }

    public Estudiante obtenerEstudiantePorLU(Long lu) {
        return estudianteRepository.findByLu(lu);
    }

    public List<Estudiante> obtenerEstudiantesPorGenero(String genero) {
        return estudianteRepository.findByGenero(genero);
    }

    public EstudianteCarrera matricularEstudiante(Long estudianteId, Long carreraId, Integer antiguedad, Date fechaInscripcion) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        Carrera carrera = carreraRepository.findById(carreraId).orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        EstudianteCarrera estudianteCarrera = new EstudianteCarrera();
        estudianteCarrera.setEstudiante(estudiante);
        estudianteCarrera.setCarrera(carrera);
        estudianteCarrera.setAntiguedad(antiguedad);
        estudianteCarrera.setFechaInscripcion(fechaInscripcion);

        return estudianteCarreraRepository.save(estudianteCarrera);
    }
}
