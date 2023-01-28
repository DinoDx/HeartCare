package c15.dev.model.dao;

import c15.dev.model.entity.Visita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Vincenzo Arnone.
 * creato il 2/1/2023.
 * Questa classe rappresenta il DAO della classe Visita.
 */
@Repository
public interface VisitaDAO extends JpaRepository<Visita, Long> {


    @Query(value="SELECT * FROM heartcare.visita WHERE id_medico=?1", nativeQuery = true)
    List<Visita> findByMedico(final long id);

    @Query(value="SELECT * FROM heartcare.visita WHERE id_paziente=?1", nativeQuery = true)
    List<Visita> findByPaziente(final long id);
}
