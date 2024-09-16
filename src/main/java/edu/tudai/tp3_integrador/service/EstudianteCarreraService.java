package edu.tudai.tp3_integrador.service;

import edu.tudai.tp3_integrador.model.Estudiante;
import edu.tudai.tp3_integrador.repository.EstudianteCarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteCarreraService {

    @Autowired
    private EstudianteCarreraRepository estudianteCarreraRepository;

    // Obtener estudiantes de una carrera filtrados por ciudad
    public List<Estudiante> obtenerEstudiantesPorCarreraYCiudad(String nombreCarrera, String ciudad) {
        return estudianteCarreraRepository.findEstudiantesByCarreraAndCiudad(nombreCarrera, ciudad);
    }
}
