package c15.dev.gestioneMisurazione.controller;

import c15.dev.gestioneComunicazione.service.GestioneComunicazioneService;
import c15.dev.gestioneMisurazione.misurazioneAdapter.ControlloMisurazioni;
import c15.dev.gestioneMisurazione.misurazioneAdapter.DispositivoMedicoAdapter;
import c15.dev.gestioneMisurazione.misurazioneAdapter.DispositivoMedicoStub;
import c15.dev.gestioneMisurazione.service.GestioneMisurazioneService;
import c15.dev.gestioneUtente.service.GestioneUtenteService;
import c15.dev.model.dto.MisurazioneDTO;
import c15.dev.model.entity.DispositivoMedico;
import c15.dev.model.entity.Misurazione;
import c15.dev.model.entity.UtenteRegistrato;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private GestioneComunicazioneService comunicazioneService;

    /**
     * Sessione
     */
    @Autowired
    private HttpSession session;
    /**
     * Service per la misurazione
     */
    @Autowired
    private GestioneMisurazioneService misurazioneService;

    /**
     * Service per la gestione utenti
     */
    @Autowired
    private GestioneUtenteService utenteService;
    private DispositivoMedicoStub dispositivoMedicoStub
            = new DispositivoMedicoStub();

    /**
     * Metodo per la registrazione del dispositivo.
     * @param requestMap
     * @param request
     */
    @PostMapping(value = "/dispositivo/registra")
    public ResponseEntity<Object>
    registraDispositivo(@RequestBody final HashMap<String, String> requestMap,
                        final HttpServletRequest request) {
        var email = request.getUserPrincipal().getName();
        var user = utenteService.findUtenteByEmail(email);
        var idUser = user.getId();

        if(!utenteService.isPaziente(user.getId())) {
            System.out.println("non sono un paziente...");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        var result = misurazioneService
                .registrazioneDispositivo(requestMap, idUser);
        if (!result) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Metodo per la rimozione del dispositivo.
     * @param dispositivo
     */
    @RequestMapping(value = "/rimuoviDispositivo", method = RequestMethod.POST)
    public boolean
    rimozioneDispositivo(@RequestParam DispositivoMedico dispositivo){
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
    public List<Misurazione>
    getFascicoloSanitarioElettronico(@RequestParam final long id) {
        if (!utenteService.isPaziente(id)) {
            return null;
        }
        return misurazioneService.getMisurazioniByPaziente(id);
    }

    /**
     *
     * @param request
     * @param map
     * @return Misurazione.
     * Questo metodo permette di avviare una registrazione sull'id.
     * del dispositivo passato input e di restituire la misurazione generata.
     *
     */
    @PostMapping(value = "/avvioMisurazione")
    public Misurazione avvioMisurazione(@RequestBody final HashMap<String,
            Object> map, final HttpServletRequest request) {

        var email = request.getUserPrincipal().getName();
        var u = utenteService.findUtenteByEmail(email);
        if(u == null || !utenteService.isPaziente(u.getId())) {
            System.out.println("DEVI ESSERE PAZIENTE");
            return null;
        }

        Long idDispositivo = Long
                .parseLong(map.get("idDispositivo")
                .toString());
        var dispositivoMedico = misurazioneService.getById(idDispositivo);
        var dispositivoAdapter =
                new DispositivoMedicoAdapter(dispositivoMedico);
        var m =  dispositivoAdapter.avvioMisurazione();

        if(ControlloMisurazioni.chiamaControllo(m)) {
            System.out.println("prima di invocare il sendNotifica");
            comunicazioneService.sendNotifica("Misurazione sballata", m.getPaziente().getId());
        }

        misurazioneService.save(m);
        return m;
    }

    /**
     * Metodo per ricevere le misurazioni tramite una categorie.
     * @param body
     * @return
     */
    @PostMapping(value = "/getMisurazioneCategoria")
    public List<Misurazione> getMisurazioniByCategoria(
            @RequestBody HashMap<String,Object> body){
        System.out.println("CATEGORIA" + body.get("categoria") + "\n");
        String cat = body.get("categoria").toString() ;
        Long idPaz = Long.parseLong(body.get("id").toString());
        return misurazioneService.getMisurazioneByCategoria(cat, idPaz);
    }

    /**
     * Metodo per ricevere le misurazioni da un paziente.
     * @param body
     * @return
     */
    @PostMapping(value = "/getAllMisurazioniByPaziente")
    public ResponseEntity<Object> getAllMisurazioniByPaziente(
            @RequestBody HashMap<String,Object> body){
        Long idPaz = Long.parseLong(body.get("id").toString());
        List<MisurazioneDTO> list =
                misurazioneService.getAllMisurazioniByPaziente(idPaz);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Metodo per ricevere le cateogire delle misurazioni di un paziente.
     * @param body
     * @return
     */
    @PostMapping(value = "/getCategorie")
    public List<String> getCategorieByPaziente(
            @RequestBody HashMap<String,Object> body){
        Long idPaz = Long.parseLong(body.get("id").toString());
        System.out.println("aoooo");
        return misurazioneService.findCategorieByPaziente(idPaz);
    }
}
