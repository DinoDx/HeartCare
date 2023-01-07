package heartcare.model.dao;

import heartcare.model.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mario Cicalese.
 * Creato il 02/01/2022.
 * Questa classe rappresenta il DAO della classe Nota.
 */
@Repository
public interface NotaDAO extends JpaRepository<Nota, Long> {
}
