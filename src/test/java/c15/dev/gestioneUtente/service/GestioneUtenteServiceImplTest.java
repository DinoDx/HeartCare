package c15.dev.gestioneUtente.service;

import c15.dev.HeartCareApplication;
import c15.dev.model.dao.PazienteDAO;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = HeartCareApplication.class)
public class GestioneUtenteServiceImplTest {

    @Mock
    private PazienteDAO pazienteDAO;

    @InjectMocks
    private GestioneUtenteServiceImpl service;

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

        paziente.setId(1L);


    }


    @Test
    public void testAssegnaCaregiver() throws Exception {

        paziente.setEmailCaregiver("provaTest@gmail.com");
        paziente.setNomeCaregiver("Mario");
        paziente.setCognomeCaregiver("Cicalese");

        Mockito.when(pazienteDAO.findById(anyLong())).thenAnswer(invocationOnMock -> {
            return Optional.of(paziente);
        });

        assertEquals(true,
                service.assegnaCaregiver(paziente.getId(),paziente.getEmailCaregiver(),paziente.getNomeCaregiver(),paziente.getCognomeCaregiver()));

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
                service.assegnaCaregiver(paziente.getId(),paziente.getEmailCaregiver(),paziente.getNomeCaregiver(),paziente.getCognomeCaregiver()));

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
                service.assegnaCaregiver(paziente.getId(),paziente.getEmailCaregiver(),paziente.getNomeCaregiver(),paziente.getCognomeCaregiver()));

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
                service.assegnaCaregiver(paziente.getId(),paziente.getEmailCaregiver(),paziente.getNomeCaregiver(),paziente.getCognomeCaregiver()));

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
                service.assegnaCaregiver(paziente.getId(),paziente.getEmailCaregiver(),paziente.getNomeCaregiver(),paziente.getCognomeCaregiver()));

    }

}
