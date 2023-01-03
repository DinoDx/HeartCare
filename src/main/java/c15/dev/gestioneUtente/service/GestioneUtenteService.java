package c15.dev.gestioneUtente.service;

import c15.dev.model.entity.UtenteRegistrato;

import java.util.Optional;

public interface GestioneUtenteService {
    /**
     * Firma del metodo di login.
     *
     * @param email    dell'utente che vuole loggare
     * @param password dell'utente che vuole loggare
     * @return UtenteRegistrato loggato
     */
    Optional<UtenteRegistrato> login(String email, String password);
}
