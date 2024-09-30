package edu.tudai.tp3_integrador.service;

import edu.tudai.tp3_integrador.dto.CarreraDto;
import edu.tudai.tp3_integrador.model.Carrera;
import edu.tudai.tp3_integrador.repository.CarreraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CarreraService {

    private final CarreraRepository carreraRepository;

    @Transactional(readOnly = true)
    public List<Carrera> getAllCarreras() {
        return carreraRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Carrera> getCarreraById(Long id) {
        return carreraRepository.findById(id);
    }

    @Transactional
    public Carrera saveCarrera(Carrera carrera) {
        return carreraRepository.save(carrera);
    }

    @Transactional
    public void deleteCarrera(Long id) {
        carreraRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<CarreraDto> obtenerCarrerasConEstudiantesInscriptos() {
        return carreraRepository.obtenerCarrerasConEstudiantesInscriptos();
    }
}