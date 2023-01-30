package c15.dev.gestioneVisita.service;

import c15.dev.gestioneUtente.service.GestioneUtenteService;
import c15.dev.model.dao.IndirizzoDAO;
import c15.dev.model.dao.VisitaDAO;
import c15.dev.model.entity.Indirizzo;
import c15.dev.model.entity.Visita;
import c15.dev.model.entity.enumeration.StatoVisita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 *  @author Leopoldo Todisco.
 *  Creato il : 03/01/2023.
 *  Questa classe rappresenta il Service utilizzato per la visita.
 */
@Service
public class GestioneVisitaServiceImpl implements GestioneVisitaService {
    @Autowired
    private VisitaDAO visitaDAO;

    @Autowired
    private GestioneUtenteService utenteService;

    @Autowired
    private IndirizzoDAO indirizzoDAO;

    /**
     * Metodo per salvare una nuova visita.
     * @param visita
     */
    @Override
    public void aggiuntaVisita(final Visita visita) {
        visitaDAO.save(visita);
    }

    /**
     * Metodo per trovare le visite programmate da un utente.
     * @param email email dell'utente.
     * @return
     */
    @Override
    public List<Visita> findVisiteProgrammateByUser(final String email) {
        var user = utenteService.findUtenteByEmail(email);
        long idUser = user.getId();

        if(utenteService.isMedico(user.getId())) {
            var list = visitaDAO.findByMedico(idUser);
            System.out.println("dopo la lista");
            return list
                    .stream()
                    .filter(v -> v.getStatoVisita()
                            .equals(StatoVisita.PROGRAMMATA)).toList();
        }

        else if(utenteService.isPaziente(user.getId())) {
            var list = visitaDAO.findByPaziente(idUser);
            return list
                    .stream()
                    .filter(v -> v.getStatoVisita()
                            .equals(StatoVisita.PROGRAMMATA)).toList();
        }

        return null;
    }

    @Override
    public Indirizzo findIndirizzoById(Long id) {
        return indirizzoDAO.findById(id).get();
    }
}
