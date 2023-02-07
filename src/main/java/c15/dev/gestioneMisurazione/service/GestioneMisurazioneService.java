package c15.dev.gestioneMisurazione.service;


import c15.dev.model.dto.MisurazioneDTO;
import c15.dev.model.entity.DispositivoMedico;
import c15.dev.model.entity.Misurazione;

import java.util.HashMap;
import java.util.List;

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
    boolean registrazioneDispositivo(DispositivoMedico dispositivo,
                                            long idPaziente);

    /**
     * Firma del metodo di registrazione Dispositivo.
     * @param dispositivo che vogliamo assegnare ad un
     *                    utente sotto forma di hashmap.
     * @param idPaziente id del paziente a cui vogliamo assegnare.
     *                   il dispositivo.
     * @return true o false.
     */
    boolean registrazioneDispositivo(HashMap<String, String> dispositivo,
                                            long idPaziente);

    /**
     * Firma del metodo di rimozione Dispositivo.
     * @param dispositivo che vogliamo rimuovere ad un utente.
     * @param idPaziente id del paziente a cui vogliamo rimuovere.
     *                   il dispositivo.
     * @return true o false.
     */
    boolean rimozioneDispositivo(DispositivoMedico dispositivo,
                                        long idPaziente);

    /**
     * @param id è l'id del paziente.
     * @return List<Misurazione>.
     * Metodo che restituisce tutte le misurazioni presenti nel database.
     */
    List<Misurazione> getMisurazioniByPaziente(Long id);

    /**
     * Firma del metodo che restituisce il dispositivo dall'id.
     * @param id
     * @return un dispositivo medico.
     */
    DispositivoMedico getById(Long id);

    /**
     * Firma del metodo getMisurazione dalla categoria.
     * @param categoria
     * @param id
     * @return lista di misurazioni di una determinata categoria.
     */
    List<Misurazione> getMisurazioneByCategoria(String categoria,
                                                       Long id);

    /**
     * Firma del metodo per salvare una misurazione.
     * @param misurazione
     * @return la misurazione salvata.
     */
    Misurazione save(Misurazione misurazione);

    /**
     * Firma del metodo trovaCategoria della misurazione da un paziente.
     * @param id
     * @return lista di stringhe in cui ogni stringa indica una categoria.
     */
    List<String> findCategorieByPaziente(Long id);

    /**
     * Firma del metodo Trova Tutte le misurazioni di un paziente.
     * @param id
     * @return elenco di misurazioni di un paziente.
     */
    List<MisurazioneDTO> getAllMisurazioniByPaziente(Long id);
}
