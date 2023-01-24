package c15.dev.gestioneComunicazione.controller;

import c15.dev.gestioneComunicazione.service.GestioneComunicazioneService;
import c15.dev.gestioneUtente.service.GestioneUtenteService;
import c15.dev.model.dao.NotificaDAO;
import c15.dev.model.entity.Notifica;
import c15.dev.model.entity.enumeration.StatoNotifica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@RestController
@RequestMapping(path =  "comunicazione")
@CrossOrigin(originPatterns = "*", allowedHeaders = "*", allowCredentials = "true")
public class GestioneComunicazioneController {
    @Autowired
    private GestioneComunicazioneService service;
    @Autowired
    private GestioneUtenteService utenteService;
    @Autowired
    private NotificaDAO daoNotifica;

    @GetMapping(path = "invioNotifica", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> invioNotifica(String messaggio,
                                                       Long idDestinatario) {
        Notifica n = new Notifica(
                LocalDate.of(2023,11,12),
                "Notifica prova",
                "tutto ok",
                StatoNotifica.NON_LETTA,
                utenteService.findPazienteById(1L)
        );
        daoNotifica.save(n);

        return Flux.just(ServerSentEvent.builder(n.getTesto()).build()).take(1);
    }
}
