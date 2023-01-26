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
    public boolean registrazioneDispositivo(DispositivoMedico dispositivo,
                                            long idPaziente);

    /**
     * Firma del metodo di rimozione Dispositivo.
     *
     * @param dispositivo che vogliamo rimuovere ad un utente.
     * @param idPaziente id del paziente a cui vogliamo rimuovere
     *                   il dispositivo.
     * @return true o false.
     */
    public boolean rimozioneDispositivo(DispositivoMedico dispositivo,
                                        long idPaziente);

    /**
     *
     * @return List<Misurazione>
     * Metodo che restituisce tutte le misurazioni presenti nel database
     */
    public List<Misurazione> getMisurazioniByPaziente(Long id);
    public DispositivoMedico getById(Long id);

    public List<Misurazione> getMisurazioneByCategoria(String categoria,
                                                       Long id);
    public Misurazione save(Misurazione misurazione);
    public MisurazionePressione save(MisurazionePressione misurazionePressione);
    public List<String> findCategorieByPaziente(Long id);
}
