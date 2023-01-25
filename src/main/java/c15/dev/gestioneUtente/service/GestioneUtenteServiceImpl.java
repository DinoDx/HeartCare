package c15.dev.gestioneUtente.service;

import c15.dev.model.dao.AdminDAO;
import c15.dev.model.dao.MedicoDAO;
import c15.dev.model.dao.PazienteDAO;
import c15.dev.model.dao.UtenteRegistratoDAO;
import c15.dev.model.dto.ModificaPazienteDTO;
import c15.dev.model.dto.UtenteRegistratoDTO;
import c15.dev.model.entity.Admin;
import c15.dev.model.entity.Medico;
import c15.dev.model.entity.Paziente;
import c15.dev.model.entity.UtenteRegistrato;
import c15.dev.utils.AuthenticationRequest;
import c15.dev.utils.AuthenticationResponse;
import c15.dev.utils.JwtService;
import c15.dev.utils.SecurityConfig;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Authenticator;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GestioneUtenteServiceImpl implements GestioneUtenteService {
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;


    /**
     * Provvede ad accedere al db per il paziente.
     */
    @Autowired
    private PazienteDAO paziente;
    /**
     * Provvede ad accedere al db per l'admin.
     */
    @Autowired
    private AdminDAO admin;
    /**
     * Provvede ad accedere al db per il medico.
     */
    @Autowired
    private MedicoDAO medico;

    @Qualifier("utenteRegistratoDAO")
    @Autowired
    private UtenteRegistratoDAO utente;


    /**
     * Metodo che permette di fare il login.
     * @return
     */
    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()));


        var user = this.findUtenteByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }

    /**
     * Metodo che assegna un caregiver a un paziente.
     * @param idPaziente del paziente a cui si vuole assegnare il caregiver
     * @param emailCaregiver email del caregiver
     * @param nomeCaregiver nome del caregiver
     * @param cognomeCaregiver nome del caregiver
     */
    @Override
    public void assegnaCaregiver(Long idPaziente,
                                 String emailCaregiver,
                                 String nomeCaregiver,
                                 String cognomeCaregiver) {

        Optional<UtenteRegistrato> pz =  paziente.findById(idPaziente);
        if(pz.isEmpty()) {
            return;
        }

        Paziente tmp = (Paziente) pz.get();
        tmp.setEmailCaregiver(emailCaregiver);
        tmp.setNomeCaregiver(nomeCaregiver);
        tmp.setCognomeCaregiver(cognomeCaregiver);
        paziente.save(tmp);
    }

    /**
     * Metodo che elimina un paziente.
     * @param idUtente
     */
    @Override
    public void rimuoviPaziente(Long idUtente) {
        Optional<UtenteRegistrato> u =paziente.findById(idUtente);
        paziente.delete(u.get());
    }

    /**
     * Metodo che elimina un medico.
     * @param idUtente
     */
    @Override
    public void rimuoviMedico(Long idUtente) {
        Optional<UtenteRegistrato> u = medico.findById(idUtente);
        medico.delete(u.get());
    }


    /**
     * Metodo che verifica se un utente è un paziente.
     * @param idUtente
     * @return
     */
    @Override
    public boolean isPaziente(long idUtente) {
        Optional<UtenteRegistrato> u = paziente.findById(idUtente);

        if (u.isEmpty()){
            return false;
        } else if (u.get().getClass().getSimpleName().equals("Paziente")) {
            return true;
        }

        return false;
    }

    /**
     * Metodo che verifica se un utente è un medico.
     * @param idUtente
     * @return
     */
    @Override
    public boolean isMedico(long idUtente) {
        Optional<UtenteRegistrato> u = medico.findById(idUtente);

        if (u.isEmpty()){
            return false;
        } else if (u.get().getClass().getSimpleName().equals("Medico")) {
            return true;
        }
        return false;
    }

    /**
     * Implementazione del metodo che verifica se un utente è un admin
     * @param idUtente id dell'utente che vogliamo controllare che sia un admin
     * @return
     */
    @Override
    public boolean isAdmin(long idUtente){
        Optional<UtenteRegistrato> u = admin.findById(idUtente);

        if (u.isEmpty()){
            return false;
        } else if (u.get().getClass().getSimpleName().equals("Admin")) {
            return true;
        }
        return false;
    }

    /**
     * Metodo che assegna un paziente ad un medico.
     * @param idMedico
     * @param idPaziente
     * @return
     */
    @Override
    public boolean assegnaPaziente(long idMedico, long idPaziente) {
        Optional<UtenteRegistrato> med = medico.findById(idMedico);
        if(med.isEmpty()) {
            return false;
        }
        Optional<UtenteRegistrato> paz = medico.findById(idPaziente);
        if(paz.isEmpty()) {
            return false;
        }
        Medico m = (Medico) med.get();
        Paziente pz = (Paziente) paz.get();
        m.getElencoPazienti().add(pz);
        pz.setMedico(m);
        paziente.save(pz);
        return true;
    }

    /**
     * Metodo che trova un Paziente tramite id.
     * @param id
     * @return
     */
    @Override
    public Paziente findPazienteById(Long id) {
        Optional<UtenteRegistrato> paz = paziente.findById(id);
        if(paz.isEmpty()){
            return null;
        }

        return (Paziente) paz.get();
    }

    /**
     * Metodo che trova Medico tramite id.
     * @param id id del medico
     * @return
     */
    @Override
    public Medico findMedicoById(Long id) {
        Optional<UtenteRegistrato> paz = medico.findById(id);
        if(paz.isEmpty()){
            return null;
        }

        return (Medico) paz.get();
    }

    @Override
    public boolean findUtenteByCf(String codiceFiscale) {
            Paziente u = paziente.findBycodiceFiscale(codiceFiscale);

        if (u == null){
            return false;
        }
        return true;
    }

    @Override
    public boolean checkByEmail(String email) {
        Paziente p = paziente.findByEmail(email);

        if(p == null){
            return false;
        }
        return true;
    }

    @Override
    public UtenteRegistrato findUtenteById(Long id) {
        Optional<UtenteRegistrato> u = utente.findById(id);
        if (u.isEmpty()){
            return null;
        }
         return u.get();
    }

    @Override
    public UtenteRegistrato findUtenteByEmail(String email) {
        UtenteRegistrato result;

        if((result = paziente.findByEmail(email)) != null) {
            return result;
        }

        else if((result = medico.findByEmail(email)) != null) {
            return result;
        }

        else if((result = admin.findByEmail(email)) != null) {
            return result;
        }

        return null;
    }


    /**
     * Metodo per fare update di un paziente nel DB.
     * @param paz è il paziente da aggiornare.
     */
    @Override
    public void updatePaziente(Paziente paz) {
        this.paziente.save(paz);
    }

    /**
     * Metodo per fare un update di un'entry nel DB.
     * @param med è il medico da aggiornare.
     */
    @Override
    public void updateMedico(Medico med) {
        this.medico.save(med);
    }

    /**
     * Metodo per ottenere tutti i medici del db.
     */
    @Override
    public List<UtenteRegistrato> getTuttiMedici() {
        return medico.findAll();
    }

    /**
     * Metodo per ottenere tutti i pazienti del db.
     */
    @Override
    public List<UtenteRegistrato> getTuttiPazienti(){
        return paziente.findAll();
    }


    /**
     * Metodo per ottenere tutti i pazienti del db di un medico.
     */
    @Override
    public List<Paziente> getPazientiByMedico(long idMedico) {

        paziente.findAll().stream().filter((p) -> p.getClass()
                        .getSimpleName().equals("Paziente"))
                .map(Paziente.class::cast)
                .forEach((p) -> System.out.println(p));

        return paziente.findAll().stream()
                .filter((p) -> p.getClass().getSimpleName().equals("Paziente"))
                .map(Paziente.class::cast)
                .filter(p -> p.getMedico().getId()==(idMedico))
                .toList();
    }

    /**
     * Metodo per modificare i dati di un paziente.
     * @param dto
     * @param idUtente
     */
    @Override
    public void modificaDatiPaziente(ModificaPazienteDTO dto, long idUtente) throws Exception {
        Paziente daModificare = findPazienteById(idUtente);

        daModificare.setNome(dto.getNome());
        daModificare.setCognome(dto.getCognome());
        daModificare.setNumeroTelefono(dto.getNumeroTelefono());
        daModificare.setEmailCaregiver(dto.getEmailCaregiver());
        daModificare.setNomeCaregiver(dto.getNomeCaregiver());
        daModificare.setCognomeCaregiver(dto.getCognomeCaregiver());
        daModificare.setPassword(dto.getPassword());
        //TODO AGGIUNGERE PURE INDIRIZZO QUANDO CI SARà
        paziente.save(daModificare);
    }

    @Override
    public void modificaDatiMedico(UtenteRegistratoDTO dto, long idUtente) throws Exception {
        Medico daModificare = findMedicoById(idUtente);

        daModificare.setNome(dto.getNome());
        daModificare.setCognome(dto.getCognome());
        daModificare.setNumeroTelefono(dto.getNumeroTelefono());
        daModificare.setPassword(dto.getPassword());

        medico.save(daModificare);

    }

}
