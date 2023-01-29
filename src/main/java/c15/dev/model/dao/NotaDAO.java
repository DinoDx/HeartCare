package c15.dev.model.dao;

import c15.dev.model.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mario Cicalese.
 * Creato il 02/01/2022.
 * Questa classe rappresenta il DAO della classe Nota.
 */
@Repository
public interface NotaDAO extends JpaRepository<Nota, Long> {
@Query("SELECT n FROM Nota n WHERE n.autore <> :id AND (n.medico.id = :id OR n.paziente.id = :id)")
List<Nota> findNoteByIdUtente(long id);

}
