package c15.dev.registrazione.service;

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
}
