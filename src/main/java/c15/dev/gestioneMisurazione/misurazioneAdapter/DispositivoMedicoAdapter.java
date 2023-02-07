package c15.dev.gestioneMisurazione.misurazioneAdapter;

import c15.dev.model.entity.DispositivoMedico;
import c15.dev.model.entity.Misurazione;
import c15.dev.model.entity.MisurazionePressione;
import c15.dev.model.entity.MisurazioneEnzimiCardiaci;
import c15.dev.model.entity.MisurazioneGlicemica;
import c15.dev.model.entity.MisurazioneCoagulazione;
import c15.dev.model.entity.MisurazioneSaturazione;
import c15.dev.model.entity.MisurazioneHolterECG;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;

/**
 * @author Mario Cicalese, Vincenzo Maria Arnone, Paolo Carmine Valletta.
 * creato il: 06/01/2023.
 * Questa classe rappresenta il design pattern adapter che converte una.
 * misurazione JSON in un oggetto misurazione.
 *
 */
public class DispositivoMedicoAdapter implements IDispositivoMedico {
    /**adaptee è il dispositivo la cui misurazione va adattata*/
    private DispositivoMedico adaptee;
    private Gson gson;

    /**
     *
     * @param adap
     * Costruttore per la classe DispositivoMedicoAdapter.
     */
    public DispositivoMedicoAdapter(final DispositivoMedico adap) {
        this.adaptee = adap;
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
        //Misurazione misurazione = gson.fromJson(misurazioneJSON, misurazioneFactory(misurazioneJSON).getClass());

        ObjectMapper om = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        try {
            System.out.println("MISURAZIONE  JSON = " + misurazioneJSON);
            Misurazione result = om.readValue(misurazioneJSON,
                    misurazioneFactory().getClass());
            result.setPaziente(adaptee.getPaziente());
            result.setDispositivoMedico(adaptee);
            System.out.println("FUNZIONA?\n");
            System.out.println(result);
            return result;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @return Misurazione.
     * Questo metodo rappresenta il design pattern Factory Method che in base.
     * alla categoria del dispositivo medico restituisce un oggetto figlio.
     * di misurazione.
     */
    public Misurazione misurazioneFactory() {
        switch (adaptee.getCategoria()) {
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