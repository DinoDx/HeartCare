package c15.dev.gestioneUtente.controller;

import c15.dev.gestioneComunicazione.service.GestioneComunicazioneService;
import c15.dev.gestioneUtente.service.GestioneUtenteService;
import c15.dev.model.dto.ModificaPazienteDTO;
import c15.dev.model.dto.UtenteRegistratoDTO;
import c15.dev.model.entity.Paziente;
import c15.dev.model.entity.Medico;
import c15.dev.model.entity.UtenteRegistrato;
import c15.dev.model.entity.enumeration.StatoVisita;
import c15.dev.utils.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
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
     * Sessione
     */
    @Autowired
    private HttpSession session;


    /**
     * Metodo per assegnare un caregiver.
     *
     * @param idPaziente
     * @param emailCaregiver
     * @param nomeCaregiver
     * @param cognomeCaregiver
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
     * @param idUtente
     */
    @RequestMapping(value = "/rimuoviUtente", method = RequestMethod.POST)
    public boolean rimuoviUtente(@RequestParam Long idUtente) {
        if (service.isPaziente(idUtente)) {
            service.rimuoviPaziente(idUtente);
            return true;
        } else if (service.isMedico(idUtente)) {
            service.rimuoviPaziente(idUtente);
            return true;
        }
        return false;
    }

    /**
     * Metodo che assegna un paziente a un medico.
     * @param idMedico
     * @param idPaziente
     */
    @RequestMapping(value = "/assegnaPaziente", method = RequestMethod.POST)
    public void assegnaPaziente(@RequestParam final long idMedico,
                                @RequestParam final long idPaziente) {
        if (service.isMedico(idMedico) && service.isPaziente(idPaziente)) {
            service.assegnaPaziente(idMedico, idPaziente);
        }
    }

    /**
     * Metodo che restituisce tutti i medici.
     * Invariante: il metodo può essere chiamato solo da admin.
     */
    @RequestMapping(value = "/getTuttiMedici", method = RequestMethod.POST)
    public List<UtenteRegistrato> getTuttiMedici() {
        UtenteRegistrato u = (UtenteRegistrato)
                session.getAttribute("utenteLoggato");
        if (service.isAdmin(u.getId())) {
            return service.getTuttiMedici()
                    .stream()
                    .filter((utente)
                            -> utente.getClass()
                            .getSimpleName()
                            .equals("Medico"))
                    .toList();
        }
        return null;
    }

    /**
     * Metodo che restituisce tutti i pazienti.
     * Invariante: il metodo può essere chiamato solo da admin.
     */
    @RequestMapping(value = "/getTuttiPazienti", method = RequestMethod.POST)
    public ResponseEntity<Object> getTuttiPazienti() {
        UtenteRegistrato u = (UtenteRegistrato)
                session.getAttribute("utenteLoggato");
        System.out.println("CIAOOOOOOO");
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
     * @param idMedico id del medico
     */
    @GetMapping(value = "/getPazientiByMedico/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>
    getPazientiByMedico(@PathVariable("id") final long idMedico) {
        System.out.println(idMedico);
        List<Paziente> paz = service.getPazientiByMedico(idMedico);
        return new ResponseEntity<>(paz, HttpStatus.OK);
    }

    /**
     * Metodo per modificare i dati di un utente.
     * @param pazienteDTO
     * @return
     */
    //TODO usare optional per vedere solo quali campi modificare
    @PostMapping("/modificaDatiUtente")
    public boolean modificaDatiPaziente(@Valid @RequestBody
                                        ModificaPazienteDTO pazienteDTO)
            throws Exception {
       /* UtenteRegistrato utente = (UtenteRegistrato)
                session.getAttribute("utenteLoggato");*/

        long id = 1L;//utente.getId();
        UtenteRegistrato utente = service.findUtenteById(id);
        if (service.isPaziente(id)) {
            if (utente.getPassword()
                    .equals(pazienteDTO.getConfermaPassword())) {
                service.modificaDatiPaziente(pazienteDTO, id);
                return true;
            }

        }
        return false;
    }

    /**
     * Metodo per modificare i dati di un medico.
     * @param pazienteDTO
     * @return
     */
    @PostMapping("/modificaDatiMedico")
    public boolean
    modificaDatiPaziente(@Valid @RequestBody
                         final UtenteRegistratoDTO pazienteDTO)
            throws Exception {
       /* UtenteRegistrato utente = (UtenteRegistrato)
                session.getAttribute("utenteLoggato");*/

        long id = 4L;//utente.getId();
        UtenteRegistrato utente = service.findUtenteById(id);
        if (service.isMedico(id)) {
            if (utente.getPassword()
                    .equals(pazienteDTO.getConfermaPassword())) {
                service.modificaDatiMedico(pazienteDTO, id);
                return true;
            }

        }
        return false;
    }


    /**
     * @author Leopoldo Todisco.
     * Metodo che permette di ottenere i dati relativi a un utente qualsiasi.
     * @param idUtente
     * @return ResponseEntity è la response che sarà fetchata dal frontend.
     * Essa comprende una Map con i dati utente e lo stato della risposta.
     */
    @PostMapping("/utente/{id}")
    public ResponseEntity<Object>
        getDatiProfiloUtente(@PathVariable("id") final Long idUtente) {

        var request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest();

        System.out.println("qui va");
        HashMap<String, Object> map = new HashMap<>();
        if(service.isPaziente(idUtente)){
            Paziente paziente = service.findPazienteById(idUtente);
            map.put("nome", paziente.getNome());
            map.put("cognome", paziente.getCognome());
            map.put("email", paziente.getEmail());
            map.put("nTelefono", paziente.getNumeroTelefono());
            map.put("emailCaregiver", paziente.getEmailCaregiver());
            map.put("nomeCaregiver", paziente.getNomeCaregiver());
            map.put("cognomeCaregiver", paziente.getCognomeCaregiver());
        }

        else if(service.isMedico(idUtente) || service.isAdmin(idUtente)){
            Medico medico = service.findMedicoById(idUtente);
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
     * @param idUtente
     * @return ResponseEntity è la response che sarà fetchata dal frontend.
     * Essa comprende una Map con i dati della home e lo stato della risposta.
     */
    @PostMapping("/Home/{id}")
    public ResponseEntity<Object>
    visualizzazioneHomeUtente(@PathVariable("id") final long idUtente) {
        HashMap<String, Object> map = new HashMap<>();

        if(service.isPaziente(idUtente)){
            Paziente paz = service.findPazienteById(idUtente);
            int nVisite = paz.getElencoVisite().stream()
                    .filter(v->
                            v.getStatoVisita().equals(StatoVisita.PROGRAMMATA))
                    .toList()
                    .size();
            map.put("numeroMisurazioni", paz.getMisurazione().size());
            map.put("appuntamentiInProgramma", nVisite);
            map.put("nome", paz.getNome());
            map.put("cognome", paz.getCognome());
            map.put("numeroNote", paz.getNote().size());
            map.put("sesso", paz.getGenere());
        }
        else if (service.isMedico(idUtente)) {
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
     * @param idPaziente
     * @return
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
     * @return
     */
    @PostMapping(value = "/searchbar")
    public ResponseEntity<Object>
    utentiSearch(@RequestBody final HashMap<String, String> requestMap) {
        var txt = requestMap.get("txt");
        var list = service.getTuttiPazienti().stream().toList();

        if(txt == null || txt.isBlank() || txt.isEmpty()) {
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
     * Metodo che permtte di ottenere un elenco di pazienti
     * in base a nome e cognome passati nella searchbar.
     * I pazienti devono appartenere al medico in questione.
     * @param requestMap il testo che viene passato.
     * @return
     */
    @PostMapping(value = "/searchbarMedico")
    public ResponseEntity<Object>
    pazientiSearch(@RequestBody final HashMap<String, String> requestMap,
                   final HttpServletRequest request) {
        /** txt indica il testo che viene passato dal frontend.*/
        var txt = requestMap.get("txt");
        /** email dell'utente che chiama il metodo.*/
        var email = request.getUserPrincipal().getName();
        var usr = service.findUtenteByEmail(email);

        if(usr == null || !usr.getRuolo().equals(Role.MEDICO)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        var medico = (Medico) usr;
        var listaPazienti = medico.getElencoPazienti();

        /** se il testo che viene passato è vuoto
         * si restituisce tutta la lista.*/
        if(txt == null || txt.isBlank() || txt.isEmpty()) {
            return new ResponseEntity<>(listaPazienti, HttpStatus.OK);
        }

        /** se il testo non è vuoto allora si filtrano i risultati.*/
        var listPaz = listaPazienti.stream()
                .filter(pz -> (pz.getNome()+" "+pz.getCognome())
                                .toLowerCase()
                                .contains(txt.toLowerCase()))
                .toList();
        return new ResponseEntity<>(listPaz, HttpStatus.OK);
    }
    @GetMapping(value = "/getIndirizzi")
    public ResponseEntity<Object> getIndirizzi(){
        return new ResponseEntity<>(service.findAllIndirizzi(),HttpStatus.OK);
    }
}


