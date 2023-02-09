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
    /**
     * Query per la ricerca di una misurazione tramite l'id del paziente.
     * @param id id del paziente.
     * @return di una misurazione.
     */
    @Query(value = "SELECT m FROM Misurazione m WHERE m.paziente.id = ?1")
     Iterable<Misurazione> findByPaziente(Long id);

    /**
     * Query che ci permette di cercare una misurazione.
     * data la sua categoria e l'id del paziente.
     * @param categoria categoria della misurazione.
     * @param id id del paziente che effettua la misurazione.
     * @return di una misurazione.
     */
    @Query(value = "SELECT m FROM Misurazione m "
            +
            "WHERE m.dispositivoMedico.categoria = ?1 and m.paziente.id = ?2")
     Iterable<Misurazione> findByCategoria(String categoria, Long id);

    /**
     * Query che ci permette di ricercare le categorie delle misurazioni.
     * tramite l'id del paziente.
     * @param id id del paziente.
     * @return di una categoria.
     */
    @Query(value = "SELECT m.dispositivoMedico.categoria FROM Misurazione m " +
            "WHERE m.paziente.id = ?1 GROUP BY m.dispositivoMedico.categoria")
     Iterable<String> findCategorieByPaziente(Long id);

    /**
     * Query che ci permette di ricercare tutte le misurazioni.
     * di un determinato paziente.
     * @param id id del paziente.
     * @return delle misurazioni.
     */
    @Query(value = "SELECT m FROM Misurazione m WHERE m.paziente.id = ?1")
     Iterable<Misurazione> getAllMisurazioniByPaziente(Long id);
}
