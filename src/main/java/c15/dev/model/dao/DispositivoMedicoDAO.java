package c15.dev.model.dao;

import c15.dev.model.entity.DispositivoMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mario Cicalese.
 * creato il 02/01/2022.
 * Questa classe rappresenta il DAO della classe DispositivoMedico.
 */
@Repository
public interface DispositivoMedicoDAO
                            extends JpaRepository<DispositivoMedico, Long> {


}
