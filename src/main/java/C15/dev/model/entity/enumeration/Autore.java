package C15.dev.model.entity.enumeration;

/**
 * @author Mario Cicalese
 * Creato il 30/12/2022
 * Questa è l'enumeratore relativa all'autore della nota
 * il campo è : displayName
 */
public enum Autore {
    /**
     * M sta per medico
     */
    M("Medico"),
    /**
     * P sta per paziente
     */
    P("Paziente");

    /**
     * Campo relativo al nome che verrà mostrata
     */
    private String displayName;

    /**
     *
     * @param displayName rappresenta il nome che verrà mostrato
     * costruttore dell'enumeratore Autore
     */
    Autore(String displayName) {
        this.displayName = displayName;
    }

    /**
     *
     * @return displayName
     * metodo che resituisce l'autore
     */
    public String getDisplayName() {
        return displayName;
    }
}
