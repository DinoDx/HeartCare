package c15.dev.model.dao;


import c15.dev.model.entity.Medico;
import org.springframework.stereotype.Repository;


/**
 * @author carlo.
 * creato il 1/1/2023.
 * Questa classe rappresenta il DAO della classe Medico.
 */
@Repository
public interface MedicoDAO extends UtenteRegistratoDAO {
    /**
     *
     * @param email
     * @param password
     * @return Medico trovato nel db.
     */
    Medico findByEmailAndPassword(String email, byte[] password);

    Medico findBycodiceFiscale(String codiceFiscale);

    /**
     *
     * @param id del medico.
     * @return Medico corrispondente.
     */
    Medico findMedicoById(Long id);

    /**
     * Ricerca di un medico tramite un email.
     * @param email email da ricercare nel db.
     * @return medico con quella email.
     */
    Medico findByEmail(String email);
}
