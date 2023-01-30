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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;


/**
 * @author Mario Cicalese
 * Creato il : 03/01/2023
 * Questa classe rappresenta il Service utilizzato per la registrazione
 */
@RestController
@CrossOrigin
@RequestMapping(path = "auth")
public class RegistrazioneController {
    @Autowired
    public RegistrazioneService registrazioneService;

    @Autowired
    public GestioneUtenteService utenteService;

    @Autowired
    public HttpSession session;

    @PostMapping(value = "/registrazione")
        public AuthenticationResponse
            registrazione(@RequestBody @Valid final HashMap<String,String> paziente)
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
        Paziente p = new Paziente(data,codice,numero,password,email,nome,cognome,genere);

        String citta = paziente.get("citta");
        String provincia = paziente.get("provincia");
        String civico = paziente.get("nCivico");
        Integer cap = Integer.valueOf(paziente.get("cap"));
        String via = paziente.get("via");

        Indirizzo ind = new Indirizzo(citta,civico,cap,provincia,via);
        registrazioneService.saveIndirizzo(ind);
        p.setIndirizzoResidenza(ind);
        return registrazioneService.registraPaziente(p);
    }

    @PostMapping(value = "/login")
    public AuthenticationResponse login(@RequestBody
                                            final AuthenticationRequest req)
                                                            throws Exception {
        return registrazioneService.login(req);
    }

    /**
     * Metodo che permette all'admin di registrare un medico.
     * @param med
     */
    @RequestMapping(value = "/registraMedico", method = RequestMethod.POST)
    public void
    registraMedico(@Valid @RequestBody final Medico med) throws Exception {
        UtenteRegistrato u = (UtenteRegistrato)
                session.getAttribute("utenteLoggato");
        if(!utenteService.isAdmin(u.getId())){
            return;
        }
        registrazioneService.registraMedico(med);
    }


    /**
     * Metodo che permette di ottenere un utente conoscendo il codice fiscale.
     * @param codiceFiscale
     * @return
     */
    @PostMapping(value = "/getByCodice")
    public ResponseEntity<Object> getByCodice(@RequestBody HashMap<String,String> codiceFiscale){
        String codice = codiceFiscale.get("codiceFiscale");
        System.out.println(codice);

        boolean approva = utenteService.findUtenteByCf(codice);
    System.out.println(approva+"codice");
        return new ResponseEntity<>(approva, HttpStatus.OK);
    }
    /**
     * Metodo per ottenere un utente avendo la sua email.
     * @param email è l'email dell'utente
     * @return
     */
    @PostMapping(value = "/getByEmail")
    public ResponseEntity<Object> getByEmail(@RequestBody HashMap<String,String> email){
        System.out.println(email);
        String mail = email.get("email");
        System.out.println(email);

        boolean approva = utenteService.checkByEmail(mail);
        System.out.println(approva+"email");
        return new ResponseEntity<>(approva,HttpStatus.OK);
    }

}
