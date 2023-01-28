package c15.dev.gestioneUtente.service;

import c15.dev.model.dto.ModificaPazienteDTO;
import c15.dev.model.dto.UtenteRegistratoDTO;
import c15.dev.model.entity.*;
import c15.dev.utils.AuthenticationRequest;
import c15.dev.utils.AuthenticationResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
     * @return UtenteRegistrato loggato
     */
    AuthenticationResponse login(AuthenticationRequest request);

    /**
     * Firma del metodo di assegnaCaregiver.
     *
     * @param idPaziente       del paziente a cui si vuole assegnare il caregiver
     * @param emailCaregiver   email del caregiver
     * @param nomeCaregiver    nome del caregiver
     * @param cognomeCaregiver nome del caregiver
     */
    void assegnaCaregiver(Long idPaziente,
                          String emailCaregiver,
                          String nomeCaregiver,
                          String cognomeCaregiver);

    /**
     * Firma del metodo rimuoviPaziente.
     *
     * @param idUtente id del paziente che deve essere rimosso
     */
    void rimuoviPaziente(Long idUtente);

    /**
     * Firma del metodo rimuoviMedico.
     *
     * @param idUtente id del medico che deve essere rimosso
     */
    void rimuoviMedico(Long idUtente);

    /**
     * Firma del metodo isPaziente.
     *
     * @param idUtente id dell'utente che vogliamo controllare che sia un paziente
     * @return
     */
    boolean isPaziente(long idUtente);

    /**
     * Firma del metodo isMedico.
     *
     * @param idUtente id dell'utente che vogliamo controllare che sia un medico
     * @return
     */
    boolean isMedico(long idUtente);

    /**
     * Firma del metodo isAdmin
     *
     * @param idUtente id dell'utente che vogliamo controllare che sia un admin
     * @return
     */
    boolean isAdmin(long idUtente);

    /**
     * Firma del metodo assegnaPaziente.
     *
     * @param idMedico   id del medico a cui assegnamo il paziente
     * @param idPaziente id del paziente che viene assegnato al medico
     * @return
     */
    boolean assegnaPaziente(long idMedico, long idPaziente);

    /**
     * Firma del metodo findPazienteById
     *
     * @param id id del paziente da ricercare
     * @return
     */
    Paziente findPazienteById(Long id);

    /**
     * Firma del metodo findMedicoById
     *
     * @param id id del medico da ricercare
     * @return
     */
    Medico findMedicoById(Long id);

    boolean findUtenteByCf(String cf);


    /**
     * Firma del metodo updatePaziente
     *
     * @param paz paziente da aggiornare
     */
    void updatePaziente(Paziente paz);

    /**
     * Firma del metodo updateMedico
     *
     * @param med medico da aggiornare
     */
    void updateMedico(Medico med);

    /**
     * Firma del metodo getTuttiMedici
     */
    List<UtenteRegistrato> getTuttiMedici();

    /**
     * Firma del metodo getTuttiPazienti
     */
    List<UtenteRegistrato> getTuttiPazienti();

    /**
     * Firma del metodo getPazientiByMedico
     */
    List<Paziente> getPazientiByMedico(long idMedico);

    /**
     * Firma del metodo modificaDatiPaziente
     * @param pazienteDTO
     * @param idUtente
     */
    void modificaDatiPaziente(final ModificaPazienteDTO pazienteDTO,
                              final long idUtente)
                                    throws Exception;
    void modificaDatiMedico(final UtenteRegistratoDTO dto, final long idUtente)
            throws Exception;

    UtenteRegistrato findUtenteById(Long id);

    UtenteRegistrato findUtenteByEmail(String email);

    boolean checkByEmail(String email);

    /**
     * Firma del metodo registraIndirizzo
     * @param ind
     */
    boolean registraIndirizzo(Indirizzo ind);


    boolean assegnaIndirizzoAdUtente(final long idUtente, final Indirizzo ind);

    boolean assegnaMedicoAPaziente(final long idMedico, final long idUtente);

    Set<DispositivoMedico> getDispositiviByPaziente(final long idPaziente);

}
