package c15.dev.gestioneUtente.service;

import c15.dev.model.dao.AdminDAO;
import c15.dev.model.dao.MedicoDAO;
import c15.dev.model.dao.PazienteDAO;
import c15.dev.model.dao.UtenteRegistratoDAO;
import c15.dev.model.entity.Paziente;
import c15.dev.model.entity.UtenteRegistrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

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

    /**
     * Metodo che permette di fare il login.
     * @param email    dell'utente che vuole loggare
     * @param password dell'utente che vuole loggare
     * @return
     */
    @Override
    public Optional<UtenteRegistrato> login(final String email, final String password) {

        try {
            MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
            byte[] pass = msgDigest.digest(password.getBytes());
            UtenteRegistrato utente;

            if ((utente = paziente.findByEmailAndPassword(email,pass)) != null) {
                return Optional.of(utente);
            } else if ((utente = medico.findByEmailAndPassword(email,pass)) != null) {
                return Optional.of(utente);
            } else if ((utente = admin.findByEmailAndPassword(email,pass)) != null) {
                return Optional.of(utente);
            }

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    /**
     * Metodo che assegna un caregiver a un paziente.
     * @param idPaziente del paziente a cui si vuole assegnare il caregiver
     * @param emailCaregiver email del caregiver
     * @param nomeCaregiver nome del caregiver
     * @param cognomeCaregiver nome del caregiver
     */
    @Override
    public void assegnaCaregiver(Long idPaziente,
                                 String emailCaregiver,
                                 String nomeCaregiver,
                                 String cognomeCaregiver) {

        Optional<UtenteRegistrato> pz =  paziente.findById(idPaziente);
        if(pz.isEmpty()) {
            return;
        }

        Paziente tmp = (Paziente) pz.get();
        tmp.setEmailCaregiver(emailCaregiver);
        tmp.setNomeCaregiver(nomeCaregiver);
        tmp.setCognomeCaregiver(cognomeCaregiver);

        paziente.save(tmp);
    }

    /**
     * Metodo che elimina un paziente.
     * @param idUtente
     */
    @Override
    public void rimuoviPaziente(Long idUtente) {
        Optional<UtenteRegistrato> u =paziente.findById(idUtente);
        paziente.delete(u.get());
    }

    /**
     * Metodo che elimina un medico.
     * @param idUtente
     */
    @Override
    public void rimuoviMedico(Long idUtente) {
        Optional<UtenteRegistrato> u = medico.findById(idUtente);
        medico.delete(u.get());
    }

    /**
     * Metodo che verifica se un utente è un paziente.
     * @param idUtente
     * @return
     */
    @Override
    public boolean isPaziente(long idUtente) {
        Optional<UtenteRegistrato> u = paziente.findById(idUtente);

        if (u.isEmpty()){
            return false;
        } else if (u.get().getClass().getSimpleName().equals("Paziente")) {
            return true;
        }

        return false;
    }

    /**
     * Metodo che verifica se un utente è un medico.
     * @param idUtente
     * @return
     */
    @Override
    public boolean isMedico(long idUtente) {
        Optional<UtenteRegistrato> u = medico.findById(idUtente);

        if (u.isEmpty()){
            return false;
        } else if (u.get().getClass().getSimpleName().equals("Medico")) {
            return true;
        }
        return false;
    }
}
