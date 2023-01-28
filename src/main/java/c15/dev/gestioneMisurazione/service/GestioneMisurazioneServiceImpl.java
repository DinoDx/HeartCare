package c15.dev.gestioneMisurazione.service;


import c15.dev.gestioneUtente.service.GestioneUtenteService;
import c15.dev.model.dao.DispositivoMedicoDAO;
import c15.dev.model.dao.MisurazioneDAO;
import c15.dev.model.dao.MisurazionePressioneDAO;
import c15.dev.model.dao.PazienteDAO;
import c15.dev.model.dto.MisurazioneDTO;
import c15.dev.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public boolean rimozioneDispositivo(DispositivoMedico dispositivo,
                                        long idPaziente) {
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
