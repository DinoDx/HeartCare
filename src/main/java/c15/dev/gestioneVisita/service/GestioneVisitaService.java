package c15.dev.gestioneVisita.service;

import c15.dev.model.entity.Indirizzo;
import c15.dev.model.entity.Visita;

import java.time.LocalDate;
import java.util.List;


/**
 * @author Leopoldo Todisco.
 * Creato il 05/01/2023.
 * Service per il package gestioneVisita.
 *
 */
public interface GestioneVisitaService {

    /**
     * Firma del metodo aggiunta visita.
     * @param visita i dati della visita da creare.
     */
    void aggiuntaVisita(Visita visita);

    /**
     * Firma del metodo trova visite programmate dall'utente.
     * @param email metodo che restituisce le visite di un utente data l'email.
     * @return la lista delle visite programmate per quell'utente.
     */
    List<Visita> findVisiteProgrammateByUser(String email);

    /**
     * Firma del metodo trova i dati di un indirizzo dato l'id.
     * @param id l'identificativo dell'indirizzo.
     * @return l'indirizzo associato a quel id.
     */
    Indirizzo findIndirizzoById(Long id);

    /**
     * Firma del metodo trova i dati di una visita dato l'id.
     * @param id l'identificativo della visita.
     * @return la visita associata a quel id.
     */
    Visita findById(long id);

    /**
     * Firma del metodo che permette di cambiare la data di una visita.
     * @param visita la visita a cui cambiare la data.
     * @param date la nuova data in cui sarà programmata la visita.
     */
    void cambiaData(Visita visita, LocalDate date);
}
