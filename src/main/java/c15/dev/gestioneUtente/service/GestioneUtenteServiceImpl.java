package c15.dev.gestioneUtente.service;

import c15.dev.model.dao.PazienteDAO;
import c15.dev.model.dao.MedicoDAO;
import c15.dev.model.dao.DispositivoMedicoDAO;
import c15.dev.model.dao.AdminDAO;
import c15.dev.model.dao.IndirizzoDAO;
import c15.dev.model.dao.UtenteRegistratoDAO;
import c15.dev.model.entity.Paziente;
import c15.dev.model.entity.Medico;
import c15.dev.model.entity.UtenteRegistrato;
import c15.dev.model.entity.Indirizzo;
import c15.dev.model.entity.DispositivoMedico;
import c15.dev.utils.AuthenticationRequest;
import c15.dev.utils.AuthenticationResponse;
import c15.dev.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
/**
 * @author Carlo.
 *  Creato il : 03/01/2023.
 * Questa classe rappresenta il Service utilizzato per la gestione utenti.
 */
@Service
@RequiredArgsConstructor
public class GestioneUtenteServiceImpl implements GestioneUtenteService {
    /**
     * Service per le operazioni che riguardano Jwt.
     */
    @Autowired
    private final JwtService jwtService;
    /**
     * provvede alle operazioni legate all'autenticazione.
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Provvede ad accedere al db per il dispositivo medico.
     */
    @Autowired
    private DispositivoMedicoDAO daoM;

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
     * Provvede ad accedere al db per l'indirizzo.
     */
    @Autowired
    private IndirizzoDAO indirizzo;

    /**
     * Provvede ad accedere al db per l'utente.
     */
    @Qualifier("utenteRegistratoDAO")
    @Autowired
    private UtenteRegistratoDAO utente;


    /**
     * Oggetto che provvede alla criptazione della password.
     */
    @Autowired
    private PasswordEncoder pwdEncoder;

