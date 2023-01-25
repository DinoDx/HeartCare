package c15.dev.registrazione.service;

import c15.dev.model.dao.MedicoDAO;
import c15.dev.model.dao.PazienteDAO;
import c15.dev.model.dao.UtenteRegistratoDAO;
import c15.dev.model.entity.Medico;
import c15.dev.model.entity.Paziente;
import c15.dev.model.entity.UtenteRegistrato;
import c15.dev.utils.AuthenticationRequest;
import c15.dev.utils.AuthenticationResponse;
import c15.dev.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @author Mario Cicalese.
 * Creato il : 03/01/2023.
 * Questa classe rappresenta il Service utilizzato per la registrazione.
 */
@Service
public class RegistrazioneServiceImpl implements RegistrazioneService {
    @Autowired
    public PazienteDAO pazienteDAO;
    @Autowired
    public MedicoDAO medicoDAO;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder pwdEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;



    @Override
    public AuthenticationResponse registraPaziente() throws Exception {
        Paziente paz2 = new Paziente(LocalDate.of(2001, 07, 14),
                "PDSPPH09E18C139A",
                "+393886122291",
                "Wpasswd2!%",
                "pinomecca1@libero.it",
                "Pino",
                "Mecca",
                "F");

        paz2.setPassword(pwdEncoder.encode(paz2.getPassword()));
        Long id = pazienteDAO.save(paz2).getId();

        var jwtToken = jwtService.generateToken(paz2);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        var user = pazienteDAO.findByEmail(request.getEmail());
        System.out.println(user.toString());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public Paziente findByemail(String email) {
        return pazienteDAO.findByEmail(email);
    }

    @Override
    public Paziente findBycodiceFiscale(String codiceFiscale) {
        return pazienteDAO.findBycodiceFiscale(codiceFiscale);
    }

    /**
     * Implementazione del metodo di registrazione medico.
     * @param med è il medico da inserire nel db.
     */
    @Override
    public void registraMedico(Medico med){
        medicoDAO.saveAndFlush(med);
    }
}
