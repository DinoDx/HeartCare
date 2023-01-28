package c15.dev.utils;

import jakarta.servlet.Filter;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author: Leopoldo Todisco, Carlo Venditto.
 * Creato il: 24/01/2023.
 * Classe di Configurazione che indica un filtro che si applica per
 * permettere le richieste cors, richieste che sono fatte da un servizio
 * presente su una macchina/porta differente dal backend Spring.
 */
@Component
@Configuration
public class CORSFilter implements Filter {

    /**
     * Il doFilter è un filtro che viene eseguito a prescindere da
     * Spring Security.
     * L'override ci consente di personalizzare questo filtro aggiungendo
     * degli header per far leggere la response al frontend.
     * @param req  The request to process
     * @param res The response associated with the request
     * @param chain    Provides access to the next filter in the chain for this
     *                 filter to pass the request and response to for further
     *                 processing
     *
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        /**
         * In questa sezione si definiscono gli header di una response.
         */
        response.setHeader("Access-Control-Allow-Origin",
                request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods",
                "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "Authorization, "
                        + "OPTIONS, "
                        + "Origin, "
                        + "X-Requested-With,"
                        + " Content-Type, "
                        + "Access-Control-Request-Headers, "
                        + "Accept, "
                        + "Access-Control-Request-Method");
        chain.doFilter(req, res);
    }
}
