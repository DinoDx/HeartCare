package c15.dev.gestioneUtente.service;

import c15.dev.model.dao.AdminDAO;
import c15.dev.model.dao.MedicoDAO;
import c15.dev.model.dao.PazienteDAO;
import c15.dev.model.entity.UtenteRegistrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@Service
public class GestioneUtenteServiceImpl implements GestioneUtenteService{
    /**
     * Provvede ad accedere al db per il paziente.
     */
    @Autowired
    private PazienteDAO paziente;
    /**
     * Provvede ad accedere al db per l'admin.
     */
    @Autowired
    private AdminDAO admin;
    /**
     * Provvede ad accedere al db per il medico.
     */
    @Autowired
    private MedicoDAO medico;
    @Override
    public UtenteRegistrato login(final String email, final String password) {

        try {
            MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
            byte[] pass = msgDigest.digest(password.getBytes());
            UtenteRegistrato utente;

            if ((utente = paziente.findByEmailAndPassword(email,pass)) != null) {
                return utente;
            } else if ((utente = medico.findByEmailAndPassword(email,pass)) != null) {
                return utente;
            } else if ((utente = admin.findByEmailAndPassword(email,pass)) != null) {
                return utente;
            }

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
