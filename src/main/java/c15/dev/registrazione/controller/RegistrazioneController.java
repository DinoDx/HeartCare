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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;


/**
 * @author Mario Cicalese
 * Creato il : 03/01/2023
 * Questa classe rappresenta il Service utilizzato per la registrazione
 */
@RestController
@RequestMapping(path = "auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RegistrazioneController {
    @Autowired
    public RegistrazioneService registrazioneService;

    @Autowired
    public GestioneUtenteService utenteService;

    @Autowired
    public HttpSession session;

    //aggiungi valid poi
    @PostMapping(value = "/registrazione")
    public  ResponseEntity<AuthenticationResponse> registrazione(@RequestBody Paziente paziente) throws Exception {
System.out.println("sono nel metodo di login");
        return ResponseEntity.ok(registrazioneService.registraPaziente(paziente));
    }

    /**
     * Metodo di login.
     *
     * @param  request è la richiesta.
     *             Al suo interno vi si trovano i valori di password ed email.
     */
    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(utenteService.login(request));
    }


    /**
     * Metodo che permette all'admin di registrare un medico.
     * @param med
     */
    @RequestMapping(value = "/registraMedico", method = RequestMethod.POST)
    public void registraMedico(@Valid @RequestBody Medico med) {
        UtenteRegistrato u = (UtenteRegistrato)
                session.getAttribute("utenteLoggato");
        if(!utenteService.isAdmin(u.getId())) {
            return;
        }
        registrazioneService.registraMedico(med);
    }
}
