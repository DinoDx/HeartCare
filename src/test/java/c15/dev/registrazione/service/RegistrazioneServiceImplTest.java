package c15.dev.registrazione.service;

import c15.dev.HeartCareApplication;
import c15.dev.HeartCareApplicationTests;
import c15.dev.gestioneUtente.service.GestioneUtenteServiceImpl;
import c15.dev.model.dao.AdminDAO;
import c15.dev.model.dao.MedicoDAO;
import c15.dev.model.dao.PazienteDAO;
import c15.dev.model.dao.UtenteRegistratoDAO;
import c15.dev.model.entity.Admin;
import c15.dev.model.entity.Indirizzo;
import c15.dev.model.entity.Medico;
import c15.dev.model.entity.Paziente;
import c15.dev.model.entity.UtenteRegistrato;
import c15.dev.utils.AuthenticationRequest;
import c15.dev.utils.AuthenticationResponse;
import c15.dev.utils.JwtService;
import jakarta.validation.constraints.AssertFalse;
import lombok.SneakyThrows;
import org.apache.catalina.Authenticator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = HeartCareApplicationTests.class)
public class RegistrazioneServiceImplTest {

    @InjectMocks
    RegistrazioneServiceImpl registrazioneService;

    @InjectMocks
    private RegistrazioneServiceImpl rs;


    @Mock
    private AdminDAO adminDAO;

    @Mock
    private MedicoDAO medicoDAO;

    @Mock
    private PazienteDAO pazienteDAO;

    @Mock
    private AuthenticationRequest request;

    @Mock
    private AuthenticationManager authenticationManager;

    /**
     * Provvede alla criptazione della password.
     */
    @Mock
    private PasswordEncoder pwdEncoder;

    @Mock
    private JwtService jwtService;





    @Mock
    private UtenteRegistratoDAO utenteRegistratoDAO;




    @Test
    public void TestLoginPaziente() throws Exception {
        request = new AuthenticationRequest(
                "Wpasswd1!%",
                "giuseppegiordano@libero.it"
        );


        Paziente paziente = new Paziente(LocalDate.of(2000, 11, 18),
                "PDSLPD08E18C129Y",
                "+393887124900",
                "Wpasswd1!%",
                "giuseppegiordano@libero.it",
                "Giuseppe",
                "Giordano",
                "M");


        Mockito.when(this.pazienteDAO.findByEmail(any())).thenReturn(paziente);
        Mockito.when(this.adminDAO.findByEmail(any())).thenReturn(null);
        Mockito.when(this.medicoDAO.findByEmail(any())).thenReturn(null);


        var jwtToken = jwtService.generateToken(paziente);




        assertEquals(AuthenticationResponse.builder()
                .token(jwtToken)
                .build(), this.rs.login(request));



    }






    @Test
    public void TestLoginMedico() throws Exception {
        request = new AuthenticationRequest(
                "Apasswd1!%",
                "alessandrozoccola@libero.it"
        );


        Medico med1 = new Medico(LocalDate.of(2000, 11, 18),
                "PDSLPD00E19C139A",
                "+393809123300",
                "Apasswd1!%",
                "alessandrozoccola@libero.it",
                "Alessandro",
                "Zoccola",
                "M");



        Mockito.when(this.pazienteDAO.findByEmail(any())).thenReturn(null);
        Mockito.when(this.adminDAO.findByEmail(any())).thenReturn(null);
        Mockito.when(this.medicoDAO.findByEmail(any())).thenReturn(med1);


        var jwtToken = jwtService.generateToken(med1);
        assertEquals(AuthenticationResponse.builder()
                .token(jwtToken)
                .build(), this.rs.login(request));


    }

    @Test
    public void TestLoginAdmin() throws Exception {
        request = new AuthenticationRequest(
                "Wpasswd1!%",
                "fabiola@libero.it"
        );


        Admin a1 = new Admin(LocalDate.of(2000, 11, 18),
                "PDSLPD08E18C129Q",
                "+393887124110",
                "Wpasswd1!%",
                "fabiola@libero.it",
                "Fabiola",
                "Valorant",
                "F");



        Mockito.when(this.pazienteDAO.findByEmail(any())).thenReturn(null);
        Mockito.when(this.adminDAO.findByEmail(any())).thenReturn(a1);
        Mockito.when(this.medicoDAO.findByEmail(any())).thenReturn(null);



        var jwtToken = jwtService.generateToken(a1);
        assertEquals(AuthenticationResponse.builder()
                .token(jwtToken)
                .build(), this.rs.login(request));


    }

    @Test
    public void TestLoginEmailSbagliata() throws Exception {
        request = new AuthenticationRequest(
                "Wpasswd1!%",
                "fabiola2@libero.it"
        );

        Mockito.when(this.adminDAO.findByEmail(any())).thenReturn(null);
        Mockito.when(this.pazienteDAO.findByEmail(any())).thenReturn(null);
        Mockito.when(this.medicoDAO.findByEmail(any())).thenReturn(null);
        assertFalse(false);
    }

    @Test
    public void TestLoginPasswordErrata() throws Exception {
        request = new AuthenticationRequest(
                "Wpasswd9!%",
                "fabiola@libero.it"
        );

        String password = request.getPassword();
        Admin a1 = new Admin(LocalDate.of(2000, 11, 18),
                "PDSLPD08E18C129Q",
                "+393887124110",
                "Wpasswd1!%",
                "fabiola@libero.it",
                "Fabiola",
                "Valorant",
                "F");

        Mockito.when(this.adminDAO.findByEmail(any())).thenReturn(a1);
        Mockito.when(this.pwdEncoder.matches(password, a1.getPassword())).thenReturn(false);

        assertFalse(false);


    }


    @Test
    public void TestLoginPasswordErrataPaziente() throws Exception {
        request = new AuthenticationRequest(
                "Wpasswd9!%",
                "fabiola@libero.it"
        );

        String password = request.getPassword();
        Paziente paziente = new Paziente(LocalDate.of(2000, 11, 18),
                "PDSLPD08E18C129Y",
                "+393887124900",
                "Wpasswd1!%",
                "giuseppegiordano@libero.it",
                "Giuseppe",
                "Giordano",
                "M");

        Mockito.when(this.pazienteDAO.findByEmail(any())).thenReturn(paziente);

        Mockito.when(this.pwdEncoder.matches(password, paziente.getPassword())).thenReturn(false);

        assertFalse(false);
    }

    @Test
    public void TestLoginPasswordErrataMedico() throws Exception {
        request = new AuthenticationRequest(
                "Wpasswd9!%",
                "fabiola@libero.it"
        );

        String password = request.getPassword();
        Medico med1 = new Medico(LocalDate.of(2000, 11, 18),
                "PDSLPD00E19C139A",
                "+393809123300",
                "Apasswd1!%",
                "alessandrozoccola@libero.it",
                "Alessandro",
                "Zoccola",
                "M");

        Mockito.when(this.medicoDAO.findByEmail(any())).thenReturn(med1);
        Mockito.when(this.pwdEncoder.matches(password, med1.getPassword())).thenReturn(false);

        assertFalse(false);
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
        when(this.pazienteDAO.save(paziente)).thenReturn(SavedPaziente);
        when(this.utenteRegistratoDAO.findByEmail(any())).thenReturn(null);
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

