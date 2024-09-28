package edu.tudai.tp3_integrador.service;

import edu.tudai.tp3_integrador.dto.CarreraDto;
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

    public List<Carrera> getAllCarreras() {
        return carreraRepository.findAll();
    }

    public Optional<Carrera> getCarreraById(Long id) {
        return carreraRepository.findById(id);
    }

    public Carrera saveCarrera(Carrera carrera) {
        return carreraRepository.save(carrera);
    }

    public void deleteCarrera(Long id) {
        carreraRepository.deleteById(id);
    }

    public List<CarreraDto> obtenerCarrerasConEstudiantesInscritos() {
        return carreraRepository.obtenerCarrerasConEstudiantesInscritos();
    }

    public Optional<Carrera> obtenerCarreraPorId(long id){
        return carreraRepository.findById(id);
    }
}