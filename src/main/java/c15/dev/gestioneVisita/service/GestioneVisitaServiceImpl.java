package c15.dev.gestioneVisita.service;

import c15.dev.gestioneUtente.service.GestioneUtenteService;
import c15.dev.model.dao.IndirizzoDAO;
import c15.dev.model.dao.VisitaDAO;
import c15.dev.model.entity.Indirizzo;
import c15.dev.model.entity.Visita;
import c15.dev.model.entity.enumeration.StatoVisita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


/**
 *  @author Leopoldo Todisco.
 *  Creato il : 03/01/2023.
 *  Questa classe rappresenta il Service utilizzato per la visita.
 */
@Service
public class GestioneVisitaServiceImpl implements GestioneVisitaService {
    /**
     * DAO per le operazioni di accesso al db delle visite.
     */
    @Autowired
    private VisitaDAO visitaDAO;
    /**
     * Service per le operazioni dell'utente.
     */
    @Autowired
    private GestioneUtenteService utenteService;

    /**
     * DAO per le operazioni di accesso al db degli indirizzi.
     */
    @Autowired
    private IndirizzoDAO indirizzoDAO;

    /**
     * Metodo per salvare una nuova visita.
     * @param visita i dati della visita da aggiungere.
     */
    @Override
    public boolean aggiuntaVisita(final Visita visita) {
        if (visita.getData().isBefore(LocalDate.now())) {
            return false;
        } else if (visita.getIndirizzoVisita() == null) {
            return false;
        } else if (visita.getMedico() == null) {
            return false;
        } else if (visita.getPaziente() == null) {
            return false;
        } else {
            visitaDAO.save(visita);
            return true;
        }
    }

    /**
     * Metodo per trovare le visite programmate da un utente.
     * @param email email dell'utente.
     * @return lista di visite programmate per l'utente
     */
    @Override
    public List<Visita> findVisiteProgrammateByUser(final String email) {
        var user = utenteService.findUtenteByEmail(email);
        long idUser = user.getId();

        if (utenteService.isMedico(user.getId())) {
            var list = visitaDAO.findByMedico(idUser);
            System.out.println("dopo la lista");
            return list
                    .stream()
                    .filter(v -> v.getStatoVisita()
                            .equals(StatoVisita.PROGRAMMATA)).toList();
        } else if (utenteService.isPaziente(user.getId())) {
            var list = visitaDAO.findByPaziente(idUser);
            return list
                    .stream()
                    .filter(v -> v.getStatoVisita()
                            .equals(StatoVisita.PROGRAMMATA)).toList();
        }

        return null;
    }

    /**
     * Metodo trova i dati di un indirizzo dato l'id.
     * @param id l'identificativo dell'indirizzo.
     * @return l'indirizzo associato a quel id.
     */
    @Override
    public Indirizzo findIndirizzoById(final Long id) {
        return indirizzoDAO.findById(id).get();
    }

    /**
     * Metodo trova i dati di una visita dato l'id.
     * @param id l'identificativo della visita.
     * @return la visita associata a quel id.
     */
    @Override
    public Visita findById(final long id) {
        return visitaDAO.findById(id).get();
    }

    /**
     * Metodo che permette di cambiare la data di una visita.
     * @param visita la visita a cui cambiare la data.
     * @param date la nuova data in cui sarà programmata la visita.
     */
    @Override
    public void cambiaData(final Visita visita, final LocalDate date) {
       visita.setData(date);
       visitaDAO.save(visita);
    }



}
