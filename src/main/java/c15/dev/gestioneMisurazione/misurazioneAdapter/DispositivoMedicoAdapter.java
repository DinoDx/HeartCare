package c15.dev.gestioneMisurazione.misurazioneAdapter;

import c15.dev.model.entity.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
/**
 * @author Mario Cicalese, Vincenzo Maria Arnone, Paolo Carmine Valletta.
 * creato il: 06/01/2023.
 * Questa classe rappresenta il design pattern adapter che converte una
 * misurazione JSON in un oggetto misurazione.
 *
 */
public class DispositivoMedicoAdapter implements IDispositivoMedico{
    private DispositivoMedico adaptee;
    Gson gson ;

    /**
     *
     * @param adaptee
     * Costruttore per la classe DispositivoMedicoAdapter.
     */
    public DispositivoMedicoAdapter(DispositivoMedico adaptee) {
        this.adaptee = adaptee;
        gson = new Gson();
    }

    /**
     *
     * @return Misurazione.
     * Questo metodo permette di avviare una misurazione.
     */
    @Override
    public Misurazione avvioMisurazione() {
        String misurazioneJSON = adaptee.avvioMisurazione();
        Misurazione misurazione = gson.fromJson(misurazioneJSON,misurazioneFactory(misurazioneJSON).getClass());
        return misurazione;
    }

    /**
     *
     * @param misurazioneJSON
     * @return Misurazione
     * Questo metodo rappresenta il design pattern Factory Method che in base
     * alla categoria del dispositivo medico restituisce un'oggetto figlia
     * di misurazione.
     */
    public Misurazione misurazioneFactory(String misurazioneJSON) {
        switch (adaptee.getCategoria().getDisplayName()) {
            case "ECG":
                return new MisurazioneHolterECG();
            case "Saturimetro":
                return new MisurazioneSaturazione();
            case "Coagulometro":
                return new MisurazioneCoagulazione();
            case "Misuratore glicemico":
                return new MisurazioneGlicemica();
            case "Misuratore di pressione":
                return new MisurazionePressione();
            case "Enzimi cardiaci":
                return new MisurazioneEnzimiCardiaci();
            default:
                return null;
        }
    }
}