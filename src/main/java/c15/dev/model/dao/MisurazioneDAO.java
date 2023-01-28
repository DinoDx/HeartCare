package c15.dev.model.dao;

import c15.dev.model.entity.Misurazione;
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
    Iterable<Misurazione> findByPaziente(Long id);

    @Query(value = "SELECT m FROM Misurazione m " +
            "WHERE m.dispositivoMedico.categoria = ?1 and m.paziente.id = ?2")
    Iterable<Misurazione> findByCategoria(String categoria, Long id);

    @Query(value = "SELECT m.dispositivoMedico.categoria FROM Misurazione m " +
            "WHERE m.paziente.id = ?1 GROUP BY m.dispositivoMedico.categoria")
    Iterable<String> findCategorieByPaziente(Long id);
}
