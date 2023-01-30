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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
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
    public void aggiungiVisita(@RequestBody final Map<String, Object> body) {
        Date dataVisita = (Date) body.get("data");
        Long idPaziente = (Long) body.get("paziente");
        Paziente paziente = utenteService.findPazienteById(idPaziente);
        Indirizzo indirizzo = (Indirizzo) body.get("indirizzo");
        // TO DO potrebbe essere necessario creare un indirizzo.
        // siccome viene passato un id.
        // va testato con il frontend.

        Long idMedico = (Long) session.getAttribute("utenteLoggato");
        Medico medicoVisita = utenteService.findMedicoById(idMedico);

        //se l'id non corrisponde a un medico si va in corto circuito.
        if(medicoVisita == null){
            return;
        }

        @Valid Visita visita = new Visita(LocalDate.of(
                dataVisita.getDay(),
                dataVisita.getMonth(),
                dataVisita.getYear()),
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
                        .nCivico(v.getIndirizzoVisita().getnCivico())
                        .comune(v.getIndirizzoVisita().getCitta())
                        .build())
                .toList();

        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }
}
