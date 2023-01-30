package c15.dev.gestioneMisurazione.service;


import c15.dev.model.dto.MisurazioneDTO;
import c15.dev.model.entity.DispositivoMedico;
import c15.dev.model.entity.Misurazione;
import c15.dev.model.entity.MisurazionePressione;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author Paolo Carmine Valletta, Alessandro Zoccola.
 * creato il: 04/01/2023.
 * Interfaccia per il service delle misurazioni.
 */
public interface GestioneMisurazioneService {
    /**
     * Firma del metodo di registrazione Dispositivo.
     * @param dispositivo che vogliamo assegnare ad un utente.
     * @param idPaziente id del paziente a cui vogliamo assegnare.
     *                   il dispositivo.
     * @return true o false.
     */
    public boolean registrazioneDispositivo(DispositivoMedico dispositivo,
                                            long idPaziente);

    public boolean registrazioneDispositivo(
            HashMap<String, String> dispositivo, long idPaziente);

    /**
     * Firma del metodo di rimozione Dispositivo.
     * @param dispositivo che vogliamo rimuovere ad un utente.
     * @param idPaziente id del paziente a cui vogliamo rimuovere.
     *                   il dispositivo.
     * @return true o false.
     */
    public boolean rimozioneDispositivo(DispositivoMedico dispositivo,
                                        long idPaziente);

    /**
     *
     * @return List<Misurazione>.
     * Metodo che restituisce tutte le misurazioni presenti nel database.
     */
    public List<Misurazione> getMisurazioniByPaziente(Long id);

    /**
     * Firma del metodo che restituisce il dispositivo dall'id.
     * @param id
     * @return
     */
    public DispositivoMedico getById(Long id);

    /**
     * Firma del metodo getMisurazione dalla categoria
     * @param categoria
     * @param id
     * @return
     */
    public List<Misurazione> getMisurazioneByCategoria(String categoria,
                                                       Long id);

    /**
     * Firma del metodo per salvare una misurazione.
     * @param misurazione
     * @return
     */
    public Misurazione save(Misurazione misurazione);

    /**
     * Firma del metodo trovaCategoria della misurazione da un paziente.
     * @param id
     * @return
     */
    public List<String> findCategorieByPaziente(Long id);

    /**
     * Firma del metodo Trova Tutte le misurazioni di un paziente.
     * @param id
     * @return
     */
    public List<MisurazioneDTO> getAllMisurazioniByPaziente(Long id);
}
