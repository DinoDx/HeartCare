package c15.dev.model.dao;

import c15.dev.model.entity.Notifica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vincenzo Arnone
 * creato il 2/1/2023
 * Questa classe rappresenta il DAO della classe Notifica.
 */
@Repository
public interface NotificaDAO extends JpaRepository<Notifica,String> {
}
