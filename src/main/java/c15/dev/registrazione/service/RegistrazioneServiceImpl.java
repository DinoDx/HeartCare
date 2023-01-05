package c15.dev.registrazione.service;

import c15.dev.model.dao.MedicoDAO;
import c15.dev.model.dao.PazienteDAO;
import c15.dev.model.entity.Medico;
import c15.dev.model.entity.Paziente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Leopoldo Todisco, Mario Cicalese
 * Creato il: 05/01/2023.
 * Implementazione dei metodi dell'interfaccia Service.
 */
@Service
public class RegistrazioneServiceImpl implements RegistrazioneService {
    /**
     * Paziente DAO per le crud operations.
     */
    @Autowired
    private PazienteDAO pazienteDAO;

    @Autowired
    private MedicoDAO medicoDAO;

    /**
     * Implementazione del metodo di registrazione.
     * @param paziente è creato dal presentation layer e passato al controller.
     *                 Il controller richiama il service.
     */
    @Override
    public void registraPaziente(Paziente paziente) {
        pazienteDAO.saveAndFlush(paziente);

    }

    /**
     * Implementazione del metodo di registrazione medico.
     * @param medico è il medico da inserire nel db.
     *
     */
    @Override
    public void registraMedico(Medico medico) {
        medicoDAO.saveAndFlush(medico);
    }
}
