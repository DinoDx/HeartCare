package c15.dev.gestioneMisurazione.controller;

import c15.dev.gestioneComunicazione.service.GestioneComunicazioneService;
import c15.dev.gestioneMisurazione.misurazioneAdapter.DispositivoMedicoAdapter;
import c15.dev.gestioneMisurazione.misurazioneAdapter.DispositivoMedicoStub;
import c15.dev.gestioneMisurazione.service.GestioneMisurazioneService;
import c15.dev.gestioneUtente.service.GestioneUtenteService;
import c15.dev.model.entity.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

    @Autowired
    private RestTemplate restTemplate;

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
    rimozioneDispositivo(@RequestParam final DispositivoMedico dispositivo,
                         final HttpServletRequest request) {
      /*  var u = request.get
        if(!utenteService.isPaziente(u.getId())){
            return false;
        }
        return misurazioneService.rimozioneDispositivo(dispositivo,
                u.getId());
                */

        return true;

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
        var list = misurazioneService.getAllMisurazioniByPaziente(idPaz);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Metodo per ricevere le cateogire delle misurazioni di un paziente.
     * @param body
     * @return
     */
    @PostMapping(value = "/getCategorie")
    public List<String> getCategorieByPaziente(
            @RequestBody HashMap<String, Object> body) {
        Long idPaz = Long.parseLong(body.get("id").toString());
        return misurazioneService.findCategorieByPaziente(idPaz);
    }

    /**
     * Metodo per prevedere se l'utente avrà un infarto.
     * @param body
     * @return
     */
    @PostMapping(value = "/avvioPredizione")
    public ResponseEntity<Object> avvioPredizione(@RequestBody final HashMap<String, String> body,
                                                    final HttpServletRequest request) {
        var email = request.getUserPrincipal().getName();
        var usr = utenteService.findUtenteByEmail(email);
        Paziente paz = (Paziente) usr;
        long id = usr.getId();

        /**
         * "age":90,
         * "sex": 1,
         * "trestbps": 180,
         * "chol": 250,
         * "fbs": 1,
         * "thalach": 250,
         * "thal": 1
         *
         * */
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        var eta = Period
                        .between(usr.getDataDiNascita(), LocalDate.now())
                        .getYears();

        map.put("age", eta);

        var sex = usr.getGenere();
        if(sex.equals("M")) {
            map.put("sex", 1);
        }
        else {
            map.put("sex", 0);
        }

        var pressione = paz.getMisurazione()
                .stream()
                .filter(s -> s.getClass().equals(MisurazionePressione.class))
                .map(s1 -> (MisurazionePressione) s1)
                .reduce((one, two) -> two)
                .get();

        map.put("trestbps", pressione.getPressioneMedia());

        var colesterolo = paz.getMisurazione()
                .stream()
                .filter(s -> s.getClass().equals(MisurazioneGlicemica.class))
                .map(s1 -> (MisurazioneGlicemica) s1)
                .reduce((one, two) -> two)
                .get();

        map.put("chol", colesterolo.getColesterolo());

        var fbs = paz.getMisurazione()
                .stream()
                .filter(s -> s.getClass().equals(MisurazioneGlicemica.class))
                .map(s -> (MisurazioneGlicemica) s)
                .reduce((one, two) -> two)
                .get();
        int flag = (fbs.getZuccheriNelSangue() > 120) ? 1 : 0;
        map.put("fbs", flag);

        map.put("thalach", pressione.getBattitiPerMinuto());

        if(body.get("infarto").equals("si")) {
            map.put("thal", 1);
        }
        else {
            map.put("thal", 0);
        }

        System.out.println(map);

        var i =  restTemplate.postForObject("http://localhost:8081/", map, String.class);
        i = i.substring(1, 2);
        System.out.println(i);

        return new ResponseEntity<>(Integer.valueOf(i), HttpStatus.OK);
    }


}
