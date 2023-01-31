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
 * Questa interfaccia è quella che espone i metodi che saranno usati.
 * dal controller del package, ma anche da altri controller e services.
 */
public interface GestioneUtenteService {
    /**
     * Firma del metodo di login.
     * @return UtenteRegistrato loggato.
     */
    AuthenticationResponse login(AuthenticationRequest request);

    /**
     * Firma del metodo di assegnaCaregiver.
     *
     * @param idPaziente del paziente a cui si vuole assegnare il caregiver.
     * @param emailCaregiver email del caregiver.
     * @param nomeCaregiver nome del caregiver.
     * @param cognomeCaregiver nome del caregiver.
     */
    void assegnaCaregiver(final Long idPaziente,
                          final String emailCaregiver,
                          final String nomeCaregiver,
                          final String cognomeCaregiver);

    /**
     * Firma del metodo rimuoviPaziente.
     * @param idUtente id del paziente che deve essere rimosso.
     */
    void rimuoviPaziente(final Long idUtente);

    /**
     * Firma del metodo rimuoviMedico.
     * @param idUtente id del medico che deve essere rimosso.
     */
    void rimuoviMedico(final Long idUtente);

    /**
     * Firma del metodo isPaziente.
     * @param idUtente id dell'utente che vogliamo controllare che sia un paziente.
     * @return
     */
    boolean isPaziente(final long idUtente);

    /**
     * Firma del metodo isMedico.
     * @param idUtente id dell'utente che vogliamo controllare che sia un medico.
     * @return
     */
    boolean isMedico(final long idUtente);

    /**
     * Firma del metodo isAdmin.
     * @param idUtente id dell'utente che vogliamo controllare che sia un admin.
     * @return
     */
    boolean isAdmin(final long idUtente);

    /**
     * Firma del metodo assegnaPaziente.
     * @param idMedico   id del medico a cui assegnamo il paziente.
     * @param idPaziente id del paziente che viene assegnato al medico.
     * @return
     */
    boolean assegnaPaziente(final long idMedico,
                            final long idPaziente);

    /**
     * Firma del metodo findPazienteById.
     * @param id id del paziente da ricercare.
     * @return
     */
    Paziente findPazienteById(final Long id);

    /**
     * Firma del metodo findMedicoById.
     * @param id id del medico da ricercare.
     * @return
     */
    Medico findMedicoById(final Long id);

    boolean findMedicoByCf(String codiceFiscale);

    boolean findUtenteByCf(final String cf);


    /**
     * Firma del metodo updatePaziente.
     * @param paz paziente da aggiornare.
     */
    void updatePaziente(final Paziente paz);

    /**
     * Firma del metodo updateMedico.
     * @param med medico da aggiornare.
     */
    void updateMedico(final Medico med);

    /**
     * Firma del metodo getTuttiMedici.
     */
    List<UtenteRegistrato> getTuttiMedici();

    /**
     * Firma del metodo getTuttiPazienti.
     */
    List<UtenteRegistrato> getTuttiPazienti();

    List<UtenteRegistrato> getTuttiUtenti();

    /**
     * Firma del metodo getPazientiByMedico.
     */
    List<Paziente> getPazientiByMedico(final long idMedico);

    /**
     * Firma del metodo modificaDatiPaziente.
     * @param pazienteDTO
     * @param idUtente
     */
    void modificaDatiPaziente(final ModificaPazienteDTO pazienteDTO,
                              final long idUtente)
            throws Exception;

    /**
     * Firma del metodo modifica dati di un medico.
     * @param dto
     * @param idUtente
     * @throws Exception
     */
    void modificaDatiMedico(final UtenteRegistratoDTO dto, final long idUtente)
            throws Exception;

    /**
     * Firma del metodo che trova un utente da un id.
     * @param id
     * @return
     */
    UtenteRegistrato findUtenteById(final Long id);

    /**
     * Firma del metodo che trova un utente da un email.
     * @param email
     * @return
     */

    UtenteRegistrato findUtenteByEmail(final String email);

    /**
     * Firma del metodo check da un email.
     * @param email
     * @return
     */

    boolean checkByEmail(final String email);

    boolean checkMedicoByEmail(String email);

    /**
     * Firma del metodo registraIndirizzo.
     * @param ind
     */
    boolean registraIndirizzo(final Indirizzo ind);

    /**
     * Firma del metodo che assegna un indirizzo ad un utente.
     * @param idUtente
     * @param ind
     * @return
     */
    boolean assegnaIndirizzoAdUtente(final long idUtente, final Indirizzo ind);

    /**
     * Firma del metodo che assegna un medico ad un paziente.
     * @param idMedico
     * @param idUtente
     * @return
     */
    boolean assegnaMedicoAPaziente(final long idMedico, final long idUtente);

    /**
     * Firma del metodo che ritorna la lista di dispositivi di un paziente,
     * @param idPaziente
     * @return
     */
    Set<DispositivoMedico> getDispositiviByPaziente(final long idPaziente);

    /**
     * Firma del metodo che trova un medico dal paziente.
     * @param idPaziente
     * @return
     */
    public  Long findMedicoByPaziente(final long idPaziente);
    public List<Indirizzo> findAllIndirizzi();

    void rimuoviUtente(Long idUtente);

    boolean controllaPassword(String pwd,
                              Long idAdmin);
}
