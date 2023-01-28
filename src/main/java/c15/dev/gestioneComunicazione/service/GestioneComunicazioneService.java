package c15.dev.gestioneComunicazione.service;

import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;

public interface GestioneComunicazioneService {
    Flux<ServerSentEvent<String>> invioNotifica(final String messaggio,
                                                final Long idDestinatario);
    void invioEmail(final String messaggio, final String emailDestinatario);

    Flux<ServerSentEvent<String>> invioNota(final String messaggio,
                                            final Long idDestinatario,
                                            final Long idMittente);


}
