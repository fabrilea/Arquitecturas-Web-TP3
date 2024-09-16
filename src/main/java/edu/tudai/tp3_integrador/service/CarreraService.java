package edu.tudai.tp3_integrador.service;

import edu.tudai.tp3_integrador.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}