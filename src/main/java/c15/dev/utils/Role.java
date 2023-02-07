package c15.dev.utils;

/**
 * @author Leopoldo Todisco, Carlo Venditto.
 * Creato il 24/01/2023.
 * Enum che indica i ruoli che un user ha.
 * I ruoli sono passati al frontend via token.
 */
public enum Role {
    /**
     * ruolo paziente.
     */
    PAZIENTE,
    /**
     * ruolo medico.
     */
    MEDICO,
    /**
     * ruolo admin.
     */
    ADMIN,
}
