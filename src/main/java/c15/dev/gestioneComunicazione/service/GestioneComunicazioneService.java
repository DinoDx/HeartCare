package c15.dev.gestioneComunicazione.service;

import c15.dev.model.dto.NotaDTO;
import c15.dev.model.entity.Nota;
import java.util.List;

public interface GestioneComunicazioneService {
    void invioEmail(final String messaggio,
                    final String emailDestinatario);

    void invioNota(final String messaggio,
                   final Long idDestinatario,
                   final Long idMittente);

    List<Nota> findAllNote();

    List<NotaDTO> findNoteByIdUtente(final long id);
    void sendNotifica(String message);
}
