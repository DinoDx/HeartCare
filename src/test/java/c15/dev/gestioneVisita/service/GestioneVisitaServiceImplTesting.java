package c15.dev.gestioneVisita.service;

import c15.dev.HeartCareApplication;
import c15.dev.HeartCareApplicationTests;
import c15.dev.model.dao.VisitaDAO;
import c15.dev.model.entity.Indirizzo;
import c15.dev.model.entity.Medico;
import c15.dev.model.entity.Paziente;
import c15.dev.model.entity.Visita;
import c15.dev.model.entity.enumeration.StatoVisita;
import jdk.jfr.Period;
import lombok.SneakyThrows;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Alessandro Zoccola
 * Creato il 03/02/2023
 * Classe di test per la gestione service di visita
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = HeartCareApplicationTests.class)
public class GestioneVisitaServiceImplTesting {
    /**
     * Mock dao per le operazioni CRUD della visita
     */
    @InjectMocks
    private GestioneVisitaServiceImpl service;
    private Paziente paziente;
    private Medico medico;
    private Indirizzo indirizzo;

    private Visita visita;
    private Visita visitaNoPaziente;
    private Visita visitaNoMedico;
    private Visita visitaNoIndirizzo;

    private Visita visitaDataPassata;

    @Mock
    private VisitaDAO visitaDAO;

    @SneakyThrows
    @BeforeEach
    public void setup(){

        paziente = new Paziente(LocalDate.now(),
                                "ZCCLSN01L05H803M",
                                "+393272349622",
                                "Wpasswd1!%",
                                "alessz@libero.it",
                                "Alessandro",
                                "Zoccola",
                                "M"
                );
        medico = new Medico(LocalDate.now(),
                            "RBTDRN88M06H703I",
                            "+393202334569",
                            "Apasswd1!%",
                            "adrien@libero.it",
                            "Adrien",
                            "Rabiot",
                            "M"
                );
        indirizzo = new Indirizzo("Salerno",
                                  "34",
                                  "84090",
                                  "Salerno",
                                  "Via Casa Mia"
                );

        visita = new Visita(LocalDate.now(),
                StatoVisita.PROGRAMMATA,
                medico,
                paziente,
                indirizzo
        );

        visitaNoPaziente = new Visita(LocalDate.now(),
                StatoVisita.PROGRAMMATA,
                medico,
                null,
                indirizzo
        );

        visitaNoMedico = new Visita(LocalDate.now(),
                StatoVisita.PROGRAMMATA,
                null,
                paziente,
                indirizzo
        );

        visitaNoIndirizzo = new Visita(LocalDate.now(),
                StatoVisita.PROGRAMMATA,
                medico,
                paziente,
                null
        );

        visitaDataPassata = new Visita(LocalDate.now().minusDays(1),
                StatoVisita.PROGRAMMATA,
                medico,
                paziente,
                indirizzo);

    }

    @Test
    public void testAggiuntaVisita(){
        assertEquals(true, service.aggiuntaVisita(visita));
    }

    @Test
    public void testAggiuntaVisitaNoPaziente(){
        assertEquals(false, service.aggiuntaVisita(visitaNoPaziente));
    }

    @Test
    public void testAggiuntaVisitaNoMedico(){
        assertEquals(false, service.aggiuntaVisita(visitaNoMedico));
    }

    @Test
    public void testAggiuntaVisitaNoIndirizzo(){
        assertEquals(false, service.aggiuntaVisita(visitaNoIndirizzo));
    }

    @Test
    public void testAggiuntaVisitaDataPassata(){
        assertEquals(false, service.aggiuntaVisita(visitaDataPassata));
    }

}
