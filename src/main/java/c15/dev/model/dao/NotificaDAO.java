package c15.dev.model.dao;

import c15.dev.model.entity.Notifica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificaDAO extends JpaRepository<Notifica,String> {
}
