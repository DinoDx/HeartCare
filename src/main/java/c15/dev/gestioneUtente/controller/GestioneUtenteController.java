package c15.dev.gestioneUtente.controller;

import c15.dev.gestioneComunicazione.service.GestioneComunicazioneService;
import c15.dev.gestioneUtente.service.GestioneUtenteService;
import c15.dev.model.entity.Indirizzo;
import c15.dev.model.entity.Paziente;
import c15.dev.model.entity.Medico;
import c15.dev.model.entity.UtenteRegistrato;
import c15.dev.model.entity.enumeration.StatoVisita;
import c15.dev.utils.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.HashMap;

/**
 * @author Leopoldo Todisco, Carlo Venditto.
 * Crato il: 03/01/2023.
 * Classe controller.
 */
@RestController
@CrossOrigin(origins = "*")
@SessionAttributes("utenteLoggato")
public class GestioneUtenteController {
    /**
     * Service per le operazioni di accesso.
     */
    @Autowired
    private GestioneUtenteService service;

    /**
     * Service per le operazioni di comunicazione.
     */
    @Autowired
    private GestioneComunicazioneService gestioneComunicazioneService;
    /**
     * Sessione.
     */
    @Autowired
    private HttpSession session;


    /**
     * Metodo per assegnare un caregiver.
     * @param idPaziente id del paziente.
     * @param emailCaregiver email del caregiver.
     * @param nomeCaregiver nome del caregiver.
     * @param cognomeCaregiver cognome del caregiver.
     */
    @RequestMapping(value = "/assegnaCaregiver", method = RequestMethod.POST)
    public void assegnaCaregiver(@RequestParam final Long idPaziente,
                                 @RequestParam final String emailCaregiver,
                                 @RequestParam final String nomeCaregiver,
                                 @RequestParam final String cognomeCaregiver) {
        if (service.isPaziente(idPaziente)) {
            service.assegnaCaregiver(idPaziente,
                    emailCaregiver,
                    nomeCaregiver,
                    cognomeCaregiver);
        }
    }

