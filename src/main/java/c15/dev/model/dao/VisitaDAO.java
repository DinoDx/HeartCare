package c15.dev.model.dao;

import c15.dev.model.entity.Visita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitaDAO extends JpaRepository<Visita,String> {
}
