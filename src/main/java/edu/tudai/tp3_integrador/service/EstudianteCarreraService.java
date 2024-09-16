package edu.tudai.tp3_integrador.service;

import edu.tudai.tp3_integrador.model.Carrera;
import edu.tudai.tp3_integrador.model.Estudiante;
import edu.tudai.tp3_integrador.model.EstudianteCarrera;
import edu.tudai.tp3_integrador.repository.EstudianteCarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteCarreraService {

    @Autowired
    private EstudianteCarreraRepository estudianteCarreraRepository;

    // Obtener estudiantes de una carrera filtrados por ciudad
    public List<Estudiante> obtenerEstudiantesPorCarreraYCiudad(String nombreCarrera, String ciudad) {
        return estudianteCarreraRepository.findEstudiantesByCarreraAndCiudad(nombreCarrera, ciudad);
    }

    public List<EstudianteCarrera> obtenerEstudiantesCarreras(){
        return estudianteCarreraRepository.findAll();
    }

    public Optional<EstudianteCarrera> obtenerEstudianteCarreraPorId(long id){
        return estudianteCarreraRepository.findById(id);
    }
}
