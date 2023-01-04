package c15.dev.gestioneMisurazione.controller;

import c15.dev.gestioneMisurazione.service.GestioneMisurazioneService;
import c15.dev.model.entity.DispositivoMedico;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Paolo Carmine Valletta, Alessandro Zoccola.
 * creato il: 04/01/2023.
 * Controller per le operazioni legate alle misurazioni
 */
@RestController
public class GestioneMisurazioneController {


    @Autowired
    HttpSession session;
    @Autowired
    private GestioneMisurazioneService misurazioneService;

    /**
     * Metodo per la registrazione del dispositivo.
     * @param dispositivo .
     */
    @RequestMapping(value = "/aggiungiDispositivo", method = RequestMethod.POST)
    // modificare odd
    public boolean registrazioneDispositivo(@RequestParam DispositivoMedico dispositivo) {
        return misurazioneService.registrazioneDispositivo(dispositivo,
                (long) session.getAttribute("utenteLoggato"));
    }
}
