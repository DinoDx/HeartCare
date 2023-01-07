package heartcare.gestioneMisurazione.misurazioneAdapter;


import com.google.gson.Gson;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Mario Cicalese, Vincenzo Maria Arnone, Paolo Carmine Valletta.
 * creato il: 06/01/2023.
 * Questa classe permette di generare le misurazioni.
 */
public class DispositivoMedicoStub {
    Map<String,Number> mappa;
    Gson gson;

    /**
     * Costruttore per la classe DispositivoMedicoStub.
     */
    public DispositivoMedicoStub() {
        this.mappa = new HashMap<>();
        gson = new Gson();
    }

    /**
     *
     * @param genere
     * @return String.
     * Questo metodo restituisce una misurazione della categoria
     * Enzimi Cardiaci.
     */
    public String MisurazioneEnzimiCardiaciStub(String genere){

        Integer mioglobina = ThreadLocalRandom.current().nextInt(0,85);
        Integer creatinKenasi;
        if(genere.toUpperCase().equals("M"))
            creatinKenasi = ThreadLocalRandom.current().nextInt(30, 200);
        else
            creatinKenasi = ThreadLocalRandom.current().nextInt(30, 150);
        Double troponinaCardiaca = ThreadLocalRandom.current().nextDouble(0.1, 10);

        mappa.put("mioglobina",mioglobina);
        mappa.put("creatinKenasi",creatinKenasi);
        mappa.put("troponinaCardiaca",troponinaCardiaca);

        String json = gson.toJson(mappa);
        return json;
    }

    /**
     *
     * @return String
     * Questo metodo restituisce una misurazione della categoria
     * Saturazione.
     */
    public String MisurazioneSaturazioneStub(){
        Integer battitiPerMinuto = ThreadLocalRandom.current().nextInt(60, 90);
        Integer percentualeSaturazione = ThreadLocalRandom.current().nextInt(75, 100);

        mappa.put("battitiPerMinuto",battitiPerMinuto);
        mappa.put("percentualeSaturazione",percentualeSaturazione);

        String json = gson.toJson(mappa);
        return json;
    }

    /**
     *
     * @param eta
     * @return String
     * Questo metodo restituisce una misurazione della categoria
     * Pressione.
     */
    public String MisurazionePressioneStub(Integer eta){
        Integer battitiPerMinuto = ThreadLocalRandom.current().nextInt(60, 90);
        Integer pressioneMinima = 0;
        Integer pressioneMassima = 0;
        Integer pressioneMedia;

        if(eta<20){
            pressioneMinima = ThreadLocalRandom.current().nextInt(73, 105);
            pressioneMassima = ThreadLocalRandom.current().nextInt(81, 120);

        }
        else if(eta>19 && eta <30){
            pressioneMinima = ThreadLocalRandom.current().nextInt(75, 108);
            pressioneMassima = ThreadLocalRandom.current().nextInt(84, 133);
        }
        else if(eta>29 && eta<40){
            pressioneMinima = ThreadLocalRandom.current().nextInt(78, 111);
            pressioneMassima = ThreadLocalRandom.current().nextInt(86, 135);
        }
        else if(eta>39 && eta<50){
            pressioneMinima = ThreadLocalRandom.current().nextInt(80, 113);
            pressioneMassima = ThreadLocalRandom.current().nextInt(88, 139);
        }
        else if(eta>49 && eta<60){
            pressioneMinima = ThreadLocalRandom.current().nextInt(81, 116);
            pressioneMassima = ThreadLocalRandom.current().nextInt(90, 144);
        }
        else if(eta>60){
            pressioneMinima = ThreadLocalRandom.current().nextInt(83, 118);
            pressioneMassima = ThreadLocalRandom.current().nextInt(91, 147);
        }

        pressioneMedia = (2*pressioneMinima+pressioneMassima)/3 ;
        mappa.put("battitiPerMinuto",battitiPerMinuto);
        mappa.put("pressioneMinima",pressioneMinima);
        mappa.put("pressioneMassima",pressioneMassima);
        mappa.put("pressioneMedia",pressioneMedia);
        String json = gson.toJson(mappa);
        return json;
    }

    /**
     *
     * @return String
     * Questo metodo restituisce una misurazione della categoria
     * Glicemica
     */
    public String MisurazioneGlicemicaStub(){
        Integer zuccheriNelSangue = ThreadLocalRandom.current().nextInt(70, 110);
        Integer colesterolo = ThreadLocalRandom.current().nextInt(100, 300);
        Integer trigliceridi = ThreadLocalRandom.current().nextInt(100, 500);

        mappa.put("zuccheriNelSangue",zuccheriNelSangue);
        mappa.put("colesterolo",colesterolo);
        mappa.put("trigliceridi",trigliceridi);
        String json = gson.toJson(mappa);
        return json;
    }

    /**
     *
     * @return String
     * Questo metodo restituisce una misurazione della categoria
     * HolterECG.
     */
    public String MisurazioneHolterECGStub(){
        Integer durataOndaP = ThreadLocalRandom.current().nextInt(60, 120);
        Integer battitiPerMinuto = ThreadLocalRandom.current().nextInt(60, 90);
        Double durataComplessoQRS = ThreadLocalRandom.current().nextDouble(0.08, 0.11);
        Double intervalloPR = ThreadLocalRandom.current().nextDouble(0.10, 0.20);
        Integer ondaT = ThreadLocalRandom.current().nextInt(300, 500);

        mappa.put("durataOndaP",durataOndaP);
        mappa.put("battitiPerMinuto",battitiPerMinuto);
        mappa.put("durataComplessoQRS",durataComplessoQRS);
        mappa.put("intervalloPR",intervalloPR);
        mappa.put("ondaT",ondaT);
        String json = gson.toJson(mappa);
        return json;
    }

    /**
     *
     * @return String
     * Questo metodo restituisce una misurazione della categoria
     * Coagulazione.
     */
    public String MisurazioneCoagulazioneStub(){
        Integer tempoDiProtrombina = ThreadLocalRandom.current().nextInt(8, 14);
        Double inr = ThreadLocalRandom.current().nextDouble(0.9, 1.3);

        mappa.put("tempoDiProtrombina",tempoDiProtrombina);
        mappa.put("INR",inr);

        String json = gson.toJson(mappa);
        return json;
    }
}
