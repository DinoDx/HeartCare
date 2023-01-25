package c15.dev.gestioneComunicazione.service;

import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;

public interface GestioneComunicazioneService {
    Flux<ServerSentEvent<String>> invioNotifica(String messaggio,
                                                Long idDestinatario);
    void invioEmail(String messaggio, String emailDestinatario);

    Flux<ServerSentEvent<String>> invioNota(String messaggio, Long idDestinatario, Long idMittente);


}
