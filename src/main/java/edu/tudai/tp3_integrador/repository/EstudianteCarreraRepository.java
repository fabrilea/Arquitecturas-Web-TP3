package edu.tudai.tp3_integrador.repository;

import edu.tudai.tp3_integrador.dto.EstudianteCarreraDto;
import edu.tudai.tp3_integrador.model.Estudiante;
import edu.tudai.tp3_integrador.model.EstudianteCarrera;
import edu.tudai.tp3_integrador.model.EstudianteCarreraPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EstudianteCarreraRepository extends JpaRepository<EstudianteCarrera, EstudianteCarreraPK> {

    @Query("SELECT new edu.tudai.tp3_integrador.dto.EstudianteCarreraDto(c.nombre, YEAR(ec.fechaInscripcion), " +
            "COUNT(ec.estudiante.id), " +
            "SUM(CASE WHEN ec.estaGraduado = TRUE THEN 1 ELSE 0 END)) " +
            "FROM EstudianteCarrera ec " +
            "JOIN ec.carrera c " +
            "JOIN ec.estudiante e " +
            "GROUP BY c.nombre, YEAR(ec.fechaInscripcion)")
    List<EstudianteCarreraDto> generarReporteCarreras();
}

