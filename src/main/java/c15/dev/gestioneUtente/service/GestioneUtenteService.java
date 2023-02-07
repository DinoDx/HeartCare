package c15.dev.gestioneUtente.service;

import c15.dev.model.entity.UtenteRegistrato;
import c15.dev.model.entity.Paziente;
import c15.dev.model.entity.Medico;
import c15.dev.model.entity.DispositivoMedico;
import c15.dev.model.entity.Indirizzo;
import c15.dev.utils.AuthenticationRequest;
import c15.dev.utils.AuthenticationResponse;

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
     * Firma del metodo di assegnaCaregiver.
     * @param idPaziente del paziente a cui si vuole assegnare il caregiver.
     * @param emailCaregiver email del caregiver.
     * @param nomeCaregiver nome del caregiver.
     * @param cognomeCaregiver nome del caregiver.
     */
    void assegnaCaregiver(Long idPaziente,
                          String emailCaregiver,
                          String nomeCaregiver,
                          String cognomeCaregiver);

    /**
     * Firma del metodo rimuoviMedico.
     * @param idUtente id del medico che deve essere rimosso.
     */
    void rimuoviMedico(Long idUtente);

    /**
     * Firma del metodo isPaziente.
     * @param idUtente id dell'utente che vogliamo controllare che sia un paziente.
     * @return true o false.
     */
    boolean isPaziente(long idUtente);

    /**
     * Firma del metodo isMedico.
     * @param idUtente id dell'utente che vogliamo controllare che sia un medico.
     * @return true o false.
     */
    boolean isMedico(long idUtente);

    /**
     * Firma del metodo isAdmin.
     * @param idUtente id dell'utente che vogliamo controllare che sia un admin.
     * @return true o false.
     */
    boolean isAdmin(long idUtente);

    /**
     * Firma del metodo assegnaPaziente.
     * @param idMedico   id del medico a cui assegnamo il paziente.
     * @param idPaziente id del paziente che viene assegnato al medico.
     * @return true o false.
     */
    boolean assegnaPaziente(long idMedico,
                            long idPaziente);

    /**
     * Firma del metodo findPazienteById.
     * @param id id del paziente da ricercare.
     * @return Paziente.
     */
    Paziente findPazienteById(Long id);

    /**
     * Firma del metodo findMedicoById.
     * @param id id del medico da ricercare.
     * @return Medico.
     */
    Medico findMedicoById(Long id);

    /**
     * Firma metodo findUtenteByCf.
     * @param cf
     * @return true o false.
     */
    boolean findUtenteByCf(String cf);


    /**
     * Firma del metodo updatePaziente.
     * @param paz paziente da aggiornare.
     */
    void updatePaziente(Paziente paz);

    void updateUtente(UtenteRegistrato u);

    /**
     * Firma del metodo getTuttiMedici.
     * @return Lista tutti di tutti i medici.
     */
    List<UtenteRegistrato> getTuttiMedici();

    /**
     * Firma del metodo getTuttiPazienti.
     * @return Lista di tutti i pazienti.
     */
    List<UtenteRegistrato> getTuttiPazienti();

    /**
     * Firma del metodo getTuttiUtenti.
     * @return Lista di tutti gli utenti.
     */
    List<UtenteRegistrato> getTuttiUtenti();

    /**
     * Firma del metodo getPazientiByMedico.
     * @param idMedico id del medico.
     * @return Lista di tutti i pazienti associati al medico
     */
    List<Paziente> getPazientiByMedico(long idMedico);



    /**
     * Firma del metodo che trova un utente da un id.
     * @param id dell'utente.
     * @return UtenteRegistrato.
     */
    UtenteRegistrato findUtenteById(Long id);

    /**
     * Firma del metodo che trova un utente da un email.
     * @param email email utente da ricercare.
     * @return UtenteRegistrato.
     */

    UtenteRegistrato findUtenteByEmail(String email);

    /**
     * Firma del metodo check da un email.
     * @param email email paziente da ricercare.
     * @return true o false.
     */

    boolean checkByEmail(String email);

    /**
     * Firma del metodo registraIndirizzo.
     * @param ind indirizzo da registrare.
     * @return true o false.
     */
    boolean registraIndirizzo(Indirizzo ind);

    /**
     * Firma del metodo che assegna un indirizzo ad un utente.
     * @param idUtente id utente.
     * @param ind indirizzo che verrà assegnato all'utente.
     * @return true o false.
     */
    boolean assegnaIndirizzoAdUtente(long idUtente, Indirizzo ind);

    /**
     * Firma del metodo che assegna un medico ad un paziente.
     * @param idMedico id del medico.
     * @param idUtente id dell'utente.
     * @return true o false.
     */
    boolean assegnaMedicoAPaziente(long idMedico, long idUtente);

    /**
     * Firma del metodo che ritorna la lista di dispositivi di un paziente,
     * @param idPaziente id del Paziente.
     * @return Insieme di dispositivi medici.
     */
    Set<DispositivoMedico> getDispositiviByPaziente(long idPaziente);

    /**
     * Firma metodo updateIndirizzo.
     * @param ind indirizzo da aggiornare.
     */
     void updateIndirizzo(Indirizzo ind);

    /**
     * Firma del metodo che trova un medico dal paziente.
     * @param idPaziente id del paziente.
     * @return Long.
     */
      Long findMedicoByPaziente(long idPaziente);

    /**
     * Firma metodo findAllIndirizzi
     * @return  Lista di indirizzi.
     */
     List<Indirizzo> findAllIndirizzi();

    /**
     * Firma metodo rimuoviUtente
     * @param idUtente id utente.
     */
    void rimuoviUtente(Long idUtente);

    /**
     * Firma metodo controllaPassword.
     * @param pwd password.
     * @param idAdmin id dell'admin.
     * @return true o false.
     */
    boolean controllaPassword(String pwd,
                              Long idAdmin);

    /**
     * Firma metodo encryptPassword.
     * @param nuovaPassword nuova password da inserire.
     * @return String
     */
    String encryptPassword(String nuovaPassword);
}
