package c15.dev.registrazione.service;

import c15.dev.model.entity.Admin;
import c15.dev.model.entity.Indirizzo;
import c15.dev.model.entity.Medico;
import c15.dev.model.entity.Paziente;
import c15.dev.utils.AuthenticationRequest;
import c15.dev.utils.AuthenticationResponse;

/**
 * @author Mario Cicalese.
 * Creato il : 03/01/2023.
 * Questa classe rappresenta il Service utilizzato per la registrazione.
 */
public interface RegistrazioneService {
    /**
     * Firma del metodo che consente la registrazione di un paziente.
     * @param paziente è il paziente che viene registrato.
     */
    public AuthenticationResponse registraPaziente(Paziente paziente)
            throws Exception;
    /**
     * Firma del metodo che consente la registrazione di un medico.
     * @param medico è il medico che viene registrato.
     */
    public AuthenticationResponse registraMedico(Medico medico)
            throws Exception;

    public void saveIndirizzo(Indirizzo ind);

    /**
     * Firma del metodo per il login.
     * @param req richiesta per il login.
     */
    public AuthenticationResponse login(AuthenticationRequest req)
            throws Exception;

    /**
     * Firma del metodo per la ricerca di un paziente tramite email.
     * @param email email parametro di ricerca.
     */
    public Paziente findByemail(String email);

    /**
     * Firma del metodo per la ricerca di un paziente tramite codice Fiscale.
     * @param codiceFiscale codice fiscale parametro di ricerca.
     */
    public Paziente findBycodiceFiscale(String codiceFiscale);

    public AuthenticationResponse registraAdmin(Admin admin)
            throws Exception;
}
