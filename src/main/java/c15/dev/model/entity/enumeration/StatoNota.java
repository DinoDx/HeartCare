package c15.dev.model.entity.enumeration;
/**
 * @author Vincenzo Arnone.
 * Creato il 30/12/2022.
 * Questa è l'enumeratore relativa allo stato della notifica.
 * il campo è: displayStato.
 */
public enum StatoNota {

    /**
     * LETTA sta per lo stato Letta.
     */
    LETTA("Letta"),

    /**
     * NON_LETTA sta per lo stato Non letta.
     */
    NON_LETTA("Non letta");


    /**
     * Campo relativo al nome dello stato che verrà mostrata.
     */
    private String displayStato;

    /**
     *
     * @param displayStato rappresenta il nome dello stato che verrà mostrato.
     * Costruttore dell'enumeratore StatoNotifica.
     */
    StatoNota(final String displayStato) {
        this.displayStato = displayStato;
    }

    /**
     *
     * @return displayStato.
     * metodo che resituisce lo stato.
     */
    public String getDisplayStato() {
        return displayStato;
    }
}
