package c15.dev.model.dao;

import c15.dev.model.entity.Indirizzo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Leopoldo Todisco.
 * creato il 02/01/2023.
 * DAO della classe Indirizzo.
 */
@Repository
public interface IndirizzoDAO extends JpaRepository<Indirizzo,String> {

}
