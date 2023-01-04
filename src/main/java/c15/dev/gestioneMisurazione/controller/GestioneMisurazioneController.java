package c15.dev.gestioneMisurazione.controller;

import c15.dev.gestioneMisurazione.service.GestioneMisurazioneService;
import c15.dev.model.entity.DispositivoMedico;
import c15.dev.model.entity.UtenteRegistrato;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Paolo Carmine Valletta, Alessandro Zoccola.
 * creato il: 04/01/2023.
 * Controller per le operazioni legate alle misurazioni.
 */
@RestController
public class GestioneMisurazioneController {

    @Autowired
    private HttpSession session;
    @Autowired
    private GestioneMisurazioneService misurazioneService;

    /**
     * Metodo per la registrazione del dispositivo.
     * @param dispositivo
     */
    @RequestMapping(value = "/aggiungiDispositivo", method = RequestMethod.POST)
    public boolean registrazioneDispositivo(
            @RequestParam DispositivoMedico dispositivo){
        UtenteRegistrato u = (UtenteRegistrato)
                session.getAttribute("utenteLoggato");
        return misurazioneService.registrazioneDispositivo(dispositivo,
                u.getId());
    }

    @RequestMapping(value = "/rimuoviDispositivo", method = RequestMethod.POST)
    public boolean rimozioneDispositivo(
            @RequestParam DispositivoMedico dispositivo){
        UtenteRegistrato u = (UtenteRegistrato)
                session.getAttribute("utenteLoggato");
        return misurazioneService.rimozioneDispositivo(dispositivo,
                u.getId());
    }
}
