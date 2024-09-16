package edu.tudai.tp3_integrador.repository;

import edu.tudai.tp3_integrador.model.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long> {

    @Query("SELECT c.nombre, COUNT(ec.estudiante.id) FROM EstudianteCarrera ec JOIN ec.carrera c GROUP BY c.id ORDER BY COUNT(ec.estudiante.id) DESC")
    List<Object[]> obtenerCarrerasConEstudiantesInscritos();

    @Query("SELECT c.nombre, YEAR(ec.fechaInscripcion), COUNT(ec.estudiante.id), SUM(CASE WHEN e.graduado = TRUE THEN 1 ELSE 0 END) " +
            "FROM EstudianteCarrera ec JOIN ec.carrera c JOIN ec.estudiante e " +
            "GROUP BY c.nombre, YEAR(ec.fechaInscripcion) ORDER BY YEAR(ec.fechaInscripcion) ASC")
    List<Object[]> generarReporteCarreras();
}
