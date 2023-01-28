package c15.dev.utils;

import c15.dev.gestioneMisurazione.service.GestioneMisurazioneService;
import c15.dev.gestioneUtente.service.GestioneUtenteService;
import c15.dev.gestioneVisita.service.GestioneVisitaService;
import c15.dev.model.entity.*;
import c15.dev.registrazione.service.RegistrazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static c15.dev.model.entity.enumeration.StatoVisita.EFFETTUATA;
import static c15.dev.model.entity.enumeration.StatoVisita.PROGRAMMATA;

/**
 * @author Leopoldo Todisco.
 * Creato il 05/01/2023.
 * Questa classe fornisce i metodi di inizializzazione e distruzione del DB.
 * Attraverso l'uso di questa classe tutto il team lavora con lo stesso DB.
 */
@Component
@Scope("singleton")
public class DBPopulator {
    @Autowired
    private RegistrazioneService regService;
    @Autowired
    private GestioneUtenteService userService;
    @Autowired
    private GestioneMisurazioneService gestioneMisurazioneService;
    @Autowired
    private GestioneVisitaService gestioneVisitaService;
    private List<Paziente> pazientiList = new ArrayList<>();
    private List<Medico> medicoList = new ArrayList<>();
    private List<Misurazione> misurazioniList = new ArrayList<>();
    private List<Indirizzo> indirizzoList = new ArrayList<>();
    private List<Visita> visitaList = new ArrayList<>();

    /**
     * Metodo post construct, viene avviato dal container automaticamente,
     * dopo che viene inizializzata la classe.
     * Si occupa di inserire elementi nel datatabase.
     * @throws Exception
     */
    @PostConstruct
    private void populateDB() throws Exception {
        LocalDate dataNascita = LocalDate.of(2000, 11, 18);
        /*
         * In questa sezione si vanno a instanziare i pazienti
         * per poi inserirli usando il service.
         * */
        Paziente paz1 = new Paziente(dataNascita,
                "PDSLPD08E18C129Y",
                "+393887124900",
                "Wpasswd1!%",
                "giuseppegiordano@libero.it",
                "Giuseppe",
                "Giordano",
                "M");

        Paziente paz2 = new Paziente(dataNascita,
                "PDSLPH00E18C129A",
                "+393887122221",
                "Wpasswd2!%",
                "pinomecca@libero.it",
                "Pino",
                "Mecca",
                "M");
        Paziente paz3 = new Paziente(dataNascita,
                "PPSLWD10E18C128A",
                "+393887124321",
                "Wpasswd2!%",
                "carloidea@libero.it",
                "Carlo",
                "Idea",
                "M");

        pazientiList.addAll(List.of(paz1, paz2, paz3));
        pazientiList.stream().forEach(paz -> {
            try {
                regService.registraPaziente(paz);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        /*
         * In questa sezione si istanziano medici.
         **/
        Medico med1 = new Medico(dataNascita,
                "PDSLPD00E19C139A",
                "+393809123300",
                "Apasswd1!%",
                "alessandrozoccola@libero.it",
                "Alessandro",
                "Zoccola",
                "M");

        Medico med2 = new Medico(dataNascita,
                "PDSLPD44E19C139U",
                "+393809233322",
                "Apasswd1!%",
                "vincenzoarnone@libero.it",
                "Vincenzo",
                "Arnone",
                "M");

        Medico med3 = new Medico(dataNascita,
                "PAFGPD00E19C139T",
                "+393809123322",
                "Apasswd1!%",
                "paoladematteo@libero.it",
                "Paola",
                "De Matteo",
                "F");

        medicoList.addAll(List.of(med1, med2, med3));
        medicoList.stream().forEach(m -> {
            try {
                regService.registraMedico(m);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        DispositivoMedico d1 = new DispositivoMedico(LocalDate.of(2023, 1, 22),
                "funziona ti prego",
                "hbdsdsdhjsdfhjdsdsdfhjdfhsdfsd",
                true,
                "Misuratore di pressione",
                paz1
        );
        DispositivoMedico d2 = new DispositivoMedico(LocalDate.of(2023, 1, 22),
                "funziona ti prego",
                "hbdsdsdhjsdfhjgsdsdfhjdfhsdfsd",
                true,
                "Misuratore di pressione",
                paz1
        );
        gestioneMisurazioneService.registrazioneDispositivo(d1, 1);
        gestioneMisurazioneService.registrazioneDispositivo(d2, 1);
        LocalDate data2 = LocalDate.of(2023, 01, 18);

        MisurazionePressione mis1 = new MisurazionePressione(data2,
                paz1,
                d1,
                120,
                110.0,
                89.0,
                99.5
        );

        gestioneMisurazioneService.save(mis1);
    }

    /**
     * Metodo pre-destroy, viene avviato dal container automaticamente
     * prima che l'oggetto relativo alla classe sia deallocato dal GC.
     * Si occupa di inserire elementi nel datatabase.
     */
    @PreDestroy
    public void clearDB(){
        /*
        * In questo blocco si vanno a eliminare dal DB tutti i pazienti
        * definiti precedentemente.
        **/
        pazientiList.stream()
                .map(paziente -> paziente.getId())
                .forEach(idPaz -> userService.rimuoviPaziente(idPaz));

        /*
        * In questo blocco si vanno a eliminare dal DB tutti i pazienti
        * definiti precedentemente.
        **/
        medicoList.stream()
                .map(medico -> medico.getId())
                .forEach(id -> userService.rimuoviMedico(id));


    }



}
