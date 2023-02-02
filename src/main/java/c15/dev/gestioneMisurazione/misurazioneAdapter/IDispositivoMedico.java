package c15.dev.gestioneMisurazione.misurazioneAdapter;

import c15.dev.model.entity.Misurazione;
/**
 * @author Mario Cicalese, Vincenzo Maria Arnone, Paolo Carmine Valletta.
 * creato il: 06/01/2023.
 * interfaccia implementata dall'adapter, chiamata dal paziente e implementata
 * dalla classe DispositivoMedicoAdapter.
 */
public interface IDispositivoMedico {
    /**
     * Firma del metodo che avvia la misurazione.
     * @return
     */
    public Misurazione avvioMisurazione();
}
