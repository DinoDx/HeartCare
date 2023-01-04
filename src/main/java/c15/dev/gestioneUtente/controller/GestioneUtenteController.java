package c15.dev.gestioneUtente.controller;

import c15.dev.gestioneUtente.service.GestioneUtenteService;
import c15.dev.model.entity.UtenteRegistrato;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Leopoldo Todisco, Carlo Venditto.
 * Crato il: 03/01/2022.
 * Classe controller.
 */
@RestController
@SessionAttributes("utenteLoggato")
public class GestioneUtenteController {
    /**
     * Service per le operazioni di accesso
     */
    @Autowired
    private GestioneUtenteService service;
    /**
     * Sessione
     */
    @Autowired
    private HttpSession session;

    /**
     * Metodo di login.
     * @param email
     * @param password
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@RequestParam String email,
                      @RequestParam String password) {

        Optional<UtenteRegistrato> utente = service.login(email, password);
        utente.ifPresent(utenteRegistrato ->
            {session.setAttribute("utenteLoggato", utente.get());} );
    }

    /**
     * Metodo di logout.
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logout() {
        session.invalidate();
    }

    /**
     * Metodo per assegnare un caregiver.
     * @param id
     * @param emailCaregiver
     * @param nomeCaregiver
     * @param cognomeCaregiver
     */
    @RequestMapping(value = "/assegnaCaregiver", method = RequestMethod.POST)
    public void assegnaCaregiver(@RequestParam Long id,
                                 @RequestParam String emailCaregiver,
                                 @RequestParam String nomeCaregiver,
                                 @RequestParam String cognomeCaregiver){
        service.assegnaCaregiver(id,emailCaregiver,nomeCaregiver,cognomeCaregiver);
    }

}
