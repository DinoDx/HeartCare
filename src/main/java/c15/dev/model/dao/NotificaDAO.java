package c15.dev.model.dao;

import c15.dev.model.entity.Notifica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Vincenzo Arnone.
 * creato il 2/1/2023.
 * Questa classe rappresenta il DAO della classe Notifica.
 */
@Repository
public interface NotificaDAO extends JpaRepository<Notifica, Long> {
    /**
     * Query che ci permette di ricercare una nota tramite il suo id.
     * @param id id della nota.
     */
    public Optional<Notifica> findById(Long id);

}
