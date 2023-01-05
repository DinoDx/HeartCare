package c15.dev.gestioneUtente.service;

import c15.dev.model.entity.UtenteRegistrato;

import java.util.Optional;

/**
 * @author Leopoldo Todisco, Carlo Venditto.
 * Creato il 03/01/2023.
 * Interfaccia Service per Gestione Utente.
 * Questa interfaccia è quella che espone i metodi che saranno usati
 * dal controller del package, ma anche da altri controller e services.
 */
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
     * @param idPaziente del paziente a cui si vuole assegnare il caregiver
     * @param emailCaregiver email del caregiver
     * @param nomeCaregiver nome del caregiver
     * @param cognomeCaregiver nome del caregiver
     */
    void assegnaCaregiver(Long idPaziente,
                          String emailCaregiver,
                          String nomeCaregiver,
                          String cognomeCaregiver);

    void rimuoviPaziente(Long idUtente);
    void rimuoviMedico(Long idUtente);

    boolean isPaziente(long idUtente);

    boolean isMedico(long idUtente);
}
