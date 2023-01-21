package c15.dev.gestioneVisita.service;

import c15.dev.model.dao.VisitaDAO;
import c15.dev.model.entity.Visita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestioneVisitaServiceImpl implements GestioneVisitaService {
    @Autowired
    private VisitaDAO visitaDAO;


    @Override
    public void aggiuntaVisita(Visita visita) {
        visitaDAO.save(visita);
    }
}
