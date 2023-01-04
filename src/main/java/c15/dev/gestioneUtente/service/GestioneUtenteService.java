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
    /**
     * Firma del metodo di assegnaCaregiver.
     *
     * @param id del paziente a cui si vuole assegnare il caregiver
     * @param emailCaregiver email del caregiver
     * @param nomeCaregiver nome del caregiver
     * @param cognomeCaregiver nome del caregiver
     */
    void assegnaCaregiver(Long id, String emailCaregiver, String nomeCaregiver, String cognomeCaregiver);
}
