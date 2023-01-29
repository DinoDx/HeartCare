package c15.dev.gestioneComunicazione.controller;

import c15.dev.gestioneComunicazione.service.GestioneComunicazioneService;
import c15.dev.gestioneUtente.service.GestioneUtenteService;
import c15.dev.model.entity.Nota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;

/**
 * @author Leopoldo Todisco, Carlo Venditto.
 * Classe controller per il sottosistema di comunicazione.
 */
@RestController
@RequestMapping(path =  "/comunicazione")
@CrossOrigin
public class GestioneComunicazioneController {
    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private GestioneComunicazioneService service;

    @Autowired
    private GestioneUtenteService utenteService;

    @PostMapping(path = "invioEmail")
    public void invioEmail() {
        System.out.println("almeno qui va");
        String messaggio = "notifica di prova";
        service.invioEmail(messaggio, "leopoldo.todiscozte@gmail.com");
    }

    @PostMapping(path = "/invioNota")
    public ResponseEntity<Object>
    invioNota(@RequestBody final HashMap<String,String> nota) {
        System.out.println(nota);
        long idDestinatario = Long.parseLong(nota.get("idDestinatario"));
        long idMittente = Long.parseLong(nota.get("idMittente"));
        service.invioNota(nota.get("nota"),idDestinatario,idMittente);
        List<Nota> note = service.findAllNote();
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @PostMapping(path = ("/fetchTutteLeNote"))
    public ResponseEntity<Object>
    fetchTutteLeNote(@RequestBody final HashMap<String,Long> utente) {
        System.out.println(utente.get("idMittente"));
        Long id = utente.get("idMittente");
        return new ResponseEntity<>(service.findNoteByIdUtente(id),
                                    HttpStatus.OK);
    }

    @PostMapping(path = "/getMedico")
    public ResponseEntity<Object>
    fetchMedcioPerPaziente(@RequestBody final HashMap<String,Long> utente) {
        Long id = utente.get("idMittente");
        return  new ResponseEntity<>(utenteService.findMedicoByPaziente(id),HttpStatus.OK);
    }
}
