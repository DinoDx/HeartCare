package c15.dev.gestioneUtente.controller;

import c15.dev.gestioneUtente.service.GestioneUtenteService;
import c15.dev.gestioneUtente.service.GestioneUtenteServiceImpl;
import c15.dev.model.entity.UtenteRegistrato;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
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
    public void login( @RequestParam String email,
                       @RequestParam String password) {
        UtenteRegistrato utente = service.login(email,password);

        if(utente == null){
            return;
        }

        session.setAttribute("utente",utente); //da vedere

    }

}
