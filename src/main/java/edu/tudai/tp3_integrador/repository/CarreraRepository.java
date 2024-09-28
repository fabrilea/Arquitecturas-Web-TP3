package edu.tudai.tp3_integrador.repository;

import edu.tudai.tp3_integrador.dto.CarreraDto;
import edu.tudai.tp3_integrador.model.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long> {

    @Query("SELECT new edu.tudai.tp3_integrador.dto.CarreraDto(c.nombre, COUNT(e.id)) " +
            "FROM Carrera c " +
            "JOIN c.estudiantes ec " +  // JOIN con la entidad intermedia EstudianteCarrera
            "JOIN ec.estudiante e " +  // JOIN con la entidad Estudiante
            "WHERE e.id IS NOT NULL " +
            "GROUP BY c.nombre " +
            "ORDER BY COUNT(e.id) DESC")
    List<CarreraDto> obtenerCarrerasConEstudiantesInscritos();
}
