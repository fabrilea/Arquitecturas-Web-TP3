package edu.tudai.tp3_integrador.service;

import edu.tudai.tp3_integrador.model.Carrera;
import edu.tudai.tp3_integrador.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    public List<Object[]> obtenerCarrerasConEstudiantesInscritos() {
        return carreraRepository.obtenerCarrerasConEstudiantesInscritos();
    }

    public List<Object[]> generarReporteCarreras() {
        return carreraRepository.generarReporteCarreras();
    }

    public List<Carrera> obtenerCarreras(){
        return carreraRepository.findAll();
    }

    public Optional<Carrera> obtenerCarreraPorId(long id){
        return carreraRepository.findById(id);
    }
}