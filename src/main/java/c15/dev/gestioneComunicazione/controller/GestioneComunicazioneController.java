package c15.dev.gestioneComunicazione.controller;

import c15.dev.gestioneComunicazione.service.GestioneComunicazioneService;
import c15.dev.gestioneUtente.service.GestioneUtenteService;
import c15.dev.model.entity.Nota;
import c15.dev.model.entity.UtenteRegistrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path =  "/comunicazione")
@CrossOrigin
public class GestioneComunicazioneController {
    @Autowired
    private GestioneComunicazioneService service;

    @Autowired
    private GestioneUtenteService utenteService;

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


    /*@GetMapping(path = "/invioNota/{idMittente}/{idDestinatario}/{nota}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> invioNota(@PathVariable("idMittente")long idMittente,
                                                   @PathVariable("idDestinatario")long idDestinatario,
                                                   @PathVariable("nota")String messaggio){
        System.out.println("Ciao sono qui");
        System.out.println(messaggio);
        Flux<ServerSentEvent<String>> risposta = null;

        //return new ResponseEntity<>(service.invioNota(messaggio,idDestinatario,idMittente), HttpStatus.OK);
        return service.invioNota(messaggio,idDestinatario,idMittente);
    }*/

    @PostMapping(path = "/invioNota")
    public ResponseEntity<Object>  invioNota(@RequestBody HashMap<String,String> nota){
        System.out.println(nota);
        long idDestinatario = Long.parseLong(nota.get("idDestinatario"));
        long idMittente = Long.parseLong(nota.get("idMittente"));
        service.invioNota(nota.get("nota"),idDestinatario,idMittente);
        List<Nota> note = service.findAllNote();
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @PostMapping(path = ("/fetchTutteLeNote"))
    public ResponseEntity<Object> fetchTutteLeNote(@RequestBody HashMap<String,Long> utente){
        System.out.println(utente.get("idMittente"));
        Long id = utente.get("idMittente");
        return new ResponseEntity<>(service.findNoteByIdUtente(id), HttpStatus.OK);
    }

    @PostMapping(path = "/getMedico")
    public ResponseEntity<Object> fetchMedcioPerPaziente(@RequestBody HashMap<String,Long> utente){
        Long id = utente.get("idMittente");
        return  new ResponseEntity<>(utenteService.findMedicoByPaziente(id),HttpStatus.OK);
    }

}
