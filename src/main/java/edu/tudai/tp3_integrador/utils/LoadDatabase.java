package edu.tudai.tp3_integrador.utils;

import edu.tudai.tp3_integrador.model.Carrera;
import edu.tudai.tp3_integrador.model.Estudiante;
import edu.tudai.tp3_integrador.model.EstudianteCarrera;
import edu.tudai.tp3_integrador.model.EstudianteCarreraPK;
import edu.tudai.tp3_integrador.repository.CarreraRepository;
import edu.tudai.tp3_integrador.repository.EstudianteRepository;
import edu.tudai.tp3_integrador.repository.EstudianteCarreraRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(@Qualifier("estudianteRepository") EstudianteRepository estudianteRepository,
                                   @Qualifier("carreraRepository") CarreraRepository carreraRepository,
                                   @Qualifier("estudianteCarreraRepository") EstudianteCarreraRepository estudianteCarreraRepository) {
        return args -> {
            log.info("Loading data into the database...");

            // Cargar algunas carreras
            Carrera ingenieria = new Carrera("Ingeniería en Sistemas");
            Carrera derecho = new Carrera("Derecho");
            Carrera medicina = new Carrera("Medicina");

            log.info("Preloading " + carreraRepository.save(ingenieria));
            log.info("Preloading " + carreraRepository.save(derecho));
            log.info("Preloading " + carreraRepository.save(medicina));

            // Cargar algunos estudiantes
            Estudiante estudiante1 = new Estudiante(1, "Carlos", "Pérez", 23, "Masculino", 12345678, "Buenos Aires", 12345);
            Estudiante estudiante2 = new Estudiante(2, "Ana", "González", 22, "Femenino", 87654321, "Rosario", 67890);

            log.info("Preloading " + estudianteRepository.save(estudiante1));
            log.info("Preloading " + estudianteRepository.save(estudiante2));

            // Matricular estudiantes en las carreras
            EstudianteCarreraPK pk1 = new EstudianteCarreraPK(estudiante1.getId(), ingenieria.getId());
            EstudianteCarrera estudianteCarrera1 = new EstudianteCarrera(pk1, Date.valueOf("2020-01-01"), Date.valueOf("2024-05-10"), true, estudiante1, ingenieria);

            EstudianteCarreraPK pk2 = new EstudianteCarreraPK(estudiante2.getId(), derecho.getId());
            EstudianteCarrera estudianteCarrera2 = new EstudianteCarrera(pk2, Date.valueOf("2022-05-07"), null, false, estudiante2, derecho);

            log.info("Preloading " + estudianteCarreraRepository.save(estudianteCarrera1));
            log.info("Preloading " + estudianteCarreraRepository.save(estudianteCarrera2));
        };
    }
}
