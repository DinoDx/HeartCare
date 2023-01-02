package c15.dev.model.entity.enumeration;

/**
 * @author Mario Cicalese.
 * Creato il 30/12/2022.
 * Questa è l'enumeratore relativa alle categorie del dispositivo medico
 * il campo è: displayName.
 */
public enum Categoria {
    /**
     * SATURIMETRO sta per la categoria Saturimetro
     */
    SATURIMETRO("Saturimetro"),
    /**
     * COAGULOMETRO sta per la categoria Coagulometro
     */
    COAGULOMETRO("Coagulometro"),
    /**
     * MISURATORE_DI_PRESSIONE sta per la categoria Misuratore di pressione
     */
    MISURATORE_DI_PRESSIONE("Misuratore di pressione"),
    /**
     * ECG sta per la categoria ECG
     */
    ECG("ECG"),
    /**
     * MISURATORE_GLICEMICO sta per la categoria Misuratore glicemico
     */
    MISURATORE_GLICEMICO("Misuratore glicemico");

    /**
     * Campo relativo al nome della categoria che verrà mostrata
     */
    private String displayName;

    /**
     *
     * @param displayName rappresenta il nome della categoria che verrà mostrato
     * costruttore dell'enumeratore Categoria
     */
    Categoria(String displayName) {
        this.displayName = displayName;
    }

    /**
     *
     * @return displayName
     * metodo che resituisce la categoria
     */
    public String getDisplayName() {
        return displayName;
    }
}
