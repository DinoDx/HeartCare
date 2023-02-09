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
     * @param confermaPassword la conferma della password.
     * @return response.
     */
    AuthenticationResponse registraPaziente(Paziente paziente,
                                            String confermaPassword)
            throws Exception;
    /**
     * Firma del metodo che consente la registrazione di un medico.
     * @param medico è il medico che viene registrato.
     * @return response.
     */
    AuthenticationResponse registraMedico(Medico medico)
            throws Exception;
    /**
     * Firma del metodo che consente la registrazione di un indirizzo.
     * @param ind è l'indirizzo che viene registrato.
     */
    void saveIndirizzo(Indirizzo ind);

    /**
     * Firma del metodo per il login.
     * @param req richiesta per il login.
     * @return response.
     */
    AuthenticationResponse login(AuthenticationRequest req)
            throws Exception;

    /**
     * Firma del metodo per la ricerca di un paziente tramite email.
     * @param email email parametro di ricerca.
     * @return restituisce il paziente.
     */
    Paziente findByemail(String email);

    /**
     * Firma del metodo per la ricerca di un paziente tramite codice Fiscale.
     * @param codiceFiscale codice fiscale parametro di ricerca.
     * @return resituisce il paziente
     */
    Paziente findBycodiceFiscale(String codiceFiscale);

    /**
     * Firma del metodo che consente la registrazione di un admin.
     * @param admin è l'admin che viene registrato.
     * @return response.
     */
    AuthenticationResponse registraAdmin(Admin admin)
            throws Exception;
}
