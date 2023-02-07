package c15.dev.registrazione.service;

import c15.dev.HeartCareApplication;
import c15.dev.model.dao.PazienteDAO;
import c15.dev.model.dao.UtenteRegistratoDAO;
import c15.dev.model.entity.Indirizzo;
import c15.dev.model.entity.Paziente;
import c15.dev.utils.AuthenticationResponse;
import c15.dev.utils.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Mario Cicalese.
 * creato il 03/02/2023.
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = HeartCareApplication.class)
public class RegistrazioneServiceImplTest {

    @InjectMocks
    RegistrazioneServiceImpl registrazioneService;
    /**
     * Provvede alle operazioni legate al token di autenticazione.
     */
    @Mock
    private JwtService jwtService;

    /**
     * Provvede alla criptazione della password.
     */
    @Mock
    private PasswordEncoder pwdEncoder;

    /**
     * Provvede alle operazioni di autenticazione.
     */
    @Mock
    private AuthenticationManager authenticationManager;

    /**
     * Mocking del DAO per simulare le CRUD del paziente
     */
    @Mock
    private PazienteDAO pazienteDAO;

    /**
     * Mocking del DAO per simulare le CRUD dell'utente
     */
    @Mock
    private UtenteRegistratoDAO utenteRegistratoDAO;
    private Indirizzo indirizzo;

    public RegistrazioneServiceImplTest() {
    }

    /**
     * metood che si occupa di testare la registrazione del paziente
     * @throws Exception
     */
    @Test
    public void registraPaziente()
            throws Exception {
        Paziente paziente = new Paziente(
                LocalDate.parse("2001-06-15"),
                "CCLMRA02G14E321Q",
                "+393421234561",
                "Wpasswd1!%",
                "mario@gmail.com",
                "Mario",
                "Cicalese",
                "M"
        );

        Paziente SavedPaziente = new Paziente(
                LocalDate.parse("2001-06-15"),
                "CCLMRA02G14E321Q",
                "+393421234561",
                "Wpasswd1!%",
                "mario@gmail.com",
                "Mario",
                "Cicalese",
                "M"
        );
        SavedPaziente.setId(1L);
        String token = jwtService.generateToken(paziente);
        Mockito.when(this.pazienteDAO.save(paziente)).thenReturn(SavedPaziente);
        Mockito.when(this.utenteRegistratoDAO.findByEmail(any())).thenReturn(null);
        assertEquals(AuthenticationResponse.builder()
                .token(token)
                .build(), this.registrazioneService.registraPaziente(paziente));
    }

    /**
     * metodo che si occupa di testare la registrazione di un paziente
     * con una mail già presente nel database
     * @throws Exception
     */
    @Test
    public void TestRegistrazioneEmailPresente() throws Exception {
        Paziente paziente = new Paziente(
                LocalDate.parse("2001-06-15"),
                "CCLMRA02G14E321Q",
                "+393421234561",
                "Wpasswd1!%",
                "mario@gmail.com",
                "Mario",
                "Cicalese",
                "M"
        );
        when(this.utenteRegistratoDAO.findByEmail(any())).thenReturn(paziente);
        assertThrows(IllegalArgumentException.class, () -> this.registrazioneService.registraPaziente(paziente));
    }

    /**
     * metodo che si occupa di testare la registrazione del paziente
     * quando viene passato passato un paziente come null
     */
    @Test
    public void TestRegistrazionePazienteNull() {
        assertThrows(IllegalArgumentException.class,
                () -> this.registrazioneService.registraPaziente(null));
    }
}
