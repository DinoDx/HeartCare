package c15.dev.gestioneUtente.service;

import c15.dev.model.entity.UtenteRegistrato;

public interface GestioneUtenteService {
    /**
     * Firma del metodo di login.
     * @param email dell'utente che vuole loggare
     * @param password dell'utente che vuole loggare
     * @return UtenteRegistrato loggato
     */
    UtenteRegistrato login(String email,String password);
}
