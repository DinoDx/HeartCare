package c15.dev.model.entity.enumeration;
/**
 * @author Vincenzo Arnone
 * Creato il 30/12/2022
 * Questa è l'enumeratore relativa allo stato della visita
 * il campo è: displayStato
 */
public enum StatoVisita {

    /**
     * PROGRAMMATA sta per lo stato Programmata
     */
    PROGRAMMATA("Programmata"),
    /**
     * EFFETTUATA sta per lo stato Effettuata
     */
    EFFETTUATA("Effettuata");

    /**
     * Campo relativo al nome dello stato che verrà mostrata
     */
    private String displayStato;

    /**
     *
     * @param displayStato rappresenta il nome dello stato che verrà mostrato
     * costruttore dell'enumeratore StatoVisita
     */
    StatoVisita(String displayStato) {
        this.displayStato = displayStato;
    }

    /**
     *
     * @return displayStato
     * metodo che resituisce lo stato
     */
    public String getDisplayStato_Visita() {
        return displayStato;
    }
}
