package c15.dev.gestioneMisurazione.service;


import c15.dev.gestioneUtente.service.GestioneUtenteService;
import c15.dev.model.dao.DispositivoMedicoDAO;
import c15.dev.model.dao.MisurazioneDAO;
import c15.dev.model.dao.MisurazionePressioneDAO;
import c15.dev.model.dao.PazienteDAO;
import c15.dev.model.dto.MisurazioneDTO;
import c15.dev.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;


/**
 * @author Paolo Carmine Valletta, Alessandro Zoccola.
 * creato il: 04/01/2023.
 * Per questo service si estende l'interfaccia GestioneMisurazioneService.
 */
@Service
public class GestioneMisurazioneServiceImpl implements GestioneMisurazioneService {

    /**
     * Provvede ad accedere al db per il dispositivo.
     */
    @Autowired
    private DispositivoMedicoDAO dispositivoDao;

    /**
     * Provvede ad accedere al db per il paziente.
     */
    @Autowired
    private PazienteDAO pazienteDao;
    /**
     * provvede ad accedere al db per effettuare operazioni sulla tabella misurazione
     */
    @Autowired
    private MisurazioneDAO misurazioneDAO;

    @Autowired
    private MisurazionePressioneDAO misurazionePressioneDAO;

    @Autowired
    private GestioneUtenteService serviceUtente;

    /**
     * Questo metodo ha il compito di controllare che la categoria
     * passata dal frontend sia una categoria contemplata.
     * @param categoria
     * @return true or false.
     */
    private boolean checkCategorie(String categoria) {
        System.out.println("la categoria da verificare " + categoria);
        if((categoria.equalsIgnoreCase("Saturimetro"))
                || (categoria.equalsIgnoreCase("Coagulometro"))
                || (categoria.equalsIgnoreCase("Misuratore di pressione"))
                || (categoria.equalsIgnoreCase("ECG"))
                || (categoria.equalsIgnoreCase("Misuratore glicemico"))) {
            return true;
        }

        return false;
    }

    /**
     * Metodo di registrazione del dispositivo che viene usato solo nel
     * DBPopulator.
     * @param dispositivo che vogliamo assegnare ad un utente.
     * @param idPaziente id del paziente a cui vogliamo assegnare
     *                   il dispositivo.
     * @return
     */
    @Override
    public boolean registrazioneDispositivo(DispositivoMedico dispositivo,
                                            long idPaziente) {
        Optional<UtenteRegistrato> paziente = pazienteDao.findById(idPaziente);
        if(paziente.isEmpty()){
            return false;
        }
        dispositivo.setPaziente((Paziente) paziente.get());
        dispositivoDao.save(dispositivo);
        return true;
    }

    /**
     * Metodo che consente di registrare un dispositivo.
     * @param map
     * @param idPaziente
     * @return
     */
    @Override
    public boolean registrazioneDispositivo(final HashMap<String, String> map,
                                            final long idPaziente) {

        Optional<UtenteRegistrato> paziente = pazienteDao.findById(idPaziente);
        if(paziente.isEmpty()){
            return false;
        }
        var descrizione = map.get("descrizione");
        var numSeriale = map.get("numeroSeriale");
        var categoria = map.get("categoria");

        System.out.println("il mio disp che sto mettendo ha: " + descrizione);

        //fai controllo per vedere le categorie
        if(!checkCategorie(categoria)) {
            System.out.println("LA CATEGORIA NON VALE.");
            return false;
        }

        DispositivoMedico dispositivo = DispositivoMedico.builder()
                .dataRegistrazione(LocalDate.now())
                .disponibile(false)
                .categoria(categoria)
                .descrizione(descrizione)
                .numeroSeriale(numSeriale)
                .paziente((Paziente) paziente.get())
                .build();

        dispositivoDao.save(dispositivo);
        System.out.println("sono nel serice impl di registrazione dispo");
        return true;
    }

    /**
     * Questo metodo consente di eliminare un dispositivo dal database.
     * @param dispositivo che vogliamo rimuovere ad un utente.
     * @param idPaziente id del paziente a cui vogliamo rimuovere
     *                   il dispositivo.
     * @return
     */
    @Override
    public boolean rimozioneDispositivo(final DispositivoMedico dispositivo,
                                        final long idPaziente) {
        Optional<UtenteRegistrato> paziente = pazienteDao.findById(idPaziente);
        if(paziente.isEmpty()){
            return false;
        }

        dispositivo.setPaziente(null);
        dispositivoDao.save(dispositivo);
        return true;
    }

    @Override
    public List<Misurazione> getMisurazioniByPaziente(Long id) {
        return (List<Misurazione>) misurazioneDAO.findByPaziente(id);
    }

    @Override
    public DispositivoMedico getById(Long id) {
        return dispositivoDao.findById(id).get();
    }

    @Override
    public List<Misurazione> getMisurazioneByCategoria(String categoria, Long id) {
        return (List<Misurazione>) misurazioneDAO.findByCategoria(categoria, id);
    }

    @Override
    public Misurazione save(Misurazione misurazione) {
        return misurazioneDAO.save(misurazione);
    }

   /* @Override
    public MisurazionePressione save(MisurazionePressione misurazionePressione) {
        return misurazionePressioneDAO.save(misurazionePressione);
    } */

    @Override
    public List<String> findCategorieByPaziente(Long id) {
        return (List<String>) misurazioneDAO.findCategorieByPaziente(id);
    }

    @Override
    public List<MisurazioneDTO> getAllMisurazioniByPaziente(Long id) {
        List<Misurazione> list = (List<Misurazione>) misurazioneDAO.getAllMisurazioniByPaziente(id);
        List<MisurazioneDTO> listDTO = list.stream().map( m -> new MisurazioneDTO(m,m.getDispositivoMedico().getCategoria())).toList();
        return listDTO;
    }
}
