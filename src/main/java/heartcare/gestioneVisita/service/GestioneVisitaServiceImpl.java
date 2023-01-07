package heartcare.gestioneVisita.service;

import heartcare.model.dao.VisitaDAO;
import heartcare.model.entity.Visita;
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
