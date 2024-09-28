package edu.tudai.tp3_integrador.repository;

import edu.tudai.tp3_integrador.dto.EstudianteDto;
import edu.tudai.tp3_integrador.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstudianteRepository  extends JpaRepository<Estudiante, Long> {
    @Query("SELECT new edu.tudai.tp3_integrador.dto.EstudianteDto(e.nombre, e.apellido, e.edad, e.genero, e.dni, e.ciudad, e.lu)" +
            " FROM Estudiante e ORDER BY e.apellido")
    public List<EstudianteDto> ordenarPorApellido();

    @Query("SELECT new edu.tudai.tp3_integrador.dto.EstudianteDto(e.nombre, e.apellido, e.edad, e.genero, e.dni, e.ciudad, e.lu) FROM Estudiante e WHERE e.lu = :lu")
    public EstudianteDto findByLu(long lu);

    @Query("SELECT new edu.tudai.tp3_integrador.dto.EstudianteDto(e.nombre, e.apellido, e.edad, e.genero, e.dni, e.ciudad, e.lu) FROM Estudiante e WHERE e.genero = :genero")
    public List<EstudianteDto> findByGenero(String genero);

    @Query("SELECT new edu.tudai.tp3_integrador.dto.EstudianteDto(e.nombre, e.apellido, e.edad, e.genero, e.dni, e.ciudad, e.lu) FROM EstudianteCarrera ec JOIN ec.estudiante e JOIN ec.carrera c WHERE c.nombre = :nombreCarrera AND e.ciudad = :ciudad")
    List<EstudianteDto> findEstudiantesByCarreraAndCiudad(String nombreCarrera, String ciudad);

}
