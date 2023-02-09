package c15.dev.utils;

import c15.dev.gestioneMisurazione.service.GestioneMisurazioneService;
import c15.dev.gestioneUtente.service.GestioneUtenteService;
import c15.dev.gestioneVisita.service.GestioneVisitaService;
import c15.dev.model.entity.Medico;
import c15.dev.model.entity.Paziente;
import c15.dev.model.entity.Misurazione;
import c15.dev.model.entity.MisurazioneCoagulazione;
import c15.dev.model.entity.MisurazioneEnzimiCardiaci;
import c15.dev.model.entity.MisurazioneGlicemica;
import c15.dev.model.entity.MisurazioneHolterECG;
import c15.dev.model.entity.MisurazionePressione;
import c15.dev.model.entity.MisurazioneSaturazione;
import c15.dev.model.entity.Admin;
import c15.dev.model.entity.DispositivoMedico;
import c15.dev.model.entity.Visita;
import c15.dev.model.entity.Indirizzo;
import c15.dev.registrazione.service.RegistrazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
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
    /**
     * service di registrazione.
     */
    @Autowired
    private RegistrazioneService regService;
    /**
     * service di gestione utente.
     */
    @Autowired
    private GestioneUtenteService userService;
    /**
     * service di gestione misurazione.
     */
    @Autowired
    private GestioneMisurazioneService gestioneMisurazioneService;
    /**
     * service di gestione visita.
     */
    @Autowired
    private GestioneVisitaService gestioneVisitaService;
    private List<Paziente> pazientiList = new ArrayList<>();
    private List<Medico> medicoList = new ArrayList<>();

    private List<Admin> adminList = new ArrayList<>();
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
                "Wpasswd1!%",
                "pinomecca@libero.it",
                "Pino",
                "Mecca",
                "M");
        Paziente paz3 = new Paziente( dataNascita,
                "PPSLWD10E18C128A",
                "+393887124321",
                "Wpasswd1!%",
                "carloidea@libero.it",
                "Carlo",
                "Idea",
                "M");
        String confermaPass = "Wpasswd1!%";

        pazientiList.addAll(List.of(paz1, paz2, paz3));
        pazientiList.stream().forEach(paz -> {
            try {
                regService.registraPaziente(paz, confermaPass);
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


        Admin a1 = new Admin(dataNascita,
                "PDSLPD08E18C129Q",
                "+393887124110",
                "Wpasswd1!%",
                "fabiola@libero.it",
                "Fabiola",
                "Valorant",
                "F");

        adminList.addAll(List.of(a1));
        adminList.stream().forEach(a -> {
            try {
                regService.registraAdmin(a);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //In questa sezione si istanziano i dispositivi per la misurazione della pressione

        DispositivoMedico d1 = new DispositivoMedico(LocalDate.of(2023, 1, 22),
                "Sfigmomanometro da braccio",
                "DFsUegbP5K7AMm6aPUNJ8Dmyv2KV4S",
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

        DispositivoMedico d3 = new DispositivoMedico(LocalDate.of(2021,5,5),
                "Pic sfigmomanometro aneroide",
                "96XD5UqTtemJNZSBkS7YkNWUusnndC",
                true,
                "Misuratore di pressione",
                paz3
        );

        //Qui ci sono i dispositivi per gli enzimi cardiaci

        DispositivoMedico d4 = new DispositivoMedico(LocalDate.of(2021,8,15),
                "Test mioglobina e troponina tramite sangue",
                "rABq8J743PnBZkgseN8cXeEZaKF5Er",
                true,
                "Enzimi cardiaci",
                paz1
        );

        DispositivoMedico d5 = new DispositivoMedico(LocalDate.of(2021,3,15),
                "Analisi TSH",
                "e5Ax4yjfR9er4NNZrjNmvYK9bEvGHa",
                true,
                "Enzimi cardiaci",
                paz2
        );

        DispositivoMedico d6 = new DispositivoMedico(LocalDate.of(2021,4,15),
                "Enzimi cardiaci e biomarcatori",
                "GjqM8rZf6uGBPAPFtPn3JahUtp2xRJ",
                true,
                "Enzimi cardiaci",
                paz3
        );

        //Qui ci sono i dispositivi medici per il saturimetro

        DispositivoMedico d7 = new DispositivoMedico(LocalDate.of(2021,9,15),
                "Beurer Po 40 Saturimetro Per Il Monitoraggio Della Saturazione",
                "uGaT6BDbHFzLhxSx7Y32X9FisiHK4k",
                true,
                "Saturimetro",
                paz1
        );

        DispositivoMedico d8 = new DispositivoMedico(LocalDate.of(2021,10,15),
                "saturazione di ossigeno, livelli di ossigeno nel sangue",
                "w5bgHth7U8uprjceemBcJLTN2jD4qF",
                true,
                "Saturimetro",
                paz2
        );

        DispositivoMedico d9 = new DispositivoMedico(LocalDate.of(2021,11,15),
                "Saturimetro per Frequenza Cardiaca",
                "XqLVLGCK9a7ymm92v3CodKT7YjBEBu",
                true,
                "Saturimetro",
                paz3
        );

        //Qui ci sono i dispositivi per il Coagulometro

        DispositivoMedico d10 = new DispositivoMedico(LocalDate.of(2021,12,25),
                "Sinocare Misuratore Glicemia, Diabete",
                "9m7v4BXVhjNpayahocURLUk2dc2unX",
                true,
                "Coagulometro",
                paz1
        );

        DispositivoMedico d11 = new DispositivoMedico(LocalDate.of(2021,11,26),
                "Coagulometro per il monitoraggio della Protrombina",
                "Jzb2YgiG2KfN6BbJrTQcS8C8Rv9BbN",
                true,
                "Coagulometro",
                paz2
        );

        DispositivoMedico d12 = new DispositivoMedico(LocalDate.of(2021,11,27),
                "Roche CoaguChek INRange Coaugulometro Misuratore INR",
                "eK7RgTKMtYkovKndJPQxEqaCkB6DxZ",
                true,
                "Coagulometro",
                paz3
        );

        //Qui ci sono i dispositivi per ElettroCardioGramma

        DispositivoMedico d13 = new DispositivoMedico(LocalDate.of(2021,7,27),
                "MINI ECG PALMARE PRINCE 180BCW CARDIO B",
                "ooNweTyrhBvh4nF3y59MvcnmovDLXe",
                true,
                "ECG",
                paz1
        );

        DispositivoMedico d14 = new DispositivoMedico(LocalDate.of(2021,7,1),
                "ELETTROCARDIOGRAFO ECG CONTEC 600G",
                "M8HnYAXtVsFgFdBfyhEGf9NDdHGyaS",
                true,
                "ECG",
                paz2
        );

        DispositivoMedico d15 = new DispositivoMedico(LocalDate.of(2021,11,2),
                "MONITOR MULTIPARAMETRICO PC-300",
                "jAn4kwrZoW5p65Yg8qEFnUnU6HJjjm",
                true,
                "ECG",
                paz3
        );

        //Qui ci sono i dispositivi per la misurazione glicemica

        DispositivoMedico d16 = new DispositivoMedico(LocalDate.of(2021,4,10),
                "MISURATORE GLICEMIA GLUCOMETRO GIMA",
                "vC28JEE2pS7ojzzdHZDJm6qqFDeDhW",
                true,
                "Misuratore glicemico",
                paz1
        );

        DispositivoMedico d17 = new DispositivoMedico(LocalDate.of(2021,4,9),
                "MISURATORE LETTORE EMATICO LUX",
                "7r3C8LyBmzm5RzsHSnFVeuimjAs7iB",
                true,
                "Misuratore glicemico",
                paz2
        );

        DispositivoMedico d18 = new DispositivoMedico(LocalDate.of(2021,4,29),
                "Glucometro Multicare-In Completo",
                "GAsapAPXsWAK8o7aEgfjhLbgBmToty",
                true,
                "Misuratore glicemico",
                paz3
        );

        gestioneMisurazioneService.registrazioneDispositivo(d1, 1);
        gestioneMisurazioneService.registrazioneDispositivo(d2, 2);
        gestioneMisurazioneService.registrazioneDispositivo(d3, 3);
        gestioneMisurazioneService.registrazioneDispositivo(d4, 1);
        gestioneMisurazioneService.registrazioneDispositivo(d5, 2);
        gestioneMisurazioneService.registrazioneDispositivo(d6, 3);
        gestioneMisurazioneService.registrazioneDispositivo(d7, 1);
        gestioneMisurazioneService.registrazioneDispositivo(d8, 2);
        gestioneMisurazioneService.registrazioneDispositivo(d9, 3);
        gestioneMisurazioneService.registrazioneDispositivo(d10, 1);
        gestioneMisurazioneService.registrazioneDispositivo(d11, 2);
        gestioneMisurazioneService.registrazioneDispositivo(d12, 3);
        gestioneMisurazioneService.registrazioneDispositivo(d13, 1);
        gestioneMisurazioneService.registrazioneDispositivo(d14, 2);
        gestioneMisurazioneService.registrazioneDispositivo(d15, 3);
        gestioneMisurazioneService.registrazioneDispositivo(d16, 1);
        gestioneMisurazioneService.registrazioneDispositivo(d17, 2);
        gestioneMisurazioneService.registrazioneDispositivo(d18, 3);

        gestioneMisurazioneService.registrazioneDispositivo(d2, 1);
        LocalDate data2 = LocalDate.of(2023, 01, 18);
        LocalDate data3 = LocalDate.of(2022, 02, 18);
        LocalDate data4 = LocalDate.of(2022, 03, 18);
        LocalDate data5 = LocalDate.of(2022, 04, 18);
        LocalDate data6 = LocalDate.of(2022, 05, 18);
        LocalDate data7 = LocalDate.of(2022, 06, 18);

        //Misurazioni per la pressione
        MisurazionePressione mis1 = new MisurazionePressione(data2,
                paz1,
                d1,
                120,
                110.0,
                80.0,
                90.5
        );

        MisurazionePressione mis2 = new MisurazionePressione(data2,
                paz2,
                d2,
                90,
                120.0,
                100.0,
                99.5
        );

        MisurazionePressione mis3 = new MisurazionePressione(data2,
                paz3,
                d3,
                80,
                99.0,
                89.0,
                85.5
        );

        MisurazionePressione mis20 = new MisurazionePressione(data2,
                paz1,
                d1,
                85,
                95.0,
                86.0,
                82.5
        );

        //Misurazioni per gli enzimi cardiaci

        MisurazioneEnzimiCardiaci mis4 = new MisurazioneEnzimiCardiaci(data3,
                paz1,
                d4,
                50.0,
                60.0,
                0.6
        );

        MisurazioneEnzimiCardiaci mis5 = new MisurazioneEnzimiCardiaci(data3,
                paz2,
                d5,
                60.0,
                100.0,
                4.0
        );

        MisurazioneEnzimiCardiaci mis6 = new MisurazioneEnzimiCardiaci(data3,
                paz3,
                d6,
                70.0,
                140.0,
                7.8
        );

        //Misurazioni con saturimetro
        MisurazioneSaturazione mis7 = new MisurazioneSaturazione(data4,
                paz1,
                d7,
                60,
                80.0
        );

        MisurazioneSaturazione mis8 = new MisurazioneSaturazione(data4,
                paz2,
                d8,
                70,
                90.0
        );

        MisurazioneSaturazione mis9 = new MisurazioneSaturazione(data4,
                paz3,
                d9,
                80,
                99.0
        );

        //Misurazioni con coagulometro
        MisurazioneCoagulazione mis10 = new MisurazioneCoagulazione(data5,
                paz1,
                d10,
                8.5,
                1.0
        );

        MisurazioneCoagulazione mis11 = new MisurazioneCoagulazione(data5,
                paz2,
                d11,
                11.3,
                1.0
        );

        MisurazioneCoagulazione mis12 = new MisurazioneCoagulazione(data5,
                paz3,
                d12,
                13.6,
                1.0
        );

        //Misurazioni per ElettroCardioGramma
        MisurazioneHolterECG mis13 = new MisurazioneHolterECG(data6,
                paz1,
                d13,
                70.0,
                70,
                0.10,
                0.10,
                490.0

        );

        MisurazioneHolterECG mis14 = new MisurazioneHolterECG(data6,
                paz2,
                d14,
                80.0,
                80,
                0.09,
                0.15,
                350.0

        );

        MisurazioneHolterECG mis15 = new MisurazioneHolterECG(data6,
                paz3,
                d15,
                90.0,
                90,
                0.08,
                0.19,
                450.0

        );

        //Misurazioni per Glicemia
        MisurazioneGlicemica mis16 = new MisurazioneGlicemica(data7,
                paz1,
                d16,
                80,
                150,
                200
        );

        MisurazioneGlicemica mis17 = new MisurazioneGlicemica(data7,
                paz2,
                d17,
                90,
                200,
                300
        );

        MisurazioneGlicemica mis18 = new MisurazioneGlicemica(data7,
                paz3,
                d18,
                100,
                250,
                400
        );

        MisurazioneGlicemica mis19 = new MisurazioneGlicemica(data7,
                paz1,
                d16,
                101,
                230,
                399
        );

        misurazioniList.addAll(List.of(mis1, mis2, mis3, mis4, mis5,
                mis6, mis7, mis8, mis9, mis10, mis11, mis12, mis13,
                mis14, mis15, mis16, mis17, mis18, mis19, mis20));
        misurazioniList.stream()
                        .forEach(mis -> gestioneMisurazioneService.save(mis));


        /*
         * In questa sezione creano gli indirizzi.
         **/

        Indirizzo ind1 = new Indirizzo("Salerno",
                "12",
                "81043",
                "SA",
                "Via roma"
        );

        Indirizzo ind2 = new Indirizzo("Caserta",
                "1",
                "80056",
                "CE",
                "Via nazionale appia"
        );

        Indirizzo ind3 = new Indirizzo("Napoli",
                "5",
                "80017",
                "NA",
                "Via campi flegrei"
        );

        Indirizzo ind4 = new Indirizzo("Nocera Inferiore",
                "2",
                "80069",
                "SA",
                "Via 4 novembre"
        );

        Indirizzo ind5 = new Indirizzo("Capua",
                "7",
                "81043",
                "CE",
                "Via ponte barbieri"
        );

        Indirizzo ind6 = new Indirizzo("Pompei",
                "56",
                "80017",
                "NA",
                "Via del vesuvio"
        );
        indirizzoList.addAll(List.of(ind1, ind2, ind3,
                ind4, ind5, ind6));
        indirizzoList.stream().forEach(i -> userService.registraIndirizzo(i));

        userService.assegnaIndirizzoAdUtente(1, ind1);
        userService.assegnaIndirizzoAdUtente(2, ind2);
        userService.assegnaIndirizzoAdUtente(3, ind3);
        userService.assegnaIndirizzoAdUtente(4, ind4);
        userService.assegnaIndirizzoAdUtente(5, ind5);
        userService.assegnaIndirizzoAdUtente(6, ind6);

        /*
            In questa sezione si assegnano i medici ai pazienti
         */
        userService.assegnaMedicoAPaziente(4, 1);
        userService.assegnaMedicoAPaziente(5, 2);
        userService.assegnaMedicoAPaziente(6, 3);

        /*
            In questa sezione si assegnano i caregiver ai pazienti
         */
        userService.assegnaCaregiver(1l,
                "caregiver1@libero.it",
                "Mario",
                "Cicalese"
        );

        userService.assegnaCaregiver(2l,
                "caregiver2@libero.it",
                "Gianluca",
                "Vialli"
        );

        userService.assegnaCaregiver(3l,
                "caregiver3@libero.it",
                "Massimiliano",
                "Allegri"
        );

        /*
            In questa sezione si istanziano le visite
         */
        LocalDate dataVisita1 = LocalDate.of(2023, 02, 18);
        LocalDate dataVisita2 = LocalDate.of(2023, 03, 20);
        LocalDate dataVisita3 = LocalDate.of(2023, 04, 22);
        LocalDate dataVisita4 = LocalDate.of(2023, 05, 6);
        LocalDate dataVisita5 = LocalDate.of(2023, 06, 8);
        LocalDate dataVisita6 = LocalDate.of(2023, 07, 10);

        Visita v1 = new Visita(dataVisita1,
                PROGRAMMATA,
                med1,
                paz1,
                ind4
        );

        Visita v2 = new Visita(dataVisita2,
                EFFETTUATA,
                med1,
                paz2,
                ind4
        );

        Visita v3 = new Visita(dataVisita3,
                PROGRAMMATA,
                med2,
                paz3,
                ind5
        );

        Visita v4 = new Visita(dataVisita4,
                EFFETTUATA,
                med2,
                paz1,
                ind5
        );

        Visita v5 = new Visita(dataVisita5,
                PROGRAMMATA,
                med3,
                paz2,
                ind6
        );

        Visita v6 = new Visita(dataVisita6,
                EFFETTUATA,
                med3,
                paz1,
                ind6
        );

        visitaList.addAll(List.of(v1, v2, v3, v4, v5, v6));
        visitaList.stream()
                .forEach(v -> gestioneVisitaService.aggiuntaVisita(v));

    }
}
