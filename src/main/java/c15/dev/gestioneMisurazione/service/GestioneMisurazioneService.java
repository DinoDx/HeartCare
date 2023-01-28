package c15.dev.gestioneMisurazione.service;


import c15.dev.model.entity.DispositivoMedico;
import c15.dev.model.entity.Misurazione;
import c15.dev.model.entity.MisurazionePressione;

import java.util.List;

/**
 * @author Paolo Carmine Valletta, Alessandro Zoccola.
 * creato il: 04/01/2023.
 * Interfaccia per il service delle misurazioni.
 */
public interface GestioneMisurazioneService {
    /**
     * Firma del metodo di registrazione Dispositivo.
     *
     * @param dispositivo che vogliamo assegnare ad un utente.
     * @param idPaziente id del paziente a cui vogliamo assegnare
     *                   il dispositivo.
     * @return true o false.
     */
    boolean registrazioneDispositivo(DispositivoMedico dispositivo,
                                            long idPaziente);

    /**
     * Firma del metodo di rimozione Dispositivo.
     *
     * @param dispositivo che vogliamo rimuovere ad un utente.
     * @param idPaziente id del paziente a cui vogliamo rimuovere
     *                   il dispositivo.
     * @return true o false.
     */
    boolean rimozioneDispositivo(DispositivoMedico dispositivo,
                                        long idPaziente);

    /**
     *
     * @return List<Misurazione>
     * Metodo che restituisce tutte le misurazioni presenti nel database
     */
    List<Misurazione> getMisurazioniByPaziente(Long id);
    DispositivoMedico getById(Long id);

    List<Misurazione> getMisurazioneByCategoria(String categoria,
                                                       Long id);
    Misurazione save(Misurazione misurazione);
    MisurazionePressione save(MisurazionePressione misurazionePressione);
    List<String> findCategorieByPaziente(Long id);
}