    /**
     * Metodo per rimuovere un Paziente o un Medico.
     * @param map hashmap contente i dati dell'utente.
     * @return ResponseEntity è la response che sarà fetchata dal frontend.
     */
    @RequestMapping(value = "/rimuoviUtente", method = RequestMethod.POST)
    public ResponseEntity<Object>
    rimuoviUtente(@RequestBody final HashMap<String, String> map) {

        String pwd = map.get("password");
        Long id = Long.valueOf(map.get("id"));

        if (service.controllaPassword(pwd, id)) {

            Long idUtente = service.findUtenteByEmail(map.get("email"))
                                                         .getId();

            if (service.isPaziente(idUtente)) {

                service.rimuoviUtente(idUtente);
                return new ResponseEntity<>(HttpStatus.OK);
            } else if (service.isMedico(idUtente)) {
                service.rimuoviMedico(idUtente);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    /**
     * Metodo che assegna un paziente a un medico.
     * @param assegnamento HashMap contenete gli id del paziente e del medico.
     * @return ResponseEntity è la response che sarà fetchata dal frontend.
     */
    @RequestMapping(value = "/assegnaPaziente", method = RequestMethod.POST)
    public ResponseEntity<Object>
    assegnaPaziente(@RequestBody final HashMap<String, String> assegnamento) {
        long idPaziente = Long.parseLong(assegnamento.get("idPaziente"));
        long idMedico = Long.parseLong(assegnamento.get("idMedico"));
        service.assegnaPaziente(idMedico, idPaziente);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * Metodo che restituisce tutti i medici.
     * Invariante: il metodo può essere chiamato solo da admin.
     * @return ResponseEntity è la response che sarà fetchata dal frontend.
     */
    @RequestMapping(value = "/getTuttiMedici", method = RequestMethod.POST)
    public ResponseEntity<Object> getTuttiMedici() {
        UtenteRegistrato u = (UtenteRegistrato)
                session.getAttribute("utenteLoggato");
        return new ResponseEntity<>(service.getTuttiMedici()
                .stream()
                .filter((utente)
                        -> utente.getClass()
                        .getSimpleName()
                        .equals("Medico"))
                .toList(), HttpStatus.OK);
    }

    /**
     * Metodo che restituisce tutti i pazienti.
     * Invariante: il metodo può essere chiamato solo da admin.
     * @return ResponseEntity è la response che sarà fetchata dal frontend.
     */
    @RequestMapping(value = "/getTuttiPazienti", method = RequestMethod.POST)
    public ResponseEntity<Object> getTuttiPazienti() {
        UtenteRegistrato u = (UtenteRegistrato)
                session.getAttribute("utenteLoggato");
        return new ResponseEntity<>(service.getTuttiPazienti()
                .stream()
                .filter((utente)
                        -> utente.getClass()
                        .getSimpleName()
                        .equals("Paziente"))
                .toList(), HttpStatus.OK);
    }

    /**
     * Metodo che restituisce tutti i pazienti di un medico.
     * @param idMedico id del medico.
     * @return ResponseEntity è la response che sarà fetchata dal frontend.
     */
    @GetMapping(value = "/getPazientiByMedico/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>
    getPazientiByMedico(@PathVariable("id") final long idMedico) {
        List<Paziente> paz = service.getPazientiByMedico(idMedico);
        return new ResponseEntity<>(paz, HttpStatus.OK);
    }



    /**
     * @author Leopoldo Todisco.
     * Metodo che permette di ottenere i dati relativi a un utente qualsiasi.
     * @param idUtente id dell'utente.
     * @return ResponseEntity è la response che sarà fetchata dal frontend.
     * Essa comprende una Map con i dati utente e lo stato della risposta.
     */
    @PostMapping("/utente/{id}")
    public ResponseEntity<Object>
        getDatiProfiloUtente(@PathVariable("id") final Long idUtente) {

        var request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest();


        HashMap<String, Object> map = new HashMap<>();
        if (service.isPaziente(idUtente)) {
            Paziente paziente = service.findPazienteById(idUtente);
            map.put("nome", paziente.getNome());
            map.put("cognome", paziente.getCognome());
            map.put("email", paziente.getEmail());
            map.put("nTelefono", paziente.getNumeroTelefono());
            map.put("emailCaregiver", paziente.getEmailCaregiver());
            map.put("nomeCaregiver", paziente.getNomeCaregiver());
            map.put("cognomeCaregiver", paziente.getCognomeCaregiver());
        } else if (service.isMedico(idUtente) || service.isAdmin(idUtente)) {
            Medico medico = service.findMedicoById(idUtente);
            map.put("nome", medico.getNome());
            map.put("cognome", medico.getCognome());
            map.put("email", medico.getEmail());
            map.put("nTelefono", medico.getNumeroTelefono());
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**
     * Metodo che restituisce i dati per la modifica.
     * @param idUtente id dell'utente.
     * @return ResponseEntity è la response che sarà fetchata dal frontend.
     */
    @PostMapping("utente/modifica/{id}")
    public ResponseEntity<Object>
    getDatiPerModifica(@PathVariable("id") final Long idUtente) {


        HashMap<String, Object> map = new HashMap<>();
        if (service.isPaziente(idUtente)) {
            Paziente paziente = service.findPazienteById(idUtente);
            Indirizzo indirizzo = paziente.getIndirizzoResidenza();
            map.put("citta", indirizzo.getCitta());
            map.put("provincia", indirizzo.getProvincia());
            map.put("via", indirizzo.getVia());
            map.put("numeroCivico", indirizzo.getNCivico());
            map.put("cap", indirizzo.getCap());
            map.put("emailCaregiver", paziente.getEmailCaregiver());
            map.put("nomeCaregiver", paziente.getNomeCaregiver());
            map.put("cognomeCaregiver", paziente.getCognomeCaregiver());
        } else if (service.isMedico(idUtente)) {
            Medico medico = service.findMedicoById(idUtente);
            Indirizzo indirizzo = medico.getIndirizzoResidenza();
            map.put("citta", indirizzo.getCitta());
            map.put("provincia", indirizzo.getProvincia());
            map.put("via", indirizzo.getVia());
            map.put("numeroCivico", indirizzo.getNCivico());
            map.put("cap", indirizzo.getCap());
            map.put("nome", medico.getNome());
            map.put("cognome", medico.getCognome());
            map.put("email", medico.getEmail());
            map.put("nTelefono", medico.getNumeroTelefono());
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    /**
     * @author Paolo Carmine Valletta.
     * Metodo che permette di visualizzare la home di un Medico o Paziente.
     * @param idUtente id dell'utente.
     * @return ResponseEntity è la response che sarà fetchata dal frontend.
     * Essa comprende una Map con i dati della home e lo stato della risposta.
     */
    @PostMapping("/Home/{id}")
    public ResponseEntity<Object>
    visualizzazioneHomeUtente(@PathVariable("id") final long idUtente) {
        HashMap<String, Object> map = new HashMap<>();

        if (service.isPaziente(idUtente)) {
            Paziente paz = service.findPazienteById(idUtente);
            int nVisite = paz.getElencoVisite().stream()
                    .filter(v ->
                            v.getStatoVisita().equals(StatoVisita.PROGRAMMATA))
                    .toList()
                    .size();
            map.put("numeroMisurazioni", paz.getMisurazione().size());
            map.put("appuntamentiInProgramma", nVisite);
            map.put("nome", paz.getNome());
            map.put("cognome", paz.getCognome());
            map.put("numeroNote", paz.getNote().size());
            map.put("sesso", paz.getGenere());
        } else if (service.isMedico(idUtente)) {
            Medico med = service.findMedicoById(idUtente);
            map.put("pazientiTotali", med.getElencoPazienti().size());
            int nVisite = med.getElencoVisite()
                    .stream()
                    .filter(v ->
                            v.getStatoVisita().equals(StatoVisita.PROGRAMMATA))
                    .toList()
                    .size();
            map.put("appuntamentiInProgramma", nVisite);
            map.put("numeroNote", med.getNote().size());
            map.put("nome", med.getNome());
            map.put("cognome", med.getCognome());
            map.put("sesso", med.getGenere());
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**
     * Metodo che permette di ottenere tutti i dispositivi.
     * associati a un paziente.
     * @param idPaziente id del paziente.
     * @return ResponseEntity è la response che sarà fetchata dal frontend.
     */
    @PostMapping(value = "/getDispositiviByUtente/{id}")
    public ResponseEntity<Object>
    getDispositiviByUtente(@PathVariable("id") final long idPaziente) {
        var set = service.getDispositiviByPaziente(idPaziente);
        return new ResponseEntity<>(set, HttpStatus.OK);
    }

    /**
     * Metodo che permtte di ottenere un elenco di utenti a partire.
     * da nome e cognome passati nella searchbar.
     * @param requestMap il testo che viene passato.
     * @return ResponseEntity è la response che sarà fetchata dal frontend.
     */
    @PostMapping(value = "/searchbar")
    public ResponseEntity<Object>
    utentiSearch(@RequestBody final HashMap<String, String> requestMap) {
        var txt = requestMap.get("txt");
        var list = service.getTuttiPazienti().stream().toList();

        if (txt == null || txt.isBlank() || txt.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }

        var listPaz = list.stream()
                .filter(pz -> (pz.getNome() + pz.getCognome())
                                .toLowerCase()
                                .contains(txt.toLowerCase()))
                .toList();

        return new ResponseEntity<>(listPaz, HttpStatus.OK);
    }

    /**
     * Metodo che permette ad un admin di ricercare gli utenti.
     * @param requestMap il testo che viene passato.
     * @return ResponseEntity è la response che sarà fetchata dal frontend.
     */
    @PostMapping(value = "/searchBarAdmin")
    public ResponseEntity<Object> utentiSearchAdmin(
              @RequestBody final HashMap<String, String> requestMap) {
        String txt = requestMap.get("txt");
        var list = service.getTuttiUtenti();

        if (txt == null || txt.isBlank() || txt.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }

        var listFiltered = list.stream()
                .filter(utente -> (utente.getNome() + " "
                        + utente.getCognome())
                                    .toLowerCase()
                                    .contains(txt.toLowerCase())).toList();

        return new ResponseEntity<>(listFiltered, HttpStatus.OK);
    }


    /**
     * Metodo che permtte di ottenere un elenco di pazienti
     * in base a nome e cognome passati nella searchbar.
     * I pazienti devono appartenere al medico in questione.
     * @param requestMap il testo che viene passato.
     * @param request la richiesta che contiene dati dell'utente.
     * @return ResponseEntity è la response che sarà fetchata dal frontend.
     */
    @PostMapping(value = "/searchbarMedico")
    public ResponseEntity<Object>
    pazientiSearch(@RequestBody final HashMap<String, String> requestMap,
                   final HttpServletRequest request) {
        var txt = requestMap.get("txt");
        var email = request.getUserPrincipal().getName();
        var usr = service.findUtenteByEmail(email);

        if (usr == null || !usr.getRuolo().equals(Role.MEDICO)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        var medico = (Medico) usr;
        var listaPazienti = medico.getElencoPazienti();


        if (txt == null || txt.isBlank() || txt.isEmpty()) {
            return new ResponseEntity<>(listaPazienti, HttpStatus.OK);
        }

        var listPaz = listaPazienti.stream()
                .filter(pz -> (pz.getNome() + " " + pz.getCognome())
                                .toLowerCase()
                                .contains(txt.toLowerCase()))
                .toList();
        return new ResponseEntity<>(listPaz, HttpStatus.OK);
    }

    /**
     * Metodo che restituisce tutti gli indirizzi.
     * @return ResponseEntity è la response che sarà fetchata dal frontend.
     */
    @GetMapping(value = "/getIndirizzi")
    public ResponseEntity<Object> getIndirizzi() {
        return new ResponseEntity<>(service.findAllIndirizzi(), HttpStatus.OK);
    }

    /**
     * Metodo che controlla che la password passata sia uguale
     * a quella della richiesta.
     * @param pwd password.
     * @param request richiesta.
     * @return ResponseEntity è la response che sarà fetchata dal frontend.
     */

    @PostMapping(value = "/controllaPassword")
    public ResponseEntity<Object>
    controllaPassword(@RequestBody final String pwd,
                      final HttpServletRequest request) {

        var email = request.getUserPrincipal().getName();
        var u = service.findUtenteByEmail(email);
        if (u == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        if (service.controllaPassword(pwd, u.getId())) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    /**
     * Metodo che restituisce tutti gli utenti.
     * @param request richiesta.
     * @return ResponseEntity è la response che sarà fetchata dal frontend.
     */
    @RequestMapping(value = "/getTuttiUtenti", method = RequestMethod.POST)
    public ResponseEntity<Object> getTuttiUtenti(
                                final HttpServletRequest request) {

        var email = request.getUserPrincipal().getName();
        if (service.findUtenteByEmail(email) == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(service.getTuttiUtenti()
                .stream()
                .toList(), HttpStatus.OK);

    }

    /**
     * Metodo che peremtte di modificare la password.
     * @param utente map contenente tutti i dati dell'utente.
     * @return ResponseEntity è la response che sarà fetchata dal frontend.
     * @throws Exception
     */
    @PostMapping("/modifica/password")
    public ResponseEntity<Object>
            modificaPassword(@RequestBody final HashMap<String, String> utente)
                                                            throws Exception {

        Long idUtente = Long.valueOf(utente.get("id"));
        String vecchiaPassword = utente.get("vecchiaPassword");
        if (service.controllaPassword(vecchiaPassword, idUtente)) {
            UtenteRegistrato u = service.findUtenteById(idUtente);
           // Paziente p = (Paziente) service.findUtenteById(idUtente);
            u.setPassword(service.encryptPassword(utente
                                            .get("nuovaPassword")));
            //u.setPassword(utente.get("nuovaPassword"));
            service.updateUtente(u);
            //service.updatePaziente(p);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }


    /**
     * Metodo che permette di modificare un indirizzo.
     * @param indirizzo map contenente i dati dell'utente.
     * @return ResponseEntity è la response che sarà fetchata dal frontend.
     */
    @PostMapping("/modifica/indirizzo")
    public ResponseEntity<Object> modificaIndirizzo(
            @RequestBody final HashMap<String, String> indirizzo) {

        Long idUtente = Long.valueOf(indirizzo.get("id"));
        Indirizzo ind = service.findUtenteById(idUtente)
                                            .getIndirizzoResidenza();
        ind.setCitta(indirizzo.get("citta"));
        ind.setProvincia(indirizzo.get("provincia"));
        ind.setNCivico(indirizzo.get("numeroCivico"));
        ind.setCap((indirizzo.get("cap")));
        ind.setVia(indirizzo.get("via"));

        service.updateIndirizzo(ind);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * Metodo che permette di modificare il caregiver di un paziente.
     * @param caregiver map contenente i dati del paziente e del caregiver.
     * @return ResponseEntity è la response che sarà fetchata dal frontend.
     */
    @PostMapping("/modifica/caregiver")
    public ResponseEntity<Object>
    modificaCaregiver(@RequestBody final HashMap<String, String> caregiver) {
        Long idUtente = Long.valueOf(caregiver.get("id"));
        Paziente p = service.findPazienteById(idUtente);
        String nome = caregiver.get("nomeCaregiver");
        String email = caregiver.get("emailCaregiver");
        String cognome = caregiver.get("cognomeCaregiver");
        if (service.assegnaCaregiver(idUtente, email, nome, cognome)) {
            gestioneComunicazioneService.invioEmail("Sei diventato caregiver",
                    p.getEmailCaregiver());

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    /**
     * Metodo che serve per restituire il medico di un paziente.
     * @param idPaziente id del paziente.
     * @return mappa con parametri del medico.
     */
    @PostMapping("/getMedico/{id}")
    public ResponseEntity<Object> getMedicoByPaziente(
            @PathVariable("id") final long idPaziente) {

        HashMap<String, Object> map = new HashMap<>();
        long idMedico = service.findMedicoByPaziente(idPaziente);
        var med = service.findMedicoById(idMedico);

        map.put("nome", med.getNome());
        map.put("cognome", med.getCognome());
        map.put("email", med.getEmail());
        map.put("telefono", med.getNumeroTelefono());

        return new ResponseEntity<>(map, HttpStatus.OK);
    }


}



