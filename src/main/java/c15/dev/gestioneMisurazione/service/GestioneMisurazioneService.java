package c15.dev.gestioneMisurazione.service;


import c15.dev.model.entity.DispositivoMedico;

/**
 * @author Paolo Carmine Valletta, Alessandro Zoccola.
 * creato il: 04/01/2023.
 * Interfaccia per il service delle misurazioni.
 */
public interface GestioneMisurazioneService {
    public boolean registrazioneDispositivo(DispositivoMedico dispositivo, long idPaziente);
}
