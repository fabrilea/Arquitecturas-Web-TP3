package edu.tudai.tp3_integrador.repository;

import edu.tudai.tp3_integrador.model.Estudiante;
import edu.tudai.tp3_integrador.model.EstudianteCarrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EstudianteCarreraRepository extends JpaRepository<EstudianteCarrera, Long> {

    @Query("SELECT e FROM EstudianteCarrera ec JOIN ec.estudiante e JOIN ec.carrera c WHERE c.nombre = :nombreCarrera AND e.ciudad = :ciudad")
    List<Estudiante> findEstudiantesByCarreraAndCiudad(String nombreCarrera, String ciudad);
}

