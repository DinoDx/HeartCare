package c15.dev.gestioneComunicazione.controller;

import c15.dev.gestioneComunicazione.service.GestioneComunicazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(path =  "comunicazione")
@CrossOrigin(originPatterns = "*", allowedHeaders = "*", allowCredentials = "true")
public class GestioneComunicazioneController {
    @Autowired
    private GestioneComunicazioneService service;

    @GetMapping(path = "invioNotifica", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> invioNotifica() {
        String messaggio = "notifica di prova";
        Long id = 1L;

        System.out.println("EVENTO ANCORA");


        return service.invioNotifica(messaggio, id);
    }

    @PostMapping(path = "invioEmail")
    public void invioEmail() {
        System.out.println("almeno qui va");
        String messaggio = "notifica di prova";
        service.invioEmail(messaggio, "leopoldo.todiscozte@gmail.com");
    }


    @PostMapping(path = "invioNota")
    public void invioNota(){
        System.out.println("Ciao sono qui");
        String messaggio = "Ciao";
        service.invioNota(messaggio,5L,1L);
    }

}
