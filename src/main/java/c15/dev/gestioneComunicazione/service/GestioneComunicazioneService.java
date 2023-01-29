package c15.dev.gestioneComunicazione.service;

import c15.dev.model.dto.NotaDTO;
import c15.dev.model.entity.Nota;

import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;

import java.util.List;

public interface GestioneComunicazioneService {
    Flux<ServerSentEvent<String>> invioNotifica(final String messaggio,
                                                final Long idDestinatario);
    void invioEmail(final String messaggio, final String emailDestinatario);

void invioNota(final String messaggio,
                                        final Long idDestinatario,
                                        final Long idMittente);

List<Nota> findAllNote();

List<NotaDTO> findNoteByIdUtente(final long id);


}
