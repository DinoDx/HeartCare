package c15.dev.registrazione.service;

import c15.dev.model.dao.PazienteDAO;
import c15.dev.model.dao.UtenteRegistratoDAO;
import c15.dev.model.entity.Paziente;
import c15.dev.model.entity.UtenteRegistrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Mario Cicalese
 * Creato il : 03/01/2023
 * Questa classe rappresenta il Service utilizzato per la registrazione
 */
@Service
public class RegistrazioneServiceImpl implements RegistrazioneService{
    @Autowired
    public PazienteDAO pazienteDAO;
    @Override
    public Paziente registraPaziente(Paziente paziente) {
        return pazienteDAO.save(paziente);
    }

    @Override
    public Paziente findByemail(String email) {
        return pazienteDAO.findByemail(email);
    }

    @Override
    public Paziente findBycodiceFiscale(String codiceFiscale) {
        return pazienteDAO.findBycodiceFiscale(codiceFiscale);
    }
}
