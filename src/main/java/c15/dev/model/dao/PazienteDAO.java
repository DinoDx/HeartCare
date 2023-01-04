package c15.dev.model.dao;

import c15.dev.model.entity.Paziente;
import org.springframework.stereotype.Repository;

/**
 * @author carlo.
 * creato il 1/1/2023.
 * Questa classe rappresenta il DAO della classe Paziente.
 */
@Repository
public interface PazienteDAO extends UtenteRegistratoDAO {
    /**
     *
     * @param email
     * @param password
     * @return Paziente trovato nel db
     */
    Paziente findByEmailAndPassword(String email, byte[] password);


}
