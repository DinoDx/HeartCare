package c15.dev.registrazione.service;

import c15.dev.model.dao.PazienteDAO;
import c15.dev.model.dao.AdminDAO;
import c15.dev.model.dao.IndirizzoDAO;
import c15.dev.model.dao.MedicoDAO;
import c15.dev.model.dao.UtenteRegistratoDAO;
import c15.dev.model.entity.Admin;
import c15.dev.model.entity.Indirizzo;
import c15.dev.model.entity.Medico;
import c15.dev.model.entity.Paziente;
import c15.dev.utils.AuthenticationRequest;
import c15.dev.utils.AuthenticationResponse;
import c15.dev.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * @author Mario Cicalese.
 * Creato il : 03/01/2023.
 * Questa classe rappresenta il Service utilizzato per la registrazione.
 */
@Service
public class RegistrazioneServiceImpl implements RegistrazioneService {
    /**
     * Provvede alle operazioni del db legate al paziente.
     */
    @Autowired
    private PazienteDAO pazienteDAO;
    /**
     * Provvede alle operazioni del db legate all'admin.
     */
    @Autowired
    private AdminDAO adminDAO;

    /**
     * Provvede alle operazioni del db legate all'indirizzo.
     */
    @Autowired
    private IndirizzoDAO indirizzoDAO;

        /**
         * Provvede alle operazioni del db legate al medico.
         */
    @Autowired
    private MedicoDAO medicoDAO;

    /**
     * Provvede alle operazioni legate al token di autenticazione.
     */
    @Autowired
    private JwtService jwtService;

    /**
     * Provvede alla criptazione della password.
     */
    @Autowired
    private PasswordEncoder pwdEncoder;

    /**
     * Provvede alle operazioni di autenticazione.
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Provvede alle operazioni del db legate all'utente.
     */
    @Autowired
    private UtenteRegistratoDAO utenteRegistratoDAO;


    /**
     * Implementazione metodo per la registrazione del paziente.
     * @param paz paziente da registrare.
     * @return response.
     */
    @Override
    public AuthenticationResponse registraPaziente(final Paziente paz)
                                                    throws Exception {

        if (paz == null) {
            throw new IllegalArgumentException("Paziente non valido");
        } else if (utenteRegistratoDAO.findByEmail(paz.getEmail()) != null) {
            throw new IllegalArgumentException(
                    "Email " + paz.getEmail() + "già presente"
            );
        }

        paz.setPassword(pwdEncoder.encode(paz.getPassword()));

        Paziente savedPaziente = pazienteDAO.save(paz);
        Long id = savedPaziente.getId();
        var jwtToken = jwtService.generateToken(paz);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    /**
     * Implementazione metodo per la registrazione dell'indirizzo.
     * @param ind indirizzo da registrare.
     */
    public void saveIndirizzo(final Indirizzo ind) {
        indirizzoDAO.save(ind);
    }

    /**
     * Implementazione del metodo di registrazione medico.
     * @param med è il medico da inserire nel db.
     * @return response.
     */
    @Override
    public AuthenticationResponse registraMedico(final Medico med)
                                                            throws Exception {
        med.setPassword(pwdEncoder.encode(med.getPassword()));
        Long id = pazienteDAO.save(med).getId();
        //TODO TOGLIERE PAZIENTEDAO E METTERE MEDICODAO
        var jwtToken = jwtService.generateToken(med);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    /**
     * Implementazione metodo per la registrazione dell'admin.
     * @param admin admin da registrare.
     * @return response.
     */
    @Override
    public AuthenticationResponse registraAdmin(final Admin admin)
            throws Exception {
        admin.setPassword(pwdEncoder.encode(admin.getPassword()));
        Long id = adminDAO.save(admin).getId();

        var jwtToken = jwtService.generateToken(admin);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    /**
     * Implementazione per il metodo del login tramite token jwt.
     * @param request parametro richiesta per il login.
     * @return response.
     */
    @Override
    public AuthenticationResponse login(final AuthenticationRequest request)
                                                            throws Exception {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        var user = pazienteDAO.findByEmail(request.getEmail());
        Medico medico = null;
        if (user == null) {
            medico = medicoDAO.findByEmail(request.getEmail());
            Admin ad = null;
            if (medico == null) {
                ad = (Admin) adminDAO.findByEmail(request.getEmail());
                var jwtToken = jwtService.generateToken(ad);
                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .build();
            }
            var jwtToken = jwtService.generateToken(medico);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        System.out.println(user.toString());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    /**
     * Implementazione metodo per la ricerca di un paziente tramite l'email.
     * @param email parametro di ricerca.
     * @return dati del paziente.
     */
    @Override
    public Paziente findByemail(final String email) {
        return pazienteDAO.findByEmail(email);
    }

    /**
     * Implementazione metodo per la ricerca di un paziente.
     * tramite il codice fiscale.
     * @param codiceFiscale parametro di ricerca.
     * @return dati del paziente.
     */
    @Override
    public Paziente findBycodiceFiscale(final String codiceFiscale) {
        return pazienteDAO.findBycodiceFiscale(codiceFiscale);
    }
}
