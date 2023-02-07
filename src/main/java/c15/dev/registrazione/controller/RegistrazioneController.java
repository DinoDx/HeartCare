package c15.dev.registrazione.controller;

import c15.dev.gestioneUtente.service.GestioneUtenteService;
import c15.dev.model.entity.Indirizzo;
import c15.dev.model.entity.Medico;
import c15.dev.model.entity.Paziente;
import c15.dev.model.entity.UtenteRegistrato;
import c15.dev.registrazione.service.RegistrazioneService;
import c15.dev.utils.AuthenticationRequest;
import c15.dev.utils.AuthenticationResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.util.HashMap;


/**
 * @author Mario Cicalese.
 * Creato il : 03/01/2023.
 * Questa classe rappresenta il Service utilizzato per la registrazione.
 */
@RestController
@CrossOrigin
@RequestMapping(path = "auth")
public class RegistrazioneController {

    /**
     * Service per le operazioni legate alla registrazione.
     */
    @Autowired
    private RegistrazioneService registrazioneService;

    /**
     * Service per le operazioni legate all'utente.
     */
    @Autowired
    private GestioneUtenteService utenteService;

    /**
     * Sessione.
     */
    @Autowired
    private HttpSession session;

    /**
     * Metodo per la registrazione di un paziente.
     * @param paziente paziente da registrare.
     * @return response.
     */
    @PostMapping(value = "/registrazione")
        public AuthenticationResponse
            registrazione(@RequestBody @Valid final
                          HashMap<String, String> paziente)
                        throws Exception {
        System.out.println(paziente);
        String nome = paziente.get("nome");
        String cognome = paziente.get("cognome");
        String password = paziente.get("password");
        String email = paziente.get("email");
        String numero = paziente.get("numeroTelefono");
        String genere = paziente.get("genere");
        String codice = paziente.get("codiceFiscale");
        LocalDate data = LocalDate.parse(paziente.get("dataDiNascita"));
        Paziente p = new Paziente(data,
                codice, numero, password, email, nome, cognome, genere);

        String citta = paziente.get("citta");
        String provincia = paziente.get("provincia");
        String civico = paziente.get("nCivico");
        String cap = (paziente.get("cap"));
        String via = paziente.get("via");

        Indirizzo ind = new Indirizzo(citta, civico, cap, provincia, via);
        registrazioneService.saveIndirizzo(ind);
        p.setIndirizzoResidenza(ind);
        return registrazioneService.registraPaziente(p,paziente.get("confermaPassword"));
    }


    /**
     * Metodo per la registrazione di un medico.
     * @param medico medico da registrare.
     * @return response.
     */
    @PostMapping(value = "/registrazioneMedico")
    public AuthenticationResponse
    registrazioneMedico(
            @RequestBody @Valid final HashMap<String, String> medico)
            throws Exception {
        String nome = medico.get("nome");
        String cognome = medico.get("cognome");
        String password = medico.get("password");
        String email = medico.get("email");
        String numero =  medico.get("numeroTelefono");
        String genere = medico.get("genere");
        String codice = medico.get("codiceFiscale");
        LocalDate data = LocalDate.parse(
                medico.get("dataDiNascita"));
        Medico m = new Medico(data, codice, numero, password, email,
                nome, cognome, genere);

        String citta = medico.get("citta");
        String provincia = medico.get("provincia");
        String civico = medico.get("nCivico");
        String cap = (medico.get("cap"));
        String via = medico.get("via");

        Indirizzo ind = new Indirizzo(citta, civico, cap, provincia, via);
        registrazioneService.saveIndirizzo(ind);
        m.setIndirizzoResidenza(ind);
        return registrazioneService.registraMedico(m);
    }

    /**
     * Metodo per il login.
     * @param req richiesta per il login.
     * @return response.
     */
    @PostMapping(value = "/login")
    public AuthenticationResponse login(@RequestBody
                                            final AuthenticationRequest req)
                                                            throws Exception {
        return registrazioneService.login(req);
    }

    /**
     * Metodo che permette all'admin di registrare un medico.
     * @param med medico da registrare.
     */
    /* @RequestMapping(value = "/registraMedico", method = RequestMethod.POST)
    public void
    registraMedico(@Valid @RequestBody final Medico med) throws Exception {
        System.out.println("ciao");
        UtenteRegistrato u = (UtenteRegistrato)
                session.getAttribute("utenteLoggato");
        if (!utenteService.isAdmin(u.getId())) {
            return;
        }
        registrazioneService.registraMedico(med);
    } */


    /**
     * Metodo che permette di ottenere un utente conoscendo il codice fiscale.
     * @param codiceFiscale restituisce un utente dato il cf.
     * @return response che contiene i dati dell'utente.
     */
    @PostMapping(value = "/getByCodice")
    public ResponseEntity<Object> getByCodice(@RequestBody
                                                 final HashMap<String, String>
                                                          codiceFiscale) {
        String codice = codiceFiscale.get("codiceFiscale");

        boolean approva = utenteService.findUtenteByCf(codice);
        return new ResponseEntity<>(approva, HttpStatus.OK);
    }
    /**
     * Metodo per ottenere un utente avendo la sua email.
     * @param email è l'email dell'utente.
     * @return response che contiene i dati dell'utente.
     */
    @PostMapping(value = "/getByEmail")
    public ResponseEntity<Object> getByEmail(@RequestBody
                                                 final HashMap<String, String>
                                                         email) {
        String mail = email.get("email");

        boolean approva = utenteService.checkByEmail(mail);
        return new ResponseEntity<>(approva, HttpStatus.OK);
    }

}
