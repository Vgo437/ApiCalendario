package calendario.api.infraestructura.repositorios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import calendario.api.dominio.entidades.Calendario;

@Repository
public interface ICalendarioRepositorio extends JpaRepository<Calendario, Integer> {

    @Query("SELECT c FROM Calendario c WHERE c.fecha >= ?1 AND c.fecha <= ?2 ORDER BY c.fecha")
    List<Calendario> listarPorPeriodo(LocalDate desde, LocalDate hasta);

}
