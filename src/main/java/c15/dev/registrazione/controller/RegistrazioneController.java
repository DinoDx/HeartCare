package c15.dev.registrazione.controller;

import c15.dev.gestioneUtente.service.GestioneUtenteService;
import c15.dev.model.entity.Medico;
import c15.dev.model.entity.Paziente;
import c15.dev.model.entity.UtenteRegistrato;
import c15.dev.registrazione.service.RegistrazioneService;
import c15.dev.utils.AuthenticationRequest;
import c15.dev.utils.AuthenticationResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author Mario Cicalese
 * Creato il : 03/01/2023
 * Questa classe rappresenta il Service utilizzato per la registrazione
 */
@RestController
@CrossOrigin
@RequestMapping(path = "auth")
public class RegistrazioneController {
    @Autowired
    public RegistrazioneService registrazioneService;

    @Autowired
    public GestioneUtenteService utenteService;

    @Autowired
    public HttpSession session;

    @PostMapping(value = "/registrazione")
        public AuthenticationResponse
            registrazione(@RequestBody @Valid final Paziente paz)
                                                    throws Exception {
        return registrazioneService.registraPaziente(paz);
    }

    @PostMapping(value = "/login")
    public AuthenticationResponse login(@RequestBody
                                            final AuthenticationRequest req)
                                                            throws Exception {
        return registrazioneService.login(req);
    }

    /**
     * Metodo che permette all'admin di registrare un medico.
     * @param med
     */
    @RequestMapping(value = "/registraMedico", method = RequestMethod.POST)
    public void
    registraMedico(@Valid @RequestBody final Medico med) throws Exception {
        UtenteRegistrato u = (UtenteRegistrato)
                session.getAttribute("utenteLoggato");
        if(!utenteService.isAdmin(u.getId())){
            return;
        }
        registrazioneService.registraMedico(med);
    }
}
