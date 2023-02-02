package c15.dev.gestioneComunicazione.controller;

import c15.dev.gestioneComunicazione.service.GestioneComunicazioneService;
import c15.dev.gestioneUtente.service.GestioneUtenteService;
import c15.dev.model.dto.NotaDTO;
import c15.dev.model.entity.Nota;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Service per le operazioni legate alla comunicazione.
     */
    @Autowired
    private GestioneComunicazioneService service;

    /**
     * Service per le operazioni legate all'utente.
     */
    @Autowired
    private GestioneUtenteService utenteService;

    /**
     * Metodo che inva una mail.
     */
    @PostMapping(path = "invioEmail")

    public void invioEmail() {
        String messaggio = "notifica di prova";
        service.invioEmail(messaggio, "leopoldo.todiscozte@gmail.com");
    }

    /**
     * Metodo che invia una nota.
     * @param nota dati della nota da inviare.
     * @return ResponseEntity è la response che sarà fetchata dal frontend.
     */
    @PostMapping(path = "/invioNota")
    public ResponseEntity<Object>
    invioNota(@RequestBody final HashMap<String,String> nota) {
        long idDestinatario = Long.parseLong(nota.get("idDestinatario"));
        long idMittente = Long.parseLong(nota.get("idMittente"));
        service.invioNota(nota.get("nota"),idDestinatario,idMittente);
        List<Nota> note = service.findAllNote();
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    /**
     * Metodo che prende tutte le note.
     * @param utente dati dell'utente di cui vogliamo ottenere le note.
     * @return è la response che sarà fetchata dal frontend.
     */
    @PostMapping(path = ("/fetchTutteLeNote"))
    public ResponseEntity<Object>
    fetchTutteLeNote(@RequestBody final HashMap<String,Long> utente) {
        Long id = utente.get("idMittente");
        return new ResponseEntity<>(service.findNoteByIdUtente(id),
                                    HttpStatus.OK);
    }

    /**
     * Metodo che permette di ottenere la lista di tutte le note.
     * in stato "non letta".
     * @param request la richiesta.
     * @return Response al cui interno c'è la lista di note.
     */

    @PostMapping("/nuoveNote")
    public ResponseEntity<Object> noteByUser(
            final HttpServletRequest request) {
        var email = request.getUserPrincipal().getName();
        if(utenteService.findUtenteByEmail(email)==null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        List<Nota> list = service.findNoteNonLetteByUser(email);

        var result = list.stream()
                .map(n -> NotaDTO.builder()
                        .nome(n.getMedico().getNome())
                        .messaggio(n.getContenuto()).build()).toList();

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    /**
     * Metodo che prende tutti i medici di ogni paziente.
     * @param utente contiene i dati dell'utente
     * @return response è la response che sarà fetchata dal frontend.
     */
    @PostMapping(path = "/getMedico")
    public ResponseEntity<Object>
    fetchMedcioPerPaziente(@RequestBody final HashMap<String,Long> utente) {
        Long id = utente.get("idMittente");
        return  new ResponseEntity<>(utenteService.findMedicoByPaziente(id),HttpStatus.OK);
    }
}
