package edu.tudai.tp3_integrador.repository;

import edu.tudai.tp3_integrador.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstudianteRepository  extends JpaRepository<Estudiante, Long> {
    @Query("SELECT t FROM Estudiante t ORDER BY t.apellido")
    public List<Estudiante> ordenarPorApellido();

    @Query("SELECT t FROM Estudiante t WHERE t.lu = :lu")
    public Estudiante findByLu(long lu);

    @Query("SELECT t FROM Estudiante t WHERE t.genero = :genero")
    public List<Estudiante> findByGenero(String genero);
}
