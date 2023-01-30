package c15.dev.gestioneComunicazione.service;

import c15.dev.model.dto.NotaDTO;
import c15.dev.model.entity.Nota;
import java.util.List;

/**
 * @author Leopoldo Todisco, Carlo Venditto.
 *
 */

public interface GestioneComunicazioneService {
    /**
     * Firma del metodo che invia una mail.
     * @param messaggio
     * @param emailDestinatario
     */
    void invioEmail(String messaggio,
                    String emailDestinatario);

    /**
     * Firma del metodo che invia una nota.
     * @param messaggio
     * @param idDestinatario
     * @param idMittente
     */
    void invioNota(String messaggio,
                   Long idDestinatario,
                   Long idMittente);

    /**
     * Firma del metodo che trova tutte le note.
     * @return
     */
    List<Nota> findAllNote();

    /**
     * Firma del metodo che cerca tutte le note di un utente.
     * @param id
     * @return
     */
    List<NotaDTO> findNoteByIdUtente(long id);

    /**
     * Firma del metodo che invia una notifica.
     * @param message
     */
    void sendNotifica(String message, Long idDest);
}
