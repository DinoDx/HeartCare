package heartcare.model.dao;

import heartcare.model.entity.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Leopoldo Todisco.
 * creato il: 31/12/2022.
 * Per questo DAO, così come per MedicoDAO e PazienteDAO
 * si estende l'interfaccia UtenteRegistratoDAO.
 */
@Repository
public interface AdminDAO extends UtenteRegistratoDAO {
    /**
     * Questo metodo è responsabile della rimozione di un admin dal sistema
     * dato il suo id.
     * @param id
     */
    @Query("DELETE FROM Admin a WHERE a.id=:id")
    void removeAdmin(Long id);

    /**
     *
     * @param email
     * @param password
     * @return Admin trovato nel db
     */
    Admin findByEmailAndPassword(String email, byte[] password);
}
