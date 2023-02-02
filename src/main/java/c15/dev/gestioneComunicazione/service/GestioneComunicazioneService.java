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
     * @param messaggio messaggio da inviare nell'email.
     * @param emailDestinatario mail a cui inviare il messaggio.
     */
    void invioEmail(String messaggio,
                    String emailDestinatario);

    /**
     * Firma del metodo che invia una nota.
     * @param messaggio messaggio da inviare.
     * @param idDestinatario id del destinatario della nota.
     * @param idMittente id del mittente della nota.
     */
    void invioNota(String messaggio,
                   Long idDestinatario,
                   Long idMittente);

    /**
     * Firma del metodo che trova tutte le note.
     * @return lista di tutte le note.
     */
    List<Nota> findAllNote();

    /**
     * Firma del metodo che cerca tutte le note di un utente.
     * @param id identificativo di un utente.
     * @return lista delle note di un utente.
     */
    List<NotaDTO> findNoteByIdUtente(long id);

    /**
     * Firma del metodo che cerca tutte le note non lette di un utente.
     * @param email email dell'utente.
     * @return lista delle note non lette dell'utente.
     */
    List<Nota> findNoteNonLetteByUser(String email);

    /**
     * Firma del metodo che invia una notifica.
     * @param message messaggio della notifica.
     * @param idDest id del destinatario della notifica.
     */
    void sendNotifica(String message, Long idDest);
}
