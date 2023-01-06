package c15.dev.gestioneMisurazione.controller;

import c15.dev.gestioneMisurazione.misurazioneAdapter.DispositivoMedicoAdapter;
import c15.dev.gestioneMisurazione.misurazioneAdapter.DispositivoMedicoStub;
import c15.dev.gestioneMisurazione.service.GestioneMisurazioneService;
import c15.dev.gestioneUtente.service.GestioneUtenteService;
import c15.dev.model.entity.DispositivoMedico;
import c15.dev.model.entity.Misurazione;
import c15.dev.model.entity.UtenteRegistrato;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private GestioneUtenteService utenteService;

    private DispositivoMedicoStub dispositivoMedicoStub = new DispositivoMedicoStub();

    /**
     * Metodo per la registrazione del dispositivo.
     * @param dispositivo
     */
    @RequestMapping(value = "/aggiungiDispositivo", method = RequestMethod.POST)
    public boolean registrazioneDispositivo(
            @RequestParam DispositivoMedico dispositivo){
        UtenteRegistrato u = (UtenteRegistrato)
                session.getAttribute("utenteLoggato");
        if(!utenteService.isPaziente(u.getId())){
            return false;
        }
        return misurazioneService.registrazioneDispositivo(dispositivo,
                u.getId());
    }

    /**
     * Metodo per la rimozione del dispositivo.
     * @param dispositivo
     */
    @RequestMapping(value = "/rimuoviDispositivo", method = RequestMethod.POST)
    public boolean rimozioneDispositivo(
            @RequestParam DispositivoMedico dispositivo){
        UtenteRegistrato u = (UtenteRegistrato)
                session.getAttribute("utenteLoggato");
        if(!utenteService.isPaziente(u.getId())){
            return false;
        }
        return misurazioneService.rimozioneDispositivo(dispositivo,
                u.getId());
    }

    /**
     *
     * @param id
     * @return List<Misurazione>
     */
    @PostMapping(value = "/FascicoloSanitarioElettronico")
    public List<Misurazione> getFascicoloSanitarioElettronico(@RequestParam long id){
        if (!utenteService.isPaziente(id)) {
            System.out.println("paziente non esistente");
            return null;
        }
        return misurazioneService.getMisurazioniByPaziente(id);
    }

    /**
     *
     * @param idDispositivo
     * @return Misurazione
     * Questo metodo permette di avviare una registrazione sull'id del dispositivo passato in input e di restituire la misurazione generata
     *
     */
    @PostMapping(value = "/avvioMisurazione")
    public Misurazione avvioMisurazione(@RequestParam Long idDispositivo){
        DispositivoMedico dispositivoMedico = misurazioneService.getById(idDispositivo);
        DispositivoMedicoAdapter dispositivoMedicoAdapter = new DispositivoMedicoAdapter(dispositivoMedico) ;
        return dispositivoMedicoAdapter.avvioMisurazione();
    }
}
