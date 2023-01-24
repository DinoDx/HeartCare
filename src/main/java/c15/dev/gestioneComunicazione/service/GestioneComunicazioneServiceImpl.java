package c15.dev.gestioneComunicazione.service;

import c15.dev.gestioneUtente.service.GestioneUtenteService;
import c15.dev.model.dao.NotaDAO;
import c15.dev.model.dao.NotificaDAO;
import c15.dev.model.entity.Medico;
import c15.dev.model.entity.Nota;
import c15.dev.model.entity.Notifica;
import c15.dev.model.entity.Paziente;
import c15.dev.model.entity.enumeration.Autore;
import c15.dev.model.entity.enumeration.StatoNotifica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

@Service
public class GestioneComunicazioneServiceImpl
        implements GestioneComunicazioneService {
    @Autowired
    private GestioneUtenteService utenteService;
    @Autowired
    private NotificaDAO daoNotifica;
    @Autowired
    private NotaDAO notaDAO;

    @Override
    public Flux<ServerSentEvent<String>> invioNotifica(String messaggio, Long idDestinatario) {


        Notifica n = new Notifica(
                LocalDate.of(2023, 11, 12),
                "Notifica Prova",
                "speriamo funzioni",
                StatoNotifica.NON_LETTA,
                utenteService.findPazienteById(1L)
        );
        daoNotifica.save(n);
  /*
   puoi usare emitter per inviare un singolo evento, altrimenti così ogni 4 secondi invia
   SseEmitter em = new SseEmitter();
        em.send(ServerSentEvent.<String>builder().event("notifica-prova")
                .data(n.getTesto()).build());
                */

        return Flux.just(ServerSentEvent.builder(n.getTesto()).build()).take(1);
    }

    @Override
    public void invioEmail(String messaggio, Long idDestinatario) {

    }

    @Override
    public void invioNota(String messaggio, Long idDestinatario, Long idMittente) {

        Medico m = utenteService.findMedicoById(5L);
        Paziente p = utenteService.findPazienteById(1L);

        Nota nota = new Nota("sassi", LocalDate.of(2022,11,10),
                    Autore.M,StatoNotifica.NON_LETTA, m,p
                );
    }
}
