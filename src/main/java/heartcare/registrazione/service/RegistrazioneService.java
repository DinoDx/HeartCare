package heartcare.registrazione.service;

import heartcare.model.entity.Medico;
import heartcare.model.entity.Paziente;

/**
 * @author Mario Cicalese
 * Creato il : 03/01/2023
 * Questa classe rappresenta il Service utilizzato per la registrazione
 */
public interface RegistrazioneService {
    public Paziente registraPaziente(Paziente paziente);
    public Paziente findByemail(String email);
    public Paziente findBycodiceFiscale(String codiceFiscale);
    /**
     * Firma del metodo che consente la registrazione di un medico.
     * @param med è il medico che viene registrato.
     */
    void registraMedico(Medico med);
}
