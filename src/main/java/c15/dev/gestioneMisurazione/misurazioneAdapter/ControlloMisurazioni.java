package c15.dev.gestioneMisurazione.misurazioneAdapter;

import c15.dev.model.entity.MisurazioneEnzimiCardiaci;
import c15.dev.model.entity.MisurazioneGlicemica;
import c15.dev.model.entity.MisurazioneHolterECG;
import c15.dev.model.entity.MisurazioneCoagulazione;
import c15.dev.model.entity.MisurazionePressione;
import c15.dev.model.entity.MisurazioneSaturazione;
import c15.dev.model.entity.Misurazione;

/**
 * @author carlo Venditto, Leopoldo Todisco.
 * Data creazione: 30/01/2023.
 * Classe che contine la logica di business legata al controllo che le
 * misurazioni non abbiano valori sballati.
 */
public class ControlloMisurazioni {

    /**
     * Metodo che data una misurazione chiama il relativo
     * metodo di controllo.
     * @param m
     * @return true o false.
     */
    public static boolean chiamaControllo(Misurazione m) {
        var classeMisurazione = m.getClass().getSimpleName();

        switch (classeMisurazione) {
            case "MisurazioneHolterECG": {
                var casted = (MisurazioneHolterECG) m;
                return ControlloMisurazioni.controlloECG(casted);
            }
            case "MisurazioneSaturazione": {
                var casted = (MisurazioneSaturazione) m;
                return ControlloMisurazioni.controlloSaturazione(casted);
            }
            case "MisurazioneCoagulazione": {
                var casted = (MisurazioneCoagulazione) m;
                return ControlloMisurazioni.controlloCoagulazione(casted);
            }
            case "MisurazioneGlicemia": {
                var casted = (MisurazioneGlicemica) m;
                return ControlloMisurazioni.controlloGlicemia(casted);
            }
            case "MisurazioneEnzimiCardiaci": {
                var casted = (MisurazioneEnzimiCardiaci) m;
                return ControlloMisurazioni.controlloEnzimiCardiaci(casted);
            }
            case "MisurazionePressione": {
                var casted = (MisurazionePressione) m;
                return ControlloMisurazioni.controlloPressione(casted);
            }
            default:
                return false;
        }
    }

    /**
     * Metodo che controlla che la misurazione ECG non abbia valori
     * troppo alti o troppo bassi.
     * @param m
     * @return
     */
    public static boolean controlloECG(MisurazioneHolterECG m) {
        var paziente = m.getPaziente();
        var durataOndaP = m.getDurataOndaP();
        var battitiPerMinuto = m.getBattitiPerMinuto();
        var durataComplessoQRS = m.getDurataComplessoQRS();
        var intervalloPR = m.getIntervalloPR();
        var ondaT = m.getOndaT();

        if(durataOndaP > 120 || battitiPerMinuto > 90
                             || durataComplessoQRS > 0.11
                             || intervalloPR > 0.20
                             || ondaT > 500) {
            return true;
        } else if(durataOndaP < 60 || battitiPerMinuto < 60
                || durataComplessoQRS < 0.08
                || intervalloPR < 0.10
                || ondaT < 300) {
            return true;
        }


        return false;
    }

    /**
     * Metodo che controlla che i valori di una misurazione saturazione siano
     * nei limiti.
     * Se sono sballati si restituisce true.
     * @param m
     * @return true or false
     */
    public static boolean controlloSaturazione(MisurazioneSaturazione m) {
        var paziente = m.getPaziente();
        var bpm = m.getBattitiPerMinuto();
        var saturazione = m.getPercentualeSaturazione();

        //true vuol dire che la misurazione è sballata
        if(saturazione >= 93 || bpm > 140 ) {
            return true;
        }

        else if(saturazione <= 85 || bpm <= 65) {
            return true;
        }

        return false;
    }

    /**
     * Metodo per controllare che la misurazione pressione
     * non abbia valori troppo alti o troppo bassi.
     * @param mp
     * @return
     */
    public static boolean controlloPressione(MisurazionePressione mp) {
        var bpm = mp.getBattitiPerMinuto();
        var max = mp.getPressioneMassima();
        var average = mp.getPressioneMedia();
        var min = mp.getPressioneMinima();

        if(bpm >= 140 || max > 150 || min > 85) {
            return true;
        }

        else if(bpm <= 65 || max < 100 || min < 80) {
            return true;
        }

        return false;
    }

    /**
     * Metodo per controllare che la misurazione coagulazione
     * non abbia valori troppo alti o troppo bassi.
     * @param m
     * @return
     */
    public static boolean controlloCoagulazione(MisurazioneCoagulazione m) {
        var paziente = m.getPaziente();
        var tempoDiProtrombina = m.getTempoDiProtrobina();
        var inr = m.getInr();

        if(tempoDiProtrombina > 20 || inr > 2.3) {
            return true;
        } else if (tempoDiProtrombina < 5 || inr < 0.5) {
            return true;
        }

        return false;
    }

    /**
     * Metodo per controllare che la misurazione glicemica
     * non abbia valori troppo alti o troppo bassi.
     * @param m
     * @return
     */
    public static boolean controlloGlicemia(MisurazioneGlicemica m) {
        var colesterolo = m.getColesterolo();
        var trigliceridi = m.getTrigliceridi();
        var zuccheri = m.getZuccheriNelSangue();

        if(colesterolo > 500 || trigliceridi > 100 || zuccheri > 500) {
            return true;
        }

        else if(colesterolo < 100 || zuccheri < 120 || trigliceridi < 80) {
            return true;
        }

        return false;
    }

    /**
     * Metodo per controllare che la misurazione degli enzimi cardiaci
     * non abbia valori troppo alti o troppo bassi.
     * @param m
     * @return
     */
    public static boolean controlloEnzimiCardiaci(MisurazioneEnzimiCardiaci m) {

        var paziente = m.getPaziente();
        var creatinKinasi = m.getCreatinKinasi();
        var mioGlobina = m.getMioglobina();
        var troponinaCardiaca = m.getTroponinaCardiaca();

        if(mioGlobina > 90 || creatinKinasi > 200 || troponinaCardiaca > 10 ) {
             return true;
        } else if(  mioGlobina < 0 ||
                creatinKinasi < 20 || troponinaCardiaca < 0 ) {

            return true;
        }

        return false;
    }
}

