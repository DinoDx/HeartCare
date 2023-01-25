package c15.dev.gestioneComunicazione.service;

import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

public interface GestioneComunicazioneService {
    Flux<ServerSentEvent<String>> invioNotifica(String messaggio,
                                                Long idDestinatario);
    void invioEmail(String messaggio, String emailDestinatario);

    void invioNota(String messaggio, Long idDestinatario, Long idMittente);


}
