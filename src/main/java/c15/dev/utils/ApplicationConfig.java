package c15.dev.utils;

import c15.dev.model.dao.UtenteRegistratoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

/**
 * @author Leopoldo Todisco, Carlo Venditto.
 * Creato il: 24/01/2023.
 * Classe di configurazione di Applicazione.
 * Allo startup Spring risolverà tutte le dipendenze
 * specificate in questa classe.
 */
@Configuration
public class ApplicationConfig {
    @Autowired  @Qualifier("utenteRegistratoDAO")
    private UtenteRegistratoDAO usrdao;

    /**
     * Estrae il bean relativo al service.
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> usrdao.findByEmail(username);
    }

    /**
     * Dependency Injection per il password encoder.
     * @return
     */
    @Bean
    public PasswordEncoder pwdEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Dependency Injection per il provider di autenticazione.
     * @return
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(pwdEncoder());
        return authenticationProvider;
    }

    /**
     * Dependency Injection per il manager di autenticazione.
     * @return
     */
    @Bean
    public AuthenticationManager
    authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();}
}
