package c15.dev.registrazione.service;

import c15.dev.model.entity.Medico;
import c15.dev.model.entity.Paziente;

/**
 * @author Leopoldo Todisco, Mario Cicalese
 */
public interface RegistrazioneService {
    /**
     * Metodo che consente la registrazione.
     * @param paziente è il paziente che viene registrato.
     */
    void registraPaziente(Paziente paziente);

    /**
     * Metodo che consente la registrazione di un medico.
     * @param medico è il medico che viene registrato.
     */
    void registraMedico(Medico medico);

}
