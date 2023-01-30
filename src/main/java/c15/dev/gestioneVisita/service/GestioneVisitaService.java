package c15.dev.gestioneVisita.service;

import c15.dev.model.entity.Visita;

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
     * @param visita
     */
    void aggiuntaVisita(final Visita visita);

    /**
     * Firma del metodo trova visite programmate dall'utente.
     * @param email
     * @return
     */
    List<Visita> findVisiteProgrammateByUser(final String email);
}
