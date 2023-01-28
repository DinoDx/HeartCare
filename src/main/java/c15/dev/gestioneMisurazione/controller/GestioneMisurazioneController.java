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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;

/**
 * @author Paolo Carmine Valletta, Alessandro Zoccola.
 * creato il: 04/01/2023.
 * Controller per le operazioni legate alle misurazioni.
 */
@RestController
@CrossOrigin
public class GestioneMisurazioneController {
    @Autowired
    private HttpSession session;
    @Autowired
    private GestioneMisurazioneService misurazioneService;
    @Autowired
    private GestioneUtenteService utenteService;
    private DispositivoMedicoStub stub = new DispositivoMedicoStub();

    /**
     * Metodo per la registrazione del dispositivo.
     * @param disp
     */
    @RequestMapping(value = "/aggiungiDispositivo",
                    method = RequestMethod.POST)
    public boolean
    registraDispositivo(@RequestParam final DispositivoMedico disp) {
        UtenteRegistrato u = (UtenteRegistrato) session.getAttribute("utenteLoggato");
        if(!utenteService.isPaziente(u.getId())) {
            return false;
        }
        return misurazioneService
                .registrazioneDispositivo(disp, u.getId());
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
    public List<Misurazione> getFascicoloSanitarioElettronico(@RequestParam long id) {
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
     * Questo metodo permette di avviare una registrazione sull'id
     * del dispositivo passato input e di restituire la misurazione generata.
     *
     */
    @PostMapping(value = "/avvioMisurazione")
    public Misurazione
            avvioMisurazione(@RequestParam final Long idDispositivo) {
        var dispositivoMedico = misurazioneService.getById(idDispositivo);
        var dispositivoAdapter = new DispositivoMedicoAdapter(dispositivoMedico);
        return dispositivoAdapter.avvioMisurazione();
    }

    /**
     * Metodo che consente di ottenere tutte le misurazioni p
     * effettuate da un paziente di una determinata categoria.
     * @param body
     * @return
     */
    @PostMapping(value = "/getMisurazioneCategoria")
    public List<Misurazione>
    getMisurazioniByCategoria(@RequestBody final HashMap<String,Object> body) {
        String cat = body.get("categoria").toString() ;
        Long idPaz = Long.parseLong(body.get("id").toString());
        return misurazioneService.getMisurazioneByCategoria(cat, idPaz);
    }

    /**
     * Metodo che restitusice un elenco di categorie delle misurazioni di
     * un paziente.
     * @param body
     * @return
     */
    @PostMapping(value = "/getCategorie")
    public List<String>
    getCategorieByPaziente(@RequestBody final HashMap<String,Object> body) {
        Long idPaz = Long.parseLong(body.get("id").toString());
        System.out.println("aoooo");
        return misurazioneService.findCategorieByPaziente(idPaz);
    }
}
