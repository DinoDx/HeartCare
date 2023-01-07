package heartcare.model.dao;

import heartcare.model.entity.Visita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vincenzo Arnone.
 * creato il 2/1/2023.
 * Questa classe rappresenta il DAO della classe Visita.
 */
@Repository
public interface VisitaDAO extends JpaRepository<Visita, Long> {
}
