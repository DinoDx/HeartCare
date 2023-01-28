package c15.dev.registrazione.service;

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
    public AuthenticationResponse registraPaziente(Paziente paziente)
            throws Exception;

    public AuthenticationResponse registraMedico(Medico medico) throws Exception;
    public AuthenticationResponse login(AuthenticationRequest req)
            throws Exception;
    public Paziente findByemail(String email);
    public Paziente findBycodiceFiscale(String codiceFiscale);
    /**
     * Firma del metodo che consente la registrazione di un medico.
     * @param med è il medico che viene registrato.
     */

}
