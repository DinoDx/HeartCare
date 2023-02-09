package c15.dev.model.dao;

import c15.dev.model.entity.UtenteRegistrato;
import org.springframework.stereotype.Repository;

/**
 * @author Leopoldo Todisco.
 * creato il: 31/12/2022.
 * Per questo DAO, così come per MedicoDAO e PazienteDAO.
 * si estende l'interfaccia UtenteRegistratoDAO.
 */
@Repository
public interface AdminDAO extends UtenteRegistratoDAO {
    /**
     * Metodo per la ricerca tramite una email.
     * @param email
     * @return utente trovato nel db.
     */
    UtenteRegistrato findByEmail(String email);
}
