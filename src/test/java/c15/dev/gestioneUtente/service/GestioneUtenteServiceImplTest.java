package c15.dev.gestioneUtente.service;


import c15.dev.HeartCareApplication;
import c15.dev.HeartCareApplicationTests;
import c15.dev.model.dao.PazienteDAO;

import c15.dev.model.dao.MedicoDAO;

import c15.dev.model.entity.Medico;
import c15.dev.model.entity.Paziente;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

/**
 * @author Paolo Carmine Valletta, Carlo Venditto.
 *  * Creato il 03/02/2023.
 *  * Classe di test per la gestione service di utente.
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = HeartCareApplicationTests.class)
public class GestioneUtenteServiceImplTest {


    /**
     *  Mocking del MedicoDAO per accedere al DB da parte del medico.
     */
    @Mock
    private MedicoDAO daoMedico;
    /**
     *  Mocking del MedicoDAO per accedere al DB da parte del paziente.
     */
    @Mock
    private PazienteDAO pazienteDAO;
    /**
     *  Inject mocking dell'implementazione del service.
     */
    @InjectMocks
    private GestioneUtenteServiceImpl service;

    private Paziente paz;
    private Medico med;
    private ArrayList<Paziente> listaPaz;



    private Paziente paziente;

    @SneakyThrows
    @BeforeEach
    public void setUp() {
        paziente = new Paziente(LocalDate.of(2000, 10, 10),
                "TDSLL00E18C129Y",
                "+393654563256",
                "Wpasswd1!%",
                "cicccio@libero.it",
                "Ciccio",
                "Pasticcio",
                "M");

        paziente.setId(10L);


        paz = new Paziente(LocalDate.of(2000, 10, 10),
                "VLLPCR01L12I234V",
                "+393381234568",
                "Wpasswd1!%",
                "paolo@libero.it",
                "Paolo",
                "Valletta",
                "M");

        med = new Medico(LocalDate.of(2000, 10, 10),
                "VDDCDD89L78I976V",
                "+393398765437",
                "Apasswd1!%",
                "leopoldo@libero.it",
                "Leopoldo",
                "Todisco",
                "M");
        paz.setId(1L);
        med.setId(2L);

        listaPaz = new ArrayList<>();

        System.out.println(paziente.toString());
    }


    @Test
    public void testAssegnaCaregiver() throws Exception {

        paziente.setEmailCaregiver("provaTest@gmail.com");
        paziente.setNomeCaregiver("Mario");
        paziente.setCognomeCaregiver("Cicalese");

        Mockito.when(pazienteDAO.findById(anyLong())).thenAnswer(invocationOnMock -> {
            return Optional.of(paziente);
        });
        System.out.println(paziente.getId());
        assertEquals(true,
                service.assegnaCaregiver(paziente.getId(), paziente.getEmailCaregiver(), paziente.getNomeCaregiver(), paziente.getCognomeCaregiver()));

    }

    @Test
    public void testAssegnaCaregiver_emailNull() throws Exception {

        paziente.setEmailCaregiver("");
        paziente.setNomeCaregiver("Mario");
        paziente.setCognomeCaregiver("Cicalese");

        Mockito.when(pazienteDAO.findById(anyLong())).thenAnswer(invocationOnMock -> {
            return Optional.of(paziente);
        });

        assertEquals(false,
                service.assegnaCaregiver(paziente.getId(), paziente.getEmailCaregiver(), paziente.getNomeCaregiver(), paziente.getCognomeCaregiver()));

    }


    @Test
    public void testAssegnaCaregiver_nomeNull() throws Exception {

        paziente.setEmailCaregiver("mariocicalese@libero.it");
        paziente.setNomeCaregiver("");
        paziente.setCognomeCaregiver("Cicalese");

        Mockito.when(pazienteDAO.findById(anyLong())).thenAnswer(invocationOnMock -> {
            return Optional.of(paziente);
        });

        assertEquals(false,
                service.assegnaCaregiver(paziente.getId(), paziente.getEmailCaregiver(), paziente.getNomeCaregiver(), paziente.getCognomeCaregiver()));

    }


    @Test
    public void testAssegnaCaregiver_cognomeNull() throws Exception {

        paziente.setEmailCaregiver("mariocicalese@libero.it");
        paziente.setNomeCaregiver("Mario");
        paziente.setCognomeCaregiver("");

        Mockito.when(pazienteDAO.findById(anyLong())).thenAnswer(invocationOnMock -> {
            return Optional.of(paziente);
        });

        assertEquals(false,
                service.assegnaCaregiver(paziente.getId(), paziente.getEmailCaregiver(), paziente.getNomeCaregiver(), paziente.getCognomeCaregiver()));

    }


    @Test
    public void testAssegnaCaregiver_emailNonValida() throws Exception {
        paziente.setEmailCaregiver("mariocicalese@libero");
        paziente.setNomeCaregiver("Mario");
        paziente.setCognomeCaregiver("Cicalese");

        Mockito.when(pazienteDAO.findById(anyLong())).thenAnswer(invocationOnMock -> {
            return Optional.of(paziente);
        });

        assertEquals(false,
                service.assegnaCaregiver(paziente.getId(), paziente.getEmailCaregiver(), paziente.getNomeCaregiver(), paziente.getCognomeCaregiver()));}


        /**
         *  Testing dell'assegnamento del paziente corretto.
         */
        @Test
        public void testAssegnaPazienteCorretto () {
            Mockito.when(pazienteDAO.findById(anyLong())).thenAnswer(invocationOnMock -> {
                return Optional.of(paz);
            });

            Mockito.when(daoMedico.findById(anyLong())).thenAnswer(invocationOnMock -> {
                return Optional.of(med);
            });

            paz.setMedico(med);
            med.getElencoPazienti().add(paz);


            assertEquals(true, service.assegnaPaziente(med.getId(), paz.getId()));

        }

        /**
         *  Testing dell'assegnamento fallito.
         *  causa: Medico non esistente.
         */
        @Test
        public void testAssegnaPazienteMedicoNonEsitente () {
            Mockito.when(daoMedico.findById(anyLong())).thenAnswer(invocationOnMock -> {
                return Optional.ofNullable(null);
            });

            paz.setMedico(med);
            med.getElencoPazienti().add(paz);


            assertEquals(false, service.assegnaPaziente(med.getId(), paz.getId()));

        }

        /**
         *  Testing dell'assegnamento fallito.
         *  causa: Paziente non esistente.
         */
        @Test
        public void testAssegnaPazienteNonEsitente () {
            Mockito.when(pazienteDAO.findById(anyLong())).thenAnswer(invocationOnMock -> {
                return Optional.ofNullable(null);
            });

            Mockito.when(daoMedico.findById(anyLong())).thenAnswer(invocationOnMock -> {
                return Optional.of(med);
            });

            paz.setMedico(med);
            med.getElencoPazienti().add(paz);


            assertEquals(false, service.assegnaPaziente(med.getId(), paz.getId()));


        }

}
