package c15.dev.gestioneMisurazione.service;


import c15.dev.gestioneUtente.service.GestioneUtenteService;
import c15.dev.model.dao.DispositivoMedicoDAO;
import c15.dev.model.dao.MisurazioneDAO;

import c15.dev.model.dao.MisurazionePressioneDAO;
import c15.dev.model.dao.PazienteDAO;
import c15.dev.model.dto.MisurazioneDTO;
import c15.dev.model.entity.DispositivoMedico;
import c15.dev.model.entity.Paziente;
import c15.dev.model.entity.UtenteRegistrato;
import c15.dev.model.entity.Misurazione;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


/**
 * @author Paolo Carmine Valletta, Alessandro Zoccola.
 * creato il: 04/01/2023.
 * Per questo service si estende l'interfaccia GestioneMisurazioneService.
 */
@Validated
@Service
public class GestioneMisurazioneServiceImpl
        implements GestioneMisurazioneService {

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
     * provvede ad accedere al db per effettuare
     * operazioni sulla tabella misurazione.
     */
    @Autowired
    private MisurazioneDAO misurazioneDAO;

    /**
     * provvede ad accedere al db per le misurazioni della pressione.
     */
    @Autowired
    private MisurazionePressioneDAO misurazionePressioneDAO;

    /**
     * Service per la gestione Utente.
     */
    @Autowired
    private GestioneUtenteService serviceUtente;

    /**
     * Questo metodo ha il compito di controllare che la categoria.
     * passata dal frontend sia una categoria contemplata.
     * @param categoria
     * @return true or false.
     */
    private boolean checkCategorie(final String categoria) {
        if ((categoria.equalsIgnoreCase("Saturimetro"))
                || (categoria.equalsIgnoreCase("Coagulometro"))
                || (categoria.equalsIgnoreCase("Misuratore di pressione"))
                || (categoria.equalsIgnoreCase("ECG"))
                || (categoria.equalsIgnoreCase("Misuratore glicemico"))) {
            return true;
        }

        return false;
    }

    /**
     * Metodo di registrazione del dispositivo che viene usato solo nel.
     * DBPopulator.
     * @param disp che vogliamo assegnare ad un utente.
     * @param idPaziente id del paziente a cui vogliamo assegnare.
     *                   il dispositivo.
     * @return true o false.
     */
    @Override
    public boolean registrazioneDispositivo(final DispositivoMedico disp,
                                            final long idPaziente) {
        Optional<UtenteRegistrato> paziente = pazienteDao.findById(idPaziente);
        if (paziente.isEmpty()) {
            return false;
        }
        disp.setPaziente((Paziente) paziente.get());
        dispositivoDao.save(disp);
        return true;
    }

    /**
     * Metodo che consente di registrare un dispositivo.
     * @param map
     * @param idPaziente
     * @return true o false.
     */
    @Override
    public boolean registrazioneDispositivo(final HashMap<String, String> map,
                                            final long idPaziente) {
        Optional<UtenteRegistrato> paziente = pazienteDao.findById(idPaziente);
        if (paziente.isEmpty()) {
            return false;
        }
        var descrizione = map.get("descrizione");
        var numSeriale = map.get("numeroSeriale");
        var categoria = map.get("categoria");

        if (!checkCategorie(categoria)) {
            return false;
        }

        @Valid DispositivoMedico dispositivo = DispositivoMedico.builder()
                    .dataRegistrazione(LocalDate.now())
                    .disponibile(false)
                    .categoria(categoria)
                    .descrizione(descrizione)
                    .numeroSeriale(numSeriale)
                    .paziente((Paziente) paziente.get())
                    .build();

        dispositivoDao.save(dispositivo);

        return true;
    }

    /**
     * Questo metodo consente di eliminare un dispositivo dal database.
     * @param dispositivo che vogliamo rimuovere ad un utente.
     * @param idPaziente id del paziente a cui vogliamo rimuovere.
     *                   il dispositivo.
     * @return true o false.
     */
    @Override
    public boolean rimozioneDispositivo(final DispositivoMedico dispositivo,
                                        final long idPaziente) {
        Optional<UtenteRegistrato> paziente = pazienteDao.findById(idPaziente);

        if (paziente.isEmpty()) {
            return false;
        }

        dispositivo.setPaziente(null);
        dispositivoDao.save(dispositivo);
        return true;
    }

    /**
     * Metodo per ricevere le misurazioni da un paziente.
     * @param id
     * @return lista di misurazione.
     */
    @Override
    public List<Misurazione> getMisurazioniByPaziente(final Long id) {
        return (List<Misurazione>) misurazioneDAO.findByPaziente(id);
    }

    /**
     * Metodo per ricevere il dispositivo dal suo id.
     * @param id
     * @return
     */
    @Override
    public DispositivoMedico getById(final Long id) {
        return dispositivoDao.findById(id).get();
    }

    /**
     * Metodo per ricevere le misurazioni da una categoria.
     * @param categoria
     * @param id
     * @return lista di misurazioni.
     */
    @Override
    public List<Misurazione> getMisurazioneByCategoria(final String categoria,
                                                       final Long id) {
        return (List<Misurazione>)
                misurazioneDAO.findByCategoria(categoria, id);
    }

    /**
     * Metodo per salvare le misurazioni.
     * @param misurazione
     * @return misurazione.
     */
    @Transactional
    @Override
    public Misurazione save(final Misurazione misurazione) {
        return misurazioneDAO.save(misurazione);
    }

    /**
     * Metodo per ricercare le categorie delle misurazioni da un paziente.
     * @param id
     * @return lista di stringhe.
     */
    @Override
    public List<String> findCategorieByPaziente(final Long id) {
        return (List<String>) misurazioneDAO.findCategorieByPaziente(id);
    }

    /**
     * Metodo per ricevere tutte le misurazioni di un paziente.
     * @param id
     * @return lista di misurazine dto.
     */
    @Override
    public List<MisurazioneDTO> getAllMisurazioniByPaziente(final Long id) {
        var list = (List<Misurazione>) misurazioneDAO
                                        .getAllMisurazioniByPaziente(id);
        List<MisurazioneDTO> listDTO = list
                .stream()
                .map(m -> new MisurazioneDTO(m, m.getDispositivoMedico()
                        .getCategoria()))
                .toList();

        return listDTO;
    }
}