    /**
     * Metodo che permette di fare il login.
     * @param request richiesta.
     * @return AuthenticationResponse.
     */
    @Override
    public AuthenticationResponse login(final AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()));


        var user = this.findUtenteByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }

    /**
     * Metodo che assegna un caregiver a un paziente.
     * @param idPaziente del paziente a cui si vuole assegnare il caregiver.
     * @param emailCaregiver email del caregiver.
     * @param nomeCaregiver nome del caregiver.
     * @param cognomeCaregiver nome del caregiver.
     */
    @Override
    public void assegnaCaregiver(final Long idPaziente,
                                 final String emailCaregiver,
                                 final String nomeCaregiver,
                                 final String cognomeCaregiver) {

        Optional<UtenteRegistrato> pz =  paziente.findById(idPaziente);
        if (pz.isEmpty()) {
            return;
        }

        Paziente tmp = (Paziente) pz.get();
        tmp.setEmailCaregiver(emailCaregiver);
        tmp.setNomeCaregiver(nomeCaregiver);
        tmp.setCognomeCaregiver(cognomeCaregiver);
        paziente.save(tmp);
    }

    /**
     * Metodo che assegna indirizzo ad utente.
     * @param idUtente id utente.
     * @param ind indirizzo da assegnare.
     * @return true o false.
     */
    @Override
    public boolean assegnaIndirizzoAdUtente(final long idUtente,
                                            final Indirizzo ind) {
        Optional<UtenteRegistrato> user = utente.findById(idUtente);
        if (user.isEmpty()) {
            return false;
        }

        user.get().setIndirizzoResidenza(ind);
        utente.save(user.get());
        return true;
    }

    /**
     * Metodo che assegna medico a paziente.
     * @param idMedico id medico.
     * @param idPaziente id paziente.
     * @return true o false.
     */
    @Override
    public boolean assegnaMedicoAPaziente(final long idMedico,
                                          final long idPaziente) {
        Medico med = findMedicoById(idMedico);
        Paziente paz = findPazienteById(idPaziente);

        paz.setMedico(med);
        paziente.saveAndFlush(paz);

        return true;
    }

    /**
     * Metodo che trova tutti i dispositivi di un paziente.
     * @param idPaziente id paziente.
     * @return insieme dispositivi medici.
     */
    @Override
    public Set<DispositivoMedico>
    getDispositiviByPaziente(final long idPaziente) {
        //Paziente pz = this.findPazienteById(idPaziente);
        Set<DispositivoMedico> res = new HashSet<>();
        res.addAll(daoM.findByPaziente(idPaziente));


        res.forEach(s -> System.out.println(s.getId()));
        return res;
    }


    /**
     * Metodo che elimina un paziente.
     * @param idUtente id utente.
     */
    @Override
    public void rimuoviPaziente(final Long idUtente) {
        Optional<UtenteRegistrato> u = paziente.findById(idUtente);
        paziente.delete(u.get());
    }

    /**
     * Metodo che elimina un medico.
     * @param idUtente id dell'utente.
     */
    @Override
    public void rimuoviMedico(final Long idUtente) {
        Optional<UtenteRegistrato> u = medico.findById(idUtente);
        medico.delete(u.get());
    }


    /**
     * Metodo che verifica se un utente è un paziente.
     * @param idUtente id dell'utente.
     * @return true o false.
     */
    @Override
    public boolean isPaziente(final long idUtente) {
        Optional<UtenteRegistrato> u = paziente.findById(idUtente);

        if (u.isEmpty()) {
            return false;
        } else if (u.get().getClass().getSimpleName().equals("Paziente")) {
            return true;
        }

        return false;
    }

    /**
     * Metodo che verifica se un utente è un medico.
     * @param idUtente id dell'utente.
     * @return true o false.
     */
    @Override
    public boolean isMedico(final long idUtente) {
        Optional<UtenteRegistrato> u = medico.findById(idUtente);

        if (u.isEmpty()) {
            return false;
        } else if (u.get().getClass().getSimpleName().equals("Medico")) {
            return true;
        }
        return false;
    }

    /**
     * Implementazione del metodo che verifica se un utente è un admin.
     * @param idUtente id dell'utente che vogliamo controllare sia un admin.
     * @return true o false.
     */
    @Override
    public boolean isAdmin(final long idUtente) {
        Optional<UtenteRegistrato> u = admin.findById(idUtente);

        if (u.isEmpty()) {
            return false;
        } else if (u.get().getClass().getSimpleName().equals("Admin")) {
            return true;
        }
        return false;
    }

    /**
     * Metodo che assegna un paziente ad un medico.
     * @param idMedico id del medico.
     * @param idPaziente id del paziente.
     * @return true o false.
     */
    @Override
    public boolean assegnaPaziente(final long idMedico,
                                   final long idPaziente) {
        Optional<UtenteRegistrato> med = medico.findById(idMedico);
        if (med.isEmpty()) {
            return false;
        }
        Optional<UtenteRegistrato> paz = medico.findById(idPaziente);
        if (paz.isEmpty()) {
            return false;
        }
        Medico m = (Medico) med.get();
        Paziente pz = (Paziente) paz.get();
        m.getElencoPazienti().add(pz);
        pz.setMedico(m);
        paziente.save(pz);
        return true;
    }

    /**
     * Metodo che trova un Paziente tramite id.
     * @param id id del paziente.
     * @return Paziente.
     */
    @Override
    public Paziente findPazienteById(final Long id) {
        Optional<UtenteRegistrato> paz = paziente.findById(id);
        if (paz.isEmpty()) {
            return null;
        }

        return (Paziente) paz.get();
    }

    /**
     * Metodo che trova Medico tramite id.
     * @param id id del medico.
     * @return Medico.
     */
    @Override
    public Medico findMedicoById(final Long id) {
        Optional<UtenteRegistrato> paz = medico.findById(id);
        if (paz.isEmpty()) {
            return null;
        }

        return (Medico) paz.get();
    }

    /**
     * Metodo che restituisce un medico tramite il codice fiscale.
     * @param codiceFiscale codice fiscale del medico.
     * @return true o false.
     */
    @Override
    public boolean findMedicoByCf(final String codiceFiscale) {
        Medico u = medico.findBycodiceFiscale(codiceFiscale);

        if (u == null) {
            return false;
        }
        return true;
    }
    /**
     * Metodo che restituisce un paziente tramite il codice fiscale.
     * @param codiceFiscale codice fiscale utente.
     * @return true o false.
     */
    @Override
    public boolean findUtenteByCf(final String codiceFiscale) {
            Paziente u = paziente.findBycodiceFiscale(codiceFiscale);

        if (u == null) {
            return false;
        }
        return true;
    }
    /**
     * Metodo che restituisce un paziente tramite la sua email.
     * @param email email del paziente.
     * @return true o false.
     */
    @Override
    public boolean checkByEmail(final String email) {
        Paziente u = paziente.findByEmail(email);
        if (u == null) {
            return false;
        }
        return true;
    }
    /**
     * Metodo che restituisce un medico tramite la sua email.
     * @param email email del medico.
     * @return true o false.
     */
    @Override
    public boolean checkMedicoByEmail(final String email) {
        Medico u = medico.findByEmail(email);
        if (u == null) {
            return false;
        }
        return true;
    }

    /**
     * Metodo per registrare indirizzo nel DB.
     * @param ind è l'indirizzo da aggiungere.
     * @return true o false.
     */
    @Override
    public boolean registraIndirizzo(final Indirizzo ind) {
        indirizzo.save(ind);
        return true;
    }

    /**
     * Metodo che restituisce un utente tramite il suo id.
     * @param id id dell'utente.
     * @return UtenteRegistrato.
     */
    @Override
    public UtenteRegistrato findUtenteById(final Long id) {
        Optional<UtenteRegistrato> u = utente.findById(id);
        if (u.isEmpty()) {
            return null;
        }
         return u.get();
    }

    /**
     * Metodo che restituisce un utente tramite la sua mail.
     * @param email email dell'utente.
     * @return UtenteRegistrato.
     */
    @Override
    public UtenteRegistrato findUtenteByEmail(final String email) {
        UtenteRegistrato result;

        if ((result = paziente.findByEmail(email)) != null) {
            return result;
        } else if ((result = medico.findByEmail(email)) != null) {
            return result;
        } else if ((result = admin.findByEmail(email)) != null) {
            return result;
        }
        return null;
    }


    /**
     * Metodo per fare update di un paziente nel DB.
     * @param paz è il paziente da aggiornare.
     */
    @Override
    public void updatePaziente(final Paziente paz) {

        this.paziente.save(paz);
    }


    /**
     * Metodo per fare update di un utente nel DB.
     * @param u utente da aggiornare.
     */
    public void updateUtente(final UtenteRegistrato u) {
        this.utente.save(u);
    }

    /**
     * Metodo per fare un update di un'entry nel DB.
     * @param med è il medico da aggiornare.
     */
    @Override
    public void updateMedico(final Medico med) {
        this.medico.save(med);
    }

    /**
     * Metodo per ottenere tutti i medici del db.
     * @return lista di tutti i medici.
     */
    @Override
    public List<UtenteRegistrato> getTuttiMedici() {
        return medico.findAll();
    }

    /**
     * Metodo per ottenere tutti i pazienti del db.
     * @return Lista di tutti i pazienti.
     */
    @Override
    public List<UtenteRegistrato> getTuttiPazienti() {
        return paziente.findAll();
    }

    /**
     * Metodo che restituisce tutti gli utenti dal db.
     * @return Lista di tutti gli utenti.
     */
    @Override
    public List<UtenteRegistrato> getTuttiUtenti() {
        return utente.findAll();
    }

    /**
     * Metodo per ottenere tutti i pazienti del db di un medico.
     * @param idMedico id del medico.
     * @return Lista di tutti i pazienti asscociati al medico.
     */
    @Override
    public List<Paziente> getPazientiByMedico(final long idMedico) {

        paziente.findAll().stream().filter((p) -> p.getClass()
                        .getSimpleName().equals("Paziente"))
                .map(Paziente.class::cast)
                .forEach((p) -> System.out.println(p));

        return paziente.findAll().stream()
                .filter((p) -> p.getClass().getSimpleName().equals("Paziente"))
                .map(Paziente.class::cast)
                .filter(p -> p.getMedico().getId() == (idMedico))
                .toList();
    }



    /**
     * Metodo per la ricerca di un medico tramite il suo paziente.
     * @param idPaziente id del paziente.
     * @return Long.
     */
    @Override
    public  Long findMedicoByPaziente(final long idPaziente) {
        Medico m = findPazienteById(idPaziente).getMedico();
        return m.getId();
    }

    /**
     * metodo che restituisce gli indirizzi dal db.
     * @return Lista di indirizzi.
     */
    @Override
    public List<Indirizzo> findAllIndirizzi() {
        return indirizzo.findAll();
    }

    /**
     * Metodo che rimuove un utente dal db.
     * @param idUtente id dell'utente.
     */
    @Override
    public void rimuoviUtente(final Long idUtente) {
        Optional<UtenteRegistrato> u = utente.findById(idUtente);
        utente.delete(u.get());
    }

    /**
     * metodo che controlla che la password passata
     * sia uguale a quella presente nel db.
     * @param pwd password passata.
     * @param idUtente id dell'utente.
     * @return true o false
     */
    @Override
    public boolean controllaPassword(final String pwd,
                                     final Long idUtente) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UtenteRegistrato u = utente.findById(idUtente).get();
        boolean isPasswordMatch = passwordEncoder.matches(pwd, u.getPassword());

        if (isPasswordMatch) {
            return true;
        }

        return false;
    }

    /**
     * Metodo che decripta la nuova password.
     * @param nuovaPassword password nuova che si vuole inserire.
     * @return String.
     */
    @Override
    public String encryptPassword(final String nuovaPassword) {
        return pwdEncoder.encode(nuovaPassword);
    }

    /**
     * Metodo che aggiorna un indirizzo.
     * @param ind indirizzo che si vuole aggiornare.
     */
    public void updateIndirizzo(final Indirizzo ind) {
        this.indirizzo.save(ind); }

}
