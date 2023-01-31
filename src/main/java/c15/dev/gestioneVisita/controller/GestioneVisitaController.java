package c15.dev.gestioneVisita.controller;

import c15.dev.gestioneUtente.service.GestioneUtenteService;
import c15.dev.gestioneVisita.service.GestioneVisitaService;
import c15.dev.model.dto.VisitaDTO;
import c15.dev.model.entity.Indirizzo;
import c15.dev.model.entity.Medico;
import c15.dev.model.entity.Paziente;
import c15.dev.model.entity.Visita;
import c15.dev.model.entity.enumeration.StatoVisita;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

/**
 * @author Leopoldo Todisco.
 * Creato il 05/02/2023.
 * Classe controller per il package gestioneVisita.
 *
 */
@RestController
@RequestMapping("/visite")
public class GestioneVisitaController {
    @Autowired
    private GestioneUtenteService utenteService;
    @Autowired
    private GestioneVisitaService visitaService;
    @Autowired
    private HttpSession session;

    /**
     * Metodo che consente di aggiungere una visita.
     * @param body
     */
    @PostMapping("/crea")
    public void aggiungiVisita(@RequestBody final Map<String, Object> body,
                               HttpServletRequest request){
        Long idPaziente = Long.parseLong(body.get("paziente").toString());
        Paziente paziente = utenteService.findPazienteById(idPaziente);

        Long idIndirizzo = Long.parseLong(body.get("indirizzo").toString());
        Indirizzo indirizzo = visitaService.findIndirizzoById(idIndirizzo);

        LocalDate dataVisita = LocalDate.parse(body.get("data").toString());
        Long idMedico = utenteService.findUtenteByEmail(request.getUserPrincipal().getName()).getId();
        Medico medicoVisita = utenteService.findMedicoById(idMedico);


        if(medicoVisita == null){
            return;
        }

       @Valid Visita visita = new Visita(dataVisita,
                StatoVisita.PROGRAMMATA,
                medicoVisita,
                paziente,
                indirizzo);
        visitaService.aggiuntaVisita(visita);
    }

    /**
     *
     * Metodo che permette di ottenere la lista di tutte le visite.
     * in stato "programmata".
     * @return Response al cui interno vi si ritrova la lista.
     * @param request è la richiesta.
     */
    @PostMapping("ottieni")
    public ResponseEntity<Object>
    visiteByUser(final HttpServletRequest request) {
        var email = request.getUserPrincipal().getName();
        if(utenteService.findUtenteByEmail(email) == null) {
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

    @PostMapping("/modificaDataVisita")
    public void modificaDataVisita(@RequestBody final Map<String, String> body){
          long idVisita =  Long.parseLong(body.get("idVisita"));
          Visita visita = visitaService.findById(idVisita);
          LocalDate datax = LocalDate.parse(body.get("nuovaData"));
          visitaService.cambiaData(visita,datax);
    }

}
