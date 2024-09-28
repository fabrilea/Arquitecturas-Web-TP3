package edu.tudai.tp3_integrador.service;

import edu.tudai.tp3_integrador.dto.EstudianteDto;
import edu.tudai.tp3_integrador.model.Carrera;
import edu.tudai.tp3_integrador.model.Estudiante;
import edu.tudai.tp3_integrador.model.EstudianteCarrera;
import edu.tudai.tp3_integrador.model.EstudianteCarreraPK;
import edu.tudai.tp3_integrador.repository.CarreraRepository;
import edu.tudai.tp3_integrador.repository.EstudianteCarreraRepository;
import edu.tudai.tp3_integrador.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;


@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private CarreraRepository carreraRepository;
    @Autowired
    private EstudianteCarreraRepository estudianteCarreraRepository;

    public List<Estudiante> getAllEstudiantes() {
        return estudianteRepository.findAll();
    }

    public Optional<Estudiante> getEstudianteById(Long id) {
        return estudianteRepository.findById(id);
    }

    //Dar alta a estudiante
    public Estudiante saveEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public void deleteEstudiante(Long id) {
        estudianteRepository.deleteById(id);
    }

    public List<EstudianteDto> obtenerEstudiantesPorApellido() {
        return estudianteRepository.ordenarPorApellido();
    }

    public EstudianteDto obtenerEstudiantePorLU(Long lu) {
        return estudianteRepository.findByLu(lu);
    }

    public List<EstudianteDto> obtenerEstudiantesPorGenero(String genero) {
        return estudianteRepository.findByGenero(genero);
    }

    public EstudianteCarrera matricularEstudiante(Long estudianteId, Long carreraId, Date fechaInscripcion, Date fechaGraduacion) {
        // Buscar el estudiante y la carrera
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        Carrera carrera = carreraRepository.findById(carreraId)
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        // Crear la clave compuesta para EstudianteCarrera
        EstudianteCarreraPK estudianteCarreraPK = new EstudianteCarreraPK(estudianteId, carreraId);

        // Crear la instancia de EstudianteCarrera
        EstudianteCarrera estudianteCarrera = new EstudianteCarrera();
        estudianteCarrera.setId(estudianteCarreraPK);  // Asignar la clave compuesta
        estudianteCarrera.setEstudiante(estudiante);
        estudianteCarrera.setCarrera(carrera);

        // Asignar la fecha de inscripción
        estudianteCarrera.setFechaInscripcion(fechaInscripcion);

        // Asignar la fecha de graduación
        estudianteCarrera.setFechaGraduacion(fechaGraduacion);

        // Asignar si está graduado o no
        if (fechaGraduacion != null){
            estudianteCarrera.setEstaGraduado(true);
        } else {
            estudianteCarrera.setEstaGraduado(false);
        }


        // Guardar la matriculación en la base de datos
        return estudianteCarreraRepository.save(estudianteCarrera);
    }

    // Obtener estudiantes de una carrera filtrados por ciudad
    public List<EstudianteDto> obtenerEstudiantesPorCarreraYCiudad(String nombreCarrera, String ciudad) {
        return estudianteRepository.findEstudiantesByCarreraAndCiudad(nombreCarrera, ciudad);
    }
}
