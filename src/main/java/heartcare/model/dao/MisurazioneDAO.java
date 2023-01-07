package heartcare.model.dao;

import heartcare.model.entity.Misurazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 *
 * @author Alessandro Zoccola.
 * Data creazione: 2/1/2023.
 * Interfaccia che indica un DAO per la misurazione.
 * Si usa questa interfaccia come "base"
 * per creare i DAO delle classi figlie di Misurazione.
 * Per questo motivo si usa l'annotazione @NoRepositoryBean
 */
@Repository
public interface MisurazioneDAO extends JpaRepository<Misurazione, Long> {
    @Query(value = "SELECT m FROM Misurazione m WHERE m.paziente.id = ?1")
    public Iterable<Misurazione> findByPaziente(Long id);
}
