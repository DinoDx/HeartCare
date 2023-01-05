package c15.dev.gestioneVisita.controller;

import c15.dev.gestioneUtente.service.GestioneUtenteService;
import c15.dev.gestioneVisita.service.GestioneVisitaService;
import c15.dev.model.entity.Indirizzo;
import c15.dev.model.entity.Medico;
import c15.dev.model.entity.Paziente;
import c15.dev.model.entity.Visita;
import c15.dev.model.entity.enumeration.StatoVisita;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

/**
 * @author Leopoldo Todisco.
 * Creato il 05/02/2023.
 * Classe controller per il package gestioneVisita.
 *
 */
@RestController
@RequestMapping("/elencoVisite")
public class GestioneVisitaController {
    @Autowired
    private GestioneUtenteService utenteService;
    @Autowired
    private GestioneVisitaService visitaService;
    @Autowired
    private HttpSession session;

    @PostMapping("/crea")
    public void aggiungiVisita(@RequestBody Map<String, Object> body) {
        Date dataVisita = (Date) body.get("data");
        Long idPaziente = (Long) body.get("paziente");
        Paziente paziente = utenteService.findPazienteById(idPaziente);
        Indirizzo indirizzo = (Indirizzo) body.get("indirizzo");
        // TO DO potrebbe essere necessario creare un indirizzo siccome viene passato un id.
        // va testato con il frontend.

        Long idMedico = (Long) session.getAttribute("utenteLoggato");
        Medico medicoVisita = utenteService.findMedicoById(idMedico);

        //se l'id non corrisponde a un medico si va in corto circuito.
        if(medicoVisita == null){
            return;
        }

        @Valid Visita visita = new Visita(new GregorianCalendar(
                dataVisita.getDay(),
                dataVisita.getMonth(),
                dataVisita.getYear()),
                StatoVisita.PROGRAMMATA,
                medicoVisita,
                paziente,
                indirizzo);
        visitaService.aggiuntaVisita(visita);

        paziente.addSingolaVisita(visita);
        medicoVisita.addSingolaVisita(visita);
        utenteService.updateMedico(medicoVisita);
        utenteService.updatePaziente(paziente);




    }
}
