package c15.dev.gestioneMisurazione.service;

import c15.dev.HeartCareApplicationTests;
import c15.dev.model.dao.DispositivoMedicoDAO;
import c15.dev.model.dao.PazienteDAO;
import c15.dev.model.entity.Paziente;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
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
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

/**
 * @author Leopoldo Todisco.
 * Creato il: 03/02/2023.
 * Classe di test per la classe GestioneMisurazione Service.
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = HeartCareApplicationTests.class)
public class GestioneMisurazioneServiceImplTest {
    /**
     * Mock del DAO per operazioni CRUD relative a un dispositivo medico.
     */
    @Mock
    private DispositivoMedicoDAO daoDispositivo;
    @Mock
    private PazienteDAO pzDao;
    @InjectMocks
    private GestioneMisurazioneServiceImpl serviceMisurazione;
    private Paziente paziente;
    private Validator validator;

    /**
     * Metodo che viene eseguito prima di tutti gli altri metodi.
     * Si usa per istanziare le risorse necessarie.
     */
    @SneakyThrows
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        paziente = new Paziente(LocalDate.of(2000, 11, 11),
                                "TDSLLD00E18C129Y",
                                "+393887868300",
                                "Wpasswd1!%",
                                "leopoldo.todiscozte@gmail.com",
                                "Leopoldo",
                                "Todisco",
                                 "M");
        paziente.setId(1L);

        Mockito.when(pzDao.findById(anyLong())).thenAnswer(invocationOnMock -> {
            return Optional.of(paziente);
        });

    }

    /**
     * Il test del metodo "registrazione dispositivo" quando la dimensione del
     * numero seriale non è compreso fra 10 e 30 viene eseguito
     * dal Jakarta validation, dunque non c'è bisogno di testare.
     */

    /**
     * Test del metodo "registrazione dispositivo" quando si inserisce
     * una categoria non valida.
     */
    @Test
    public void testRegistrazioneDispositivoCategoriaNonValida() {
        HashMap<String, String> disp = new HashMap<>();
        disp.put("descrizione", "Bello");
        disp.put("numeroSeriale", "12345456122142357");
        disp.put("categoria", "Sfigmomanometro");

        assertEquals(false, serviceMisurazione.registrazioneDispositivo(disp, 1L));
    }

    /**
     * Test del metodo registrazione dispositivo quando si inserisce
     * un dispositivo con tutti i parametri corretti.
     */
    @Test
    public void testRegistrazioneDispositivo_Success() {
        HashMap<String, String> disp = new HashMap<>();
        disp.put("descrizione", "Bello");
        disp.put("numeroSeriale", "12345456122142357");
        disp.put("categoria", "Misuratore di pressione");

        assertEquals(true, serviceMisurazione.registrazioneDispositivo(disp, 1L));
    }



}
