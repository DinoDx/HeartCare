package c15.dev.gestioneVisita.controller;

import c15.dev.gestioneUtente.service.GestioneUtenteService;
import c15.dev.gestioneVisita.service.GestioneVisitaService;
import c15.dev.model.dto.VisitaDTO;
import c15.dev.model.entity.Indirizzo;
import c15.dev.model.entity.Medico;
import c15.dev.model.entity.Paziente;
import c15.dev.model.entity.Visita;
import c15.dev.model.entity.enumeration.StatoVisita;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author Leopoldo Todisco.
 * Creato il 05/02/2023.
 * Classe controller per il package gestioneVisita.
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/visite")
public class GestioneVisitaController {

    /**
     * Service per le operazioni dell'utente.
     */
    @Autowired
    private GestioneUtenteService utenteService;

    /**
     * Service per le operazioni legate alla visita.
     */
    @Autowired
    private GestioneVisitaService visitaService;

    /**
     * Sessione.
     */
    @Autowired
    private HttpSession session;

    /**
     * Metodo che consente di aggiungere una visita.
     * @param body che contiene i dati della visita.
     * @param request contiene la richiesta.
     * @return visita creata.
     */
    @PostMapping("/crea")
    public ResponseEntity<Object> aggiungiVisita(@RequestBody final
                                                     Map<String, Object> body,
                               final HttpServletRequest request) {
        Long idPaziente = Long.parseLong(body.get("paziente").toString());
        Paziente paziente = utenteService.findPazienteById(idPaziente);

        Long idIndirizzo = Long.parseLong(body.get("indirizzo").toString());
        Indirizzo indirizzo = visitaService.findIndirizzoById(idIndirizzo);

        LocalDate dataVisita = LocalDate.parse(body.get("data").toString());
        Long idMedico = utenteService
                .findUtenteByEmail(request.getUserPrincipal()
                        .getName()).getId();
        Medico medicoVisita = utenteService.findMedicoById(idMedico);


        if (medicoVisita == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

       @Valid Visita visita = new Visita(dataVisita,
                StatoVisita.PROGRAMMATA,
                medicoVisita,
                paziente,
                indirizzo);
        if (visitaService.aggiuntaVisita(visita)) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

    }

    /**
     *
     * Metodo che permette di ottenere la lista di tutte le visite.
     * in stato "programmata".
     * @param request è la richiesta.
     * @return Response al cui interno vi si ritrova la lista.
     */
    @PostMapping("ottieni")
    public ResponseEntity<Object>
    visiteByUser(final HttpServletRequest request) {
        var email = request.getUserPrincipal().getName();
        if (utenteService.findUtenteByEmail(email) == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        List<Visita> list = visitaService.findVisiteProgrammateByUser(email);

        var resultList = (List<VisitaDTO>) list.stream()
                .map(v -> VisitaDTO.builder()
                        .idPaziente(v.getPaziente().getId())
                        .data(v.getData())
                        .nomePaziente(v.getPaziente().getNome())
                        .cognomePaziente(v.getPaziente().getCognome())
                        .genere(v.getPaziente().getGenere())
                        .numeroTelefono(v.getPaziente().getNumeroTelefono())
                        .viaIndirizzo(v.getIndirizzoVisita().getVia())
                        .nCivico(v.getIndirizzoVisita().getNCivico())
                        .provincia(v.getIndirizzoVisita().getProvincia())
                        .comune(v.getIndirizzoVisita().getCitta())
                        .idVisita(v.getId())
                        .build())
                .toList();

        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    /**
     *
     * Metodo che permette di modificare la data di una visita.
     * @param body contiene i dati della visita.
     */
    @PostMapping("/modificaDataVisita")
    public void modificaDataVisita(@RequestBody final
                                       Map<String, String> body) {
          long idVisita =  Long.parseLong(body.get("idVisita"));
          Visita visita = visitaService.findById(idVisita);
          LocalDate datax = LocalDate.parse(body.get("nuovaData"));
          visitaService.cambiaData(visita, datax);
    }

}
